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

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import jp.l1j.server.datatables.NpcSpawnTable;
import jp.l1j.server.datatables.SpawnTable;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.L1Spawn;
import jp.l1j.server.model.L1Teleport;
import jp.l1j.server.packets.server.S_SystemMessage;

public class L1ToSpawn implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1ToSpawn.class.getName());
	private static final Map<Integer, Integer> _spawnId = new HashMap<Integer, Integer>();

	private L1ToSpawn() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1ToSpawn();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			if (!_spawnId.containsKey(pc.getId())) {
				_spawnId.put(pc.getId(), 0);
			}
			int id = _spawnId.get(pc.getId());
			if (arg.isEmpty() || arg.equals("+")) {
				id++;
			} else if (arg.equals("-")) {
				id--;
			} else {
				StringTokenizer st = new StringTokenizer(arg);
				id = Integer.parseInt(st.nextToken());
			}
			L1Spawn spawn = NpcSpawnTable.getInstance().getTemplate(id);
			if (spawn == null) {
				spawn = SpawnTable.getInstance().getTemplate(id);
			}
			if (spawn != null) {
				L1Teleport.teleport(pc, spawn.getLocX(), spawn.getLocY(),
						spawn.getMapId(), 5, false);
				pc.sendPackets(new S_SystemMessage("spawnid(" + id
						+ ")の元へ飛びます"));
			} else {
				pc.sendPackets(new S_SystemMessage("spawnid(" + id
						+ ")は見つかりません"));
			}
			_spawnId.put(pc.getId(), id);
		} catch (Exception exception) {
			pc.sendPackets(new S_SystemMessage(cmdName + " spawnid|+|-"));
		}
	}
}
