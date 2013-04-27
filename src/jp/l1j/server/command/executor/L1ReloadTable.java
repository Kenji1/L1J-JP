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

package jp.l1j.server.command.executor;

import java.util.logging.Logger;
import jp.l1j.server.datatables.ArmorSetTable;
import jp.l1j.server.datatables.CookingRecipeTable;
import jp.l1j.server.datatables.DoorTable;
import jp.l1j.server.datatables.DropRateTable;
import jp.l1j.server.datatables.DropTable;
import jp.l1j.server.datatables.ItemRateTable;
import jp.l1j.server.datatables.ItemTable;
import jp.l1j.server.datatables.ShopTable;
import jp.l1j.server.datatables.DungeonTable;
import jp.l1j.server.datatables.MagicDollTable;
import jp.l1j.server.datatables.MapTable;
import jp.l1j.server.datatables.MobGroupTable;
import jp.l1j.server.datatables.MobSkillTable;
import jp.l1j.server.datatables.NpcActionTable;
import jp.l1j.server.datatables.NpcChatTable;
import jp.l1j.server.datatables.NpcTable;
import jp.l1j.server.datatables.PetItemTable;
import jp.l1j.server.datatables.PetTypeTable;
import jp.l1j.server.datatables.PolyTable;
import jp.l1j.server.datatables.RandomDungeonTable;
import jp.l1j.server.datatables.ResolventTable;
import jp.l1j.server.datatables.RestartLocationTable;
import jp.l1j.server.datatables.ReturnLocationTable;
import jp.l1j.server.datatables.SkillTable;
import jp.l1j.server.datatables.SprTable;
import jp.l1j.server.datatables.TrapTable;
import jp.l1j.server.datatables.UbTable;
import jp.l1j.server.datatables.WeaponSkillTable;
import jp.l1j.server.model.instance.L1PcInstance;
import jp.l1j.server.packets.server.S_SystemMessage;

public class L1ReloadTable implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1ReloadTable.class.getName());

	private L1ReloadTable() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1ReloadTable();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		pc.sendPackets(new S_SystemMessage("reloading the tables..."));
		ArmorSetTable.getInstance().reload();
		CookingRecipeTable.getInstance().reload();
		DoorTable.getInstance().reload();
		DropRateTable.getInstance().reload();
		DropTable.getInstance().reload();
		DungeonTable.getInstance().reload();
		ItemTable.getInstance().reload();
		ItemRateTable.getInstance().reload();
		MagicDollTable.getInstance().reload();
		MapTable.getInstance().reload();
		MobGroupTable.getInstance().reload();
		MobSkillTable.getInstance().reload();
		NpcActionTable.getInstance().reload();
		NpcChatTable.getInstance().reload();
		NpcTable.getInstance().reload();
		PetItemTable.getInstance().reload();
		PetTypeTable.getInstance().reload();
		PolyTable.getInstance().reload();
		RandomDungeonTable.getInstance().reload();
		ResolventTable.getInstance().reload();
		RestartLocationTable.getInstance().reload();
		ReturnLocationTable.reload();
		ShopTable.getInstance().reload();
		SkillTable.getInstance().reload();
		SprTable.getInstance().reload();
		TrapTable.getInstance().reload();
		UbTable.getInstance().reload();
		WeaponSkillTable.getInstance().reload();
		pc.sendPackets(new S_SystemMessage("reload the tables is complete."));
	}
}
