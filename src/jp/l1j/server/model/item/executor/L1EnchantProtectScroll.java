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
package jp.l1j.server.model.item.executor;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import jp.l1j.server.datatables.ItemTable;
import jp.l1j.server.model.instance.L1ItemInstance;
import jp.l1j.server.model.instance.L1PcInstance;
import jp.l1j.server.model.inventory.L1PcInventory;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.utils.PerformanceTimer;

@XmlAccessorType(XmlAccessType.FIELD)
public class L1EnchantProtectScroll {

	private static Logger _log = Logger.getLogger(L1EnchantProtectScroll.class.getName());

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "ItemEffectList")
	private static class ItemEffectList implements Iterable<L1EnchantProtectScroll> {
		@XmlElement(name = "Item")
		private List<L1EnchantProtectScroll> _list;

		public Iterator<L1EnchantProtectScroll> iterator() {
			return _list.iterator();
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	private static class Effect {
		@XmlAttribute(name = "ItemId")
		private int _itemId;

		public int getItemId() {
			return _itemId;
		}
		
		@XmlAttribute(name = "DownLevel")
		private int _downLevel;

		public int getDownLevel() {
			return _downLevel;
		}
	}

	private static final String PATH = "./data/xml/Item/EnchantProtectScroll.xml";

	private static final HashMap<Integer, L1EnchantProtectScroll> _dataMap = new HashMap<Integer, L1EnchantProtectScroll>();

	public static L1EnchantProtectScroll get(int id) {
		return _dataMap.get(id);
	}

	@XmlAttribute(name = "ItemId")
	private int _itemId;

	private int getItemId() {
		return _itemId;
	}

	@XmlAttribute(name = "Remove")
	private int _remove;

	private int getRemove() {
		return _remove;
	}

	@XmlElement(name = "Effect")
	private CopyOnWriteArrayList<Effect> _effects;

	private List<Effect> getEffects() {
		return _effects;
	}
	
	private boolean init() {
		if (ItemTable.getInstance().getTemplate(getItemId()) == null) {
			System.out.println("アイテムID " + getItemId() + " のテンプレートが見つかりません。");
			return false;
		}
		for (Effect each : getEffects()) {
			if (ItemTable.getInstance().getTemplate(each.getItemId()) == null) {
				System.out.println("アイテムID " + each.getItemId() + " のテンプレートが見つかりません。");
				return false;
			}
		}
		return true;
	}
	
	public static void load() {
		PerformanceTimer timer = new PerformanceTimer();
		System.out.print("loading enchant protect scroll...");
		try {
			JAXBContext context = JAXBContext.newInstance(L1EnchantProtectScroll.ItemEffectList.class);

			Unmarshaller um = context.createUnmarshaller();

			File file = new File(PATH);
			ItemEffectList list = (ItemEffectList) um.unmarshal(file);

			for (L1EnchantProtectScroll each : list) {
				if (each.init()) {
					_dataMap.put(each.getItemId(), each);
				}
			}
		} catch (Exception e) {
			_log.log(Level.SEVERE, PATH + "のロードに失敗。", e);
			System.exit(0);
		}
		System.out.println("OK! " + timer.elapsedTimeMillis() + "ms");
	}

	public boolean use(L1PcInstance pc, L1ItemInstance item, L1ItemInstance target) {
		
		int maxChargeCount = item.getItem().getMaxChargeCount();
		int chargeCount = item.getChargeCount();
		if (maxChargeCount > 0 && chargeCount <= 0) {
			// \f1何も起きませんでした。
			pc.sendPackets(new S_ServerMessage(79));
			return false;
		}
		
		Effect effect = null;
		for (Effect each : getEffects()) {
			if (target.getItemId() == each.getItemId()) {
				effect = each;
				break;
			}
		}
		
		if (effect == null) {
			pc.sendPackets(new S_ServerMessage(1309));
			// この装備には蒸発保護スクロールが使えません。
			return false;
		}
		
		if (target.isProtected()) {
			pc.sendPackets(new S_ServerMessage(2123));
			// すでに保護中のアイテムには使用できません。
			return false;
		}

		target.setProtected(true); // 保護中
		pc.getInventory().updateItem(target, L1PcInventory.COL_EQUIPPED);
		pc.sendPackets(new S_ServerMessage(1308, target.getLogName()));
		// %0が魔力の力で蒸発から守られます。

		if (getRemove() > 0) {
			if (chargeCount > 0) {
				item.setChargeCount(chargeCount - getRemove());
				pc.getInventory().updateItem(item,
						L1PcInventory.COL_CHARGE_COUNT);
			} else {
				pc.getInventory().removeItem(item, getRemove());
			}
		}

		return true;
	}

	public int getDownLevel(int itemId) {
		Effect effect = null;
		for (Effect each : getEffects()) {
			if (itemId == each.getItemId()) {
				effect = each;
				break;
			}
		}
		
		if (effect != null) {
			return effect.getDownLevel();
		}
		
		return 0;
	}
			
}
