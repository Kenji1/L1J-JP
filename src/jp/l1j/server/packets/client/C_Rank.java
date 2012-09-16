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

import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.ClientThread;
import jp.l1j.server.datatables.CharacterTable;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.L1Clan;
import jp.l1j.server.model.L1World;
import jp.l1j.server.packets.server.S_PacketBox;
import jp.l1j.server.packets.server.S_ServerMessage;

// Referenced classes of package jp.l1j.server.clientpackets:
// ClientBasePacket

public class C_Rank extends ClientBasePacket {

	private static final String C_RANK = "[C] C_Rank";
	private static Logger _log = Logger.getLogger(C_Rank.class.getName());

	public C_Rank(byte abyte0[], ClientThread clientthread) throws Exception {
		super(abyte0);

		int data = readC(); // ?
		int rank = readC();
		String name = readS();

		L1PcInstance pc = clientthread.getActiveChar();
		L1PcInstance targetPc = L1World.getInstance().getPlayer(name);

		if (data == 1) {
			if (pc == null) {
				return;
			}

			L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
			if (clan == null) {
				return;
			}

			if (rank != L1Clan.CLAN_RANK_SUBLEADER
					&& rank != L1Clan.CLAN_RANK_GUARDIAN
					&& rank != L1Clan.CLAN_RANK_ELITE
					&& rank != L1Clan.CLAN_RANK_REGULAR) {
				// ランクを変更する人の名前とランクを入力してください。[ランク = ガーディアン、エリート、一般]
				pc.sendPackets(new S_ServerMessage(2150));
				return;
			}

			int pcRank = pc.getClanRank();
			int targetPcRank = targetPc.getClanRank();
			if ((pcRank == L1Clan.CLAN_RANK_LEADER
					|| pcRank == L1Clan.CLAN_RANK_SUBLEADER)
					&& pc.getLevel() < 25) {
				// この権限を遂行するには、レベル25以上でなければなりません。
				pc.sendPackets(new S_ServerMessage(2471));
			}

			if (pcRank == L1Clan.CLAN_RANK_GUARDIAN && pc.getLevel() < 40) {
				// この権限を遂行するには、レベル40以上でなければなりません。
				pc.sendPackets(new S_ServerMessage(2472));
			}
			
			if (pcRank == L1Clan.CLAN_RANK_ELITE
					|| pcRank == L1Clan.CLAN_RANK_REGULAR
				// エリート、一般は、階級任命の権限はない
				|| (pcRank == L1Clan.CLAN_RANK_LEADER
					&& rank == L1Clan.CLAN_RANK_LEADER)
				|| (pcRank == L1Clan.CLAN_RANK_SUBLEADER
					&& (rank == L1Clan.CLAN_RANK_LEADER
					|| rank == L1Clan.CLAN_RANK_SUBLEADER))
				|| (pcRank == L1Clan.CLAN_RANK_GUARDIAN
					&& (rank == L1Clan.CLAN_RANK_LEADER
					|| rank == L1Clan.CLAN_RANK_SUBLEADER
					|| rank == L1Clan.CLAN_RANK_GUARDIAN))
				// 任命しようとしているランクが自分のランクと同じか上位
				|| (pcRank == L1Clan.CLAN_RANK_LEADER
					&& targetPcRank == L1Clan.CLAN_RANK_LEADER)
				|| (pcRank == L1Clan.CLAN_RANK_SUBLEADER
					&& (targetPcRank == L1Clan.CLAN_RANK_LEADER
					|| targetPcRank == L1Clan.CLAN_RANK_SUBLEADER))
				|| (pcRank == L1Clan.CLAN_RANK_GUARDIAN
					&& (targetPcRank == L1Clan.CLAN_RANK_LEADER
					|| targetPcRank == L1Clan.CLAN_RANK_SUBLEADER
					|| targetPcRank == L1Clan.CLAN_RANK_GUARDIAN))
				){ // 任命対象が自分のランクと同じか上位
				pc.sendPackets(new S_ServerMessage(2068)); // 自分より低いランクのみ変更できます。
				return;
			}

			if (targetPc != null) { // オンライン中
				if (pc.getClanid() == targetPc.getClanid()) { // 同じクラン
					try {
						targetPc.setClanRank(rank);
						targetPc.save(); // DBにキャラクター情報を書き込む
						targetPc.sendPackets(new S_PacketBox(
								S_PacketBox.MSG_RANK_CHANGED, rank));
						// あなたのランクが%sに変更されました。
					} catch (Exception e) {
						_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
					}
				} else {
					pc.sendPackets(new S_ServerMessage(414)); // 同じ血盟員ではありません。
					return;
				}
			} else { // オフライン中
				L1PcInstance restorePc = CharacterTable.getInstance()
						.restoreCharacter(name);
				if (restorePc != null
						&& restorePc.getClanid() == pc.getClanid()) { // 同じクラン
					try {
						restorePc.setClanRank(rank);
						restorePc.save(); // DBにキャラクター情報を書き込む
					} catch (Exception e) {
						_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
					}
				} else {
					pc.sendPackets(new S_ServerMessage(109, name)); // %0という名前の人はいません。
					return;
				}
			}
		} else if (data == 2) {
			pc.sendPackets(new S_ServerMessage(74, "同盟リスト"));
		} else if (data == 3) {
			pc.sendPackets(new S_ServerMessage(74, "同盟加入"));
		} else if (data == 4) {
			pc.sendPackets(new S_ServerMessage(74, "同盟脱退"));
		} else {
		}
	}

	@Override
	public String getType() {
		return C_RANK;
	}
}