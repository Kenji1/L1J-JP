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

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.ClientThread;
import jp.l1j.server.datatables.CharacterTable;
import jp.l1j.server.datatables.MailTable;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.L1Clan;
import jp.l1j.server.model.L1World;
import jp.l1j.server.packets.server.S_Mail;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.templates.L1Mail;

// Referenced classes of package jp.l1j.server.clientpackets:
// ClientBasePacket

public class C_Mail extends ClientBasePacket {

	private static final String C_MAIL = "[C] C_Mail";

	private static Logger _log = Logger.getLogger(C_Mail.class.getName());

	private static int TYPE_NORMAL_MAIL = 0; // 一般
	private static int TYPE_CLAN_MAIL = 1; // 血盟
	private static int TYPE_MAIL_BOX = 2; // 保管箱

	public C_Mail(byte abyte0[], ClientThread client) {
		super(abyte0);
		int type = readC();
		L1PcInstance pc = client.getActiveChar();

		if (type == 0x00 || type == 0x01 || type == 0x02) { // 開く
			pc.sendPackets(new S_Mail(pc.getName(), type));
		} else if (type == 0x10 || type == 0x11 || type == 0x12) { // 読む
			int mailId = readD();
			L1Mail mail = MailTable.getInstance().getMail(mailId);
			if (mail.getReadStatus() == 0) {
				MailTable.getInstance().setReadStatus(mailId);
			}
			pc.sendPackets(new S_Mail(mailId, type));
		} else if (type == 0x20) { // 一般メールを書く
			if (!pc.getInventory().checkItem(40308, 50)) {
				pc.sendPackets(new S_ServerMessage(189)); // アデナが不足しています。
				return;
			}
			int unknow = readH();
			String receiverName = readS();
			byte[] text = readByte();
			L1PcInstance receiver = L1World.getInstance().
					getPlayer(receiverName);
			if (receiver != null) { // オンライン中
				if (getMailSizeByReceiver(receiverName,
						TYPE_NORMAL_MAIL) >= 20) {
					pc.sendPackets(new S_Mail(type));
					return;
				}
				MailTable.getInstance().writeMail(TYPE_NORMAL_MAIL,
						receiverName, pc, text);
				pc.getInventory().consumeItem(40308, 50);
				if (receiver.getOnlineStatus() == 1) {
					receiver.sendPackets(new S_Mail(receiverName,
							TYPE_NORMAL_MAIL));
				}
			} else { // オフライン中
				try {
					L1PcInstance restorePc = CharacterTable.getInstance()
							.restoreCharacter(receiverName);
					if (restorePc != null) {
						if (getMailSizeByReceiver(receiverName,
								TYPE_NORMAL_MAIL) >= 20) {
							pc.sendPackets(new S_Mail(type));
							return;
						}
						MailTable.getInstance().writeMail(TYPE_NORMAL_MAIL,
								receiverName, pc, text);
						pc.getInventory().consumeItem(40308, 50);
					} else {
						pc.sendPackets(new S_ServerMessage(109, receiverName)); // %0という名前の人はいません。
					}
				} catch (Exception e) {
					_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				}
			}
		} else if (type == 0x21) { // 血盟メールを書く
			if (!pc.getInventory().checkItem(40308, 1000)) {
				pc.sendPackets(new S_ServerMessage(189)); // アデナが不足しています。
				return;
			}
			int unknow = readH();
			String clanName = readS();
			byte[] text = readByte();
			L1Clan clan = L1World.getInstance().getClan(clanName);
			if (clan != null) {
				for (String name : clan.getAllMembers()) {
					int size = getMailSizeByReceiver(name, TYPE_CLAN_MAIL);
					if (size >= 50) {
						continue;
					}
					MailTable.getInstance().writeMail(TYPE_CLAN_MAIL, name,
							pc, text);
					pc.getInventory().consumeItem(40308, 1000);
					L1PcInstance clanPc = L1World.getInstance().
							getPlayer(name);
					if (clanPc != null) { // オンライン中
						clanPc.sendPackets(new S_Mail(name,
								TYPE_CLAN_MAIL));
					}
				}
			}
		} else if (type == 0x30 || type == 0x31 || type == 0x32) { // 削除
			int mailId = readD();
			MailTable.getInstance().deleteMail(mailId);
			pc.sendPackets(new S_Mail(mailId, type));
		} else if(type == 0x40) { // 保管箱に保存
			int mailId = readD();
			MailTable.getInstance().setMailType(mailId, TYPE_MAIL_BOX);
			pc.sendPackets(new S_Mail(mailId, type));
		}
	}

	private int getMailSizeByReceiver(String receiverName, int type) {
		ArrayList<L1Mail> mails = new ArrayList<L1Mail>();
		for (L1Mail mail : MailTable.getInstance().getAllMail()) {
			if (mail.getReceiverName().equalsIgnoreCase(receiverName)) {
				if (mail.getType() == type) {
					mails.add(mail);
				}
			}
		}
		return mails.size();
	}

	@Override
	public String getType() {
		return C_MAIL;
	}
}
