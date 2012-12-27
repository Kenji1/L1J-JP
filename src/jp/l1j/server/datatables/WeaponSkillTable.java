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
import jp.l1j.server.model.L1WeaponSkill;
import jp.l1j.server.utils.L1DatabaseFactory;
import jp.l1j.server.utils.SqlUtil;

public class WeaponSkillTable {
	private static Logger _log = Logger.getLogger(WeaponSkillTable.class.getName());

	private static WeaponSkillTable _instance;

	private final HashMap<Integer, L1WeaponSkill> _weaponIdIndex
			= new HashMap<Integer, L1WeaponSkill>();

	public static WeaponSkillTable getInstance() {
		if (_instance == null) {
			_instance = new WeaponSkillTable();
		}
		return _instance;
	}

	private WeaponSkillTable() {
		loadWeaponSkill();
	}

	private void loadWeaponSkill() {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {

			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("SELECT * FROM weapon_skill");
			rs = pstm.executeQuery();
			fillWeaponSkillTable(rs);
		} catch (SQLException e) {
			_log.log(Level.SEVERE, "error while creating weapon_skill table",
					e);
		} finally {
			SqlUtil.close(rs);
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}
	}

	private void fillWeaponSkillTable(ResultSet rs) throws SQLException {
		while (rs.next()) {
			int weaponId = rs.getInt("weapon_id");
			int probability = rs.getInt("probability");
			int probEnchant = rs.getInt("prob_enchant");
			int fixDamage = rs.getInt("fix_damage");
			int randomDamage = rs.getInt("random_damage");
			int skillId = rs.getInt("skill_id");
			boolean isArrowType = rs.getBoolean("arrow_type");
			boolean isMr = rs.getBoolean("isMr");
			boolean isAttrMr = rs.getBoolean("isAttrMr");
			L1WeaponSkill weaponSkill = new L1WeaponSkill(weaponId,
					probability, probEnchant, fixDamage, randomDamage,
					skillId, isArrowType, isMr, isAttrMr);
			_weaponIdIndex.put(weaponId, weaponSkill);
		}
		_log.config("武器スキルリスト " + _weaponIdIndex.size() + "件ロード");
	}

	public L1WeaponSkill getTemplate(int weaponId) {
		return _weaponIdIndex.get(weaponId);
	}

}
