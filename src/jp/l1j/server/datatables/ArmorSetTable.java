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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.templates.L1ArmorSets;
import jp.l1j.server.utils.L1DatabaseFactory;
import jp.l1j.server.utils.SqlUtil;

public class ArmorSetTable {
	private static Logger _log = Logger.getLogger(ArmorSetTable.class.getName());

	private static ArmorSetTable _instance;

	private final ArrayList<L1ArmorSets> _armorSetList
			= new ArrayList<L1ArmorSets>();

	public static ArmorSetTable getInstance() {
		if (_instance == null) {
			_instance = new ArmorSetTable();
		}
		return _instance;
	}

	private ArmorSetTable() {
		load();
	}

	private void load() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM armor_set");
			rs = pstm.executeQuery();
			fillTable(rs);
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "error while creating armor_set table", e);
		} finally {
			SqlUtil.close(rs);
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}
	}

	private void fillTable(ResultSet rs) throws SQLException {
		while (rs.next()) {
			L1ArmorSets as = new L1ArmorSets();
			as.setId(rs.getInt("id"));
			as.setSets(rs.getString("sets"));
			as.setPolyId(rs.getInt("poly_id"));
			as.setAc(rs.getInt("ac"));
			as.setStr(rs.getInt("str"));
			as.setDex(rs.getInt("dex"));
			as.setCon(rs.getInt("con"));
			as.setWis(rs.getInt("wis"));
			as.setCha(rs.getInt("cha"));
			as.setInt(rs.getInt("int"));
			as.setHp(rs.getInt("hp"));
			as.setHpr(rs.getInt("hpr"));
			as.setMp(rs.getInt("mp"));
			as.setMpr(rs.getInt("mpr"));
			as.setSp(rs.getInt("sp"));
			as.setMr(rs.getInt("mr"));
			as.setHitModifier(rs.getInt("hit_modifier"));
			as.setDmgModifer(rs.getInt("dmg_modifier"));
			as.setBowHitModifier(rs.getInt("bow_hit_modifier"));
			as.setBowDmgModifier(rs.getInt("bow_dmg_modifier"));
			as.setDefenseFire(rs.getInt("damage_reduction"));
			as.setDefenseWater(rs.getInt("weight_reduction"));
			as.setDefenseFire(rs.getInt("defense_fire"));
			as.setDefenseWater(rs.getInt("defense_water"));
			as.setDefenseEarth(rs.getInt("defense_earth"));
			as.setDefenseWind(rs.getInt("defense_wind"));
			as.setResistStun(rs.getInt("resist_stun"));
			as.setResistStone(rs.getInt("resist_stone"));
			as.setResistSleep(rs.getInt("resist_sleep"));
			as.setResistFreeze(rs.getInt("resist_freeze"));
			as.setResistHold(rs.getInt("resist_hold"));
			as.setResistBlind(rs.getInt("resist_blind"));
			as.setIsHaste(rs.getBoolean("is_haste"));
			as.setExpBonus(rs.getInt("exp_bonus"));
			
			_armorSetList.add(as);
		}
	}

	public L1ArmorSets[] getAllList() {
		return _armorSetList.toArray(new L1ArmorSets[_armorSetList.size()]);
	}

}
