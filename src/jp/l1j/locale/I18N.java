/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.l1j.locale;

import java.util.ResourceBundle;

/**
 *
 * @author Administrator
 */
public class I18N {
	
	private static XMLResourceBundleControl control = new XMLResourceBundleControl();
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("./locale/lang", control);

	/**
	 * <li>ja_JP 利用メモリ: %s MB</li>
	 * <li>en_US Memory usage: %s MB</li>
	 * <li>zh_TW 記憶體使用量: %s MB</li>
	 * <li>zh_CN 内存使用量: %s MB</li>
	 */
	public static final String I18N_MEMORY_USEAGE = bundle.getString("MemoryUsage");
	
	/**
	 * <li>ja_JP クライアント接続待機中...</li>
	 * <li>en_US Waiting for client connection...</li>
	 * <li>zh_TW 正在等待用戶端連線...</li>
	 * <li>zh_CN 正在等待用户连接...</li>
	 */
	public static final String I18N_WAITING_FOR_CLIENT = bundle.getString("WaittingForClient");
	
	/**
	 * <li>ja_JP %s 接続試行中</li>
	 * <li>en_US %s Try to connect</li>
	 * <li>zh_TW %s 正在嘗試連線</li>
	 * <li>zh_CN %s 正在尝试连线</li>
	 */
	public static final String I18N_TRYING_TO_CONNECTION = bundle.getString("TryingToConnection");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US Banned IP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_BANNED_IP = bundle.getString("BannedIP");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_GENERATE_SERVER_SOCKET = bundle.getString("GenerateServerSocket");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_SERVER_SETTINGS = bundle.getString("ServerSettings");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_EXP = bundle.getString("Exp");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_LAWFUL = bundle.getString("Lawful");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_KARMA = bundle.getString("Karma");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_ITEM_DROP = bundle.getString("ItemDrop");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_ADENA_DROP = bundle.getString("AdenaDrop");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_ENCHANT_WEAPON = bundle.getString("EnchantWeapon");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_ENCHANT_ARMOR = bundle.getString("EnchantArmor");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_ENCHANT_ATTRIBUTE = bundle.getString("EnchantAttribute");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_GLOBAL_CHAT = bundle.getString("GlobalChat");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_PVP = bundle.getString("PvP");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_NON_PVP = bundle.getString("NonPvP");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_MAX_USERS = bundle.getString("MaxUsers");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_LOADING_COMPLETE = bundle.getString("LoadingComplete");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_SHUTDOWN_THE_SERVER = bundle.getString("ShutdownTheServer");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_PLEASE_LOGOUT = bundle.getString("PleaseLogout");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_SHUTDOWN_AFTER_FEW_MINUTES = bundle.getString("ShutdownAfterFewMinutes");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_SHUTDOWN_AFTER_FEW_SECONDS = bundle.getString("ShutdownAfterFewSeconds");
	
	/**
	 * <li>ja_JP 禁止IP(%s)</li>
	 * <li>en_US BannedIP(%s)</li>
	 * <li>zh_TW 禁止IP(%s)</li>
	 * <li>zh_CN 禁止IP(%s)</li>
	 */
	public static final String I18N_SHUTDOWN_ABORT = bundle.getString("ShutdownAbort");
		
}
