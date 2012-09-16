/**
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
package jp.l1j.server.model.npc.action;

import java.util.ArrayList;
import java.util.List;
import jp.l1j.configure.Config;
import jp.l1j.server.datatables.ItemTable;
import jp.l1j.server.model.Instance.L1ItemInstance;
import jp.l1j.server.model.Instance.L1NpcInstance;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.L1Object;
import jp.l1j.server.model.L1ObjectAmount;
import jp.l1j.server.model.inventory.L1PcInventory;
import jp.l1j.server.model.npc.L1NpcHtml;
import jp.l1j.server.packets.server.S_HowManyMake;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.random.RandomGenerator;
import jp.l1j.server.random.RandomGeneratorFactory;
import jp.l1j.server.templates.L1Item;
import jp.l1j.server.utils.IterableElementList;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class L1NpcMakeItemAction extends L1NpcXmlAction {
	private final List<L1ObjectAmount<Integer>> _materials = new ArrayList<L1ObjectAmount<Integer>>();
	private final List<L1ObjectAmount<Integer>> _items = new ArrayList<L1ObjectAmount<Integer>>();
	private final boolean _isAmountInputable;
	private final L1NpcAction _actionOnSucceed;
	private final L1NpcAction _actionOnFail;

	public L1NpcMakeItemAction(Element element) {
		super(element);

		_isAmountInputable = L1NpcXmlParser.getBoolAttribute(element,
				"AmountInputable", true);
		NodeList list = element.getChildNodes();
		for (Element elem : new IterableElementList(list)) {
			if (elem.getNodeName().equalsIgnoreCase("Material")) {
				int id = Integer.valueOf(elem.getAttribute("ItemId"));
				int amount = Integer.valueOf(elem.getAttribute("Amount"));
				_materials.add(new L1ObjectAmount<Integer>(id, amount));
				continue;
			}
			if (elem.getNodeName().equalsIgnoreCase("Item")) {
				int id = Integer.valueOf(elem.getAttribute("ItemId"));
				int amount = Integer.valueOf(elem.getAttribute("Amount"));
				_items.add(new L1ObjectAmount<Integer>(id, amount));
				continue;
			}
		}

		if (_items.isEmpty() || _materials.isEmpty()) {
			throw new IllegalArgumentException();
		}

		Element elem = L1NpcXmlParser.getFirstChildElementByTagName(element,
				"Succeed");
		_actionOnSucceed = elem == null ? null : new L1NpcListedAction(elem);
		elem = L1NpcXmlParser.getFirstChildElementByTagName(element, "Fail");
		_actionOnFail = elem == null ? null : new L1NpcListedAction(elem);
	}

	private boolean makeItems(L1PcInstance pc, String npcName, int amount) {
		if (amount <= 0) {
			return false;
		}

		boolean isEnoughMaterials = true;
		for (L1ObjectAmount<Integer> material : _materials) {
			if (!pc.getInventory().checkItemNotEquipped(material.getObject(),
					material.getAmount() * amount)) {
				L1Item temp = ItemTable.getInstance().getTemplate(
						material.getObject());
				pc.sendPackets(new S_ServerMessage(337, temp.getName() + "("
						+ ((material.getAmount() * amount) - pc.getInventory()
						.countItems(temp.getItemId())) + ")")); // \f1%0が不足しています。
				isEnoughMaterials = false;
			}
		}
		if (!isEnoughMaterials) {
			return false;
		}

		// 容量と重量の計算
		int countToCreate = 0; // アイテムの個数（纏まる物は1個）
		int weight = 0;

		for (L1ObjectAmount<Integer> makingItem : _items) {
			L1Item temp = ItemTable.getInstance().getTemplate(
					makingItem.getObject());
			if (temp.isStackable()) {
				if (!pc.getInventory().checkItem(makingItem.getObject())) {
					countToCreate += 1;
				}
			} else {
				countToCreate += makingItem.getAmount() * amount;
			}
			weight += temp.getWeight() * (makingItem.getAmount() * amount)
					/ 1000;
		}
		// 容量確認
		if (pc.getInventory().getSize() + countToCreate > 180) {
			pc.sendPackets(new S_ServerMessage(263)); // \f1一人のキャラクターが持って歩けるアイテムは最大180個までです。
			return false;
		}
		// 重量確認
		if (pc.getMaxWeight() < pc.getInventory().getWeight() + weight) {
			pc.sendPackets(new S_ServerMessage(82)); // アイテムが重すぎて、これ以上持てません。
			return false;
		}

		for (L1ObjectAmount<Integer> material : _materials) {
			// 材料消費
			pc.getInventory().consumeItem(material.getObject(),
					material.getAmount() * amount);
		}

		// ユニーク制作レートを取得
		double uniqueRate = Config.RATE_MAKE_UNIQUE_ITEMS;
		RandomGenerator random = RandomGeneratorFactory.getSharedRandom();
				
		for (L1ObjectAmount<Integer> makingItem : _items) {
			L1ItemInstance item = pc.getInventory().storeItem(
					makingItem.getObject(), makingItem.getAmount() * amount);
			if (item != null) {
				// ユニークオプションを付加
				if (item.getItem().getType2() == 1 || item.getItem().getType2() == 2) { // weapon or armor
					if (item.getItem().getType2() == 1) { // weapon
						item.setHitModifier(_getUniqueOption(Config.UNIQUE_MAX_HIT_MODIFIER, uniqueRate));
						item.setDmgModifier(_getUniqueOption(Config.UNIQUE_MAX_DMG_MODIFIER, uniqueRate));
					} else if (item.getItem().getType2() == 2) { // armor
						item.setAc(_getUniqueOption(Config.UNIQUE_MAX_AC, uniqueRate));
					}
					item.setStr(_getUniqueOption(Config.UNIQUE_MAX_STR, uniqueRate));
					item.setCon(_getUniqueOption(Config.UNIQUE_MAX_CON, uniqueRate));
					item.setDex(_getUniqueOption(Config.UNIQUE_MAX_DEX, uniqueRate));
					item.setWis(_getUniqueOption(Config.UNIQUE_MAX_WIS, uniqueRate));
					item.setInt(_getUniqueOption(Config.UNIQUE_MAX_INT, uniqueRate));
					item.setCha(_getUniqueOption(Config.UNIQUE_MAX_CHA, uniqueRate));
					item.setHp(_getUniqueOption(Config.UNIQUE_MAX_HP, uniqueRate));
					item.setHpr(_getUniqueOption(Config.UNIQUE_MAX_HPR, uniqueRate));
					item.setMp(_getUniqueOption(Config.UNIQUE_MAX_MP, uniqueRate));
					item.setMpr(_getUniqueOption(Config.UNIQUE_MAX_MPR, uniqueRate));
					item.setSp(_getUniqueOption(Config.UNIQUE_MAX_SP, uniqueRate));
					item.setMr(_getUniqueOption(Config.UNIQUE_MAX_MR, uniqueRate));
					item.setDefenseEarth(_getUniqueOption(Config.UNIQUE_MAX_DEFENSE_EARTH, uniqueRate));
					item.setDefenseWater(_getUniqueOption(Config.UNIQUE_MAX_DEFENSE_WATER, uniqueRate));
					item.setDefenseFire(_getUniqueOption(Config.UNIQUE_MAX_DEFENSE_FIRE, uniqueRate));
					item.setDefenseWind(_getUniqueOption(Config.UNIQUE_MAX_DEFENSE_WIND, uniqueRate));
					item.setResistStun(_getUniqueOption(Config.UNIQUE_MAX_RESIST_STUN, uniqueRate));
					item.setResistStone(_getUniqueOption(Config.UNIQUE_MAX_RESIST_STONE, uniqueRate));
					item.setResistSleep(_getUniqueOption(Config.UNIQUE_MAX_RESIST_SLEEP, uniqueRate));
					item.setResistFreeze(_getUniqueOption(Config.UNIQUE_MAX_RESIST_FREEZE, uniqueRate));
					item.setResistHold(_getUniqueOption(Config.UNIQUE_MAX_RESIST_HOLD, uniqueRate));
					item.setResistBlind(_getUniqueOption(Config.UNIQUE_MAX_RESIST_BLIND, uniqueRate));
					item.setExpBonus(_getUniqueOption(Config.UNIQUE_MAX_EXP_BONUS, uniqueRate));
				}
				
				String itemName = ItemTable.getInstance().getTemplate(
						makingItem.getObject()).getName();
				if (makingItem.getAmount() * amount > 1) {
					itemName = itemName + " (" + makingItem.getAmount()
							* amount + ")";
				}
				pc.sendPackets(new S_ServerMessage(143, npcName, itemName)); // \f1%0が%1をくれました。
			}
		}
		return true;
	}

	/**
	 * 指定されたインベントリ内に、素材が何セットあるか数える
	 */
	private int countNumOfMaterials(L1PcInventory inv) {
		int count = Integer.MAX_VALUE;
		for (L1ObjectAmount<Integer> material : _materials) {
			int numOfSet = inv.countItems(material.getObject())
					/ material.getAmount();
			count = Math.min(count, numOfSet);
		}
		return count;
	}

	@Override
	public L1NpcHtml execute(String actionName, L1PcInstance pc, L1Object obj,
			byte[] args) {
		int numOfMaterials = countNumOfMaterials(pc.getInventory());
		if (1 < numOfMaterials && _isAmountInputable) {
			pc.sendPackets(new S_HowManyMake(obj.getId(), numOfMaterials,
					actionName));
			return null;
		}
		return executeWithAmount(actionName, pc, obj, 1);
	}

	@Override
	public L1NpcHtml executeWithAmount(String actionName, L1PcInstance pc,
			L1Object obj, int amount) {
		L1NpcInstance npc = (L1NpcInstance) obj;
		L1NpcHtml result = null;
		if (makeItems(pc, npc.getNpcTemplate().getName(), amount)) {
			if (_actionOnSucceed != null) {
				result = _actionOnSucceed.execute(actionName, pc, obj,
						new byte[0]);
			}
		} else {
			if (_actionOnFail != null) {
				result = _actionOnFail
						.execute(actionName, pc, obj, new byte[0]);
			}
		}
		return result == null ? L1NpcHtml.HTML_CLOSE : result;
	}

	private int _getUniqueOption(int n, double ratePct) {
		RandomGenerator random = RandomGeneratorFactory.getSharedRandom();
		
		int chance = random.nextInt(100) + 1 ;
		
		if (ratePct >= (double) chance) {
			return random.nextInt(n);
		} else {
			return 0;
		}
	}
}
