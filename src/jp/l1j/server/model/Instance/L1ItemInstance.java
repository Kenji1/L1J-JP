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

package jp.l1j.server.model.Instance;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import jp.l1j.configure.Config;
import jp.l1j.server.datatables.FurnitureSpawnTable;
import jp.l1j.server.datatables.InnKeyTable;
import jp.l1j.server.datatables.ItemTable;
import jp.l1j.server.datatables.LetterTable;
import jp.l1j.server.datatables.NpcTable;
import jp.l1j.server.datatables.PetTable;
import jp.l1j.server.datatables.RaceTicketTable;
import jp.l1j.server.model.L1ChargeTimer;
import jp.l1j.server.model.L1ExpirationTimer;
import jp.l1j.server.model.L1ItemOwnerTimer;
import jp.l1j.server.model.L1Object;
import jp.l1j.server.model.L1World;
import static jp.l1j.server.model.item.L1ItemOptionId.*;
import static jp.l1j.server.model.skill.L1SkillId.*;
import jp.l1j.server.packets.server.S_OwnCharStatus;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.random.RandomGenerator;
import jp.l1j.server.random.RandomGeneratorFactory;
import jp.l1j.server.templates.L1Armor;
import jp.l1j.server.templates.L1InventoryItem;
import jp.l1j.server.templates.L1Item;
import jp.l1j.server.templates.L1Npc;
import jp.l1j.server.templates.L1Pet;
import jp.l1j.server.utils.BinaryOutputStream;
import jp.l1j.server.utils.IntRange;

// Referenced classes of package jp.l1j.server.model:
// L1Object, L1PcInstance

public class L1ItemInstance extends L1Object {
	
	private static final RandomGenerator random = RandomGeneratorFactory.getSharedRandom();
	
	private static final long serialVersionUID = 1L;

	private final L1InventoryItem _inventoryItem;

	private L1Item _item;

	private int _lastWeight;

	private L1PcInstance _pc;

	private boolean _isRunning = false;

	private EnchantTimer _timer;

	private int _bless;

	@Override
	public int getId() {
		return _inventoryItem.getId();
	}

	@Override
	public void setId(int id) {
		_inventoryItem.setId(id);
	}

	public L1ItemInstance() {
		_inventoryItem = new L1InventoryItem();
		setCount(1);
	}

	public L1ItemInstance(L1Item item, int count) {
		this();
		setItem(item);
		setCount(count);
	}

	public L1ItemInstance(L1InventoryItem item) {
		_inventoryItem = item;
		_item = (ItemTable.getInstance().getTemplate(item.getItemId()));
	}

	/**
	 * アイテムが確認(鑑定)済みであるかを返す。
	 * 
	 * @return 確認済みならtrue、未確認ならfalse。
	 */
	public boolean isIdentified() {
		return _inventoryItem.isIdentified();
	}

	/**
	 * アイテムが確認(鑑定)済みであるかを設定する。
	 * 
	 * @param identified
	 *            確認済みならtrue、未確認ならfalse。
	 */
	public void setIdentified(boolean identified) {
		_inventoryItem.setIdentified(identified);
	}

	/**
	 * @return true アイテムは封印済み<br>
	 *         false アイテムは通常
	 */
	public boolean isSealed() {
		return _inventoryItem.isSealed();
	}

	/**
	 * @param isSealed
	 *            true アイテムは封印済み<br>
	 *            false アイテムは通常
	 */
	public void setSealed(boolean isSealed) {
		_inventoryItem.setSealed(isSealed);
	}

	public String getName() {
		return _item.getName();
	}

	/**
	 * アイテムの個数を返す。
	 * 
	 * @return アイテムの個数
	 */
	public int getCount() {
		return _inventoryItem.getItemCount();
	}

	/**
	 * アイテムの個数を設定する。
	 * 
	 * @param count
	 *            アイテムの個数
	 */
	public void setCount(int count) {
		_inventoryItem.setItemCount(count);
	}

	/**
	 * アイテムが装備されているかを返す。
	 * 
	 * @return アイテムが装備されていればtrue、装備されていなければfalse。
	 */
	public boolean isEquipped() {
		return _inventoryItem.isEquipped();
	}

	/**
	 * アイテムが装備されているかを設定する。
	 * 
	 * @param equipped
	 *            アイテムが装備されていればtrue,装備されていなければfalse。
	 */
	public void setEquipped(boolean equipped) {
		_inventoryItem.setEquipped(equipped);
	}

	public L1Item getItem() {
		return _item;
	}

	public void setItem(L1Item item) {
		_item = item;
		_inventoryItem.setItemId(item.getItemId());
	}

	public int getItemId() {
		return _inventoryItem.getItemId();
	}

	public void setItemId(int itemId) {
		_inventoryItem.setItemId(itemId);
	}

	public boolean isStackable() {
		return _item.isStackable();
	}

	@Override
	public void onAction(L1PcInstance player) {
	}

	public int getEnchantLevel() {
		return _inventoryItem.getEnchantLevel();
	}

	public void setEnchantLevel(int enchantLevel) {
		_inventoryItem.setEnchantLevel(enchantLevel);
	}

	public int getGfxId() {
		return _item.getGfxId();
	}

	public int getDurability() {
		return _inventoryItem.getDurability();
	}

	public int getChargeCount() {
		return _inventoryItem.getChargeCount();
	}

	public void setChargeCount(int chargeCount) {
		_inventoryItem.setChargeCount(chargeCount);
	}

	public int getChargeTime() {
		return _inventoryItem.getChargeTime();
	}

	public void setChargeTime(int chargeTime) {
		_inventoryItem.setChargeTime(chargeTime);
	}

	public Timestamp getExpirationTime() {
		return _inventoryItem.getExpirationTime();
	}

	public void setExpirationTime(Timestamp expirationTime) {
		_inventoryItem.setExpirationTime(expirationTime);
	}

	public void setLastUsed(Timestamp lastUsed) {
		_inventoryItem.setLastUsed(lastUsed);
	}

	public Timestamp getLastUsed() {
		return _inventoryItem.getLastUsed();
	}

	public int getLastWeight() {
		return _lastWeight;
	}

	public void setLastWeight(int weight) {
		_lastWeight = weight;
	}

	public int getStatusForPacket() {
		// 0:祝福 1:通常 2:呪い 3:未鑑定
		// 128:祝福&封印 129:&封印 130:呪い&封印 131:未鑑定&封印
		int bless = 0;
		if (_item.getItemId() <= 100000) {
			bless = 1;
		} else if (_item.getItemId() < 200000) {
			bless = 0;
		} else {
			bless = 2;
		}
		if (!isIdentified()) {
			bless = 3;
		}
		if (isSealed()) {
			bless += 128;
		}
		return bless;
	}

	public void setAttrEnchantKind(int attrEnchantKind) {
		_inventoryItem.setAttrEnchantKind(attrEnchantKind);
	}

	public int getAttrEnchantKind() {
		return _inventoryItem.getAttrEnchantKind();
	}

	public void setAttrEnchantLevel(int attrEnchantLevel) {
		_inventoryItem.setAttrEnchantLevel(attrEnchantLevel);
	}

	public int getAttrEnchantLevel() {
		return _inventoryItem.getAttrEnchantLevel();
	}

	public int getAc() {
		return _inventoryItem.getAc();
	}
	
	public void setAc(int _ac) {
		_inventoryItem.setAc(_ac);
	}
	
	public int getStr() {
		return _inventoryItem.getStr();
	}
	
	public void setStr(int _str) {
		_inventoryItem.setStr(_str);
	}
		
	public int getCon() {
		return _inventoryItem.getCon();
	}
	
	public void setCon(int _con) {
		_inventoryItem.setCon(_con);
	}
		
	public int getDex() {
		return _inventoryItem.getDex();
	}
	
	public void setDex(int _dex) {
		_inventoryItem.setDex(_dex);
	}
	
	public int getWis() {
		return _inventoryItem.getWis();
	}
	
	public void setWis(int _wis) {
		_inventoryItem.setWis(_wis);
	}
		
	public int getCha() {
		return _inventoryItem.getCha();
	}
	
	public void setCha(int _cha) {
		_inventoryItem.setCha(_cha);
	}
		
	public int getInt() {
		return _inventoryItem.getInt();
	}
	
	public void setInt(int _int) {
		_inventoryItem.setInt(_int);
	}
	
	public int getHp() {
		return _inventoryItem.getHp();
	}
	
	public void setHp(int _hp) {
		_inventoryItem.setHp(_hp);
	}
	
	public int getHpr() {
		return _inventoryItem.getHpr();
	}
	
	public void setHpr(int _hpr) {
		_inventoryItem.setHpr(_hpr);
	}
	
	public int getMp() {
		return _inventoryItem.getMp();
	}
	
	public void setMp(int _mp) {
		_inventoryItem.setMp(_mp);
	}
		
	public int getMpr() {
		return _inventoryItem.getMpr();
	}
	
	public void setMpr(int _mpr) {
		_inventoryItem.setMpr(_mpr);
	}
				
	public int getSp() {
		return _inventoryItem.getSp();
	}
	
	public void setSp(int _sp) {
		_inventoryItem.setSp(_sp);
	}
	
	public int getMr() {
		return _inventoryItem.getMr();
	}
	
	public void setMr(int _mr) {
		_inventoryItem.setMr(_mr);
	}
	
	public int getHitModifier() {
		return _inventoryItem.getHitModifier();
	}
	
	public void setHitModifier(int _hitModifier) {
		_inventoryItem.setHitModifier(_hitModifier);
	}
	
	public int getDmgModifier() {
		return _inventoryItem.getDmgModifier();
	}
	
	public void setDmgModifier(int _dmgModifier) {
		_inventoryItem.setDmgModifier(_dmgModifier);
	}
	
	public int getBowHitModifier() {
		return _inventoryItem.getBowHitModifier();
	}
	
	public void setBowHitModifier(int _hitModifier) {
		_inventoryItem.setBowHitModifier(_hitModifier);
	}
	
	public int getBowDmgModifier() {
		return _inventoryItem.getBowDmgModifier();
	}
	
	public void setBowDmgModifier(int _dmgModifier) {
		_inventoryItem.setBowDmgModifier(_dmgModifier);
	}
	
	public int getDefenseEarth() {
		return _inventoryItem.getDefenseEarth();
	}
	
	public void setDefenseEarth(int _defenseEarth) {
		_inventoryItem.setDefenseEarth(_defenseEarth);
	}
	
	public int getDefenseWater() {
		return _inventoryItem.getDefenseWater();
	}
	
	public void setDefenseWater(int _defenseWater) {
		_inventoryItem.setDefenseWater(_defenseWater);
	}
	
	public int getDefenseFire() {
		return _inventoryItem.getDefenseFire();
	}
	
	public void setDefenseFire(int _defenseFire) {
		_inventoryItem.setDefenseFire(_defenseFire);
	}
	
	public int getDefenseWind() {
		return _inventoryItem.getDefenseWind();
	}
	
	public void setDefenseWind(int _defenseWind) {
		_inventoryItem.setDefenseWind(_defenseWind);
	}
	
	public int getResistStun() {
		return _inventoryItem.getResistStun();
	}
	
	public void setResistStun(int _resistStun) {
		_inventoryItem.setResistStun(_resistStun);
	}
	
	public int getResistStone() {
		return _inventoryItem.getResistStone();
	}
	
	public void setResistStone(int _resistStone) {
		_inventoryItem.setResistStone(_resistStone);
	}
	
	public int getResistSleep() {
		return _inventoryItem.getResistSleep();
	}
	
	public void setResistSleep(int _resistSleep) {
		_inventoryItem.setResistSleep(_resistSleep);
	}
	
	public int getResistFreeze() {
		return _inventoryItem.getResistFreeze();
	}
	
	public void setResistFreeze(int _resistFreeze) {
		_inventoryItem.setResistFreeze(_resistFreeze);
	}
	
	public int getResistHold() {
		return _inventoryItem.getResistHold();
	}
	
	public void setResistHold(int _resistHold) {
		_inventoryItem.setResistHold(_resistHold);
	}
	
	public int getResistBlind() {
		return _inventoryItem.getResistBlind();
	}
	
	public void setResistBlind(int _resistBlind) {
		_inventoryItem.setResistBlind(_resistBlind);
	}

	public int getExpBonus() {
		return _inventoryItem.getExpBonus();
	}
	
	public void setExpBonus(int _expBonus) {
		_inventoryItem.setExpBonus(_expBonus);
	}

	public boolean isHaste() {
		return getItem().isHaste() || _inventoryItem.isHaste();
	}
	
	public void setIsHaste(boolean _isHaste) {
		_inventoryItem.setIsHaste(_isHaste);
	}
	
	public boolean getCanBeDmg() {
		if (getItem().getType2() == 1) {
			return getItem().getCanbeDmg() && _inventoryItem.getCanBeDmg();
		} else {
			return _inventoryItem.getCanBeDmg();
		}
	}
	
	public void setCanBeDmg(boolean _canBeDmg) {
		_inventoryItem.setCanBeDmg(_canBeDmg);
	}
	
	public boolean isUnique() {
		return _inventoryItem.isUnique();
	}
	
	public void setIsUnique(boolean _isUnique) {
		_inventoryItem.setIsUniuqe(_isUnique);
	}
	
	public int getPotionRecoveryRate() {
		return _inventoryItem.getPotionRecoveryRate();
	}
	
	public void setPotionRecoveryRate(int _potionRecoveryRate) {
		_inventoryItem.setPotionRecoveryRate(_potionRecoveryRate);
	}
	/*
	 * 耐久性、0~127まで -の値は許可しない。
	 */
	public void setDurability(int durability) {
		_inventoryItem.setDurability(IntRange.ensure(durability, 0, 127));
	}

	public int getWeight() {
		if (getItem().getWeight() == 0) {
			return 0;
		} else {
			return Math.max(getCount() * getItem().getWeight() / 1000, 1);
		}
	}

	/**
	 * 鞄や倉庫で表示される形式の名前を個数を指定して取得する。<br>
	 */
	public String getNumberedViewName(int count) {
		StringBuilder name = new StringBuilder(getNumberedName(count));
		int itemType2 = getItem().getType2();
		int itemId = getItem().getItemId();

		if (itemId == 40314 || itemId == 40316) { // ペットのアミュレット
			L1Pet pet = PetTable.getInstance().getTemplate(getId());
			if (pet != null) {
				L1Npc npc = NpcTable.getInstance().getTemplate(pet.getNpcId());
				// name.append("[Lv." + pet.getLevel() + " "
				// + npc.getNameId() + "]");
				name.append("[Lv." + pet.getLevel() + " " + pet.getName()
						+ "]HP" + pet.getHp() + " " + npc.getNameId());
			}
		}

		if (getExpirationTime() != null) { // 有効期限付きアイテム
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(getExpirationTime().getTime());
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DATE);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int minute = cal.get(Calendar.MINUTE);
			name.append(" [" + month + "-" + day + " " + hour + ":" + minute + "]");
		}
		
		if (getItem().getType2() == 0 && getItem().getType() == 2) { // light系アイテム
			if (isNowLighting()) {
				name.append(" ($10)");
			}
			if (itemId == 40001 || itemId == 40002) { // ランプorランタン
				if (getChargeTime() <= 0) {
					name.append(" ($11)");
				}
			}
		}

		if (isEquipped()) {
			if (itemType2 == 1) {
				name.append(" ($9)"); // 装備(Armed)
			} else if (itemType2 == 2) {
				name.append(" ($117)"); // 装備(Worn)
			} else if (itemType2 == 0 && getItem().getType() == 11) { // petitem
				name.append(" ($117)"); // 装備(Worn)
			}
		}
		return name.toString();
	}

	/**
	 * 鞄や倉庫で表示される形式の名前を返す。<br>
	 * 例:+10 カタナ (装備)
	 */
	public String getViewName() {
		return getNumberedViewName(getCount());
	}

	/**
	 * ログに表示される形式の名前を返す。<br>
	 * 例:アデナ(250) / +6 ダガー
	 */
	public String getLogName() {
		return getNumberedName(getCount());
	}

	/**
	 * ログに表示される形式の名前を、個数を指定して取得する。
	 */
	public String getNumberedName(int count) {
		StringBuilder name = new StringBuilder();

		if (isUnique()) { // ユニークアイテムの接頭詞
			name.append(Config.UNIQUE_PREFIX + " ");
		}
		
		if (isIdentified()) {
			if (getItem().getType2() == 1) { // 武器
				int attrEnchantLevel = getAttrEnchantLevel();
				if (attrEnchantLevel > 0) {
					String attrStr = null;
					switch (getAttrEnchantKind()) {
					case 1: // 地
						if (attrEnchantLevel == 1) {
							attrStr = "$6124";
						} else if (attrEnchantLevel == 2) {
							attrStr = "$6125";
						} else if (attrEnchantLevel == 3) {
							attrStr = "$6126";
						}
						break;
					case 2: // 火
						if (attrEnchantLevel == 1) {
							attrStr = "$6115";
						} else if (attrEnchantLevel == 2) {
							attrStr = "$6116";
						} else if (attrEnchantLevel == 3) {
							attrStr = "$6117";
						}
						break;
					case 4: // 水
						if (attrEnchantLevel == 1) {
							attrStr = "$6118";
						} else if (attrEnchantLevel == 2) {
							attrStr = "$6119";
						} else if (attrEnchantLevel == 3) {
							attrStr = "$6120";
						}
						break;
					case 8: // 風
						if (attrEnchantLevel == 1) {
							attrStr = "$6121";
						} else if (attrEnchantLevel == 2) {
							attrStr = "$6122";
						} else if (attrEnchantLevel == 3) {
							attrStr = "$6123";
						}
						break;
					default:
						break;
					}
					name.append(attrStr + " ");
				}
			}
			if (getItem().getType2() == 1 || getItem().getType2() == 2) { // 武器・防具
				if (getEnchantLevel() >= 0) {
					name.append("+" + getEnchantLevel() + " ");
				} else if (getEnchantLevel() < 0) {
					name.append(String.valueOf(getEnchantLevel()) + " ");
				}
			}
		}
		if (isIdentified()) {
			name.append(_item.getIdentifiedNameId());
		} else {
			name.append(_item.getUnidentifiedNameId());
		}
		if (isIdentified()) {
			if (getItem().getMaxChargeCount() > 0) {
				name.append(" (" + getChargeCount() + ")");
			}
			if (getItem().getItemId() == 20383) { // 騎馬用ヘルム
				name.append(" (" + getChargeCount() + ")");
			}
			if (getItem().getChargeTime() > 0 && getItem().getType2() != 0) { // 武器防具で使用時間制限あり
				name.append(" [" + getChargeTime() + "]");
			}
		}

		if (count > 1) {
			name.append(" (" + count + ")");
		}

		return name.toString();
	}

	// 宿屋のキー名称
	public String getInnKeyName() {
		StringBuilder name = new StringBuilder();
		name.append(" #");
		String chatText = String.valueOf(getKeyId());
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < chatText.length(); i++) {
			if (i >= 5) {
				break;
			}
			s1 = s1 + String.valueOf(chatText.charAt(i));
		}
		name.append(s1);
		for (int i = 0; i < chatText.length(); i++) {
			if ((i % 2) == 0) {
				s1 = String.valueOf(chatText.charAt(i));
			} else {
				s2 = s1 + String.valueOf(chatText.charAt(i));
				name.append(Integer.toHexString(Integer.valueOf(s2))
						.toLowerCase()); // 16進数
			}
		}
		return name.toString();
	}

	/**
	 * アイテムの状態からサーバーパケットで利用する形式のバイト列を生成し、返す。
	 */
	public byte[] getStatusBytes() {
		int itemType2 = getItem().getType2();
		int itemId = getItemId();
		BinaryOutputStream os = new BinaryOutputStream();

		if (itemType2 == 0) { // etcitem
			switch (getItem().getType()) {
			case 2: // light
				os.writeC(22); // 明るさ
				os.writeH(getItem().getLightRange());
				break;
			case 7: // food
				os.writeC(21);
				// 栄養
				os.writeH(getItem().getFoodVolume());
				break;
			case 0: // arrow
			case 15: // sting
				os.writeC(1); // 打撃値
				os.writeC(getItem().getDmgSmall());
				os.writeC(getItem().getDmgLarge());
				break;
			case 11: // 使用可能ペット：[ハイペット]
				os.writeC(7);
				os.writeC(128);
				os.writeC(23); // 材質
				break;
			default:
				os.writeC(23); // 材質
				break;
			}
			os.writeC(getItem().getMaterial());
			os.writeD(getWeight());

		} else if (itemType2 == 1 || itemType2 == 2) { // weapon | armor
			if (itemType2 == 1) { // weapon
				// 打撃値
				os.writeC(1);
				os.writeC(getItem().getDmgSmall());
				os.writeC(getItem().getDmgLarge());
				os.writeC(getItem().getMaterial());
				os.writeD(getWeight());
			} else if (itemType2 == 2) { // armor
				// AC
				os.writeC(19);
				int ac = getItem().getAc() + getAc();
				if (ac < 0) {
					ac = ac - ac - ac;
				}
				os.writeC(ac);
				os.writeC(getItem().getMaterial());
				if (getItem().getType2() == 2
						&& getItem().getType() >= 10
						&& getItem().getType() <= 13) {
					os.writeC(((L1Armor) getItem()).getGrade());
					// 装飾品グレード 　 0:上級 1:中級 2:下級 3:特
				} else {
					os.writeC(-1);
				}
				os.writeD(getWeight());
			}
			// 強化数
			if (getEnchantLevel() != 0) {
				os.writeC(2);
				os.writeC(getEnchantLevel());
			}
			// 損傷度
			if (getDurability() != 0) {
				os.writeC(3);
				os.writeC(getDurability());
			}
			// 両手武器
			if (getItem().isTwohanded()) {
				os.writeC(4);
			}
			// 攻撃成功
			if (itemType2 == 1) { // weapon
				if (getItem().getHitModifier() != 0 || getHitModifier() != 0) {
					os.writeC(5);
					os.writeC(getItem().getHitModifier() + getHitModifier());
				}
			} else if (itemType2 == 2) { // armor
				if (getItem().getHitModifierByArmor() != 0 || getHitModifier() != 0) {
					os.writeC(5);
					os.writeC(getItem().getHitModifierByArmor() + getHitModifier());
				}
			}
			// 追加打撃
			if (itemType2 == 1) { // weapon
				if (getItem().getDmgModifier() != 0 || getDmgModifier() != 0) {
					os.writeC(6);
					os.writeC(getItem().getDmgModifier() + getDmgModifier());
				}
			} else if (itemType2 == 2) { // armor
				if (getItem().getDmgModifierByArmor() != 0 || getDmgModifier() != 0) {
					os.writeC(6);
					os.writeC(getItem().getDmgModifierByArmor() + getDmgModifier());
				}
			}
			// 使用可能
			int bit = 0;
			bit |= getItem().isUseRoyal() ? 1 : 0;
			bit |= getItem().isUseKnight() ? 2 : 0;
			bit |= getItem().isUseElf() ? 4 : 0;
			bit |= getItem().isUseMage() ? 8 : 0;
			bit |= getItem().isUseDarkelf() ? 16 : 0;
			bit |= getItem().isUseDragonknight() ? 32 : 0;
			bit |= getItem().isUseIllusionist() ? 64 : 0;
			// bit |= getItem().isUseHiPet() ? 64 : 0; // ハイペット
			os.writeC(7);
			os.writeC(bit);
			// 弓の命中率補正
			if (getItem().getBowHitModifierByArmor() != 0 || getBowHitModifier() != 0) {
				os.writeC(24);
				os.writeC(getItem().getBowHitModifierByArmor() + getBowHitModifier());
			}
			// 弓のダメージ補正
			if (getItem().getBowDmgModifierByArmor() != 0 || getBowDmgModifier() != 0) {
				os.writeC(35);
				os.writeC(getItem().getBowDmgModifierByArmor() + getBowDmgModifier());
			}
			// MP吸収
			if (itemId == 126 || itemId == 127) { // マナスタッフ、鋼鉄のマナスタッフ
				os.writeC(16);
			}
			// HP吸収
			if (itemId == 262) { // ディストラクション
				os.writeC(34);
			}
			// STR~CHA
			if (getItem().getStr() != 0 || getStr() != 0) {
				os.writeC(8);
				os.writeC(getItem().getStr() + getStr());
			}
			if (getItem().getDex() != 0 || getDex() != 0) {
				os.writeC(9);
				os.writeC(getItem().getDex() + getDex());
			}
			if (getItem().getCon() != 0 || getCon() != 0) {
				os.writeC(10);
				os.writeC(getItem().getCon() + getCon());
			}
			if (getItem().getWis() != 0 || getWis() != 0) {
				os.writeC(11);
				os.writeC(getItem().getWis() + getWis());
			}
			if (getItem().getInt() != 0 || getInt() != 0) {
				os.writeC(12);
				os.writeC(getItem().getInt() + getInt());
			}
			if (getItem().getCha() != 0 || getCha() != 0) {
				os.writeC(13);
				os.writeC(getItem().getCha() + getCha());
			}
			// HP, MP
			if (getItem().getHp() != 0 || getHp() != 0) {
				os.writeC(14);
				os.writeH(getItem().getHp() + getHp());
			}
			if (getItem().getMp() != 0 || getMp() != 0) {
				os.writeC(32);
				os.writeC(getItem().getMp() + getMp());
			}
			// SP(魔力)
			if (getItem().getSp() != 0 || getSp() != 0) {
				os.writeC(17);
				os.writeC(getItem().getSp() + getSp());
			}
			// ヘイスト
			if (isHaste()) {
				os.writeC(18);
			}
			// 火の属性
			if (getItem().getDefenseFire() != 0 || getDefenseFire() != 0) {
				os.writeC(27);
				os.writeC(getItem().getDefenseFire() + getDefenseFire());
			}
			// 水の属性
			if (getItem().getDefenseWater() != 0 || getDefenseWater() != 0) {
				os.writeC(28);
				os.writeC(getItem().getDefenseWater() + getDefenseWater());
			}
			// 風の属性
			if (getItem().getDefenseWind() != 0 || getDefenseWind() != 0) {
				os.writeC(29);
				os.writeC(getItem().getDefenseWind() + getDefenseWind());
			}
			// 地の属性
			if (getItem().getDefenseEarth() != 0 || getDefenseEarth() != 0) {
				os.writeC(30);
				os.writeC(getItem().getDefenseEarth() + getDefenseEarth());
			}
			// 凍結耐性
			if (getItem().getResistFreeze() != 0 || getResistFreeze() != 0) {
				os.writeC(15);
				os.writeH(getItem().getResistFreeze() + getResistFreeze());
				os.writeC(33);
				os.writeC(1);
			}
			// 石化耐性
			if (getItem().getResistStone() != 0 || getResistStone() != 0) {
				os.writeC(15);
				os.writeH(getItem().getResistStone() + getResistStone());
				os.writeC(33);
				os.writeC(2);
			}
			// 睡眠耐性
			if (getItem().getResistSleep() != 0 || getResistSleep() != 0) {
				os.writeC(15);
				os.writeH(getItem().getResistSleep() + getResistSleep());
				os.writeC(33);
				os.writeC(3);
			}
			// 暗闇耐性
			if (getItem().getResistBlind() != 0 || getResistBlind() != 0) {
				os.writeC(15);
				os.writeH(getItem().getResistBlind() + getResistBlind());
				os.writeC(33);
				os.writeC(4);
			}
			// スタン耐性
			if (getItem().getResistStun() != 0 || getResistStun() != 0) {
				os.writeC(15);
				os.writeH(getItem().getResistStun() + getResistStun());
				os.writeC(33);
				os.writeC(5);
			}
			// ホールド耐性
			if (getItem().getResistHold() != 0 || getResistHold() != 0) {
				os.writeC(15);
				os.writeH(getItem().getResistHold() + getResistHold());
				os.writeC(33);
				os.writeC(6);
			}
			// MR
			if (getItem().getMr() != 0 || getMr() != 0) {
				os.writeC(15);
				os.writeH(getItem().getMr() + getMr());
			}
			// 経験値ボーナス
			if (getItem().getExpBonus() != 0 || getExpBonus() != 0) {
				os.writeC(36);
				os.writeC(getItem().getExpBonus() + getExpBonus());
			}
			// HP自然回復
			if (getItem().getHpr() != 0 || getHpr() != 0) {
				os.writeC(37);
				os.writeC(getItem().getHpr() + getHpr());
			}
			// MP自然回復
			if (getItem().getMpr() != 0 || getMpr() != 0) {
				os.writeC(38);
				os.writeC(getItem().getMpr() + getMpr());
			}
			// 幸運
			// if (getItem.getLuck() != 0) {
			// os.writeC(20);
			// os.writeC(val);
			// }
			// 種類
			// if (getItem.getDesc() != 0) {
			// os.writeC(25);
			// os.writeH(val); // desc.tbl ID
			// }
			// レベル
			// if (getItem.getLevel() != 0) {
			// os.writeC(26);
			// os.writeH(val);
			// }
		}
		return os.getBytes();
	}

	class EnchantTimer extends TimerTask {

		public EnchantTimer() {
		}

		@Override
		public void run() {
			try {
				int type = getItem().getType();
				int type2 = getItem().getType2();
				int itemId = getItem().getItemId();
				if (_pc != null && _pc.getInventory().checkItem(itemId)) {
					if (type == 2 && type2 == 2 && isEquipped()) {
						_pc.addAc(3);
						_pc.sendPackets(new S_OwnCharStatus(_pc));
					}
				}
				setAcByMagic(0);
				setDmgByMagic(0);
				setHolyDmgByMagic(0);
				setHitByMagic(0);
				_pc.sendPackets(new S_ServerMessage(308, getLogName()));
				_isRunning = false;
				_timer = null;
			} catch (Exception e) {
			}
		}
	}

	private int _acByMagic = 0;

	public int getAcByMagic() {
		return _acByMagic;
	}

	public void setAcByMagic(int i) {
		_acByMagic = i;
	}

	private int _dmgByMagic = 0;

	public int getDmgByMagic() {
		return _dmgByMagic;
	}

	public void setDmgByMagic(int i) {
		_dmgByMagic = i;
	}

	private int _holyDmgByMagic = 0;

	public int getHolyDmgByMagic() {
		return _holyDmgByMagic;
	}

	public void setHolyDmgByMagic(int i) {
		_holyDmgByMagic = i;
	}

	private int _hitByMagic = 0;

	public int getHitByMagic() {
		return _hitByMagic;
	}

	public void setHitByMagic(int i) {
		_hitByMagic = i;
	}

	public void setSkillArmorEnchant(L1PcInstance pc, int skillId, int skillTime) {
		int type = getItem().getType();
		int type2 = getItem().getType2();
		if (_isRunning) {
			_timer.cancel();
			int itemId = getItem().getItemId();
			if (pc != null && pc.getInventory().checkItem(itemId)) {
				if (type == 2 && type2 == 2 && isEquipped()) {
					pc.addAc(3);
					pc.sendPackets(new S_OwnCharStatus(pc));
				}
			}
			setAcByMagic(0);
			_isRunning = false;
			_timer = null;
		}

		if (type == 2 && type2 == 2 && isEquipped()) {
			pc.addAc(-3);
			pc.sendPackets(new S_OwnCharStatus(pc));
		}
		setAcByMagic(3);
		_pc = pc;
		_timer = new EnchantTimer();
		(new Timer()).schedule(_timer, skillTime);
		_isRunning = true;
	}

	public void setSkillWeaponEnchant(L1PcInstance pc, int skillId,
			int skillTime) {
		if (getItem().getType2() != 1) {
			return;
		}
		if (_isRunning) {
			_timer.cancel();
			setDmgByMagic(0);
			setHolyDmgByMagic(0);
			setHitByMagic(0);
			_isRunning = false;
			_timer = null;
		}

		switch (skillId) {
		case HOLY_WEAPON:
			setHolyDmgByMagic(1);
			setHitByMagic(1);
			break;

		case ENCHANT_WEAPON:
			setDmgByMagic(2);
			break;

		case BLESS_WEAPON:
			setDmgByMagic(2);
			setHitByMagic(2);
			break;

		case SHADOW_FANG:
			setDmgByMagic(5);
			break;

		default:
			break;
		}

		_pc = pc;
		_timer = new EnchantTimer();
		(new Timer()).schedule(_timer, skillTime);
		_isRunning = true;
	}

	public void setUniqueOptions(double uniqueRate) {
		if (getItem().getType2() != 1 && getItem().getType2() != 2) { // 武器・防具以外
			return;
		}

		ArrayList<Integer> options = new ArrayList<Integer>() {
			{
				add(OPT_STR); add(OPT_CON); add(OPT_DEX); add(OPT_WIS);
				add(OPT_INT); add(OPT_CHA); add(OPT_HP); add(OPT_HPR);
				add(OPT_MP); add(OPT_MPR); add(OPT_SP); add(OPT_MR);
				add(OPT_DEF_EARTH); add(OPT_DEF_WATER); add(OPT_DEF_FIRE);
				add(OPT_DEF_WIND); add(OPT_RES_STUN); add(OPT_RES_STONE);
				add(OPT_RES_SLEEP); add(OPT_RES_FREEZE); add(OPT_RES_HOLD);
				add(OPT_RES_BLIND); add(OPT_EXP_BONUS); add(OPT_HASTE);
				add(OPT_HIT_MOD); add(OPT_DMG_MOD);
			}
		};
		
		if (getItem().getType2() == 1) { // 武器
			options.add(OPT_CAN_DMG);
		} else { // 防具
			options.add(OPT_AC);
			options.add(OPT_BOW_HIT_MOD);
			options.add(OPT_BOW_DMG_MOD);
		}

		boolean isUnique = false;
		for(int i = 0; i < Config.UNIQUE_MAX_OPTIONS; i++) {
			int res = 0;
			int j = random.nextInt(options.size());
			int option = options.get(j);
			options.remove(j);
			
			if (option == OPT_STR) {
				res = calcUniqueOption(Config.UNIQUE_MAX_STR, uniqueRate);
				if (res > 0) {
					setStr(res);
					isUnique = true;
				}
			} else if (option == OPT_CON) {
				res = calcUniqueOption(Config.UNIQUE_MAX_CON, uniqueRate);
				if (res > 0) {
					setCon(res);
					isUnique = true;
				}
			} else if (option == OPT_DEX) {
				res = calcUniqueOption(Config.UNIQUE_MAX_DEX, uniqueRate);
				if (res > 0) {
					setDex(res);
					isUnique = true;
				}
			} else if (option == OPT_WIS) {
				res = calcUniqueOption(Config.UNIQUE_MAX_WIS, uniqueRate);
				if (res > 0) {
					setWis(res);
					isUnique = true;
				}
			} else if (option == OPT_INT) {
				res = calcUniqueOption(Config.UNIQUE_MAX_INT, uniqueRate);
				if (res > 0) {
					setInt(res);
					isUnique = true;
				}
			} else if (option == OPT_CHA) {
				res = calcUniqueOption(Config.UNIQUE_MAX_CHA, uniqueRate);
				if (res > 0) {
					setCha(res);
					isUnique = true;
				}
			} else if (option == OPT_HP) {
				res = calcUniqueOption(Config.UNIQUE_MAX_HP, uniqueRate);
				if (res > 0) {
					setHp(res);
					isUnique = true;
				}
			} else if (option == OPT_HPR) {
				res = calcUniqueOption(Config.UNIQUE_MAX_HPR, uniqueRate);
				if (res > 0) {
					setHpr(res);
					isUnique = true;
				}
			} else if (option == OPT_MP) {
				res = calcUniqueOption(Config.UNIQUE_MAX_MP, uniqueRate);
				if (res > 0) {
					setMp(res);
					isUnique = true;
				}
			} else if (option == OPT_MPR) {
				res = calcUniqueOption(Config.UNIQUE_MAX_MPR, uniqueRate);
				if (res > 0) {
					setMpr(res);
					isUnique = true;
				}
			} else if (option == OPT_SP) {
				res = calcUniqueOption(Config.UNIQUE_MAX_SP, uniqueRate);
				if (res > 0) {
					setSp(res);
					isUnique = true;
				}
			} else if (option == OPT_MR) {
				res = calcUniqueOption(Config.UNIQUE_MAX_MR, uniqueRate);
				if (res > 0) {
					setMr(res);
					isUnique = true;
				}
			} else if (option == OPT_DEF_EARTH) {
				res = calcUniqueOption(Config.UNIQUE_MAX_DEFENSE_EARTH, uniqueRate);
				if (res > 0) {
					setDefenseEarth(res);
					isUnique = true;
				}
			} else if (option == OPT_DEF_WATER) {
				res = calcUniqueOption(Config.UNIQUE_MAX_DEFENSE_WATER, uniqueRate);
				if (res > 0) {
					setDefenseWater(res);
					isUnique = true;
				}
			} else if (option == OPT_DEF_FIRE) {
				res = calcUniqueOption(Config.UNIQUE_MAX_DEFENSE_FIRE, uniqueRate);
				if (res > 0) {
					setDefenseFire(res);
					isUnique = true;
				}
			} else if (option == OPT_DEF_WIND) {
				res = calcUniqueOption(Config.UNIQUE_MAX_DEFENSE_WIND, uniqueRate);
				if (res > 0) {
					setDefenseWind(res);
					isUnique = true;
				}
			} else if (option == OPT_RES_STUN) {
				res = calcUniqueOption(Config.UNIQUE_MAX_RESIST_STUN, uniqueRate);
				if (res > 0) {
					setResistStun(res);
					isUnique = true;
				}
			} else if (option == OPT_RES_STONE) {
				res = calcUniqueOption(Config.UNIQUE_MAX_RESIST_STONE, uniqueRate);
				if (res > 0) {
					setResistStone(res);
					isUnique = true;
				}
			} else if (option == OPT_RES_SLEEP) {
				res = calcUniqueOption(Config.UNIQUE_MAX_RESIST_SLEEP, uniqueRate);
				if (res > 0) {
					setResistSleep(res);
					isUnique = true;
				}
			} else if (option == OPT_RES_FREEZE) {	
				res = calcUniqueOption(Config.UNIQUE_MAX_RESIST_FREEZE, uniqueRate);
				if (res > 0) {
					setResistFreeze(res);
					isUnique = true;
				}
			} else if (option == OPT_RES_HOLD) {
				res = calcUniqueOption(Config.UNIQUE_MAX_RESIST_HOLD, uniqueRate);
				if (res > 0) {
					setResistHold(res);
					isUnique = true;
				}
			} else if (option == OPT_RES_BLIND) {
				res = calcUniqueOption(Config.UNIQUE_MAX_RESIST_BLIND, uniqueRate);
				if (res > 0) {
					setResistBlind(res);
					isUnique = true;
				}
			} else if (option == OPT_EXP_BONUS) {
				res = calcUniqueOption(Config.UNIQUE_MAX_EXP_BONUS, uniqueRate);
				if (res > 0) {
					setExpBonus(res);
					isUnique = true;
				}
			} else if (option == OPT_HASTE) {
				if (Config.UNIQUE_HASTE) {
					res = calcUniqueOption(1, uniqueRate);
				} else {
					res = 0;
				}
				if (res > 0) {
					setIsHaste(true);
					if (getItem().isHaste() == false) {
						isUnique = true;
					}
				} else {
					setIsHaste(getItem().isHaste());
				}
			} else if (option == OPT_HIT_MOD) {
				res = calcUniqueOption(Config.UNIQUE_MAX_HIT_MODIFIER, uniqueRate);
				if (res > 0) {
					setHitModifier(res);
					isUnique = true;
				}
			} else if (option == OPT_DMG_MOD) {
				res = calcUniqueOption(Config.UNIQUE_MAX_DMG_MODIFIER, uniqueRate);
				if (res > 0) {
					setDmgModifier(res);
					isUnique = true;
				}
			} else if (option == OPT_BOW_HIT_MOD) {
				res = calcUniqueOption(Config.UNIQUE_MAX_BOW_HIT_MODIFIER, uniqueRate);
				if (res > 0) {
					setBowHitModifier(res);
					isUnique = true;
				}
			} else if (option == OPT_BOW_DMG_MOD) {
				res = calcUniqueOption(Config.UNIQUE_MAX_BOW_DMG_MODIFIER, uniqueRate);
				if (res > 0) {
					setBowDmgModifier(res);
					isUnique = true;
				}
			} else if (option == OPT_CAN_DMG) {
				if (Config.UNIQUE_CAN_DMG) {
					res = calcUniqueOption(1, uniqueRate);
				} else {
					res = 1;
				}
				if (res > 0) {
					setCanBeDmg(getItem().getCanbeDmg());
				} else {
					setCanBeDmg(false);
					if (getItem().getCanbeDmg() == true) {
						isUnique = true;
					}
				}
			} else if (option == OPT_AC) {
				res = calcUniqueOption(Config.UNIQUE_MAX_AC, uniqueRate);
				if (res > 0) {
					setAc(res);
					isUnique = true;
				}
			}
		}
		setIsUnique(isUnique);
	}

	private int calcUniqueOption(int n, double ratePct) {
		int chance = random.nextInt(100) + 1 ;
		
		if (ratePct >= (double) chance) {
			return random.nextInt(n);
		} else {
			return 0;
		}
	}
	
	private int _itemOwnerId = 0;

	public int getItemOwnerId() {
		return _itemOwnerId;
	}

	public void setItemOwnerId(int i) {
		_itemOwnerId = i;
	}

	public void startItemOwnerTimer(L1PcInstance pc) {
		setItemOwnerId(pc.getId());
		L1ItemOwnerTimer timer = new L1ItemOwnerTimer(this, 10000);
		timer.begin();
	}

	public boolean isChargeDoll() { // 課金マジックドールの判定
		int[] dollId = new int[] {
			49320, 49321, 49322, 49323, 49324, 49325, // ジャイアント
			49326, 49327, 49328, 49329, 49330, 49331, // サイクロプス
			49332, 49333, 49334, 49335, 49336, 49337, // マーメイド
			49338, 49339, 49340, 49341, 49342, 49343, // ブルート
			49365, 49366, 49367, 49368, 49369, 49370, // ペンギン(兄)
			49371, 49372, 49373, 49374, 49375, 49376  // ペンギン(妹)
		};

		return Arrays.binarySearch(dollId, getItemId()) < 0 ? false : true;
	}
	
	private L1ChargeTimer _chargeTimer;

	public void startChargeTimer(L1PcInstance pc) {
		if (getChargeTime() > 0) {
			_chargeTimer = new L1ChargeTimer(pc, this);
			Timer timer = new Timer(true);
			timer.scheduleAtFixedRate(_chargeTimer, 0, 1000);
		}
	}

	public void stopChargeTimer() {
		if (_chargeTimer != null && getChargeTime() > 0) {
			_chargeTimer.cancel();
			_chargeTimer = null;
		}
	}
	
	private L1ExpirationTimer _expirationTimer;

	public void startExpirationTimer(L1PcInstance pc) {
		if (getExpirationTime() != null) {
			_expirationTimer = new L1ExpirationTimer(pc, this);
			Timer timer = new Timer(true);
			timer.scheduleAtFixedRate(_expirationTimer, 0, 60000);
		}
	}

	public void stopExpirationTimer(L1PcInstance pc) {
		if (getExpirationTime() != null) {
			_expirationTimer.cancel();
			_expirationTimer = null;
		}
	}

	private boolean _isNowLighting = false;

	public boolean isNowLighting() {
		return _isNowLighting;
	}

	public void setNowLighting(boolean flag) {
		_isNowLighting = flag;
	}

	// 宿屋関連
	private int _keyId = 0;

	public int getKeyId() {
		return _keyId;
	}

	public void setKeyId(int i) {
		_keyId = i;
	}

	private int _innNpcId = 0;

	public int getInnNpcId() {
		return _innNpcId;
	}

	public void setInnNpcId(int i) {
		_innNpcId = i;
	}

	private boolean _isHall;

	public boolean checkRoomOrHall() {
		return _isHall;
	}

	public void setHall(boolean i) {
		_isHall = i;
	}

	private Timestamp _dueTime;

	public Timestamp getDueTime() {
		return _dueTime;
	}

	public void setDueTime(Timestamp i) {
		_dueTime = i;
	}

	public void save() {
		_inventoryItem.save();
	}

	public void save(Connection con) {
		_inventoryItem.save(con);
	}

	public void delete() {
		onDelete();
		_inventoryItem.delete();
	}

	public void delete(Connection con) {
		onDelete();
		_inventoryItem.delete(con);
	}

	public void setOwner(int ownerId, int location) {
		_inventoryItem.setOwnerId(ownerId);
		_inventoryItem.setLocation(location);
	}

	public int getOwnerId() {
		return _inventoryItem.getOwnerId();
	}

	public int getOwnerLocation() {
		return _inventoryItem.getLocation();
	}

	private void onDelete() {
		int itemId = getItem().getItemId();
		if (itemId == 40314 || itemId == 40316) { // ペットのアミュレット
			PetTable.getInstance().deletePet(getId());
		} else if (itemId >= 49016 && itemId <= 49025) { // 便箋
			LetterTable lettertable = new LetterTable();
			lettertable.deleteLetter(getId());
		} else if (itemId >= 41383 && itemId <= 41400) { // 家具
			for (L1Object l1object : L1World.getInstance().getObject()) {
				if (l1object instanceof L1FurnitureInstance) {
					L1FurnitureInstance furniture = (L1FurnitureInstance) l1object;
					if (furniture.getItemObjId() == getId()) { // 既に引き出している家具
						FurnitureSpawnTable.getInstance().deleteFurniture(
								furniture);
					}
				}
			}
		} else if (getItemId() == 40309) {// レースチケット
			RaceTicketTable.getInstance().deleteTicket(getId());
		} else if (getItem().getItemId() == 40312) {// 宿屋のキー記録
			InnKeyTable.deleteKey(this);
		}
	}
}