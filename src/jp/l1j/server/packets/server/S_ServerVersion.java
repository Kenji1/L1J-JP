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
//	private static final int uptime = (int) (System.currentTimeMillis() / 1000); // 3.5C
	/** . */
	private static final int UPTIME = 1327204035; // (int) (System.currentTimeMillis() / 1000);
	/** 服务器版本. */
	private static final int SERVER_VERSION = 12022011;
	/** 缓存版本. */
	private static final int CACHE_VERSION = 12021603;
	/** 认证(身份验证)版本. */
	private static final int AUTH_VERSION = 2010083002;
	/** NPC版本. */
	private static final int NPC_VERSION = 12020302;

/*
 * [Server] opcode = 89 0000: 59 00 02/ c9 60 01 00/ 6a 60 01 00/ 01 ee 00 00/
 * db Y...`..j`....... 0010: 3c 01 00/ ec 66 c4 49/ 00 00 03 58 0d 00 00 10 5f
 * <...f.I...X...._
 */
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
		writeC(0x02);

		// all version
		// If the user level is a administrator,
		// inputs /ver to be able to print out all version in game
		// If the user level isn't a administrator
		// inputs /ver to be able to print out client version in game
		// writeD(0x00009D7C); // server verion // 2.70C
		// writeD(0x0000791A); // cache verion // 2.70C
		// writeD(0x0000791A); // auth verion // 2.70C
		// writeD(0x00009DD1); // npc verion // 2.70C
		// writeD(0x000160c9); // server verion 3.0c
		// writeD(0x0001606a); // cache verion 3.0c
		// writeD(0x0000ee01); // auth verion 3.0c
		// writeD(0x00013cdb); // npc verion 3.0c
		// writeD(0x009a45e9); // server verion 3.3C
		// writeD(0x009a44a9); // cache verion 3.3C
		// writeD(0x77cf6eba); // auth verion 3.3C
		// writeD(0x00018978); // npc verion 3.3C
//		writeD(0x00a8c732); // server verion 3.5C
//		writeD(0x00a8c6a7); // cache verion 3.5C
//		writeD(0x77cf6eba); // auth verion 3.5C
//		writeD(0x00a8cdad); // npc verion 3.5C
		writeD(SERVER_VERSION); // 3.52TW server version
        writeD(CACHE_VERSION); // 3.52TW cache version
        writeD(AUTH_VERSION); // 3.52TW auth version
        writeD(NPC_VERSION); // 3.52TW npc version
        writeD(UPTIME);

		// ↓ unknown
		// ↓ Old 270Lin.bin
		// ↓ New 270Lin.bin
		// ↓ isn't game time
		// writeD(0x882a2cc6); // 2.70C
		// writeD(0x49c466ec); // 3.0c
		// writeD(0x4cf07bc0); // 3.3C
//		writeD(uptime); // 3.5C

		writeC(0x00); // unknown
		writeC(0x00); // unknown

		// Country
		// 0.US 3.Taiwan 4.Janpan 5.China
		writeC(CLIENT_LANGUAGE);
//		writeD(0x00000000);
		// unknown
//		writeC(0xae); // 3.5C
//        writeC(0xb2); // 3.5C
		writeC(0x00);
        writeC(0x00);
        writeC(0x00);
        writeC(0x00);
        writeC(0x08);
        writeC(0x00);
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
