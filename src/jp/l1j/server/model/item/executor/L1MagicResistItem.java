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
import jp.l1j.server.utils.PerformanceTimer;

@XmlAccessorType(XmlAccessType.FIELD)
public class L1MagicResistItem {

	private static Logger _log = Logger.getLogger(L1MagicResistItem.class.getName());

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "ItemEffectList")
	private static class ItemEffectList implements Iterable<L1MagicResistItem> {
		@XmlElement(name = "Item")
		private List<L1MagicResistItem> _list;

		public Iterator<L1MagicResistItem> iterator() {
			return _list.iterator();
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	private static class Effect {
		@XmlAttribute(name = "Mr")
		private int _mr;

		public int getMr() {
			return _mr;
		}
	}

	private static final String PATH = "./data/xml/Item/MagicResistItem.xml";

	private static final HashMap<Integer, L1MagicResistItem> _dataMap = new HashMap<Integer, L1MagicResistItem>();

	public static L1MagicResistItem get(int id) {
		return _dataMap.get(id);
	}

	@XmlAttribute(name = "ItemId")
	private int _itemId;

	private int getItemId() {
		return _itemId;
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
		return true;
	}
	
	public static void load() {
		PerformanceTimer timer = new PerformanceTimer();
		System.out.print("loading magic resist item...");
		try {
			JAXBContext context = JAXBContext.newInstance(L1MagicResistItem.ItemEffectList.class);

			Unmarshaller um = context.createUnmarshaller();

			File file = new File(PATH);
			ItemEffectList list = (ItemEffectList) um.unmarshal(file);

			for (L1MagicResistItem each : list) {
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

	public int getMr() {
		Effect effect = getEffect();
		
		if (effect != null) {
			return effect.getMr();
		}
		
		return 0;
	}
}
