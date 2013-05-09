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
package jp.l1j.server.packets.server;

import jp.l1j.configure.Config;
import jp.l1j.server.codes.Opcodes;

public class S_ServerVersion extends ServerBasePacket {
	private static final String S_SERVER_VERSION = "[S] ServerVersion";

	private static final int CLIENT_LANGUAGE = Config.CLIENT_LANGUAGE;

	/** システム時間検証 */
	// private static final int UPTIME = 1327204035;
	private static final int UPTIME = (int) (System.currentTimeMillis() / 1000); // TODO

	/** サーバーバージョン */
	// private static final int SERVER_VERSION = 12022011; // 3.52C
	private static final int SERVER_VERSION = 120913203; // 3.53C

	/** キャッシュバージョン */
	// private static final int CACHE_VERSION = 12021603; // 3.52C
	private static final int CACHE_VERSION = 120913200; // 3.53C

	/** 認証バージョン */
	private static final int AUTH_VERSION = 2010083002;

	/** NPCバージョン */
	// private static final int NPC_VERSION = 12020302; // 3.52C
	private static final int NPC_VERSION = 120913201; // 3.53C

	public S_ServerVersion() {
		writeC(Opcodes.S_OPCODE_SERVERVERSION);
		
		// Auth Check client Version
		// 1 = Check
		// 0 = no check
		// > 1 no check
		// type : boolean
		writeC(0x00);
		
		// your server id, first id = 2
		// id = 0, ????
		// id = 1, ????
		writeC(0x00);

		// all versions
		// If the user level is a administrator,
		// inputs /ver to be able to print out all version in game
		// If the user level isn't a administrator
		// inputs /ver to be able to print out client version in game	
		writeD(SERVER_VERSION);
		writeD(CACHE_VERSION);
		writeD(AUTH_VERSION);
		writeD(NPC_VERSION);
		
		// system time (old)
		// writeD(UPTIME); // 3.52C
		writeD(0x00000000); // 3.53C
		
		// unknown
		writeC(0x00);// 未解析パケット
		writeC(0x00);// 未解析パケット
		
		// Country
		// 0.US 3.Taiwan 4.Janpan 5.China
		writeC(CLIENT_LANGUAGE);
		
		// unknown
		writeD(490882);// 未解析パケット
		
		// system time (new)
		writeD(UPTIME); // 3.53C
	}

	@Override
	public byte[] getContent() {
		return getBytes();
	}

	@Override
	public String getType() {
		return S_SERVER_VERSION;
	}
}
