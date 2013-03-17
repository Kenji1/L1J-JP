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

package jp.l1j.locale;

import java.util.ResourceBundle;

public class I18N {
	
	private static XMLResourceBundleControl control = new XMLResourceBundleControl();
	private static ResourceBundle bundle = ResourceBundle.getBundle("./locale/lang", control);

	/*
	 * jp.l1j.server.GameServer.java
	 */
	public static final String I18N_MEMORY_USEAGE = bundle.getString("MemoryUsage");
	public static final String I18N_WAITING_FOR_CLIENT = bundle.getString("WaittingForClient");
	public static final String I18N_TRYING_TO_CONNECTION = bundle.getString("TryingToConnection");
	public static final String I18N_BANNED_IP = bundle.getString("BannedIP");
	public static final String I18N_GENERATE_SERVER_SOCKET = bundle.getString("GenerateServerSocket");
	public static final String I18N_SERVER_SETTINGS = bundle.getString("ServerSettings");
	public static final String I18N_EXP = bundle.getString("Exp");
	public static final String I18N_LAWFUL = bundle.getString("Lawful");
	public static final String I18N_KARMA = bundle.getString("Karma");
	public static final String I18N_ITEM_DROP = bundle.getString("ItemDrop");
	public static final String I18N_ADENA_DROP = bundle.getString("AdenaDrop");
	public static final String I18N_ENCHANT_WEAPON = bundle.getString("EnchantWeapon");
	public static final String I18N_ENCHANT_ARMOR = bundle.getString("EnchantArmor");
	public static final String I18N_ENCHANT_ATTRIBUTE = bundle.getString("EnchantAttribute");
	public static final String I18N_GLOBAL_CHAT = bundle.getString("GlobalChat");
	public static final String I18N_PVP = bundle.getString("PvP");
	public static final String I18N_NON_PVP = bundle.getString("NonPvP");
	public static final String I18N_MAX_USERS = bundle.getString("MaxUsers");
	public static final String I18N_LOADING_COMPLETE = bundle.getString("LoadingComplete");
	public static final String I18N_SHUTDOWN_THE_SERVER = bundle.getString("ShutdownTheServer");
	public static final String I18N_PLEASE_LOGOUT = bundle.getString("PleaseLogout");
	public static final String I18N_SHUTDOWN_AFTER_FEW_MINUTES = bundle.getString("ShutdownAfterFewMinutes");
	public static final String I18N_SHUTDOWN_AFTER_FEW_SECONDS = bundle.getString("ShutdownAfterFewSeconds");
	public static final String I18N_SHUTDOWN_ABORT = bundle.getString("ShutdownAbort");
		
}
