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
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.model.instance.L1PcInstance;
import jp.l1j.server.model.L1Clan;
import jp.l1j.server.model.L1World;
import jp.l1j.server.utils.IdFactory;
import jp.l1j.server.utils.L1DatabaseFactory;
import jp.l1j.server.utils.SqlUtil;

// Referenced classes of package jp.l1j.server:
// IdFactory

public class ClanTable {

	private static Logger _log = Logger.getLogger(ClanTable.class.getName());

	private final HashMap<Integer, L1Clan> _clans = new HashMap<Integer, L1Clan>();

	private static ClanTable _instance;

	public static ClanTable getInstance() {
		if (_instance == null) {
			_instance = new ClanTable();
		}
		return _instance;
	}

	private ClanTable() {
		{
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;

			try {
				con = L1DatabaseFactory.getInstance().getConnection();
				pstm = con.prepareStatement("SELECT * FROM clans ORDER BY clan_id");
				rs = pstm.executeQuery();
				
				while (rs.next()) {
					int clan_id = rs.getInt(1);
					L1Clan clan = new L1Clan(clan_id);
					// clan.SetClanId(clanData.getInt(1));
					clan.setClanName(rs.getString(2));
					clan.setLeaderId(rs.getInt(3));
					clan.setLeaderName(rs.getString(4));
					clan.setCastleId(rs.getInt(5));
					clan.setHouseId(rs.getInt(6));

					L1World.getInstance().storeClan(clan);
					_clans.put(clan_id, clan);
				}

			} catch (SQLException e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			} finally {
				SqlUtil.close(rs);
				SqlUtil.close(pstm);
				SqlUtil.close(con);
			}
		}

		Collection<L1Clan> AllClan = L1World.getInstance().getAllClans();
		for (L1Clan clan : AllClan) {
			Connection con = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;

			try {
				con = L1DatabaseFactory.getInstance().getConnection();
				pstm = con.prepareStatement("SELECT name FROM characters WHERE clan_id = ?");
				pstm.setInt(1, clan.getClanId());
				rs = pstm.executeQuery();

				while (rs.next()) {
					clan.addMemberName(rs.getString(1));
				}
			} catch (SQLException e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			} finally {
				SqlUtil.close(rs);
				SqlUtil.close(pstm);
				SqlUtil.close(con);
			}
		}
		// クラン倉庫のロード
		for (L1Clan clan : AllClan) {
			clan.getWarehouse().loadItems();
		}
	}

	public L1Clan createClan(L1PcInstance player, String clanName) {
		for (L1Clan oldClans : L1World.getInstance().getAllClans()) {
			if (oldClans.getClanName().equalsIgnoreCase(clanName)) {
				return null;
			}
		}
		L1Clan clan = new L1Clan(IdFactory.getInstance().nextId());
		clan.setClanName(clanName);
		clan.setLeaderId(player.getId());
		clan.setLeaderName(player.getName());
		clan.setCastleId(0);
		clan.setHouseId(0);

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("INSERT INTO clans SET clan_id=?, clan_name=?, leader_id=?, leader_name=?, has_castle=?, has_house=?");
			pstm.setInt(1, clan.getClanId());
			pstm.setString(2, clan.getClanName());
			pstm.setInt(3, clan.getLeaderId());
			pstm.setString(4, clan.getLeaderName());
			pstm.setInt(5, clan.getCastleId());
			pstm.setInt(6, clan.getHouseId());
			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}

		L1World.getInstance().storeClan(clan);
		_clans.put(clan.getClanId(), clan);

		player.setClanid(clan.getClanId());
		player.setClanname(clan.getClanName());
		player.setClanRank(L1Clan.CLAN_RANK_LEADER);
		clan.addMemberName(player.getName());
		try {
			// DBにキャラクター情報を書き込む
			player.save();
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
		return clan;
	}

	public void updateClan(L1Clan clan) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("UPDATE clans SET clan_id=?, leader_id=?, leader_name=?, has_castle=?, has_house=? WHERE clan_name=?");
			pstm.setInt(1, clan.getClanId());
			pstm.setInt(2, clan.getLeaderId());
			pstm.setString(3, clan.getLeaderName());
			pstm.setInt(4, clan.getCastleId());
			pstm.setInt(5, clan.getHouseId());
			pstm.setString(6, clan.getClanName());
			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}
	}

	public void deleteClan(String clan_name) {
		L1Clan clan = L1World.getInstance().getClan(clan_name);
		if (clan == null) {
			return;
		}
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("DELETE FROM clans WHERE clan_name=?");
			pstm.setString(1, clan_name);
			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}
		clan.getWarehouse().clearItems();
		clan.getWarehouse().deleteAllItems();

		L1World.getInstance().removeClan(clan);
		_clans.remove(clan.getClanId());
	}

	public L1Clan getTemplate(int clan_id) {
		return _clans.get(clan_id);
	}

}
