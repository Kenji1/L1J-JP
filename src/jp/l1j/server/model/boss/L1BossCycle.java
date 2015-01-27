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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class L1BossCycle {
	
	private final String name;
	
	private final LocalDateTime baseTime;
	
	private final Duration period;
	
	private final Duration start;
	
	private final Duration end;

	/**
	 * 新規のボス周期を生成する。
	 * @param name ボス周期の名前 (Caspa, DK など)
	 * @param baseTime 基準時刻
	 * @param period 周期の間隔
	 * @param start スポーンを開始するまでの時間
	 * @param end スポーンを終了するまでの時間
	 */
	public L1BossCycle(String name, LocalDateTime baseTime, Duration period, Duration start, Duration end) {
		this.name = name;
		this.baseTime = baseTime;
		this.period = period;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * ボスがスポーンする時刻かどうかを返す
	 * 時刻が現在の周期の start と end の間にあるかどうかを調べる。
	 * @param time 時刻
	 * @return ボスがスポーンする時刻であれば true, そうでなければ false
	 */
	public boolean inSpawnTime(LocalDateTime time) {
		LocalDateTime currentSpawnStartTime = spawnStartTime(currentCycleStartTime(time));
		LocalDateTime currentSpawnEndTime = spawnEndTime(currentCycleStartTime(time));
		return (time.isEqual(currentSpawnStartTime) || time.isAfter(currentSpawnStartTime))
				&& (time.isEqual(currentSpawnEndTime) || time.isBefore(currentSpawnEndTime));
	}
	
	/**
	 * 時刻を次の周期まで進めたものを返す
	 * 
	 * (現在の周期のスポーンが開始する時刻が 0:00 で
	 * 周期の間隔が 30m なら
	 * 次の周期のスポーンが開始する時刻は 0:30)
	 * @param time 時刻
	 * @return 周期の長さを足した時刻
	 */
	public LocalDateTime nextTime(LocalDateTime time) {
		return time.plus(period);
	}
	
	/**
	 * 現在いる周期の開始時刻を返す
	 * @param now 現在時刻
	 * @return 周期の開始時刻
	 */
	public LocalDateTime currentCycleStartTime(LocalDateTime now) {
		LocalDateTime dateTime = now.minusNanos(baseTime.until(now, ChronoUnit.NANOS) % period.toNanos());
		if (now.isAfter(spawnEndTime(dateTime))) {
			dateTime = nextTime(dateTime);
		}
		return dateTime;
	}
	
	/**
	 * その周期のスポーンが開始する時刻を返す
	 * @param cycleStartTime 周期が開始する時刻
	 * @return スポーンが開始する時刻
	 */
	public LocalDateTime spawnStartTime(LocalDateTime cycleStartTime) {
		return cycleStartTime.plus(start);
	}
	
	/**
	 * その周期のスポーンが終了する時刻を返す
	 * @param cycleStartTime 周期が開始する時刻
	 * @return スポーンが終了する時刻
	 */
	public LocalDateTime spawnEndTime(LocalDateTime cycleStartTime) {
		return cycleStartTime.plus(end);
	}
	
	public String getName() {
		return name;
	}
}
