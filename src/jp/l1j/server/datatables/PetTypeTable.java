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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.templates.L1Npc;
import jp.l1j.server.templates.L1PetType;
import jp.l1j.server.utils.IntRange;
import jp.l1j.server.utils.L1DatabaseFactory;
import jp.l1j.server.utils.SqlUtil;

public class PetTypeTable {
	private static PetTypeTable _instance;
	
	private static Logger _log = Logger.getLogger(PetTypeTable.class.getName());
	
	private Map<Integer, L1PetType> _types = new HashMap<Integer, L1PetType>();
	
	private Set<String> _defaultNames = new HashSet<String>();

	public static void load() {
		_instance = new PetTypeTable();
	}

	public static PetTypeTable getInstance() {
		return _instance;
	}

	private PetTypeTable() {
		loadTypes();
	}

	private void loadTypes() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM pet_types");
			rs = pstm.executeQuery();
			while (rs.next()) {
				int npcId = rs.getInt("npc_id");
				L1Npc npc = NpcTable.getInstance().getTemplate(npcId);
				String name = npc != null ? npc.getName() : null;
				int tameItemId = rs.getInt("tame_item_id");
				int minHpUp = rs.getInt("min_hpup");
				int maxHpUp = rs.getInt("max_hpup");
				int minMpUp = rs.getInt("min_mpup");
				int maxMpUp = rs.getInt("max_mpup");
				int transformItemId = rs.getInt("transform_item_id");
				int transformNpcId = rs.getInt("transform_npc_id");
				int msgIds[] = new int[5];
				for (int i = 0; i < 5; i++) {
					msgIds[i] = rs.getInt("message_id" + (i + 1));
				}
				int defyMsgId = rs.getInt("defy_message_id");
				boolean useEquipment = rs.getBoolean("use_equipment");
				IntRange hpUpRange = new IntRange(minHpUp, maxHpUp);
				IntRange mpUpRange = new IntRange(minMpUp, maxMpUp);
				_types.put(npcId, new L1PetType(npcId, name,
						tameItemId, hpUpRange, mpUpRange, transformItemId,
						transformNpcId, msgIds, defyMsgId, useEquipment));
				_defaultNames.add(name.toLowerCase());
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SqlUtil.close(rs);
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}
	}

	public L1PetType get(int baseNpcId) {
		return _types.get(baseNpcId);
	}

	public boolean isNameDefault(String name) {
		return _defaultNames.contains(name.toLowerCase());
	}
}