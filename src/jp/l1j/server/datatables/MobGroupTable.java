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

package jp.l1j.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.templates.L1MobGroup;
import jp.l1j.server.templates.L1NpcCount;
import jp.l1j.server.utils.L1DatabaseFactory;
import jp.l1j.server.utils.PerformanceTimer;
import jp.l1j.server.utils.SqlUtil;
import jp.l1j.server.utils.collections.Lists;

public class MobGroupTable {
	private static Logger _log = Logger.getLogger(MobGroupTable.class.getName());

	private static MobGroupTable _instance;

	private static HashMap<Integer, L1MobGroup> _mobGroups = new HashMap<Integer, L1MobGroup>();

	public static MobGroupTable getInstance() {
		if (_instance == null) {
			_instance = new MobGroupTable();
		}
		return _instance;
	}

	private MobGroupTable() {
		loadMobGroups(_mobGroups);
	}

	private void loadMobGroups(HashMap<Integer, L1MobGroup> mobGroups) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			PerformanceTimer timer = new PerformanceTimer();
			System.out.print("loading mob groups...");
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM mob_groups");
			rs = pstm.executeQuery();
			while (rs.next()) {
				int mobGroupId = rs.getInt("id");
				boolean isRemoveGroup = (rs.getBoolean("remove_group_if_leader_die"));
				int leaderId = rs.getInt("leader_id");
				List<L1NpcCount> minions = Lists.newArrayList();
				for (int i = 1; i <= 7; i++) {
					int id = rs.getInt("minion" + i + "_id");
					int count = rs.getInt("minion" + i + "_count");
					minions.add(new L1NpcCount(id, count));
				}
				L1MobGroup mobGroup = new L1MobGroup(mobGroupId, leaderId,
						minions, isRemoveGroup);
				mobGroups.put(mobGroupId, mobGroup);
			}
			_log.config("Mob Groups: " + mobGroups.size() + "groups");
			System.out.println("OK! " + timer.elapsedTimeMillis() + "ms");
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "error while creating mob_groups table", e);
		} finally {
			SqlUtil.close(rs, pstm, con);
		}
	}

	public void reload() {
		HashMap<Integer, L1MobGroup> mobGroups = new HashMap<Integer, L1MobGroup>();
		loadMobGroups(mobGroups);
		_mobGroups = mobGroups;
	}
	
	public L1MobGroup getTemplate(int mobGroupId) {
		return _mobGroups.get(mobGroupId);
	}
}
