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

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.datatables.NpcSpawnTable;
import jp.l1j.server.datatables.NpcTable;
import jp.l1j.server.datatables.SpawnTable;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.packets.server.S_SystemMessage;
import jp.l1j.server.templates.L1Npc;
import jp.l1j.server.utils.L1SpawnUtil;

public class L1InsertSpawn implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1InsertSpawn.class.getName());

	private L1InsertSpawn() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1InsertSpawn();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		String msg = null;

		try {
			StringTokenizer tok = new StringTokenizer(arg);
			String type = tok.nextToken();
			int npcId = Integer.parseInt(tok.nextToken().trim());
			L1Npc template = NpcTable.getInstance().getTemplate(npcId);

			if (template == null) {
				msg = "該当するNPCが見つかりません。";
				return;
			}
			if (type.equals("mob")) {
				if (!template.getImpl().equals("L1Monster")) {
					msg = "指定したNPCはL1Monsterではありません。";
					return;
				}
				SpawnTable.storeSpawn(pc, template);
			} else if (type.equals("npc")) {
				NpcSpawnTable.getInstance().storeSpawn(pc, template);
			}
			L1SpawnUtil.spawn(pc, npcId, 0, 0);
			msg = new StringBuilder().append(template.getName()).append(
					" (" + npcId + ") ").append("を追加しました。").toString();
		} catch (Exception e) {
			_log.log(Level.SEVERE, "", e);
			msg = cmdName + " mob|npc NPCID と入力して下さい。";
		} finally {
			if (msg != null) {
				pc.sendPackets(new S_SystemMessage(msg));
			}
		}
	}
}
