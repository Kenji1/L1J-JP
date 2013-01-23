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
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.templates.L1MagicDoll;
import jp.l1j.server.utils.L1DatabaseFactory;
import jp.l1j.server.utils.SqlUtil;

public class MagicDollTable {

	private static Logger _log = Logger.getLogger(MagicDollTable.class.getName());

	private static MagicDollTable _instance;

	private final HashMap<Integer, L1MagicDoll> _dolls = new HashMap<Integer, L1MagicDoll>();

	public static MagicDollTable getInstance() {
		if (_instance == null) {
			_instance = new MagicDollTable();
		}
		return _instance;
	}

	private MagicDollTable() {
		load();
	}

	private void load() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM magic_dolls");
			rs = pstm.executeQuery();
			while (rs.next()) {
				L1MagicDoll doll = new L1MagicDoll();
				int itemId = rs.getInt("item_id");
				doll.setItemId(itemId);
				doll.setDollId(rs.getInt("doll_id"));
				doll.setAc(rs.getInt("ac"));
				doll.setStr(rs.getInt("str"));
				doll.setCon(rs.getInt("con"));
				doll.setDex(rs.getInt("dex"));
				doll.setInt(rs.getInt("int"));
				doll.setWis(rs.getInt("wis"));
				doll.setCha(rs.getInt("cha"));
				doll.setHp(rs.getInt("hp"));
				doll.setHpr(rs.getInt("hpr"));
				doll.setHprTime(rs.getBoolean("hpr_time"));
				doll.setMp(rs.getInt("mp"));
				doll.setMpr(rs.getInt("mpr"));
				doll.setMprTime(rs.getBoolean("mpr_time"));
				doll.setMr(rs.getInt("mr"));
				doll.setHit(rs.getInt("hit"));
				doll.setDmg(rs.getInt("dmg"));
				doll.setDmgChance(rs.getInt("dmg_chance"));
				doll.setBowHit(rs.getInt("bow_hit"));
				doll.setBowDmg(rs.getInt("bow_dmg"));
				doll.setDmgReduction(rs.getInt("dmg_reduction"));
				doll.setDmgReductionChance(rs.getInt("dmg_reduction_chance"));
				doll.setDmgEvasionChance(rs.getInt("dmg_evasion_chance"));
				doll.setWeightReduction(rs.getInt("weight_reduction"));
				doll.setResistStun(rs.getInt("resist_stun"));
				doll.setResistStone(rs.getInt("resist_stone"));
				doll.setResistSleep(rs.getInt("resist_sleep"));
				doll.setResistFreeze(rs.getInt("resist_freeze"));
				doll.setResistHold(rs.getInt("resist_hold"));
				doll.setResistBlind(rs.getInt("resist_blind"));
				doll.setExpBonus(rs.getInt("exp_bonus"));
				doll.setMakeItemId(rs.getInt("make_item_id"));
				doll.setSkillId(rs.getByte("skill_id"));
				doll.setSkillChance(rs.getByte("skill_chance"));

				_dolls.put(new Integer(itemId), doll);
			}
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SqlUtil.close(rs);
			SqlUtil.close(pstm);
			SqlUtil.close(con);

		}
	}

	public L1MagicDoll getTemplate(int itemId) {
		if (_dolls.containsKey(itemId)) {
			return _dolls.get(itemId);
		}
		return null;
	}

}
