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
package jp.l1j.server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.l1j.server.codes.ActionCodes;
import jp.l1j.server.utils.IdFactory;
import jp.l1j.server.datatables.NpcTable;
import jp.l1j.server.model.Instance.L1DoorInstance;
import jp.l1j.server.model.Instance.L1ItemInstance;
import jp.l1j.server.model.Instance.L1NpcInstance;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.inventory.L1Inventory;
import jp.l1j.server.random.RandomGenerator;
import jp.l1j.server.random.RandomGeneratorFactory;
import jp.l1j.server.packets.server.S_CharVisualUpdate;
import jp.l1j.server.packets.server.S_DoActionGFX;
import jp.l1j.server.packets.server.S_MapID;
import jp.l1j.server.packets.server.S_OtherCharPacks;
import jp.l1j.server.packets.server.S_OwnCharPack;
import jp.l1j.server.packets.server.S_RemoveObject;
import jp.l1j.server.packets.server.S_ServerMessage;
import jp.l1j.server.packets.server.S_Weather;
import jp.l1j.server.utils.collections.Maps;

public class L1DragonSlayer {
	private static Logger _log = Logger.getLogger(L1DragonSlayer.class.getName());

	private static RandomGenerator _random = RandomGeneratorFactory.newRandom();

	private static L1DragonSlayer _instance;

	public static final int STATUS_DRAGONSLAYER_NONE = 0;
	public static final int STATUS_DRAGONSLAYER_READY_1RD = 1;
	public static final int STATUS_DRAGONSLAYER_READY_2RD = 2;
	public static final int STATUS_DRAGONSLAYER_READY_3RD = 3;
	public static final int STATUS_DRAGONSLAYER_READY_4RD = 4;
	public static final int STATUS_DRAGONSLAYER_START_1RD = 5;
	public static final int STATUS_DRAGONSLAYER_START_2RD = 6;
	public static final int STATUS_DRAGONSLAYER_START_2RD_1 = 7;
	public static final int STATUS_DRAGONSLAYER_START_2RD_2 = 8;
	public static final int STATUS_DRAGONSLAYER_START_2RD_3 = 9;
	public static final int STATUS_DRAGONSLAYER_START_2RD_4 = 10;
	public static final int STATUS_DRAGONSLAYER_START_3RD = 11;
	public static final int STATUS_DRAGONSLAYER_START_3RD_1 = 12;
	public static final int STATUS_DRAGONSLAYER_START_3RD_2 = 13;
	public static final int STATUS_DRAGONSLAYER_START_3RD_3 = 14;
	public static final int STATUS_DRAGONSLAYER_END_1 = 15;
	public static final int STATUS_DRAGONSLAYER_END_2 = 16;
	public static final int STATUS_DRAGONSLAYER_END_3 = 17;
	public static final int STATUS_DRAGONSLAYER_END_4 = 18;
	public static final int STATUS_DRAGONSLAYER_END_5 = 19;
	public static final int STATUS_DRAGONSLAYER_END = 20;

	public static final int STATUS_NONE = 0;
	public static final int STATUS_READY_SPAWN = 1;
	public static final int STATUS_SPAWN = 2;

	private static class DragonSlayer {
		private ArrayList<L1PcInstance> _members = new ArrayList<L1PcInstance>();
	}

	private static final Map<Integer, DragonSlayer> _dataMap = Maps.newHashMap();

	public static L1DragonSlayer getInstance() {
		if (_instance == null) {
			_instance = new L1DragonSlayer();
		}
		return _instance;
	}

	private boolean[] _portalNumber = new boolean[24];

	public boolean[] getPortalNumber() {
		return _portalNumber;
	}

	public void setPortalNumber(int number, boolean i) {
		_portalNumber[number] = i;
	}

	private boolean[] _checkDragonPortal = new boolean[4];

	public boolean[] checkDragonPortal() {
		_checkDragonPortal[0] = false; // アンタラス
		_checkDragonPortal[1] = false; // パプリオン
		_checkDragonPortal[2] = false; // リンドビオル
		_checkDragonPortal[3] = false; // ヴァラカス

		for (int i = 0; i < 12; i++) {
			if (!getPortalNumber()[i]) {
				if (i < 6) { // アンタラス
					_checkDragonPortal[0] = true;
				} else if (i < 12) { // パプリオン
					_checkDragonPortal[1] = true;
				} else if (i < 18) { // リンドビオル
					_checkDragonPortal[2] = true;
				} else if (i < 24) { // ヴァラカス
					_checkDragonPortal[3] = true;
				}
			}
		}
		return _checkDragonPortal;
	}

	private L1NpcInstance[] _portal = new L1NpcInstance[24];

	public L1NpcInstance[] portalPack() {
		return _portal;
	}

	public void setPortalPack(int number, L1NpcInstance portal) {
		_portal[number] = portal;
	}

	private int[] _DragonSlayerStatus = new int[24];

	public int[] getDragonSlayerStatus() {
		return _DragonSlayerStatus;
	}

	public void setDragonSlayerStatus(int portalNum, int i) {
		_DragonSlayerStatus[portalNum] = i;
	}

	private int _hiddenDragonValleyStatus = 0;

	public int checkHiddenDragonValleyStatus() {
		return _hiddenDragonValleyStatus;
	}

	public void setHiddenDragonValleyStatus(int i) {
		_hiddenDragonValleyStatus = i;
	}

	public void addPlayerList(L1PcInstance pc, int portalNum) {
		if (_dataMap.containsKey(portalNum)) {
			if (!_dataMap.get(portalNum)._members.contains(pc)) {
				_dataMap.get(portalNum)._members.add(pc);
			}
		}
	}

	public void removePlayer(L1PcInstance pc, int portalNum) {
		if (_dataMap.containsKey(portalNum)) {
			if (_dataMap.get(portalNum)._members.contains(pc)) {
				_dataMap.get(portalNum)._members.remove(pc);
			}
		}
	}

	private void clearPlayerList(int portalNum) {
		if (_dataMap.containsKey(portalNum)) {
			_dataMap.get(portalNum)._members.clear();
		}
	}

	public int getPlayersCount(int num) {
		DragonSlayer _DragonSlayer = null;
		if (!_dataMap.containsKey(num)) {
			_DragonSlayer = new DragonSlayer();
			_dataMap.put(num, _DragonSlayer);
		}
		return _dataMap.get(num)._members.size();
	}

	private L1PcInstance[] getPlayersArray(int num) {
		return _dataMap.get(num)._members.toArray(new L1PcInstance[_dataMap.get(num)._members.size()]);
	}

	public void startDragonSlayer(int portalNum) {
		if (getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_NONE) {
			setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_READY_1RD);
			DragonSlayerTimer timer = new DragonSlayerTimer(portalNum, STATUS_DRAGONSLAYER_READY_1RD, 150000);
			timer.begin();
		}
	}

	public void startDragonSlayer2rd(int portalNum) {
		if (getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_START_1RD) {
			if (portalNum < 6) {
				sendMessage(portalNum, 1573, null);
				// アンタラス：愚か者めが！俺を怒らせるのか。
			} else if (portalNum < 12) {
				sendMessage(portalNum, 1661, null);
				// パプリオン：笑わせるな！あいつらがお前と共にあの世をさまようことになる、俺の生贄か！
			// } else if (portalNum < 18) {
			// リンドビオル(未実装)
			// } else if (portalNum < 24) {
			// ヴァラカス(未実装)
			}
			setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_START_2RD);
			DragonSlayerTimer timer = new DragonSlayerTimer(portalNum, STATUS_DRAGONSLAYER_START_2RD, 10000);
			timer.begin();
		}
	}

	public void startDragonSlayer3rd(int portalNum) {
		if (getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_START_2RD_4) {
			if (portalNum < 6) {
				sendMessage(portalNum, 1577, null);
				// クレイ：うぉーっ！血塗られた魂の叫びが聞こえないのか！死ね！
			} else if (portalNum < 12) {
				sendMessage(portalNum, 1665, null);
				// 巫女サエル：パプリオンの力がかなり落ちたようです！勇士たちよ！もうちょっとです！
			// } else if (portalNum < 18) {
			// リンドビオル(未実装)
			// } else if (portalNum < 24) {
			// ヴァラカス(未実装)
			}
			setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_START_3RD);
			DragonSlayerTimer timer = new DragonSlayerTimer(portalNum, STATUS_DRAGONSLAYER_START_3RD, 10000);
			timer.begin();
		}
	}

	public void endDragonSlayer(int portalNum) {
		if (getDragonSlayerStatus()[portalNum] == STATUS_DRAGONSLAYER_START_3RD_3) {
			setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_END_1);
			DragonSlayerTimer timer = new DragonSlayerTimer(portalNum, STATUS_DRAGONSLAYER_END_1, 10000);
			timer.begin();
		}
	}

	public void endDragonPortal(int portalNum) {
		if (getDragonSlayerStatus()[portalNum] != STATUS_DRAGONSLAYER_END_5) {
			setDragonSlayerStatus(portalNum, STATUS_DRAGONSLAYER_END_5);
			DragonSlayerTimer timer = new DragonSlayerTimer(portalNum, STATUS_DRAGONSLAYER_END_5, 5000);
			timer.begin();
		}
	}

	public class DragonSlayerTimer extends TimerTask {

		private final int _num;
		private final int _status;
		private final int _time;

		public DragonSlayerTimer(int num, int status, int time) {
			_num = num;
			_status = status;
			_time = time;
		}

		@Override
		public void run() {
			short mapId = (short) (1005 + _num);
			int[] msg = new int[9];
			if (_num < 6) {
				msg = new int[] { 1570, 1571, 1572, 1574, 1575, 1576, 1578, 1579, 1581 };
			} else if (_num <= 12) {
				msg = new int[] { 1657, 1658, 1659, 1662, 1663, 1664, 1666, 1667, 1669 };
			// } else if (_num <= 18) {
			// リンドビオル(未実装) 
			// } else if (_num <= 24) {
			// ヴァラカス(未実装)
			}
			switch (_status) {
				// 第1ステージ
				case STATUS_DRAGONSLAYER_READY_1RD:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_READY_2RD);
					sendMessage(_num, msg[0], null);
					// アンタラス：誰だ、私を眠りから覚ましたのは。
					// パプリオン：俺の領域を侵すとは…その勇気は高く買おう。
					DragonSlayerTimer timer_1rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_READY_2RD, 10000);
					timer_1rd.begin();
					break;
				case STATUS_DRAGONSLAYER_READY_2RD:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_READY_3RD);
					sendMessage(_num, msg[1], null);
					// クレイ：アンタラス！お前を追ってこの漆黒の闇までやって来た！
					// 巫女サエル：この卑劣なパプリオンめ！私を騙した代償を払わせてやる！
					DragonSlayerTimer timer_2rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_READY_3RD, 10000);
					timer_2rd.begin();
					break;
				case STATUS_DRAGONSLAYER_READY_3RD:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_READY_4RD);
					sendMessage(_num, msg[2], null);
					// アンタラス：笑止千万！クレイ！もう一度あの世へ送ってやる！
					// パプリオン：封印を解く時にはお前が助けになったが…俺に二回目の慈悲はない…
					DragonSlayerTimer timer_3rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_READY_4RD, 10000);
					timer_3rd.begin();
					break;
				case STATUS_DRAGONSLAYER_READY_4RD:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_1RD);
					if (_num >= 0 && _num <= 5) {
						spawn(91200, _num, 32783, 32693, mapId, 10, 0); // アンタラスLv1
					} else {
						spawn(91514, _num, 32955, 32839, mapId, 10, 0); // パプリオンLv1
					}
					break;
				// 第2ステージ
				case STATUS_DRAGONSLAYER_START_2RD:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_2RD_1);
					sendMessage(_num, msg[3], null);
					// クレイ：勇士たちよ！君たちの剣にアデンの運命がかかっている。アンタラスの黒い息遣いを止めることができるのは君たちだけだ！
					// 巫女サエル：勇士たちよ！あの邪悪なパプリオンを倒して、エヴァ王国にかけられた血の呪いを解いてください！
					DragonSlayerTimer timer_4rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_START_2RD_1, 10000);
					timer_4rd.begin();
					break;
				case STATUS_DRAGONSLAYER_START_2RD_1:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_2RD_2);
					sendMessage(_num, msg[4], null);
					// アンタラス：そんなガラクタで俺に勝てるとでも思ってるのか！グハハハ！
					// パプリオン：手頃なおもちゃだな！ハハハ！
					DragonSlayerTimer timer_5rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_START_2RD_2, 30000);
					timer_5rd.begin();
					break;
				case STATUS_DRAGONSLAYER_START_2RD_2:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_2RD_3);
					sendMessage(_num, msg[5], null);
					// アンタラス：そろそろうまいめしにありつけそうだな。おまえらの血のにおい、うーんたまらん！
					// パプリオン：骨の髄まで凍りつくほどの恐怖を味わせてやろう！
					DragonSlayerTimer timer_6rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_START_2RD_3, 10000);
					timer_6rd.begin();
					break;
				case STATUS_DRAGONSLAYER_START_2RD_3:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_2RD_4);
					if (_num >= 0 && _num <= 5) {
						spawn(91201, _num, 32783, 32693, mapId, 10, 0); // アンタラスLv2
					} else {
						spawn(91515, _num, 32955, 32839, mapId, 10, 0); // パプリオンLv2
					}
					break;
				// 第3ステージ
				case STATUS_DRAGONSLAYER_START_3RD:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_3RD_1);
					sendMessage(_num, msg[6], null);
					// アンタラス：生意気なやつらめ！死にたいのか！
					// パプリオン：おまえの言うところの希望なるものが、虚構であることを教えてやろう！
					DragonSlayerTimer timer_7rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_START_3RD_1, 40000);
					timer_7rd.begin();
					break;
				case STATUS_DRAGONSLAYER_START_3RD_1:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_3RD_2);
					sendMessage(_num, msg[7], null);
					// アンタラス：許さん！すぐに父上がお出ましになるだろう。
					// パプリオン：サエルの味方をしたことを後悔させてやろう！この愚か者めが！
					DragonSlayerTimer timer_8rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_START_3RD_2, 10000);
					timer_8rd.begin();
					break;
				case STATUS_DRAGONSLAYER_START_3RD_2:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_START_3RD_3);
					if (_num >= 0 && _num <= 5) {
						spawn(91202, _num, 32783, 32693, mapId, 10, 0); // アンタラスLv3
					} else {
						spawn(91516, _num, 32955, 32839, mapId, 10, 0); // パプリオンLv3
					}
					break;
				case STATUS_DRAGONSLAYER_END_1:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_END_2);
					sendMessage(_num, msg[8], null);
					// クレイ：おお…最強の勇士であることを示してみせた最高のナイトよ！試練に打ち勝ってアンタラスをしとめたのだな。恨みは解けるだろう。ウハハハ！
					// 巫女サエル：ありがとうございます。あなたがたはアデン最高の勇士です。これでエヴァ王国にかけられた長年の呪いも解けるでしょう。
					if (checkHiddenDragonValleyStatus() == STATUS_NONE) { // 準備開啟隱匿的巨龍谷入口
						setHiddenDragonValleyStatus(STATUS_READY_SPAWN);
						DragonSlayerTimer timer_9rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_END_2, 10000);
						timer_9rd.begin();
					} else { // 直接結束
						if (getDragonSlayerStatus()[_num] != STATUS_DRAGONSLAYER_END_5) {
							setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_END_5);
							DragonSlayerTimer timer = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_END_5, 5000);
							timer.begin();
						}
					}
					break;
				case STATUS_DRAGONSLAYER_END_2:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_END_3);
					sendMessage(_num, 1582, null); // ドワーフの叫び：ウェルダン村で隠された竜の地の扉が開きました。
					if (checkHiddenDragonValleyStatus() == STATUS_READY_SPAWN) {
						setHiddenDragonValleyStatus(STATUS_SPAWN);
						spawn(91066, -1, 33726, 32506, (short) 4, 0, 86400000);
					}
					DragonSlayerTimer timer_10rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_END_3, 5000);
					timer_10rd.begin();
					break;
				case STATUS_DRAGONSLAYER_END_3:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_END_4);
					sendMessage(_num, 1583, null); // ドワーフの叫び：ウェルダン村で隠された竜の地の扉が開かれています。
					DragonSlayerTimer timer_11rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_END_4, 5000);
					timer_11rd.begin();
					break;
				case STATUS_DRAGONSLAYER_END_4:
					setDragonSlayerStatus(_num, STATUS_DRAGONSLAYER_END_5);
					sendMessage(_num, 1584, null); // ドワーフの叫び：早くこの地を去りなさい。そろそろ扉が閉じます。
					DragonSlayerTimer timer_12rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_END_5, 5000);
					timer_12rd.begin();
					break;
				case STATUS_DRAGONSLAYER_END_5:
					if (portalPack()[_num] != null) {
						portalPack()[_num].setStatus(ActionCodes.ACTION_Die);
						portalPack()[_num].broadcastPacket(new S_DoActionGFX(portalPack()[_num].getId(), ActionCodes.ACTION_Die));
						portalPack()[_num].deleteMe();
					}
					resetDragonSlayer(_num);
					DragonSlayerTimer timer_13rd = new DragonSlayerTimer(_num, STATUS_DRAGONSLAYER_END, 300000);
					timer_13rd.begin();
					break;
				case STATUS_DRAGONSLAYER_END:
					setPortalNumber(_num, false);
					break;
			}
			cancel();
		}

		public void begin() {
			Timer timer = new Timer();
			timer.schedule(this, _time);
		}
	}

	public void sendMessage(int portalNum, int type, String msg) {
		short mapId = (short) (1005 + portalNum);
		L1PcInstance[] players = getPlayersArray(portalNum);
		for (L1PcInstance pc : players) {
			if (pc.getMapId() == mapId
					&& portalNum >= 0 && portalNum <= 5 // アンタラスレア
					&& pc.getX() >= 32744 && pc.getX() <= 32812
					&& pc.getY() >= 32659 && pc.getY() <= 32723) {
				pc.sendPackets(new S_ServerMessage(type, msg));
			} else if (pc.getMapId() == mapId
					&& portalNum >= 6 && portalNum <= 11 // パプリオンレア
					&& pc.getX() >= 32928 && pc.getX() <= 32993
					&& pc.getY() >= 32812 && pc.getY() <= 32860) {
				pc.sendPackets(new S_ServerMessage(type, msg));
			} else if (pc.getMapId() == mapId
					&& portalNum >= 12 && portalNum <= 17 // リンドビオルレア
					&& pc.getX() >= 32830 && pc.getX() <= 32863
					&& pc.getY() >= 32861 && pc.getY() <= 32898) {
				pc.sendPackets(new S_ServerMessage(type, msg));
			} else if (pc.getMapId() == mapId
					&& portalNum >= 18 && portalNum <= 23 // ヴァラカスレア
					&& pc.getX() >= 32808 && pc.getX() <= 32863
					&& pc.getY() >= 32748 && pc.getY() <= 32792) {
				pc.sendPackets(new S_ServerMessage(type, msg));
			}
		}
	}

	private boolean isDragonSlayer(int portalNum, L1PcInstance pc) {
		boolean isDragonSlayer = false;
		short mapId = (short) (1005 + portalNum);
		if (pc.getMapId() == mapId
				&& portalNum >= 0 && portalNum <= 5 // アンタラスレア
				&& pc.getX() >= 32744 && pc.getX() <= 32812
				&& pc.getY() >= 32659 && pc.getY() <= 32723) {
			isDragonSlayer = true;
		} else if (pc.getMapId() == mapId
				&& portalNum >= 6 && portalNum <= 11 // パプリオンレア
				&& pc.getX() >= 32928 && pc.getX() <= 32993
				&& pc.getY() >= 32812 && pc.getY() <= 32860) {
			isDragonSlayer = true;
		} else if (pc.getMapId() == mapId
				&& portalNum >= 12 && portalNum <= 17 // リンドビオルレア
				&& pc.getX() >= 32830 && pc.getX() <= 32863
				&& pc.getY() >= 32861 && pc.getY() <= 32898) {
			isDragonSlayer = true;
		} else if (pc.getMapId() == mapId
				&& portalNum >= 18 && portalNum <= 23 // ヴァラカスレア
				&& pc.getX() >= 32808 && pc.getX() <= 32863
				&& pc.getY() >= 32748 && pc.getY() <= 32792) {
			isDragonSlayer = true;
		}
		return isDragonSlayer;
	}
	
	private String getDragonName(int npcId) {
		L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(npcId);
		return npc.getNpcTemplate().getName();
	}
	
	public void dropRewardItem(int portalNum, int npcId) {
		short mapId = (short) (1005 + portalNum);
		int dragon[] = new int[] { 91200, 91201, 91202, // アンタラス
			91514, 91515, 91516 }; //  パプリオン
		int reward[] = new int[] { 50553, 50553, 50556, 50553, 50553, 50557 };
		int i = Arrays.binarySearch(dragon, npcId);
		L1PcInstance[] players = getPlayersArray(portalNum);
		for (L1PcInstance pc : players) {
			if (isDragonSlayer(portalNum, pc)) {
				L1ItemInstance item = pc.getInventory().storeItem(reward[i], 1);
				String npcName = getDragonName(npcId);
				String itemName = item.getItem().getName();
				pc.sendPackets(new S_ServerMessage(143, npcName, itemName));			
			}
		}
	}

	public void resetDragonSlayer(int portalNumber) {
		short mapId = (short) (1005 + portalNumber);

		for (Object obj : L1World.getInstance().getVisibleObjects(mapId).values()) {
			if (obj instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) obj;
				if (pc != null) {
					if (pc.isDead()) {
						reStartPlayer(pc);
					} else {
						pc.setPortalNumber(-1);
						L1Teleport.teleport(pc, 33710, 32521, (short) 4, pc.getHeading(), true);
					}
				}
			} else if (obj instanceof L1DoorInstance) {
				L1DoorInstance door = (L1DoorInstance) obj;
				door.close();
			} else if (obj instanceof L1NpcInstance) {
				L1NpcInstance npc = (L1NpcInstance) obj;
				if ((npc.getMaster() == null)
						&& (npc.getNpcTemplate().getNpcId() < 81301 && npc.getNpcTemplate().getNpcId() > 81306)) {
					npc.deleteMe();
				}
			} else if (obj instanceof L1Inventory) {
				L1Inventory inventory = (L1Inventory) obj;
				inventory.clearItems();
			}
		}
		setPortalPack(portalNumber, null);
		setDragonSlayerStatus(portalNumber, STATUS_DRAGONSLAYER_NONE);
		clearPlayerList(portalNumber);
	}

	private void reStartPlayer(L1PcInstance pc) {
		pc.stopPcDeleteTimer();

		int[] loc = Getback.GetBack_Location(pc, true);

		pc.removeAllKnownObjects();
		pc.broadcastPacket(new S_RemoveObject(pc));

		pc.setCurrentHp(pc.getLevel());
		pc.setFood(40);
		pc.setDead(false);
		pc.setStatus(0);
		L1World.getInstance().moveVisibleObject(pc, loc[2]);
		pc.setX(loc[0]);
		pc.setY(loc[1]);
		pc.setMap((short) loc[2]);
		pc.sendPackets(new S_MapID(pc.getMapId(), pc.getMap().isUnderwater()));
		pc.broadcastPacket(new S_OtherCharPacks(pc));
		pc.sendPackets(new S_OwnCharPack(pc));
		pc.sendPackets(new S_CharVisualUpdate(pc));
		pc.startHpRegeneration();
		pc.startMpRegeneration();
		pc.sendPackets(new S_Weather(L1World.getInstance().getWeather()));
		if (pc.getHellTime() > 0) {
			pc.beginHell(false);
		}
	}

	public void spawn(int npcId, int portalNumber, int X, int Y, short mapId, int randomRange,
			int timeMillisToDelete) {
		try {
			L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(npcId);
			npc.setId(IdFactory.getInstance().nextId());
			npc.setMap(mapId);

			if (randomRange == 0) {
				npc.setX(X);
				npc.setY(Y);
			} else {
				int tryCount = 0;
				do {
					tryCount++;
					npc.setX(X + _random.nextInt(randomRange) - _random.nextInt(randomRange));
					npc.setY(Y + _random.nextInt(randomRange) - _random.nextInt(randomRange));
					if (npc.getMap().isInMap(npc.getLocation())
							&& npc.getMap().isPassable(npc.getLocation())) {
						break;
					}
					Thread.sleep(1);
				} while (tryCount < 50);

				if (tryCount >= 50) {
					npc.setX(X);
					npc.setY(Y);
				}
			}

			npc.setHomeX(npc.getX());
			npc.setHomeY(npc.getY());
			npc.setHeading(_random.nextInt(8));
			npc.setPortalNumber(portalNumber);

			L1World.getInstance().storeObject(npc);
			L1World.getInstance().addVisibleObject(npc);

			if (npc.getGfxId() == 7548 || npc.getGfxId() == 7550 || npc.getGfxId() == 7552
					|| npc.getGfxId() == 7554 || npc.getGfxId() == 7585
					|| npc.getGfxId() == 7539 || npc.getGfxId() == 7557 || npc.getGfxId() == 7558
					|| npc.getGfxId() == 7864 || npc.getGfxId() == 7869 || npc.getGfxId() == 7870) {
				npc.setDelayTime(ActionCodes.ACTION_AxeWalk, L1NpcInstance.ATTACK_SPEED);
				for (L1PcInstance pc : L1World.getInstance().getVisiblePlayer(npc)) {
					npc.onPerceive(pc);
					S_DoActionGFX gfx = new S_DoActionGFX(npc.getId(), ActionCodes.ACTION_AxeWalk);
					pc.sendPackets(gfx);
				}
			}

			npc.updateLight();
			npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // チャット開始
			
			if (0 < timeMillisToDelete) {
				L1NpcDeleteTimer timer = new L1NpcDeleteTimer(npc,
						timeMillisToDelete);
				timer.begin();
			}
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

}
