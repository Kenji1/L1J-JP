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

package jp.l1j.server.templates;

import java.util.Calendar;
import jp.l1j.server.datatables.HouseTable;
import jp.l1j.server.model.L1World;
import jp.l1j.server.model.instance.L1PcInstance;

public class L1AuctionHouse {
	public L1AuctionHouse() {
	}

	// アジトID
	private int _houseId;

	public int getHouseId() {
		return _houseId;
	}

	public void setHouseId(int i) {
		_houseId = i;
	}

	// アジト名
	public String getHouseName() {
		L1House house = HouseTable.getInstance().getHouseTable(_houseId);
		return house != null ? house.getHouseName() : null;
	}

	public int getHouseArea() {
		L1House house = HouseTable.getInstance().getHouseTable(_houseId);
		return house != null ? house.getHouseArea() : 0;
	}

	// 入札期限
	private Calendar _deadline;

	public Calendar getDeadline() {
		return _deadline;
	}

	public void setDeadline(Calendar i) {
		_deadline = i;
	}

	// 入札価格
	private int _price;

	public int getPrice() {
		return _price;
	}

	public void setPrice(int i) {
		_price = i;
	}

	// 場所名
	public String getLocation() {
		L1House house = HouseTable.getInstance().getHouseTable(_houseId);
		return house != null ? house.getLocation() : null;
	}

	// 所有者ID
	private int _ownerId;

	public int getOwnerId() {
		return _ownerId;
	}

	public void setOwnerId(int i) {
		_ownerId = i;
	}

	// 所有者名
	public String getOwner() {
		L1PcInstance owner = (L1PcInstance) L1World.getInstance().findObject(_ownerId);
		return owner != null ? owner.getName() : null;
	}

	// 入札者ID
	private int _bidderId;

	public int getBidderId() {
		return _bidderId;
	}

	public void setBidderId(int i) {
		_bidderId = i;
	}

	// 入札者名
	public String getBidder() {
		L1PcInstance bidder = (L1PcInstance) L1World.getInstance().findObject(_bidderId);
		return bidder != null ? bidder.getName() : null;
	}
}