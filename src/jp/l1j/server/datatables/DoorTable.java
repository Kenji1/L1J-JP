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

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.codes.ActionCodes;
import jp.l1j.server.model.instance.L1DoorInstance;
import jp.l1j.server.model.L1Location;
import jp.l1j.server.model.L1World;
import jp.l1j.server.templates.L1DoorGfx;
import jp.l1j.server.templates.L1DoorSpawn;
import jp.l1j.server.utils.IdFactory;
import jp.l1j.server.utils.collections.Lists;
import jp.l1j.server.utils.collections.Maps;

public class DoorTable {
	private static Logger _log = Logger.getLogger(DoorTable.class.getName());
	private static DoorTable _instance;

	private final Map<L1Location, L1DoorInstance> _doors = Maps.newConcurrentHashMap();
	private final Map<L1Location, L1DoorInstance> _doorDirections = Maps.newConcurrentHashMap();

	public static void initialize() {
		_instance = new DoorTable();
	}

	public static DoorTable getInstance() {
		return _instance;
	}

	private DoorTable() {
		loadDoors();
	}

	private void loadDoors() {
		for (L1DoorSpawn spawn : L1DoorSpawn.all()) {
			L1Location loc = spawn.getLocation();
			if (_doors.containsKey(loc)) {
				_log.log(Level.WARNING, String.format(
						"Duplicate door location: id = %d", spawn.getId()));
				continue;
			}
			createDoor(spawn.getId(), spawn.getGfx(), loc, spawn.getHp(),
					spawn.getKeeper(), spawn.DoorOpen());
		}
	}

	private void putDirections(L1DoorInstance door) {
		for (L1Location key : makeDirectionsKeys(door)) {
			_doorDirections.put(key, door);
		}
	}

	private void removeDirections(L1DoorInstance door) {
		for (L1Location key : makeDirectionsKeys(door)) {
			_doorDirections.remove(key);
		}
	}

	private List<L1Location> makeDirectionsKeys(L1DoorInstance door) {
		List<L1Location> keys = Lists.newArrayList();
		int left = door.getLeftEdgeLocation();
		int right = door.getRightEdgeLocation();
		if (door.getDirection() == 0) {
			for (int x = left; x <= right; x++) {
				keys.add(new L1Location(x, door.getY(), door.getMapId()));
			}
		} else {
			for (int y = left; y <= right; y++) {
				keys.add(new L1Location(door.getX(), y, door.getMapId()));
			}
		}
		return keys;
	}

	public L1DoorInstance createDoor(int doorId, L1DoorGfx gfx, L1Location loc,
			int hp, int keeper, boolean Open) {
		if (_doors.containsKey(loc)) {
			return null;
		}
		L1DoorInstance door = new L1DoorInstance(doorId, gfx, loc, hp, keeper, Open);

		door.setId(IdFactory.getInstance().nextId());

		L1World.getInstance().storeObject(door);
		L1World.getInstance().addVisibleObject(door);

		_doors.put(door.getLocation(), door);
		putDirections(door);
		return door;
	}

	public void deleteDoorByLocation(L1Location loc) {
		L1DoorInstance door = _doors.remove(loc);
		if (door != null) {
			removeDirections(door);
			door.deleteMe();
		}
	}

	public int getDoorDirection(L1Location loc) {
		L1DoorInstance door = _doorDirections.get(loc);
		if (door == null || door.getOpenStatus() == ActionCodes.ACTION_Open) {
			return -1;
		}
		return door.getDirection();
	}

	public L1DoorInstance findByDoorId(int doorId) {
		for (L1DoorInstance door : _doors.values()) {
			if (door.getDoorId() == doorId) {
				return door;
			}
		}
		return null;
	}

	public L1DoorInstance[] getDoorList() {
		return _doors.values().toArray(new L1DoorInstance[_doors.size()]);
	}
}
