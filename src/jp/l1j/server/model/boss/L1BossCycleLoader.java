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

package jp.l1j.server.model.boss;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import jp.l1j.server.datatables.BossSpawnTable;
import jp.l1j.server.utils.PerformanceTimer;

public class L1BossCycleLoader {
	
	private static final Logger LOG = Logger.getLogger(L1BossCycleLoader.class.getName());
	
	private static final L1BossCycleLoader instance = new L1BossCycleLoader();
	
	private static final LocalDate initDate = LocalDate.now();
	
	private final Map<String, L1BossCycle> bossCycles = new HashMap<>();
	
	public static L1BossCycleLoader getInstance() {
		return instance;
	}
	
	private L1BossCycleLoader() {
		
	}

	/**
	 * ボス周期を XML から読み込む
	 * サーバー起動時は reload() ではなくこちらを使用
	 */
	public synchronized void load() {
		PerformanceTimer timer = new PerformanceTimer();
		readFile();
		// spawn_boss_mobsから読み込んで配置
		BossSpawnTable.fillSpawnTable();
		System.out.println("loading boss cycles...OK! " + timer.elapsedTimeMillis() + "ms");
	}
	
	/**
	 * 現在のボス周期を破棄し新たにXML から読み込む
	 */
	public synchronized void reload() {
		PerformanceTimer timer = new PerformanceTimer();
		readFile();
		System.out.println("loading boss cycles...OK! " + timer.elapsedTimeMillis() + "ms");
	}
	
	private void readFile() {
		try {
			bossCycles.clear();
			// BookOrder クラスをバインディングするコンテキストを生成
			JAXBContext context = JAXBContext.newInstance(BossCycleList.class);
			// XML -> POJO 変換を行うアンマーシャラを生成
			Unmarshaller um = context.createUnmarshaller();
			// XML -> POJO 変換
			File file = new File("./data/xml/Cycle/BossCycle.xml");
			BossCycleList bossList = (BossCycleList) um.unmarshal(file);
			for (BossCycle c : bossList.getBossCycles()) {
				L1Base base = new L1Base(c.getBase(), initDate);
				L1Cycle cycle = new L1Cycle(c.getCycle());
				L1BossCycle bossCycle = new L1BossCycle(c.getName(), base.getBaseTime(), cycle.getPeriod(), cycle.getStart(), cycle.getEnd());
				bossCycles.put(c.getName(), bossCycle);
			}
			// userデータがあれば上書き
			File userFile = new File("./data/xml/Cycle/users/BossCycle.xml");
			if (userFile.exists()) {
				bossList = (BossCycleList) um.unmarshal(userFile);
				for (BossCycle c : bossList.getBossCycles()) {
					L1Base base = new L1Base(c.getBase(), initDate);
					L1Cycle cycle = new L1Cycle(c.getCycle());
					L1BossCycle bossCycle = new L1BossCycle(c.getName(), base.getBaseTime(), cycle.getPeriod(), cycle.getStart(), cycle.getEnd());
					bossCycles.put(c.getName(), bossCycle);
				}
			}
		} catch (Exception e) {
			LOG.log(Level.SEVERE, "BossCycleを読み込めませんでした", e);
			System.exit(0);
		}
	}
	
	/**
	 * その名前のボス周期を返す
	 * @param name
	 * @return ボス周期
	 */
	public synchronized L1BossCycle getBossCycle(String name) {
		return bossCycles.get(name);
	}
}
