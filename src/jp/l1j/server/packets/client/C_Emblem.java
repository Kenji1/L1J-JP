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

package jp.l1j.server.packets.client;

import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.ClientThread;
import jp.l1j.server.model.instance.L1PcInstance;
import jp.l1j.server.model.L1World;
import jp.l1j.server.packets.server.S_Emblem;

// Referenced classes of package jp.l1j.server.clientpackets:
// ClientBasePacket

public class C_Emblem extends ClientBasePacket {

	private static final String C_EMBLEM = "[C] C_Emblem";
	private static Logger _log = Logger.getLogger(C_Emblem.class.getName());

	public C_Emblem(byte abyte0[], ClientThread clientthread)
			throws Exception {
		super(abyte0);

		L1PcInstance player = clientthread.getActiveChar();
		if (player.getClanid() != 0) {
			String emblem_file = String.valueOf(player.getClanid());

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream("emblem/" + emblem_file);
				for (short cnt = 0; cnt < 384; cnt++) {
					fos.write(readC());
				}
			} catch (Exception e) {
				_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
				throw e;
			} finally {
				if (null != fos) {
					fos.close();
				}
				fos = null;
			}
			player.sendPackets(new S_Emblem(player.getClanid()));
			L1World.getInstance().broadcastPacketToAll(new S_Emblem(player.getClanid()));
		}
	}

	@Override
	public String getType() {
		return C_EMBLEM;
	}
}
