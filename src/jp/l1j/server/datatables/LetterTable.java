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

package jp.l1j.server.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.l1j.server.utils.L1DatabaseFactory;
import jp.l1j.server.utils.SqlUtil;

public class LetterTable {
	private static Logger _log = Logger.getLogger(LetterTable.class.getName());

	private static LetterTable _instance;

	public LetterTable() {
	}

	public static LetterTable getInstance() {
		if (_instance == null) {
			_instance = new LetterTable();
		}
		return _instance;
	}

	// テンプレートID一覧
	// 16:キャラクターが存在しない
	// 32:荷物が多すぎる
	// 48:血盟が存在しない
	// 64:※内容が表示されない(白字)
	// 80:※内容が表示されない(黒字)
	// 96:※内容が表示されない(黒字)
	// 112:おめでとうございます。%nあなたが参加された競売は最終価格%0アデナの価格で落札されました。
	// 128:あなたが提示された金額よりももっと高い金額を提示した方が現れたため、残念ながら入札に失敗しました。
	// 144:あなたが参加した競売は成功しましたが、現在家を所有できる状態にありません。
	// 160:あなたが所有していた家が最終価格%1アデナで落札されました。
	// 176:あなたが申請なさった競売は、競売期間内に提示した金額以上での支払いを表明した方が現れなかったため、結局取り消されました。
	// 192:あなたが申請なさった競売は、競売期間内に提示した金額以上での支払いを表明した方が現れなかったため、結局取り消されました。
	// 208:あなたの血盟が所有している家は、本領主の領地に帰属しているため、今後利用したいのなら当方に税金を収めなければなりません。
	// 224:あなたは、あなたの家に課せられた税金%0アデナをまだ納めていません。
	// 240:あなたは、結局あなたの家に課された税金%0を納めなかったので、警告どおりにあなたの家に対する所有権を剥奪します。

	public void writeLetter(int itemObjectId, int code, String sender, String receiver,
			String date, int templateId, byte[] subject, byte[] content) {
		Connection con = null;
		PreparedStatement pstm1 = null;
		ResultSet rs = null;
		PreparedStatement pstm2 = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm1 = con.prepareStatement("SELECT * FROM letters ORDER BY item_obj_id");
			rs = pstm1.executeQuery();
			pstm2 = con.prepareStatement("INSERT INTO letters SET item_obj_id=?, code=?, sender=?, receiver=?, date=?, template_id=?, subject=?, content=?");
			pstm2.setInt(1, itemObjectId);
			pstm2.setInt(2, code);
			pstm2.setString(3, sender);
			pstm2.setString(4, receiver);
			pstm2.setString(5, date);
			pstm2.setInt(6, templateId);
			pstm2.setBytes(7, subject);
			pstm2.setBytes(8, content);
			pstm2.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SqlUtil.close(rs);
			SqlUtil.close(pstm1);
			SqlUtil.close(pstm2);
			SqlUtil.close(con);
		}
	}

	public void deleteLetter(int itemObjectId) {
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			pstm = con.prepareStatement("DELETE FROM letters WHERE item_obj_id=?");
			pstm.setInt(1, itemObjectId);
			pstm.execute();
		} catch (SQLException e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		} finally {
			SqlUtil.close(pstm);
			SqlUtil.close(con);
		}
	}
}
