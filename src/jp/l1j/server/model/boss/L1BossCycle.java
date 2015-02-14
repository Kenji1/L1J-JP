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
	 * @param start タイムが開始するまでの時間
	 * @param end タイムが終了するまでの時間
	 */
	public L1BossCycle(String name, LocalDateTime baseTime, Duration period, Duration start, Duration end) {
		this.name = name;
		this.baseTime = baseTime;
		this.period = period;
		this.start = start;
		this.end = end;
	}
	
	/**
	 * 時刻を次の周期まで進めたものを返す
	 * 
	 * (現在のタイムが開始する時刻が 0:17 で
	 * 周期の間隔が 30m なら
	 * 次のタイムが開始する時刻は 0:47)
	 * @param time 時刻
	 * @return 周期の長さを足した時刻
	 */
	public LocalDateTime nextTime(LocalDateTime time) {
		return time.plus(period);
	}
	
	/**
	 * まだ終了時刻を過ぎておらず，現在時刻に最も近いタイム が開始する時刻を返す
	 * @param now 現在時刻
	 * @return タイムが開始する時刻
	 */
	public LocalDateTime currentTimeStartTime(LocalDateTime now) {
		LocalDateTime timeStartTime = now.minusNanos(baseTime.until(now, ChronoUnit.NANOS) % period.plus(start).toNanos());
		if (timeEndTime(timeStartTime).isBefore(now)) {
			timeStartTime = nextTime(timeStartTime);
		}
		return timeStartTime;
	}
	
	/**
	 * そのタイムのスポーンが終了する時刻を返す
	 * @param timeStartTime タイムが開始する時刻
	 * @return スポーンが終了する時刻
	 */
	public LocalDateTime timeEndTime(LocalDateTime timeStartTime) {
		return timeStartTime.plus(end).minus(start);
	}
	
	public String getName() {
		return name;
	}
}
