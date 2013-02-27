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

import java.util.logging.Logger;
import jp.l1j.server.model.instance.L1ItemInstance;
import jp.l1j.server.model.instance.L1PcInstance;
import static jp.l1j.server.model.skill.L1SkillId.*;
import jp.l1j.server.packets.server.S_ChangeShape;
import jp.l1j.server.packets.server.S_CharVisualUpdate;
import jp.l1j.server.packets.server.S_HpUpdate;
import jp.l1j.server.packets.server.S_OwnCharAttrDef;
import jp.l1j.server.packets.server.S_OwnCharStatus;
import jp.l1j.server.packets.server.S_SpMr;

// Referenced classes of package jp.l1j.server.model:
// L1Cooking

public class L1Awake {
	private static Logger _log = Logger.getLogger(L1Awake.class
			.getName());

	private L1Awake() {
	}

	public static void start(L1PcInstance pc, int skillId) {
		if (skillId == pc.getAwakeSkillId()) { // 再詠唱なら解除
			stop(pc);
		} else if (pc.getAwakeSkillId() != 0) { // 他の覚醒スキルの場合はリターン
			return;
		} else {
			if (skillId == AWAKEN_ANTHARAS) { // 覚醒：アンタラス
				pc.addMaxHp(35);
				pc.sendPackets(new S_HpUpdate(pc.getCurrentHp(), pc.getMaxHp()));
				if (pc.isInParty()) { // パーティー中
					pc.getParty().updateMiniHP(pc);
				}
				pc.addAc(-8);
			} else if (skillId == AWAKEN_FAFURION) { // 覚醒：パプリオン
				pc.addMr(25);
				pc.sendPackets(new S_SpMr(pc));
				pc.addWind(15);
				pc.addWater(15);
				pc.addFire(15);
				pc.addEarth(15);
				pc.sendPackets(new S_OwnCharAttrDef(pc));
			} else if (skillId == AWAKEN_VALAKAS) { // 覚醒：ヴァラカス
				pc.addStr(3);
				pc.addCon(3);
				pc.addDex(3);
				pc.addCha(3);
				pc.addInt(3);
				pc.addWis(3);
			}
			pc.sendPackets(new S_OwnCharStatus(pc));
			pc.setAwakeSkillId(skillId);
			doPoly(pc, skillId);
			pc.startMpReductionByAwake();
		}
	}

	public static void stop(L1PcInstance pc) {
		int skillId = pc.getAwakeSkillId();
		if (skillId == AWAKEN_ANTHARAS) { // 覚醒：アンタラス
			pc.addMaxHp(-35);
			pc.sendPackets(new S_HpUpdate(pc.getCurrentHp(), pc.getMaxHp()));
			if (pc.isInParty()) { // パーティー中
				pc.getParty().updateMiniHP(pc);
			}
			pc.addAc(8);
		} else if (skillId == AWAKEN_FAFURION) { // 覚醒：パプリオン
			pc.addMr(-25);
			pc.sendPackets(new S_SpMr(pc));
			pc.addWind(-15);
			pc.addWater(-15);
			pc.addFire(-15);
			pc.addEarth(-15);
			pc.sendPackets(new S_OwnCharAttrDef(pc));
		} else if (skillId == AWAKEN_VALAKAS) { // 覚醒：ヴァラカス
			pc.addStr(-3);
			pc.addCon(-3);
			pc.addDex(-3);
			pc.addCha(-3);
			pc.addInt(-3);
			pc.addWis(-3);
		}
		pc.sendPackets(new S_OwnCharStatus(pc));
		pc.setAwakeSkillId(0);
		undoPoly(pc);
		pc.stopMpReductionByAwake();
	}

	public static void doPoly(L1PcInstance pc, int skillId) {
		int polyId = 0;
		if (skillId == AWAKEN_ANTHARAS) {//TODO 覚醒[アンタラス]
			polyId = 9362;
		} else if (skillId == AWAKEN_FAFURION) {//TODO 覚醒[パプリオン]
			polyId = 9364;
		} else if (skillId == AWAKEN_VALAKAS) {//TODO 覚醒[ヴァラカス]
			polyId = 9363;
		}
		if (pc.hasSkillEffect(SHAPE_CHANGE)) {
			pc.killSkillEffectTimer(SHAPE_CHANGE);
		}
		pc.setTempCharGfx(polyId);
		pc.sendPackets(new S_ChangeShape(pc.getId(), polyId));
		if (!pc.isGmInvis() && !pc.isInvisble()) {
			pc.broadcastPacket(new S_ChangeShape(pc.getId(), polyId));
		}
		L1ItemInstance weapon = pc.getWeapon();
		if (weapon != null) {
			S_CharVisualUpdate charVisual = new S_CharVisualUpdate(pc);
			pc.sendPackets(charVisual);
			pc.broadcastPacket(charVisual);
		}
	}

	public static void undoPoly(L1PcInstance pc) {
		int classId = pc.getClassId();
		pc.setTempCharGfx(classId);
		pc.sendPackets(new S_ChangeShape(pc.getId(), classId));
		pc.broadcastPacket(new S_ChangeShape(pc.getId(), classId));
		L1ItemInstance weapon = pc.getWeapon();
		if (weapon != null) {
			S_CharVisualUpdate charVisual = new S_CharVisualUpdate(pc);
			pc.sendPackets(charVisual);
			pc.broadcastPacket(charVisual);
		}
	}

}
