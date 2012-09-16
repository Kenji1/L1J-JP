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
package jp.l1j.server.model.inventory;

import java.util.List;
import jp.l1j.server.model.Instance.L1ItemInstance;
import jp.l1j.server.model.L1World;
import jp.l1j.server.templates.L1InventoryItem;

public class L1WarehouseInventory extends L1Inventory {
	private static final long serialVersionUID = 1L;
	private final int _ownerId;
	private final int _ownerLocation;

	public L1WarehouseInventory(int ownerId, int ownerLocation) {
		_ownerId = ownerId;
		_ownerLocation = ownerLocation;
	}

	// DBの読込
	@Override
	public void loadItems() {
		List<L1InventoryItem> inventoryItems = L1InventoryItem
				.findByOwnerIdAndLocation(_ownerId, _ownerLocation);

		List<L1ItemInstance> items = L1InventoryItem
				.instantiate(inventoryItems);
		for (L1ItemInstance item : items) {
			_items.add(item);
			L1World.getInstance().storeObject(item);
		}
	}

	// DBへ登録
	@Override
	public void insertItem(L1ItemInstance item) {
		item.setOwner(_ownerId, _ownerLocation);
		item.save();
	}

	// DBを更新
	@Override
	public void updateItem(L1ItemInstance item) {
		item.save();
	}

	// DBのクラン倉庫のアイテムを全て削除(血盟解散時のみ使用)
	public synchronized void deleteAllItems() {
		L1InventoryItem.deleteAll(_ownerId);
	}

	@Override
	public int getOwnerLocation() {
		return _ownerLocation;
	}
}