/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jp.l1j.server.packets.client;

import java.util.logging.Logger;
import jp.l1j.configure.Config;
import jp.l1j.server.ClientThread;
import jp.l1j.server.controller.LoginController;
import jp.l1j.server.datatables.IpTable;
import jp.l1j.server.exception.AccountAlreadyLoginException;
import jp.l1j.server.exception.GameServerFullException;
import jp.l1j.server.packets.server.S_CommonNews;
import jp.l1j.server.packets.server.S_LoginResult;
import jp.l1j.server.templates.L1Account;

// Referenced classes of package jp.l1j.server.clientpackets:
// ClientBasePacket

public class C_AuthLogin extends ClientBasePacket {

	private static final String C_AUTH_LOGIN = "[C] C_AuthLogin";
	private static Logger _log = Logger.getLogger(C_AuthLogin.class.getName());

	public C_AuthLogin(byte[] decrypt, ClientThread client) {
		super(decrypt);
		String accountName = readS().toLowerCase();
		String password = readS();

		String ip = client.getIp();
		String host = client.getHostname();

		_log.finest("Request AuthLogin from user : " + accountName);

		if (!Config.ALLOW_2PC) {
			for (ClientThread tempClient : LoginController.getInstance()
					.getAllAccounts()) {
				if (ip.equalsIgnoreCase(tempClient.getIp())) {
					_log.info("2PCのログインを拒否しました。account=" + accountName
							+ " host=" + host);
					client.sendPacket(new S_LoginResult(
							S_LoginResult.REASON_USER_OR_PASS_WRONG));
					return;
				}
			}
		}

		L1Account account = L1Account.findByName(accountName);
		if (IpTable.getInstance().isBannedIpMask(host)
				&& (account == null || account.getAccessLevel() == 0)) {
			_log.info("指定IP範囲からの接続を拒否しました。 account=" + accountName + " host="
					+ host);
			client.sendPacket(new S_LoginResult(
					S_LoginResult.REASON_USER_OR_PASS_WRONG));
			return;
		}

		if (account == null) {
			if (Config.AUTO_CREATE_ACCOUNTS) {
				account = L1Account.create(accountName, password, ip, host);
			} else {
				_log.warning("account missing for user " + accountName);
			}
		}
		if (account == null || !account.validatePassword(password)) {
			client.sendPacket(new S_LoginResult(
					S_LoginResult.REASON_USER_OR_PASS_WRONG));
			return;
		}
		if (account.isBanned()) { // BANアカウント
			_log.info("BANアカウントのログインを拒否しました。account=" + accountName + " host="
					+ host);
			client.sendPacket(new S_LoginResult(
					S_LoginResult.REASON_USER_OR_PASS_WRONG));
			return;
		}

		try {
			LoginController.getInstance().login(client, account);
			account.updateLastActivatedTime(); // 最終ログイン日を更新する
			client.setAccount(account);
			client.sendPacket(new S_LoginResult(S_LoginResult.REASON_LOGIN_OK));
			client.sendPacket(new S_CommonNews());
		} catch (GameServerFullException e) {
			client.kick();
			_log.info("接続人数上限に達している為(" + client.getHostname()
					+ ")のログインを拒否し、切断しました。");
			return;
		} catch (AccountAlreadyLoginException e) {
			client.kick();
			_log.info("同一IDでの重複接続の為(" + client.getHostname()
					+ ")との接続を強制切断しました。");
			return;
		}
	}

	@Override
	public String getType() {
		return C_AUTH_LOGIN;
	}

}