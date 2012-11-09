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
import jp.l1j.server.model.L1World;
import jp.l1j.server.packets.server.S_Disconnect;
import jp.l1j.server.packets.server.S_SystemMessage;

public class L1Kick implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Kick.class.getName());

	private L1Kick() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Kick();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			L1PcInstance target = L1World.getInstance().getPlayer(arg);

			if (target != null) {
				target.saveInventory();
				pc.sendPackets(new S_SystemMessage((new StringBuilder())
						.append(target.getName()).append("さんをキックしました。")
						.toString()));
				target.sendPackets(new S_Disconnect());
			} else {
				pc.sendPackets(new S_SystemMessage(
						"そのような名前のキャラクターはワールド内には存在しません。"));
			}
		} catch (Exception e) {
			pc.sendPackets(new S_SystemMessage(cmdName + " キャラクター名 と入力して下さい。"));
		}
	}
}
