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
import jp.l1j.server.utils.SqlUtil;
import jp.l1j.server.utils.collections.Lists;

public class MobGroupTable {
	private static Logger _log = Logger.getLogger(MobGroupTable.class.getName());

	private static MobGroupTable _instance;

	private final HashMap<Integer, L1MobGroup> _mobGroupIndex = new HashMap<Integer, L1MobGroup>();

	public static MobGroupTable getInstance() {
		if (_instance == null) {
			_instance = new MobGroupTable();
		}
		return _instance;
	}

	private MobGroupTable() {
		loadMobGroup();
	}

	private void loadMobGroup() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM mob_groups");
			rs = pstm.executeQuery();
			while (rs.next()) {
				int mobGroupId = rs.getInt("id");
				boolean isRemoveGroup = (rs
						.getBoolean("remove_group_if_leader_die"));
				int leaderId = rs.getInt("leader_id");
				List<L1NpcCount> minions = Lists.newArrayList();
				for (int i = 1; i <= 7; i++) {
					int id = rs.getInt("minion" + i + "_id");
					int count = rs.getInt("minion" + i + "_count");
					minions.add(new L1NpcCount(id, count));
				}
				L1MobGroup mobGroup = new L1MobGroup(mobGroupId, leaderId,
						minions, isRemoveGroup);
				_mobGroupIndex.put(mobGroupId, mobGroup);
			}
			_log.config("MOBグループリスト " + _mobGroupIndex.size() + "件ロード");
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "error while creating mob_groups table", e);
		} finally {
			SqlUtil.close(rs);
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}
	}

	public L1MobGroup getTemplate(int mobGroupId) {
		return _mobGroupIndex.get(mobGroupId);
	}

}
