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
import jp.l1j.server.datatables.SkillTable;
import jp.l1j.server.model.Instance.L1ItemInstance;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.inventory.L1PcInventory;
import jp.l1j.server.model.skill.L1BuffUtil;
import jp.l1j.server.model.skill.L1SkillUse;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.utils.PerformanceTimer;

@XmlAccessorType(XmlAccessType.FIELD)
public class L1SpellWand {

	private static Logger _log = Logger.getLogger(L1SpellWand.class.getName());

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "ItemEffectList")
	private static class ItemEffectList implements Iterable<L1SpellWand> {
		@XmlElement(name = "Item")
		private List<L1SpellWand> _list;

		public Iterator<L1SpellWand> iterator() {
			return _list.iterator();
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	private static class Effect {
		@XmlAttribute(name = "SkillId")
		private int _skillId;

		public int getSkillId() {
			return _skillId;
		}
	}

	private static final String PATH = "./data/xml/Item/SpellWand.xml";

	private static final HashMap<Integer, L1SpellWand> _dataMap = new HashMap<Integer, L1SpellWand>();

	public static L1SpellWand get(int id) {
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
	private Effect _effect;

	private Effect getEffect() {
		return _effect;
	}

	private boolean init() {
		if (ItemTable.getInstance().getTemplate(getItemId()) == null) {
			System.out.println("アイテムID " + getItemId() + " のテンプレートが見つかりません。");
			return false;
		}
		Effect effect = getEffect();
		if (SkillTable.getInstance().findBySkillId(effect.getSkillId()) == null) {
			System.out.println("SkillID " + effect.getSkillId() + " が見つかりません。");
			return false;
		}
		return true;
	}

	public static void load() {
		PerformanceTimer timer = new PerformanceTimer();
		System.out.print("loading spell wand...");
		try {
			JAXBContext context = JAXBContext.newInstance(L1SpellWand.ItemEffectList.class);

			Unmarshaller um = context.createUnmarshaller();

			File file = new File(PATH);
			ItemEffectList list = (ItemEffectList) um.unmarshal(file);

			for (L1SpellWand each : list) {
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

	public boolean use(L1PcInstance pc, L1ItemInstance item, int objid,
			int locx, int locy) {
		L1BuffUtil.cancelBarrier(pc); // アブソルート バリアの解除
		
		int maxChargeCount = item.getItem().getMaxChargeCount();
		int chargeCount = item.getChargeCount();
		if (maxChargeCount > 0 && chargeCount <= 0) {
			// \f1何も起きませんでした。
			pc.sendPackets(new S_ServerMessage(79));
			return false;
		}
//		
//		L1Object targetPc = L1World.getInstance().findObject(objid);
//		pc.sendPackets(new S_UseAttackSkill(pc, objid, 10, locx, locy,
//				ActionCodes.ACTION_Wand));
//		pc.broadcastPacket(new S_UseAttackSkill(pc, objid, 10, locx, locy,
//				ActionCodes.ACTION_Wand));
//		
		Effect effect = getEffect();

		L1SkillUse l1skilluse = new L1SkillUse();
		l1skilluse.handleCommands(pc, effect.getSkillId(), objid,
				locx, locy, null, 0, L1SkillUse.TYPE_SPELLSC);
		
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

}
