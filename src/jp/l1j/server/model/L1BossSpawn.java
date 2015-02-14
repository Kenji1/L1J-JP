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

package jp.l1j.server.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import jp.l1j.configure.Config;
import jp.l1j.server.GeneralThreadPool;
import jp.l1j.server.model.boss.L1BossCycle;
import jp.l1j.server.model.boss.L1BossCycleLoader;
import jp.l1j.server.templates.L1Npc;

public class L1BossSpawn extends L1Spawn {
	
	private String cycleType;
	
	private int probability;
	
	private int bossAmount;
	
	private LocalDateTime lastStartTime;

	public L1BossSpawn(L1Npc mobTemplate) {
		super(mobTemplate);
	}

	@Override
	public void executeSpawnTask(int spawnNumber, int objectId) {
		/*
		ボスモンスターが死んだときにこのメソッドが呼ばれる。
		正確には ボスモンスターのインスタンスの onDecay() が呼ばれたとき。
		*/
		
		// bossAmount をデクリメントしつつ すべてのモンスターが死んだかどうかを確認する。
		if (currentBossAmount() > 0) {
			return;
		}
		
		schedule(false, spawnNumber, objectId);
	}

	@Override
	public void init() {
		if (probability <= 0) {
			return;
		}
		schedule(true, 0, 0);
	}
	
	private void schedule(boolean init, int spawnNumber, int objectId) {
		// リロードされた時のために 毎回ボス周期を取りに行く
		L1BossCycle bossCycle = L1BossCycleLoader.getInstance().getBossCycle(cycleType);
		if (bossCycle == null) {
			throw new RuntimeException(cycleType + " not found");
		}
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime start = bossCycle.currentTimeStartTime(now);
		LocalDateTime end = bossCycle.timeEndTime(start);
		if (probability()) {
			if ((lastStartTime != null && lastStartTime.isEqual(start))
					|| (init && !Config.INIT_BOSS_SPAWN && isOnTime(now, start, end))) {
				start = bossCycle.nextTime(start);
				end = bossCycle.nextTime(end);
			}
		} else {
			do {
				start = bossCycle.nextTime(start);
				end = bossCycle.nextTime(end);
			} while (!probability());
		}
		
		lastStartTime = start;
		
		/* 
		現在時刻が start より後ならば 現在時刻からスポーンを開始する。
		その場合，下にある
		sleepTime = now.until(start, ChronoUnit.MILLIS)
		は，0を返す。
		*/
		if (now.isAfter(start)) {
			start = now;
		}
		long sleepTime;
		sleepTime = now.until(start, ChronoUnit.MILLIS);
		sleepTime += random(start, end, ChronoUnit.MILLIS);
		
		GeneralThreadPool.getInstance().schedule(() -> {
			for (int i = 0; i < getAmount(); ++i) {
				doSpawn(spawnNumber, objectId);
			}
			bossAmount = getAmount();
		}, sleepTime);
	}
	
	private boolean probability() {
		return Math.floor(Math.random() * 100) + 1 <= probability;
	}
	
	private synchronized int currentBossAmount() {
		return --bossAmount;
	}
	
	/**
	 * time が ボスタイムかどうかを返す
	 * 時刻が start と end の間にあるかどうかを調べる。
	 * @param time 時刻
	 * @return ボスタイムであれば true, そうでなければ false
	 */
	private static boolean isOnTime(LocalDateTime time, LocalDateTime start, LocalDateTime end) {
		return (time.isEqual(start) || time.isAfter(start))
				&& (time.isEqual(end) || time.isBefore(end));
	}
	
	/**
	 * start から end の間のランダムな時刻までの時間を返す。
	 * @param start 始点
	 * @param end 終点
	 * @param temporalUnit 時間の単位
	 * @return 時間
	 */
	private static long random(LocalDateTime start, LocalDateTime end, TemporalUnit temporalUnit) {
		return (long) Math.floor(Math.random() * start.until(end, temporalUnit));
	}

	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}

	public void setPercentage(int probability) {
		this.probability = probability;
	}
}
