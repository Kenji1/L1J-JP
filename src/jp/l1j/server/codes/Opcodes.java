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

package jp.l1j.server.codes;

public class Opcodes {

	public Opcodes() {
	}
	/** 3.53C測試 Client Packet */

	/** 要求創立血盟 */
	public static final int C_OPCODE_CREATECLAN = 1;
	/** 要求存入寶庫資金 */
	public static final int C_OPCODE_DEPOSIT = 2;
	/** 要求查詢朋友名單 */
	public static final int C_OPCODE_BUDDYLIST = 3;
	/** 請求 決定下次圍城時間(官方已取消使用)-->修正城堡總管全部功能. */
	public static final int C_OPCODE_CHANGEWARTIME = 4;
	/** 要求改變角色面向 */
	public static final int C_OPCODE_CHANGEHEADING = 5;
	/** 要求角色攻擊 */
	public static final int C_OPCODE_ATTACK = 6;
	/** TODO Add cash and items to warehouse */
	public static final int C_OPCODE_OPTIONAL_WAREHOUSE = 8;
	/** 要求查詢PK次數 */
	public static final int C_OPCODE_CHECKPK = 10;
	/** 要求使用廣播聊天頻道 */
	public static final int C_OPCODE_CHATGLOBAL = 11;
	/** 要求退出觀看模式 */
	public static final int C_OPCODE_EXIT_GHOST = 12;
	/** 要求寫入公佈欄訊息 */
	public static final int C_OPCODE_BOARDWRITE = 13;
	/** 要求切換角色 */
	public static final int C_OPCODE_CHANGECHAR = 14;
	/** 要求使用一般聊天頻道 */
	public static final int C_OPCODE_CHAT = 15;
	/** 要求重置人物點數 */
	public static final int C_OPCODE_CHARRESET = 17;
	/** 要求離開遊戲 */
	public static final int C_OPCODE_QUITGAME = 18;
	/** 請求 完成學習魔法(材料). */
	public static final int C_OPCODE_SKILLBUYOKITEM = 19;
	/** 請求更新周圍物件(坐標點/洞穴點切換進出後). */
	public static final int C_OPCODE_TELEPORT2 = 20;
	/** 要求丟棄物品 */
	public static final int C_OPCODE_DROPITEM = 22;
	/** 要求寵物攻擊指定物件 */
	public static final int C_OPCODE_SELECTTARGET = 23;
	/** 請求 學習魔法清單(材料). */
	public static final int C_OPCODE_SKILLBUYITEM = 26;
	/** 要求脫離血盟 */
	public static final int C_OPCODE_LEAVECLANE = 27;
	/** 要求刪除公佈欄內容 */
	public static final int C_OPCODE_BOARDDELETE = 28;
	/** 要求學習魔法 完成 */
	public static final int C_OPCODE_SKILLBUYOK = 29;
	/** 要求使用拒絕名單 */
	public static final int C_OPCODE_EXCLUDE = 32;
	/** 要求物件對話視窗結果 */
	public static final int C_OPCODE_NPCACTION = 34;
	/** 要求確定數量選取 */
	public static final int C_OPCODE_AMOUNT = 35;
	/** 要求召喚到身邊(gm) */
	public static final int C_OPCODE_CALL = 36;
	/** 要求驅逐人物離開血盟 */
	public static final int C_OPCODE_BANCLAN = 38;
	/** 要求使用物品 */
	public static final int C_OPCODE_USEITEM = 39;
	/** 要求脫離隊伍 */
	public static final int C_OPCODE_LEAVEPARTY = 40;
	/** 要求血盟盟標 */
	public static final int C_OPCODE_CLAN = 43;
	/** 要求取消雙方交易 */
	public static final int C_OPCODE_TRADEADDCANCEL = 44;
	/** 要求打開郵箱 */
	public static final int C_OPCODE_MAIL = 47;
	/** 要求公告後顯示人物列表 */
	public static final int C_OPCODE_COMMONCLICK = 48;
	/** 要求領出寶庫資金 */
	public static final int C_OPCODE_DRAWAL = 49;
	/** 要求下船 */
	public static final int C_OPCODE_SHIP = 52;
	/** 要求加入血盟 */
	public static final int C_OPCODE_JOINCLAN = 54;
	/** 要求傳送更新周圍物件*/
	public static final int C_OPCODE_TELEPORT = 55;
	/** 要求交易(個人) */
	public static final int C_OPCODE_TRADE = 57;
	/** 要求更新時間 */
	public static final int C_OPCODE_KEEPALIVE = 58;
	/** 請求 回到登入畫面. */
	public static final int C_OPCODE_RETURNTOLOGIN = 59;
	/** 要求完成交易(個人) */
	public static final int C_OPCODE_TRADEADDOK = 60;
	/** 要求進入遊戲 */
	public static final int C_OPCODE_LOGINTOSERVER = 62;
	/** 要求給予角色血盟階級 */
	public static final int C_OPCODE_RANK = 64;
	/** 要求撿取物品 */
	public static final int C_OPCODE_PICKUPITEM = 65;
	/** 要求查詢遊戲人數 */
	public static final int C_OPCODE_WHO = 66;
	/** 要求變更倉庫密碼*/
	public static final int C_OPCODE_WAREHOUSELOCK = 67;
	/** 要求交易(添加物品) */
	public static final int C_OPCODE_TRADEADDITEM = 68;
	/** 要求查看隊伍 */
	public static final int C_OPCODE_PARTYLIST = 69;
	/** 要求角色表情動作 */
	public static final int C_OPCODE_EXTCOMMAND = 70;
	/** 要求使用遠距武器 */
	public static final int C_OPCODE_ARROWATTACK = 71;
	/** 要求使用寵物裝備 */
	public static final int C_OPCODE_USEPETITEM = 72;
	/** 要求點選項目的結果 */
	public static final int C_OPCODE_ATTR = 73;
	/** 要求稅收設定 */
	public static final int C_OPCODE_TAXRATE = 74;
	/** 要求上傳盟標 */
	public static final int C_OPCODE_EMBLEM = 75;
	/** 要求查詢血盟成員 */
	public static final int C_OPCODE_PLEDGE = 77;
	/** 要求寵物回報選單 */
	public static final int C_OPCODE_PETMENU = 78;
	/** 要求讀取公佈欄 */
	public static final int C_OPCODE_BOARD = 79;
	/** 要求角色移動 */
	public static final int C_OPCODE_MOVECHAR = 80;
	/** 要求死亡後重新開始 */
	public static final int C_OPCODE_RESTART = 81;
	/** 要求刪除角色 */
	public static final int C_OPCODE_DELETECHAR = 83;
	/** 要求物件對話視窗 */
	public static final int C_OPCODE_NPCTALK = 84;
	/** 要求損壞武器維修清單 */
	public static final int C_OPCODE_FIX_WEAPON_LIST = 85;
	/** 要求使用技能 */
	public static final int C_OPCODE_USESKILL = 86;
	/** 要求刪除物品 */
	public static final int C_OPCODE_DELETEINVENTORYITEM = 87;
	/** 要求紀錄快速鍵 */
	public static final int C_OPCODE_CHARACTERCONFIG = 88;
	/** 要求決鬥 */
	public static final int C_OPCODE_FIGHT = 89;
	/** 要求宣戰 */
	public static final int C_OPCODE_WAR = 90;
	/** 要求發送伺服器版本 */
	public static final int C_OPCODE_CLIENTVERSION = 92;
	/** 要求增加記憶座標 */
	public static final int C_OPCODE_BOOKMARK = 93;
	/** 要求學習魔法(金幣) */
	public static final int C_OPCODE_SKILLBUY = 95;
	/** 要求樂豆登入伺服器 */
	public static final int C_OPCODE_AUTO = 96;
	/** 請求 僱請傭兵列表(購買傭兵完成). */
	public static final int C_OPCODE_HIRESOLDIER = 97;
	/** 要求取得列表物品 */
	public static final int C_OPCODE_RESULT = 100;
	/** 要求創造角色 */
	public static final int C_OPCODE_NEWCHAR = 102;
	/** 要求閱讀佈告欄訊息 */
	public static final int C_OPCODE_BOARDREAD = 103;
	/** 要求釣魚收桿 */
	public static final int C_OPCODE_FISHCLICK = 104;
	/** 要求對話頻道控制 */
	public static final int C_OPCODE_LOGINTOSERVEROK = 111;
	/** 要求結婚 */
	public static final int C_OPCODE_PROPOSE = 112;
	/** 要求傳送進入地監*/
	public static final int C_OPCODE_ENTERPORTAL = 113;
	/** 要求個人商店物品列表 */
	public static final int C_OPCODE_PRIVATESHOPLIST = 114;
	/** 要求維修損壞武器 */
	public static final int C_OPCODE_SELECTLIST = 115;
	/** 要求公佈欄下一頁*/
	public static final int C_OPCODE_BOARDNEXT = 116;
	/** 請求設置城內治安管理OK. */
	public static final int C_OPCODE_SETCASTLESECURITY = 117; // XXX
	/** 要求踢出隊伍 */
	public static final int C_OPCODE_BANPARTY = 118;
	/** 要求新增好友 */
	public static final int C_OPCODE_ADDBUDDY = 119;
	/** 要求刪除記憶座標 */
	public static final int C_OPCODE_BOOKMARKDELETE = 120;
	/** 要求刪除好友 */
	public static final int C_OPCODE_DELETEBUDDY = 121;
	/** 要求賦予封號 */
	public static final int C_OPCODE_TITLE = 122;
	/** 要求給予物品 */
	public static final int C_OPCODE_GIVEITEM = 123;
	/** 請求 選擇 變更攻城時間. */
	public static final int C_OPCODE_SELECTWARTIME = 124;
	/** 要求開個人商店 */
	public static final int C_OPCODE_SHOP = 125;
	/** 要求登入伺服器 */
	public static final int C_OPCODE_LOGINPACKET = 126;
	/** 要求開關門 */
	public static final int C_OPCODE_DOOR = 127;
	/** 要求使用密語聊天頻道 */
	public static final int C_OPCODE_CHATWHISPER = 128;
	/** 要求傳送位置 */
	public static final int C_OPCODE_SENDLOCATION = 129;
	/** 要求邀請加入隊伍創立隊伍 */
	public static final int C_OPCODE_CREATEPARTY = 130;
	/** 請求聊天隊伍 */
	public static final int C_OPCODE_CAHTPARTY = 131;

	// 未知
	/** 請求 配置已僱用的士兵. */
	public static final int C_OPCODE_PUTSOLDIER = 3; // XXX
	/** 未使用 - 請求 配置已僱用的士兵OK. */
	public static final int C_OPCODE_PUTHIRESOLDIER = 5; // XXX
	/** 請求 配置城牆上的弓箭手OK. */
	public static final int C_OPCODE_PUTBOWSOLDIER = 7; // XXX
	/** 未使用 - 請求 進入遊戲(確定服務器登入訊息). */
	public static final int C_OPCODE_COMMONINFO = 9; // XXX

	/** 3.53C測試 Server Packet （服務端代碼） */

	/** 傳送點座標鎖定*/
	public static final int S_OPCODE_TELEPORT = 0;
	/** 敏捷提升封包 */
	public static final int S_OPCODE_DEXUP = 1;
	/** 正義值更新 */
	public static final int S_OPCODE_LAWFUL = 2;
	/** 角色狀態 */
	public static final int S_OPCODE_OWNCHARSTATUS2 = 3;
	/** 選取物品需求數量 */
	public static final int S_OPCODE_INPUTAMOUNT = 4;
	/** 封包盒子(多功能封包). */
	public static final int S_OPCODE_PACKETBOX = 5;
	/** 封包盒子(多功能封包). */
	public static final int S_OPCODE_ACTIVESPELLS = 5;
	/** 封包盒子(多功能封包). */
	public static final int S_OPCODE_SKILLICONGFX = 5;
	/** 伺服器版本 */
	public static final int S_OPCODE_SERVERVERSION = 6;
	/** 物件攻擊 */
	public static final int S_OPCODE_ATTACKPACKET = 7;
	/** 角色名稱變紫色 */
	public static final int S_OPCODE_PINKNAME = 8;
	/** 血盟戰爭訊息*/
	public static final int S_OPCODE_WAR = 9;
	/** 郵件封包 */
	public static final int S_OPCODE_MAIL = 10;
	/** 物件復活 */
	public static final int S_OPCODE_RESURRECTION = 11;
	/** 魔法物品效果 加速 */
	public static final int S_OPCODE_SKILLHASTE = 12;
	/** 效果圖示水底呼吸 */
	public static final int S_OPCODE_BLESSOFEVA = 13;
	/** 魔法效果 - 暗盲咒術*/
	public static final int S_OPCODE_CURSEBLIND = 14;
	/** 倉庫物品名單 */
	public static final int S_OPCODE_SHOWRETRIEVELIST = 15;
	/** 魔法 | 物品效果圖示 勇敢藥水 */
	public static final int S_OPCODE_SKILLBRAVE = 16;
	/** 取出城堡寶庫金幣 */
	public static final int S_OPCODE_DRAWAL = 17;
	/** 損壞武器名單 */
	public static final int S_OPCODE_SELECTLIST = 18;
	/** 血盟小屋名單 */
	public static final int S_OPCODE_HOUSELIST = 19;
	/** 物件外型改變 */
	public static final int S_OPCODE_POLY = 20;
	/** 魔力更新 */
	public static final int S_OPCODE_MPUPDATE = 21;
	/** 設置圍城時間. */
	public static final int S_OPCODE_WARTIME = 22;
	/** 佈告欄 訊息列表*/
	public static final int S_OPCODE_BOARD = 23;
	/** 改變物件名稱 */
	public static final int S_OPCODE_CHANGENAME = 24;
	/** 選項封包 */
	public static final int S_OPCODE_YES_NO = 25;
	/** 物件面向 */
	public static final int S_OPCODE_CHANGEHEADING = 26;
	/** 角色移除 */
	public static final int S_OPCODE_DETELECHAROK = 29;
	/** 初始化OpCode */
	public static final int S_OPCODE_INITPACKET = 30;
	/** 存入資金城堡寶庫 */
	public static final int S_OPCODE_DEPOSIT = 31;
	/** 未使用 - 閱讀郵件(舊). */
	public static final int S_OPCODE_LETTER = 33;
	/** 角色皇冠 */
	public static final int S_OPCODE_CASTLEMASTER = 34;
	/** 一般聊天頻道 */
	public static final int S_OPCODE_NORMALCHAT = 35;
	/** 海底波紋 */
	public static final int S_OPCODE_LIQUOR = 36;
	/** 增加交易物品封包 */
	public static final int S_OPCODE_TRADEADDITEM = 37;
	/** 商店販售清單物品 */
	public static final int S_OPCODE_SHOWSHOPBUYLIST = 38;
	/** 進入遊戲 */
	public static final int S_OPCODE_LOGINTOGAME = 40;
	/** 產生動畫 [ 地點 ] */
	public static final int S_OPCODE_EFFECTLOCATION = 44;
	/** 重置設定 */
	public static final int S_OPCODE_CHARRESET = 48;
	/** 寵物控制條 */
	public static final int S_OPCODE_PETCTRL = 48;
	/** 角色屬性與能力值 */
	public static final int S_OPCODE_OWNCHARSTATUS = 49;
	/** 公告視窗 */
	public static final int S_OPCODE_COMMONNEWS = 50;
	/** 魔法動畫 精準目標 */
	public static final int S_OPCODE_TRUETARGET = 51;
	/** 撥放音效 */
	public static final int S_OPCODE_SOUND = 52;
	/** 物品色彩 */
	public static final int S_OPCODE_ITEMCOLOR = 53;
	/** 立即中斷連線 */
	public static final int S_OPCODE_DISCONNECT = 54;
	/** 門開關屬性 */
	public static final int S_OPCODE_ATTRIBUTE = 57;
	/** 更新目前遊戲時間*/
	public static final int S_OPCODE_GAMETIME = 58;
	/** NPC物品販賣*/
	public static final int S_OPCODE_SHOWSHOPSELLLIST = 59;
	/** 角色列表 */
	public static final int S_OPCODE_CHARAMOUNT = 61;
	/** 切換角色武器外形狀態 */
	public static final int S_OPCODE_CHARVISUALUPDATE = 62;
	/** 角色封號 */
	public static final int S_OPCODE_CHARTITLE = 63;
	/** 戒指 */
	public static final int S_OPCODE_ABILITY = 64;
	/** 角色防禦屬性防禦 */
	public static final int S_OPCODE_OWNCHARATTRDEF = 65;
	/** 遊戲天氣 */
	public static final int S_OPCODE_WEATHER = 66;
	/** 物件移動 */
	public static final int S_OPCODE_MOVEOBJECT = 69;
	/** 物品資訊訊息 */
	public static final int S_OPCODE_IDENTIFYDESC = 70;
	/** 交易狀態 */
	public static final int S_OPCODE_TRADESTATUS = 71;
	/** 物品顯示名稱 */
	public static final int S_OPCODE_ITEMNAME = 72;
	/** 血盟小屋地圖*/
	public static final int S_OPCODE_HOUSEMAP = 74;
	/** 物品刪除 */
	public static final int S_OPCODE_DELETEINVENTORYITEM = 75;
	/** 角色盟徽 */
	public static final int S_OPCODE_EMBLEM = 76;
	/** 登入狀態*/
	public static final int S_OPCODE_LOGINRESULT = 77;
	/** 產生動畫*/
	public static final int S_OPCODE_SKILLSOUNDGFX = 78;
	/** NPC聊天*/
	public static final int S_OPCODE_NPCSHOUT = 79;
	/** 物品增加 */
	public static final int S_OPCODE_ADDITEM = 81;
	/** 角色創造事件 */
	public static final int S_OPCODE_NEWCHARWRONG = 83;
	/** 更新血盟數據. */
	public static final int S_OPCODE_UPDATECLANID = 84;
	/** 魔法攻擊力與魔法防禦力 */
	public static final int S_OPCODE_SPMR = 86;
	/** 力量提升封包 */
	public static final int S_OPCODE_STRUP = 87;
	/** Ping Time. */
	public static final int S_OPCODE_PINGTIME = 88;
	/** 物件隱形 & 現形 */
	public static final int S_OPCODE_INVIS = 89;
	/** 增加魔法 */
	public static final int S_OPCODE_ADDSKILL = 90;
	/** 產生對話視窗 */
	public static final int S_OPCODE_SHOWHTML = 91;
	/** 選擇一個目標 */
	public static final int S_OPCODE_SELECTTARGET = 92;
	/** 魔法效果 :中毒 */
	public static final int S_OPCODE_POISON = 93;
	/** 更新亮度 */
	public static final int S_OPCODE_LIGHT = 94;
	/** 交易視窗 */
	public static final int S_OPCODE_TRADE = 95;
	/** 稅收設定 */
	public static final int S_OPCODE_TAXRATE = 96;
	/** 創造角色 */
	public static final int S_OPCODE_NEWCHARPACK = 97;
	/** 體力更新 */
	public static final int S_OPCODE_HPUPDATE = 98;
	/** 閱讀佈告欄訊息*/
	public static final int S_OPCODE_BOARDREAD = 99;
	/** 使用地圖道具. */
	public static final int S_OPCODE_USEMAP = 100;
	/** 物件血條 */
	public static final int S_OPCODE_HPMETER = 101;
	/** 吉倫魔法學習購買清單*/
	public static final int S_OPCODE_SKILLBUY = 102;
	/** 角色座標名單 */
	public static final int S_OPCODE_BOOKMARKS = 103;
	/** 更新角色所在的地圖 */
	public static final int S_OPCODE_MAPID = 104;
	/** 更新現在的地圖 （水中）. */
	public static final int S_OPCODE_UNDERWATER = 104;
	/** 物件刪除 */
	public static final int S_OPCODE_REMOVE_OBJECT = 105;
	/** 廣播聊天頻道 */
	public static final int S_OPCODE_GLOBALCHAT = 106;
	/** 魔法效果 : 詛咒類 */
	public static final int S_OPCODE_PARALYSIS = 107;
	/** 角色資訊 */
	public static final int S_OPCODE_CHARLIST = 108;
	/** 移除魔法名單 */
	public static final int S_OPCODE_DELSKILL = 112;
	/** 範圍魔法 */
	public static final int S_OPCODE_RANGESKILLS = 113;
	/** 藍色訊息 { 使用String-h.tbl } */
	public static final int S_OPCODE_BLUEMESSAGE = 115;
	/** 物品狀態更新 */
	public static final int S_OPCODE_ITEMSTATUS = 116;
	/** 物品數量更新 */
	public static final int S_OPCODE_ITEMAMOUNT = 116;
	/** 經驗值更新封包 */
	public static final int S_OPCODE_EXP = 117;
	/** 物件新增主人. */
	public static final int S_OPCODE_NEWMASTER = 118;
	/** 物件動作*/
	public static final int S_OPCODE_DOACTIONGFX = 119;
	/** 物件 */
	public static final int S_OPCODE_CHARPACK = 120;
	/** 物件道具 */
	public static final int S_OPCODE_DROPITEM = 120;
	/** 魔法效果 : 防禦纇 */
	public static final int S_OPCODE_SKILLICONSHIELD = 121;
	/** 物品名單 */
	public static final int S_OPCODE_INVLIST = 123;
	/** 角色個人商店 { 購買 } */
	public static final int S_OPCODE_PRIVATESHOPLIST = 124;
	/** 伺服器訊息*/
	public static final int S_OPCODE_SERVERMSG = 125;
	/** 僱請傭兵(傭兵購買視窗). */
	public static final int S_OPCODE_HIRESOLDIER = 126;
	/** 要求使用密語聊天頻道 */
	public static final int S_OPCODE_WHISPERCHAT = 126;

	// 未知
	/** 未使用 - 修理武器清單. */
	public static final int S_OPCODE_FIX_WEAPON_MENU = 10;
	/** 未使用 - 配置城牆上的弓箭手列表(傭兵購買視窗). */
	public static final int S_OPCODE_PUTBOWSOLDIERLIST = 11;
	/** 可配置排列傭兵數(HTML)(EX:僱用的總傭兵數:XX 可排列的傭兵數:XX ). */
	public static final int S_OPCODE_PUTHIRESOLDIER = 13;
	/** 未使用 - 強制登出人物. */
	public static final int S_OPCODE_CHAROUT = 17;
	/** 未使用 - 畫面中紅色訊息(登入來源). */
	public static final int S_OPCODE_REDMESSAGE = 18;
}
