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
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.packets.server.S_SystemMessage;

public class L1Describe implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Describe.class.getName());

	private L1Describe() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Describe();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringBuilder msg = new StringBuilder();
			pc.sendPackets(new S_SystemMessage("-- describe: " + pc.getName()
					+ " --"));
			int hpr = pc.getHpr();
			int mpr = pc.getMpr();
			msg.append("Dmg: +" + pc.getDmgup() + " / ");
			msg.append("Hit: +" + pc.getHitup() + " / ");
			msg.append("BowDmg: +" + pc.getBowDmgup() + " / ");
			msg.append("BowHit: +" + pc.getBowHitup() + " / ");
			msg.append("MR: " + pc.getMr() + " / ");
			msg.append("HPR: " + hpr + " / ");
			msg.append("MPR: " + mpr + " / ");
			msg.append("凍結耐性: " + pc.getResistFreeze() + " / ");
			msg.append("スタン耐性: " + pc.getResistStun() + " / ");
			msg.append("石化耐性: " + pc.getResistStone() + " / ");
			msg.append("睡眠耐性: " + pc.getResistSleep() + " / ");
			msg.append("ホールド耐性: " + pc.getResistHold() + " / ");
			msg.append("闇耐性: " + pc.getResistBlind() + " / ");
			msg.append("カルマ: " + pc.getKarma() + " / ");
			msg.append("所持Item数: " + pc.getInventory().getSize() + " / ");
			msg.append("追加Exp: +" + pc.getExpBonusPct() + "% / ");
			pc.sendPackets(new S_SystemMessage(msg.toString()));
		} catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(cmdName + " コマンドエラー"));
		}
	}
}
