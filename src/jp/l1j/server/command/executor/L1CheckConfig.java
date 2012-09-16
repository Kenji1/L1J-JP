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

package jp.l1j.server.command.executor;

import java.util.logging.Logger;
import jp.l1j.configure.Config;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.packets.server.S_SystemMessage;

public class L1CheckConfig implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Describe.class.getName());

	private L1CheckConfig() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1CheckConfig();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			pc.sendPackets(new S_SystemMessage("*** このサーバーの現在の設定 ***"));

			StringBuilder msg = new StringBuilder();

			msg.append("経験値倍率：" + Config.RATE_XP  + "倍 / ");
			msg.append("アライメント倍率：" + Config.RATE_LA + "倍 / ");
			msg.append("カルマ倍率：" + Config.RATE_KARMA + "倍 / ");
			msg.append("ドロップ倍率：" + Config.RATE_DROP_ITEMS + "倍 / ");
			msg.append("取得アデナ倍率：" + Config.RATE_DROP_ADENA + "倍 / ");
			msg.append("武器エンチャント成功率：" + Config.ENCHANT_CHANCE_WEAPON + "％ / ");
			msg.append("防具エンチャント成功率：" + Config.ENCHANT_CHANCE_ARMOR + "％ / ");
			msg.append("属性強化成功率：" + Config.ATTR_ENCHANT_CHANCE + "％ / ");
			msg.append("装飾品強化成功率：" + Config.ENCHANT_CHANCE_ACCESSORY + "％ / ");
			msg.append("MD強化成功率：" + Config.ENCHANT_CHANCE_DOLL + "％ / ");
			msg.append("重量軽減倍率：" + Config.RATE_WEIGHT_LIMIT + "倍 / ");
			msg.append("接続人数制限：最大 " + Config.MAX_ONLINE_USERS + "人 / ");

			pc.sendPackets(new S_SystemMessage(msg.toString()));
		} catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(cmdName + " コマンドエラー"));
		}
	}
}
