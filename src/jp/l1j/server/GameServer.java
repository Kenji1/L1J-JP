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

package jp.l1j.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.logging.Logger;
import jp.l1j.configure.Config;
import jp.l1j.server.command.GMCommandsConfig;
import jp.l1j.server.controller.Announcements;
import jp.l1j.server.controller.AnnouncementsCycle;
import jp.l1j.server.controller.LoginController;
import jp.l1j.server.controller.ShutdownController;
import jp.l1j.server.controller.timer.AuctionTimeController;
import jp.l1j.server.controller.timer.FishingTimeController;
import jp.l1j.server.controller.timer.HomeTownTimeController;
import jp.l1j.server.controller.timer.HouseTaxTimeController;
import jp.l1j.server.controller.timer.LightTimeController;
import jp.l1j.server.controller.timer.NpcChatTimeController;
import jp.l1j.server.controller.timer.MapTimeController;
import jp.l1j.server.controller.timer.ShutdownTimeController;
import jp.l1j.server.controller.timer.UbTimeController;
import jp.l1j.server.controller.timer.WarTimeController;
import jp.l1j.server.datatables.CastleTable;
import jp.l1j.server.datatables.CharacterTable;
import jp.l1j.server.datatables.ChatLogTable;
import jp.l1j.server.datatables.ClanTable;
import jp.l1j.server.datatables.CookingRecipeTable;
import jp.l1j.server.datatables.DoorTable;
import jp.l1j.server.datatables.DropItemTable;
import jp.l1j.server.datatables.DropTable;
import jp.l1j.server.datatables.FurnitureSpawnTable;
import jp.l1j.server.datatables.GetBackRestartTable;
import jp.l1j.server.datatables.InnTable;
import jp.l1j.server.datatables.IpTable;
import jp.l1j.server.datatables.ItemTable;
import jp.l1j.server.datatables.MagicDollTable;
import jp.l1j.server.datatables.MailTable;
import jp.l1j.server.datatables.MapsTable;
import jp.l1j.server.datatables.MobGroupTable;
import jp.l1j.server.datatables.MobSkillTable;
import jp.l1j.server.datatables.NpcActionTable;
import jp.l1j.server.datatables.NpcChatTable;
import jp.l1j.server.datatables.NpcSpawnTable;
import jp.l1j.server.datatables.NpcTable;
import jp.l1j.server.datatables.NpcTalkDataTable;
import jp.l1j.server.datatables.PetTable;
import jp.l1j.server.datatables.PetTypeTable;
import jp.l1j.server.datatables.PolyTable;
import jp.l1j.server.datatables.RaceTicketTable;
import jp.l1j.server.datatables.ResolventTable;
import jp.l1j.server.datatables.ShopTable;
import jp.l1j.server.datatables.SkillTable;
import jp.l1j.server.datatables.SpawnTable;
import jp.l1j.server.datatables.SprListTable;
import jp.l1j.server.datatables.SprTable;
import jp.l1j.server.datatables.UbSpawnTable;
import jp.l1j.server.datatables.WeaponSkillTable;
import jp.l1j.server.model.Dungeon;
import jp.l1j.server.model.ElementalStoneGenerator;
import jp.l1j.server.model.Getback;
import jp.l1j.server.model.Instance.L1PcInstance;
import jp.l1j.server.model.L1BossCycle;
import jp.l1j.server.model.L1BugBearRace;
import jp.l1j.server.model.L1CastleLocation;
import jp.l1j.server.model.L1DeleteItemOnGround;
import jp.l1j.server.model.L1NpcRegenerationTimer;
import jp.l1j.server.model.L1World;
import jp.l1j.server.model.gametime.L1GameTimeClock;
import jp.l1j.server.model.item.executor.L1BeginnerItem;
import jp.l1j.server.model.item.executor.L1BlankScroll;
import jp.l1j.server.model.item.executor.L1BlessOfEva;
import jp.l1j.server.model.item.executor.L1BluePotion;
import jp.l1j.server.model.item.executor.L1BravePotion;
import jp.l1j.server.model.item.executor.L1CurePotion;
import jp.l1j.server.model.item.executor.L1Elixir;
import jp.l1j.server.model.item.executor.L1ExtraPotion;
import jp.l1j.server.model.item.executor.L1FireCracker;
import jp.l1j.server.model.item.executor.L1FloraPotion;
import jp.l1j.server.model.item.executor.L1Furniture;
import jp.l1j.server.model.item.executor.L1GreenPotion;
import jp.l1j.server.model.item.executor.L1HealingPotion;
import jp.l1j.server.model.item.executor.L1MagicEye;
import jp.l1j.server.model.item.executor.L1MagicPotion;
import jp.l1j.server.model.item.executor.L1Material;
import jp.l1j.server.model.item.executor.L1MaterialChoice;
import jp.l1j.server.model.item.executor.L1PolyPotion;
import jp.l1j.server.model.item.executor.L1PolyScroll;
import jp.l1j.server.model.item.executor.L1PolyWand;
import jp.l1j.server.model.item.executor.L1Roulette;
import jp.l1j.server.model.item.executor.L1ShowMessage;
import jp.l1j.server.model.item.executor.L1SpawnWand;
import jp.l1j.server.model.item.executor.L1SpeedUpClock;
import jp.l1j.server.model.item.executor.L1SpellScroll;
import jp.l1j.server.model.item.executor.L1SpellWand;
import jp.l1j.server.model.item.executor.L1TeleportAmulet;
import jp.l1j.server.model.item.executor.L1ThirdSpeedPotion;
import jp.l1j.server.model.item.executor.L1TreasureBox;
import jp.l1j.server.model.item.executor.L1UnknownMaliceWeapon;
import jp.l1j.server.model.item.executor.L1WisdomPotion;
import jp.l1j.server.model.map.L1WorldMap;
import jp.l1j.server.model.map.executor.L1MapTimer;
import jp.l1j.server.model.trap.L1WorldTraps;
import jp.l1j.server.utils.IdFactory;
import jp.l1j.server.utils.SystemUtil;


// Referenced classes of package jp.l1j.server:
// ClientThread, Logins, RateTable, IdFactory,
// LoginController, GameTimeController, Announcements,
// MobTable, SpawnTable, SkillsTable, PolyTable,
// TeleportLocations, ShopTable, NpcTalkDataTable, NpcSpawnTable,
// IpTable, Shutdown, NpcTable, MobGroupTable, NpcShoutTable

public class GameServer extends Thread {
	private ServerSocket _serverSocket;
	private static Logger _log = Logger.getLogger(GameServer.class.getName());
	private LoginController _loginController;
	private static int YesNoCount = 0;

	@Override
	public void run() {
		System.out.println("利用メモリ: " + SystemUtil.getUsedMemoryMB() + "MB");
		System.out.println("クライアント接続待機中...");
		while (true) {
			try {
				Socket socket = _serverSocket.accept();
				System.out.println("接続試行中IP " + socket.getInetAddress());
				String host = socket.getInetAddress().getHostAddress();
				if (IpTable.getInstance().isBannedIp(host)) {
					_log.info("banned IP(" + host + ")");
				} else {
					ClientThread client = new ClientThread(socket);
					GeneralThreadPool.getInstance().execute(client);
				}
			} catch (IOException ioexception) {
			}
		}
	}

	private static GameServer _instance;

	private GameServer() {
		super("GameServer");
	}

	public static GameServer getInstance() {
		if (_instance == null) {
			_instance = new GameServer();
		}
		return _instance;
	}

	private void puts(String message, Object... args) {
		String msg = (args.length == 0) ? message : String
				.format(message, args);
		System.out.println(msg);
	}

	private void printStartupMessage() {
		puts("サーバーセッティング: サーバーソケット生成");

		puts("┌───────────────────────────────┐");
		puts("│  Lineage 3.52C  開発　 By L1J For All User\t\t │");
		puts("└───────────────────────────────┘" + "\n");

		puts("●●●●〈サーバー設定〉●●●●");
		puts("");
		puts("┌「経験値」: %s【倍】", Config.RATE_XP);
		puts("├「アライメント」: %s【倍】", Config.RATE_LA);
		puts("├「カルマ」: %s【倍】", Config.RATE_KARMA);
		puts("├「ドロップ率」: %s【倍】", Config.RATE_DROP_ITEMS);
		puts("├「取得アデナ」: %s【倍】", Config.RATE_DROP_ADENA);
		puts("├「武器エンチャント成功率」: %s【%%】", Config.ENCHANT_CHANCE_WEAPON);
		puts("├「防具エンチャント成功率」: %s【%%】", Config.ENCHANT_CHANCE_ARMOR);
		puts("├「属性強化成功率」: %s【%%】", Config.ATTR_ENCHANT_CHANCE);
		puts("├「全体チャット可能Lv」: %s", Config.GLOBAL_CHAT_LEVEL);
		puts("├「Non-PvP設定」: %s", Config.ALT_NONPVP ? "無効（PvP可能）" : "有効（PvP不可）");
		puts("└「接続人数制限」： 最大%d人", Config.MAX_ONLINE_USERS);

		puts("┌───────────────────────────────┐");
		puts("│  Lineage 3.52C  開発　 By L1J For All User\t\t │");
		puts("└───────────────────────────────┘" + "\n");
	}

	public void initialize() throws Exception {
		printStartupMessage();

		String host = Config.GAME_SERVER_HOST_NAME;
		int port = Config.GAME_SERVER_PORT;
		if (!"*".equals(host)) {
			InetAddress inetaddress = InetAddress.getByName(host);
			inetaddress.getHostAddress();
			_serverSocket = new ServerSocket(port, 50, inetaddress);
		} else {
			_serverSocket = new ServerSocket(port);
		}

		IdFactory.getInstance();
		L1WorldMap.getInstance();
		_loginController = LoginController.getInstance();
		_loginController.setMaxAllowedOnlinePlayers(Config.MAX_ONLINE_USERS);

		// 全キャラクターネームロード
		CharacterTable.getInstance().loadAllCharName();

		// オンライン状態リセット
		CharacterTable.clearOnlineStatus();

		// ゲーム時間時計
		L1GameTimeClock.init();

		// UBタイムコントローラー
		UbTimeController ubTimeContoroller = UbTimeController.getInstance();
		GeneralThreadPool.getInstance().execute(ubTimeContoroller);

		// 戦争タイムコントローラー
		WarTimeController warTimeController = WarTimeController.getInstance();
		GeneralThreadPool.getInstance().execute(warTimeController);

		// 精霊の石生成
		if (Config.ELEMENTAL_STONE_AMOUNT > 0) {
			ElementalStoneGenerator elementalStoneGenerator = ElementalStoneGenerator
					.getInstance();
			GeneralThreadPool.getInstance().execute(elementalStoneGenerator);
		}

		// ホームタウン
		HomeTownTimeController.getInstance();

		// アジト競売タイムコントローラー
		AuctionTimeController auctionTimeController = AuctionTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(auctionTimeController);

		// アジト税金タイムコントローラー
		HouseTaxTimeController houseTaxTimeController = HouseTaxTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(houseTaxTimeController);

		// 釣りタイムコントローラー
		FishingTimeController fishingTimeController = FishingTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(fishingTimeController);

		// NPCチャットタイムコントローラー
		NpcChatTimeController npcChatTimeController = NpcChatTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(npcChatTimeController);

		// ライトタイムコントローラー
		LightTimeController lightTimeController = LightTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(lightTimeController);

		// マップタイムコントローラー
		MapTimeController mapTimeController = MapTimeController.getInstance();
		MapTimeController.load();
		GeneralThreadPool.getInstance().execute(mapTimeController);

		// 自動シャットダウンコントローラー
		if (Config.AUTO_SHUTDOWN) {
			ShutdownTimeController shutdownTimeController = ShutdownTimeController.getInstance();
			ShutdownTimeController.load();
			GeneralThreadPool.getInstance().execute(shutdownTimeController);
		}

		Announcements.getInstance();
		AnnouncementsCycle.getInstance();

		MobSkillTable.initialize();
		NpcTable.initialize();
		new L1DeleteItemOnGround().initialize();

		DoorTable.initialize();
		SpawnTable.getInstance();
		MobGroupTable.getInstance();
		SkillTable.initialize();
		PolyTable.getInstance();
		ItemTable.getInstance();
		DropTable.getInstance();
		DropItemTable.getInstance();
		ShopTable.getInstance();
		NpcTalkDataTable.getInstance();
		L1World.getInstance();
		L1WorldTraps.getInstance();
		Dungeon.getInstance();
		NpcSpawnTable.getInstance();
		IpTable.getInstance();
		MapsTable.getInstance();
		UbSpawnTable.getInstance();
		PetTable.getInstance();
		ClanTable.getInstance();
		CastleTable.getInstance();
		L1CastleLocation.setCastleTaxRate(); // これはCastleTable初期化後でなければいけない
		GetBackRestartTable.getInstance();
		// DoorSpawnTable.getInstance();
		GeneralThreadPool.getInstance();
		L1NpcRegenerationTimer.getInstance();
		ChatLogTable.getInstance();
		WeaponSkillTable.getInstance();
		NpcActionTable.load();
		GMCommandsConfig.load();
		Getback.loadGetBack();
		PetTypeTable.load();
		L1BossCycle.load();
		L1BeginnerItem.load();
		L1BlankScroll.load();
		L1BlessOfEva.load();
		L1BluePotion.load();
		L1BravePotion.load();
		L1CurePotion.load();
		L1Elixir.load();
		L1ExtraPotion.load();
		L1FireCracker.load();
		L1FloraPotion.load();
		L1Furniture.load();
		L1GreenPotion.load();
		L1HealingPotion.load();
		L1MagicEye.load();
		L1MagicPotion.load();
		L1MapTimer.load();
		L1Material.load();
		L1MaterialChoice.load();
		L1PolyPotion.load();
		L1PolyScroll.load();
		L1PolyWand.load();
		L1Roulette.load();
		L1ShowMessage.load();
		L1SpawnWand.load();
		L1SpeedUpClock.load();
		L1SpellScroll.load();
		L1SpellWand.load();
		L1TeleportAmulet.load();
		L1ThirdSpeedPotion.load();
		L1TreasureBox.load();
		L1UnknownMaliceWeapon.load();
		L1WisdomPotion.load();
		SprTable.getInstance();
		ResolventTable.getInstance();
		FurnitureSpawnTable.getInstance();
		NpcChatTable.getInstance();
		MailTable.getInstance();
		SprListTable.getInstance();
		RaceTicketTable.getInstance();
		L1BugBearRace.getInstance();
		InnTable.getInstance();
		MagicDollTable.getInstance();
		CookingRecipeTable.initialize();
		System.out.println("ローディング完了");
		Runtime.getRuntime().addShutdownHook(ShutdownController.getInstance());

		this.start();
	}

	/**
	 * オンライン中のプレイヤー全てに対してkick、キャラクター情報の保存をする。
	 */
	public void disconnectAllCharacters() {
		Collection<L1PcInstance> players = L1World.getInstance()
				.getAllPlayers();
		for (L1PcInstance pc : players) {
			pc.getNetConnection().setActiveChar(null);
			pc.getNetConnection().kick();
		}
		// 全員Kickした後に保存処理をする
		for (L1PcInstance pc : players) {
			ClientThread.quitGame(pc);
			L1World.getInstance().removeObject(pc);
		}
	}

	private class ServerShutdownThread extends Thread {
		private final int _secondsCount;

		public ServerShutdownThread(int secondsCount) {
			_secondsCount = secondsCount;
		}

		@Override
		public void run() {
			L1World world = L1World.getInstance();
			try {
				int secondsCount = _secondsCount;
				world.broadcastServerMessage("ただいまより、サーバーをシャットダウンします。");
				world.broadcastServerMessage("安全な場所でログアウトしてください");
				while (0 < secondsCount) {
					if (secondsCount <= 30) {
						world.broadcastServerMessage("ゲームが" + secondsCount
								+ "秒後にシャットダウンします。ゲームを中断してください。");
					} else {
						if (secondsCount % 60 == 0) {
							world.broadcastServerMessage("ゲームが" + secondsCount
									/ 60 + "分後にシャットダウンします。");
						}
					}
					Thread.sleep(1000);
					secondsCount--;
				}
				shutdown();
			} catch (InterruptedException e) {
				world.broadcastServerMessage("シャットダウンが中断されました。サーバーは通常稼動中です。");
				return;
			}
		}
	}

	private ServerShutdownThread _shutdownThread = null;

	public synchronized void shutdownWithCountdown(int secondsCount) {
		if (_shutdownThread != null) {
			// 既にシャットダウン要求が行われている
			// TODO エラー通知が必要かもしれない
			return;
		}
		_shutdownThread = new ServerShutdownThread(secondsCount);
		GeneralThreadPool.getInstance().execute(_shutdownThread);
	}

	public void shutdown() {
		disconnectAllCharacters();
		System.exit(0);
	}

	public synchronized void abortShutdown() {
		if (_shutdownThread == null) {
			// シャットダウン要求が行われていない
			// TODO エラー通知が必要かもしれない
			return;
		}

		_shutdownThread.interrupt();
		_shutdownThread = null;
	}

	public static int getYesNoCount() {
		YesNoCount += 1;
		return YesNoCount;
	}
}