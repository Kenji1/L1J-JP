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
package jp.l1j.server.model;

import static jp.l1j.server.model.skill.L1SkillId.*;
import jp.l1j.server.codes.ActionCodes;
import jp.l1j.server.datatables.SkillTable;
import jp.l1j.server.datatables.WeaponSkillTable;
import jp.l1j.server.model.Instance.L1ItemInstance;
import jp.l1j.server.model.Instance.L1MonsterInstance;
import jp.l1j.server.model.Instance.L1NpcInstance;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.Instance.L1PetInstance;
import jp.l1j.server.model.Instance.L1SummonInstance;
import jp.l1j.server.model.skill.L1SkillUse;
import jp.l1j.server.random.RandomGenerator;
import jp.l1j.server.random.RandomGeneratorFactory;
import jp.l1j.server.packets.server.S_DoActionGFX;
import jp.l1j.server.packets.server.S_EffectLocation;
import jp.l1j.server.packets.server.S_Paralysis;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.packets.server.S_SkillSound;
import jp.l1j.server.packets.server.S_UseArrowSkill;
import jp.l1j.server.packets.server.S_UseAttackSkill;
import jp.l1j.server.templates.L1Skill;

// Referenced classes of package jp.l1j.server.model:
// L1PcInstance

public class L1WeaponSkill {
	private static RandomGenerator _random = RandomGeneratorFactory.newRandom();

	private int _weaponId;

	private int _probability;

	private int _fixDamage;

	private int _randomDamage;

	private int _area;

	private int _skillId;

	private int _skillTime;

	private int _effectId;

	private int _effectTarget; // エフェクトの対象 0:相手 1:自分

	private boolean _isArrowType;

	private int _attr;

	public L1WeaponSkill(int weaponId, int probability, int fixDamage,
			int randomDamage, int area, int skillId, int skillTime,
			int effectId, int effectTarget, boolean isArrowType, int attr) {
		_weaponId = weaponId;
		_probability = probability;
		_fixDamage = fixDamage;
		_randomDamage = randomDamage;
		_area = area;
		_skillId = skillId;
		_skillTime = skillTime;
		_effectId = effectId;
		_effectTarget = effectTarget;
		_isArrowType = isArrowType;
		_attr = attr;
	}

	public int getWeaponId() {
		return _weaponId;
	}

	public int getProbability() {
		return _probability;
	}

	public int getFixDamage() {
		return _fixDamage;
	}

	public int getRandomDamage() {
		return _randomDamage;
	}

	public int getArea() {
		return _area;
	}

	public int getSkillId() {
		return _skillId;
	}

	public int getSkillTime() {
		return _skillTime;
	}

	public int getEffectId() {
		return _effectId;
	}

	public int getEffectTarget() {
		return _effectTarget;
	}

	public boolean isArrowType() {
		return _isArrowType;
	}

	public int getAttr() {
		return _attr;
	}

	public static double getWeaponSkillDamage(L1PcInstance pc, L1Character cha,
			int weaponId) {
		L1WeaponSkill weaponSkill = WeaponSkillTable.getInstance().getTemplate(
				weaponId);
		if (pc == null || cha == null || weaponSkill == null) {
			return 0;
		}

		int chance = _random.nextInt(100) + 1;
		if (weaponSkill.getProbability() < chance) {
			return 0;
		}

		int skillId = weaponSkill.getSkillId();
		if (skillId != 0) {
			L1Skill skill = SkillTable.getInstance().findBySkillId(skillId);
			if (skill != null && skill.getTarget().equals("buff")) {
				if (!isFreeze(cha)) { // 凍結状態orカウンターマジック中
					cha.setSkillEffect(skillId,
							weaponSkill.getSkillTime() * 1000);
				}
			}
		}

		int effectId = weaponSkill.getEffectId();
		if (effectId != 0) {
			int chaId = 0;
			if (weaponSkill.getEffectTarget() == 0) {
				chaId = cha.getId();
			} else {
				chaId = pc.getId();
			}
			boolean isArrowType = weaponSkill.isArrowType();
			if (!isArrowType) {
				pc.sendPackets(new S_SkillSound(chaId, effectId));
				pc.broadcastPacket(new S_SkillSound(chaId, effectId));
			} else {
				S_UseAttackSkill packet = new S_UseAttackSkill(pc, cha.getId(),
						effectId, cha.getX(), cha.getY(),
						ActionCodes.ACTION_Attack, false);
				pc.sendPackets(packet);
				pc.broadcastPacket(packet);
			}
		}

		double damage = 0;
		int randomDamage = weaponSkill.getRandomDamage();
		if (randomDamage != 0) {
			damage = _random.nextInt(randomDamage);
		}
		damage += weaponSkill.getFixDamage();

		int area = weaponSkill.getArea();
		if (area > 0 || area == -1) { // 範囲の場合
			for (L1Object object : L1World.getInstance().getVisibleObjects(cha,
					area)) {
				if (object == null) {
					continue;
				}
				if (!(object instanceof L1Character)) {
					continue;
				}
				if (object.getId() == pc.getId()) {
					continue;
				}
				if (object.getId() == cha.getId()) { // 攻撃対象はL1Attackで処理するため除外
					continue;
				}

				// 攻撃対象がMOBの場合は、範囲内のMOBにのみ当たる
				// 攻撃対象がPC,Summon,Petの場合は、範囲内のPC,Summon,Pet,MOBに当たる
				if (cha instanceof L1MonsterInstance) {
					if (!(object instanceof L1MonsterInstance)) {
						continue;
					}
				}
				if (cha instanceof L1PcInstance
						|| cha instanceof L1SummonInstance
						|| cha instanceof L1PetInstance) {
					if (!(object instanceof L1PcInstance
							|| object instanceof L1SummonInstance
							|| object instanceof L1PetInstance || object instanceof L1MonsterInstance)) {
						continue;
					}
				}

				if (object instanceof L1PcInstance) { // セーフティゾーンの場合は除外
					if (((L1Character) cha).getZoneType() == 1) {
						continue;
					}
				}

				damage = calcDamageReduction(pc, (L1Character) object, damage,
						weaponSkill.getAttr());
				if (damage <= 0) {
					continue;
				}
				if (object instanceof L1PcInstance) {
					L1PcInstance targetPc = (L1PcInstance) object;
					targetPc.sendPackets(new S_DoActionGFX(targetPc.getId(),
							ActionCodes.ACTION_Damage));
					targetPc.broadcastPacket(new S_DoActionGFX(
							targetPc.getId(), ActionCodes.ACTION_Damage));
					targetPc.receiveDamage(pc, (int) damage, false);
				} else if (object instanceof L1SummonInstance
						|| object instanceof L1PetInstance
						|| object instanceof L1MonsterInstance) {
					L1NpcInstance targetNpc = (L1NpcInstance) object;
					targetNpc.broadcastPacket(new S_DoActionGFX(targetNpc
							.getId(), ActionCodes.ACTION_Damage));
					targetNpc.receiveDamage(pc, (int) damage);
				}
			}
		}

		return calcDamageReduction(pc, cha, damage, weaponSkill.getAttr());
	}

	public static double getBaphometStaffDamage(L1PcInstance pc, L1Character cha) {
		double dmg = 0;
		int chance = _random.nextInt(100) + 1;
		if (14 >= chance) {
			int locx = cha.getX();
			int locy = cha.getY();
			int sp = pc.getSp();
			int intel = pc.getInt();
			double bsk = 0;
			if (pc.hasSkillEffect(BERSERKERS)) {
				bsk = 0.2;
			}
			dmg = (intel + sp) * (1.8 + bsk) + _random.nextInt(intel + sp)
					* 1.8;
			S_EffectLocation packet = new S_EffectLocation(locx, locy, 129);
			pc.sendPackets(packet);
			pc.broadcastPacket(packet);
		}
		return calcDamageReduction(pc, cha, dmg, L1Skill.ATTR_EARTH);
	}

	public static double getDiceDaggerDamage(L1PcInstance pc,
			L1PcInstance targetPc, L1ItemInstance weapon) {
		double dmg = 0;
		int chance = _random.nextInt(100) + 1;
		if (3 >= chance) {
			dmg = targetPc.getCurrentHp() * 2 / 3;
			if (targetPc.getCurrentHp() - dmg < 0) {
				dmg = 0;
			}
			String msg = weapon.getLogName();
			pc.sendPackets(new S_ServerMessage(158, msg));
			// \f1%0が蒸発してなくなりました。
			pc.getInventory().removeItem(weapon, 1);
		}
		return dmg;
	}

	public static double getKiringkuDamage(L1PcInstance pc, L1Character cha) {
		int dmg = 0;
		int dice = 5;
		int diceCount = 2;
		int value = 0;
		int kiringkuDamage = 0;
		int charaIntelligence = 0;
		int getTargetMr = 0;
		if (pc.getWeapon().getItem().getItemId() == 270) {
			value = 16;
		} else {
			value = 14;
		}

		for (int i = 0; i < diceCount; i++) {
			kiringkuDamage += (_random.nextInt(dice) + 1);
		}
		kiringkuDamage += value;

		int weaponAddDmg = 0; // 武器による追加ダメージ
		L1ItemInstance weapon = pc.getWeapon();
		if (weapon != null) {
			weaponAddDmg = weapon.getItem().getMagicDmgModifier();
		}
		kiringkuDamage += weaponAddDmg;

		int spByItem = pc.getSp() - pc.getTrueSp(); // アイテムによるSP変動
		charaIntelligence = pc.getInt() + spByItem - 12;
		if (charaIntelligence < 1) {
			charaIntelligence = 1;
		}
		double kiringkuCoefficientA = (1.0 + charaIntelligence * 3.0 / 32.0);

		kiringkuDamage *= kiringkuCoefficientA;

		if (cha instanceof L1PcInstance) { // 連続魔法ダメージ軽減
			L1PcInstance _targetPc = (L1PcInstance) cha;
			long nowTime = System.currentTimeMillis();
			long oldTime = _targetPc.getOldTime();

			if (oldTime != 0) {
				long interval = nowTime - oldTime;
				int index = _targetPc.getNumberOfDamaged() - 1;

				if (2000 > interval) {
					double coefficient_r = 2.0 / 3.0;
					if (index == 0) {
						_targetPc.setOldTime(nowTime);
					}
					if (index > 8) {
						index = 8;
					}
					double coefficient_R = Math.pow(coefficient_r, index);

					kiringkuDamage *= coefficient_R;
					if (index < 8) {
						_targetPc.addNumberOfDamaged(1);
					}
				} else {
					if (4000 > interval && index > 0) {
						_targetPc.setNumberOfDamaged(2);
						_targetPc.setOldTime(oldTime + 2000);
					} else {
						_targetPc.setNumberOfDamaged(1);
						_targetPc.setOldTime(nowTime);
					}
				}
			} else {
				_targetPc.addNumberOfDamaged(1);
				_targetPc.setOldTime(nowTime);
			}
		}

		double kiringkuFloor = Math.floor(kiringkuDamage);

		dmg += kiringkuFloor + pc.getWeapon().getEnchantLevel()
				+ pc.getOriginalMagicDamage();

		if (pc.hasSkillEffect(ILLUSION_AVATAR)) {
			dmg += 10;
		}

		if (pc.getWeapon().getItem().getItemId() == 270) {
			pc.sendPackets(new S_SkillSound(pc.getId(), 6983));
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 6983));
		} else {
			pc.sendPackets(new S_SkillSound(pc.getId(), 7049));
			pc.broadcastPacket(new S_SkillSound(pc.getId(), 7049));
		}

		return calcDamageReduction(pc, cha, dmg, 0);
	}

	public static double getAreaSkillWeaponDamage(L1PcInstance pc,
			L1Character cha, int weaponId) {
		double dmg = 0;
		int probability = 0;
		int attr = 0;
		int chance = _random.nextInt(100) + 1;
		if (weaponId == 263) { // フリージングランサー
			probability = 5;
			attr = L1Skill.ATTR_WATER;
		} else if (weaponId == 260) { // レイジングウィンド
			probability = 4;
			attr = L1Skill.ATTR_WIND;
		}
		if (probability >= chance) {
			int sp = pc.getSp();
			int intel = pc.getInt();
			int area = 0;
			int effectTargetId = 0;
			int effectId = 0;
			L1Character areaBase = cha;
			double damageRate = 0;

			if (weaponId == 263) { // フリージングランサー
				area = 3;
				damageRate = 1.4D;
				effectTargetId = cha.getId();
				effectId = 1804;
				areaBase = cha;
			} else if (weaponId == 260) { // レイジングウィンド
				area = 4;
				damageRate = 1.5D;
				effectTargetId = pc.getId();
				effectId = 758;
				areaBase = pc;
			}
			double bsk = 0;
			if (pc.hasSkillEffect(BERSERKERS)) {
				bsk = 0.2;
			}
			dmg = (intel + sp) * (damageRate + bsk)
					+ _random.nextInt(intel + sp) * damageRate;
			pc.sendPackets(new S_SkillSound(effectTargetId, effectId));
			pc.broadcastPacket(new S_SkillSound(effectTargetId, effectId));

			for (L1Object object : L1World.getInstance().getVisibleObjects(
					areaBase, area)) {
				if (object == null) {
					continue;
				}
				if (!(object instanceof L1Character)) {
					continue;
				}
				if (object.getId() == pc.getId()) {
					continue;
				}
				if (object.getId() == cha.getId()) { // 攻撃対象は除外
					continue;
				}

				// 攻撃対象がMOBの場合は、範囲内のMOBにのみ当たる
				// 攻撃対象がPC,Summon,Petの場合は、範囲内のPC,Summon,Pet,MOBに当たる
				if (cha instanceof L1MonsterInstance) {
					if (!(object instanceof L1MonsterInstance)) {
						continue;
					}
				}
				if (cha instanceof L1PcInstance
						|| cha instanceof L1SummonInstance
						|| cha instanceof L1PetInstance) {
					if (!(object instanceof L1PcInstance
							|| object instanceof L1SummonInstance
							|| object instanceof L1PetInstance || object instanceof L1MonsterInstance)) {
						continue;
					}
				}

				if (object instanceof L1PcInstance) { // セーフティゾーンの場合は除外
					if (((L1Character) cha).getZoneType() == 1) {
						continue;
					}
				}

				dmg = calcDamageReduction(pc, (L1Character) object, dmg, attr);
				if (dmg <= 0) {
					continue;
				}
				if (object instanceof L1PcInstance) {
					L1PcInstance targetPc = (L1PcInstance) object;
					targetPc.sendPackets(new S_DoActionGFX(targetPc.getId(),
							ActionCodes.ACTION_Damage));
					targetPc.broadcastPacket(new S_DoActionGFX(
							targetPc.getId(), ActionCodes.ACTION_Damage));
					targetPc.receiveDamage(pc, (int) dmg, false);
				} else if (object instanceof L1SummonInstance
						|| object instanceof L1PetInstance
						|| object instanceof L1MonsterInstance) {
					L1NpcInstance targetNpc = (L1NpcInstance) object;
					targetNpc.broadcastPacket(new S_DoActionGFX(targetNpc
							.getId(), ActionCodes.ACTION_Damage));
					targetNpc.receiveDamage(pc, (int) dmg);
				}
			}
		}
		return calcDamageReduction(pc, cha, dmg, attr);
	}

	public static double getLightningEdgeDamage(L1PcInstance pc, L1Character cha) {
		double dmg = 0;
		int chance = _random.nextInt(100) + 1;
		if (4 >= chance) {
			int sp = pc.getSp();
			int intel = pc.getInt();
			double bsk = 0;
			if (pc.hasSkillEffect(BERSERKERS)) {
				bsk = 0.2;
			}
			dmg = (intel + sp) * (2 + bsk) + _random.nextInt(intel + sp) * 2;

			pc.sendPackets(new S_SkillSound(cha.getId(), 10));
			pc.broadcastPacket(new S_SkillSound(cha.getId(), 10));
		}
		return calcDamageReduction(pc, cha, dmg, L1Skill.ATTR_WIND);
	}

	public static void giveArkMageDiseaseEffect(L1PcInstance pc, L1Character cha) {
		int chance = _random.nextInt(1000) + 1;
		int probability = (5 - ((cha.getMr() / 10) * 5)) * 10;
		if (probability == 0) {
			probability = 10;
		}
		if (probability >= chance) {
			L1SkillUse l1skilluse = new L1SkillUse();
			l1skilluse.handleCommands(pc, 56, cha.getId(), cha.getX(), cha
					.getY(), null, 0, L1SkillUse.TYPE_GMBUFF);
		}
	}

	public static void getMaliceWeaponDamage(L1PcInstance pc, L1Character cha,
			L1ItemInstance weapon) {
		int chance = weapon.getEnchantLevel() + 1; // 発動確率は1+強化数%

		if (_random.nextInt(100) + 1 <= chance) {
			if (weapon.getItemId() == 276) { // マリス エレメント キーリンク
				L1Skill l1skills = SkillTable.getInstance().findBySkillId(
						MIND_BREAK); // マインドブレイク
				L1Magic magic = new L1Magic(pc, cha);

				pc.sendPackets(new S_SkillSound(cha.getId(), l1skills
						.getCastGfx()));
				pc.broadcastPacket(new S_SkillSound(cha.getId(), l1skills
						.getCastGfx()));

				int damage = magic.calcMagicDamage(l1skills.getSkillId());

				if (cha instanceof L1PcInstance) {
					L1PcInstance targetPc = (L1PcInstance) cha;
					targetPc.receiveDamage(pc, damage, false);
				} else if (cha instanceof L1NpcInstance) {
					L1NpcInstance targetNpc = (L1NpcInstance) cha;
					targetNpc.receiveDamage(pc, damage);
				}

				// MPを5減少させる
				if (cha.getCurrentMp() >= 5) {
					cha.setCurrentMp(cha.getCurrentMp() - 5);
				} else {
					cha.setCurrentMp(0);
				}
			} else if (weapon.getItemId() == 277) { // マリス エレメント チェーンソード
				L1Skill l1skills = SkillTable.getInstance().findBySkillId(
						THUNDER_GRAB); // サンダーグラップ
				L1Magic magic = new L1Magic(pc, cha);

				pc.sendPackets(new S_SkillSound(cha.getId(), l1skills
						.getCastGfx()));
				pc.broadcastPacket(new S_SkillSound(cha.getId(), l1skills
						.getCastGfx()));

				int time = l1skills.getBuffDuration() * 1000;
				int damage = magic.calcMagicDamage(l1skills.getSkillId());

				if(!(cha.hasSkillEffect(STATUS_HOLD))){
					L1EffectSpawn.getInstance().spawnEffect(81182, time,
							cha.getX(), cha.getY(), cha.getMapId());
					if (cha instanceof L1PcInstance) {
						L1PcInstance targetPc = (L1PcInstance) cha;
						targetPc.setSkillEffect(STATUS_FREEZE, time);
						targetPc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND,
								true));
						targetPc.sendPackets(new S_DoActionGFX(targetPc.getId(),
								ActionCodes.ACTION_Damage));
						targetPc.broadcastPacket(new S_DoActionGFX(
								targetPc.getId(), ActionCodes.ACTION_Damage));
						targetPc.broadcastPacket(new S_SkillSound(targetPc.getId(), 4184));
						targetPc.receiveDamage(pc, damage, false);
					} else if (cha instanceof L1MonsterInstance
							|| cha instanceof L1SummonInstance
							|| cha instanceof L1PetInstance) {
						L1NpcInstance targetNpc = (L1NpcInstance) cha;
						targetNpc.setSkillEffect(STATUS_HOLD, time);
						targetNpc.broadcastPacket(new S_DoActionGFX(targetNpc
								.getId(), ActionCodes.ACTION_Damage));
						targetNpc.broadcastPacket(new S_SkillSound(targetNpc.getId(), 4184));
						targetNpc.receiveDamage(pc, damage);
					}
				}
				return;
			} else if (weapon.getItemId() == 278) { // マリス エレメント クロウ
				L1SkillUse l1skilluse = new L1SkillUse();
				l1skilluse.handleCommands(pc,
						CURSE_POISON, // カーズポイズン
						cha.getId(), cha.getX(), cha.getY(), null, 0,
						L1SkillUse.TYPE_GMBUFF);
			} else if (weapon.getItemId() == 279) { // マリス エレメント スタッフ
				L1Skill l1skills = SkillTable.getInstance().findBySkillId(
						CHILL_TOUCH); // チルタッチ
				L1Magic magic = new L1Magic(pc, cha);

				pc.sendPackets(new S_SkillSound(pc.getId(), l1skills
						.getCastGfx()));
				pc.broadcastPacket(new S_SkillSound(pc.getId(), l1skills
						.getCastGfx()));

				int damage = magic.calcMagicDamage(l1skills.getSkillId());
				if (cha instanceof L1PcInstance) {
					L1PcInstance targetPc = (L1PcInstance) cha;
					targetPc.sendPackets(new S_DoActionGFX(targetPc.getId(),
							ActionCodes.ACTION_Damage));
					targetPc.broadcastPacket(new S_DoActionGFX(
							targetPc.getId(), ActionCodes.ACTION_Damage));
					targetPc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
					targetPc.receiveDamage(pc, damage, false);
					pc.setCurrentHp(pc.getCurrentHp() + damage);
				} else if (cha instanceof L1NpcInstance) {
					L1NpcInstance targetNpc = (L1NpcInstance) cha;
					targetNpc.broadcastPacket(new S_DoActionGFX(targetNpc
							.getId(), ActionCodes.ACTION_Damage));
					targetNpc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
					targetNpc.receiveDamage(pc, damage);
					pc.setCurrentHp(pc.getCurrentHp() + damage);
				}
			} else if (weapon.getItemId() == 280) { // マリス エレメント ボウ
				L1Skill l1skills = SkillTable.getInstance().findBySkillId(15); // ファイアアロー
				L1Magic magic = new L1Magic(pc, cha);

				pc.sendPackets(new S_UseArrowSkill(pc, cha.getId(), l1skills
						.getCastGfx(), cha.getX(), cha.getY(), true));
				pc.broadcastPacket(new S_UseArrowSkill(pc, cha.getId(),
						l1skills.getCastGfx(), cha.getX(), cha.getY(), true));

				int damage = magic.calcMagicDamage(l1skills.getSkillId());
				if (cha instanceof L1PcInstance) {
					L1PcInstance targetPc = (L1PcInstance) cha;
					targetPc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
					targetPc.receiveDamage(pc, damage, false);
				} else if (cha instanceof L1NpcInstance) {
					L1NpcInstance targetNpc = (L1NpcInstance) cha;
					targetNpc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
					targetNpc.receiveDamage(pc, damage);
				}

			} else if (weapon.getItemId() == 281) { // マリス エレメント ソード
				L1Skill l1skills = SkillTable.getInstance().findBySkillId(25); // ファイアボール
				L1Magic magic = new L1Magic(pc, cha);

				pc.sendPackets(new S_SkillSound(cha.getId(), 218));
				pc.broadcastPacket(new S_SkillSound(cha.getId(), 218));

				int damage = magic.calcMagicDamage(l1skills.getSkillId());
				if (cha instanceof L1PcInstance) {
					L1PcInstance targetPc = (L1PcInstance) cha;
					targetPc.sendPackets(new S_DoActionGFX(targetPc.getId(),
							ActionCodes.ACTION_Damage));
					targetPc.broadcastPacket(new S_DoActionGFX(
							targetPc.getId(), ActionCodes.ACTION_Damage));
					targetPc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
					targetPc.receiveDamage(pc, damage, false);
				} else if (cha instanceof L1NpcInstance) {
					L1NpcInstance targetNpc = (L1NpcInstance) cha;
					targetNpc.broadcastPacket(new S_DoActionGFX(targetNpc
							.getId(), ActionCodes.ACTION_Damage));
					targetNpc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
					targetNpc.receiveDamage(pc, damage);
				}

				for (L1Object object : L1World.getInstance().getVisibleObjects(
						cha, l1skills.getArea())) {
					if (cha instanceof L1PcInstance) {
						if (object instanceof L1PcInstance) {
							L1PcInstance targetPc = (L1PcInstance) object;
							if (targetPc.getZoneType() == 1) {
								continue;
							}
							if (!targetPc.isDead()
									&& pc.getId() != targetPc.getId()) {
								L1Magic magic_area = new L1Magic(pc, targetPc);

								pc.sendPackets(new S_SkillSound(targetPc
										.getId(), 218));
								pc.broadcastPacket(new S_SkillSound(targetPc
										.getId(), 218));
								targetPc.sendPackets(new S_DoActionGFX(targetPc
										.getId(), ActionCodes.ACTION_Damage));
								targetPc.broadcastPacket(new S_DoActionGFX(
										targetPc.getId(),
										ActionCodes.ACTION_Damage));

								int damage_area = magic_area
										.calcMagicDamage(l1skills.getSkillId());
								targetPc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
								targetPc.receiveDamage(pc, damage_area, false);
							}
						}
					} else if (cha instanceof L1NpcInstance) {
						if (object instanceof L1NpcInstance) {
							L1NpcInstance targetNpc = (L1NpcInstance) object;
							if (!targetNpc.isDead()) {
								L1Magic magic_area = new L1Magic(pc, targetNpc);

								pc.sendPackets(new S_SkillSound(targetNpc
										.getId(), 218));
								pc.broadcastPacket(new S_SkillSound(targetNpc
										.getId(), 218));
								targetNpc.broadcastPacket(new S_DoActionGFX(
										targetNpc.getId(),
										ActionCodes.ACTION_Damage));

								int damage_area = magic_area
										.calcMagicDamage(l1skills.getSkillId());
								targetNpc.removeSkillEffect(ERASE_MAGIC); // イレースマジック中なら、攻撃魔法で解除
								targetNpc.receiveDamage(pc, damage_area);
							}
						}
					}
				}
			}
		}
	}

	public static void giveFettersEffect(L1PcInstance pc, L1Character cha) {
		int fettersTime = 8000;
		if (isFreeze(cha)) { // 凍結状態orカウンターマジック中
			return;
		}
		if ((_random.nextInt(100) + 1) <= 2) {
			L1EffectSpawn.getInstance().spawnEffect(81182, fettersTime,
					cha.getX(), cha.getY(), cha.getMapId());
			if (cha instanceof L1PcInstance) {
				L1PcInstance targetPc = (L1PcInstance) cha;
				targetPc.setSkillEffect(STATUS_FREEZE, fettersTime);
				targetPc.sendPackets(new S_SkillSound(targetPc.getId(), 4184));
				targetPc.broadcastPacket(new S_SkillSound(targetPc.getId(),
						4184));
				targetPc.sendPackets(new S_Paralysis(S_Paralysis.TYPE_BIND,
						true));
			} else if (cha instanceof L1MonsterInstance
					|| cha instanceof L1SummonInstance
					|| cha instanceof L1PetInstance) {
				L1NpcInstance npc = (L1NpcInstance) cha;
				npc.setSkillEffect(STATUS_HOLD, fettersTime);
				npc.broadcastPacket(new S_SkillSound(npc.getId(), 4184));
			}
		}
	}

	public static double calcDamageReduction(L1PcInstance pc, L1Character cha,
			double dmg, int attr) {
		// 凍結状態orカウンターマジック中
		if (isFreeze(cha)) {
			return 0;
		}

		// MRによるダメージ軽減
		int mr = cha.getMr();
		double mrFloor = 0;
		if (mr <= 100) {
			mrFloor = Math.floor((mr - pc.getOriginalMagicHit()) / 2);
		} else if (mr >= 100) {
			mrFloor = Math.floor((mr - pc.getOriginalMagicHit()) / 10);
		}
		double mrCoefficient = 0;
		if (mr <= 100) {
			mrCoefficient = 1 - 0.01 * mrFloor;
		} else if (mr >= 100) {
			mrCoefficient = 0.6 - 0.01 * mrFloor;
		}
		dmg *= mrCoefficient;

		// 属性によるダメージ軽減
		int resist = 0;
		if (attr == L1Skill.ATTR_EARTH) {
			resist = cha.getEarth();
		} else if (attr == L1Skill.ATTR_FIRE) {
			resist = cha.getFire();
		} else if (attr == L1Skill.ATTR_WATER) {
			resist = cha.getWater();
		} else if (attr == L1Skill.ATTR_WIND) {
			resist = cha.getWind();
		}
		int resistFloor = (int) (0.32 * Math.abs(resist));
		if (resist >= 0) {
			resistFloor *= 1;
		} else {
			resistFloor *= -1;
		}
		double attrDeffence = resistFloor / 32.0;
		dmg = (1.0 - attrDeffence) * dmg;

		return dmg;
	}

	private static boolean isFreeze(L1Character cha) {
		if (cha.hasSkillEffect(STATUS_FREEZE)) {
			return true;
		}
		if (cha.hasSkillEffect(ABSOLUTE_BARRIER)) {
			return true;
		}
		if (cha.hasSkillEffect(ICE_LANCE)) {
			return true;
		}
		if (cha.hasSkillEffect(FREEZING_BLIZZARD)) {
			return true;
		}
		if (cha.hasSkillEffect(FREEZING_BREATH)) {
			return true;
		}
		if (cha.hasSkillEffect(EARTH_BIND)) {
			return true;
		}

		// カウンターマジック判定
		if (cha.hasSkillEffect(COUNTER_MAGIC)) {
			cha.removeSkillEffect(COUNTER_MAGIC);
			int castgfx = SkillTable.getInstance().findBySkillId(COUNTER_MAGIC)
					.getCastGfx();
			cha.broadcastPacket(new S_SkillSound(cha.getId(), castgfx));
			if (cha instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillSound(pc.getId(), castgfx));
			}
			return true;
		}
		return false;
	}

}
