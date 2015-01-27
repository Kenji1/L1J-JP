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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L1Cycle {
	
	private final Duration period;
	
	private final Duration start;
	
	private final Duration end;
	
	public L1Cycle(Cycle cycle) throws Exception {
		period = initPeriod(cycle.getPeriod());
		start = initStart(cycle.getStart(), period);
		end = initEnd(cycle, period, start);
	}

	public Duration getPeriod() {
		return period;
	}

	public Duration getStart() {
		return start;
	}

	public Duration getEnd() {
		return end;
	}
	
	private static Duration initPeriod(String periodStr) throws Exception {
		if (periodStr != null) {
			Duration period = parse(periodStr);
			if (period.isZero() || period.isNegative()) {
				throw new Exception("Period must be more than 0.");
			}
			return period;
		} else {
			throw new Exception("Period Not Found.");
		}
	}
	
	private static Duration initStart(String startStr, Duration period) throws Exception {
		if (startStr != null) {
			Duration start = parse(startStr);
			if (start.isNegative()) {
				throw new Exception("Start must be 0 or more than 0.");
			} else if (start.compareTo(period) > 0) {
				throw new Exception("Start must be Period or less than Period.");
			}
			return start;
		} else {
			return Duration.ZERO;
		}
	}
	
	private static Duration initEnd(Cycle cycle, Duration period, Duration start) throws Exception {
		String endStr = cycle.getEnd();
		if (endStr != null) {
			Duration end = parse(endStr);
			if (end.isNegative()) {
				throw new Exception("End must be 0 or more than 0.");
			} else if (end.compareTo(period) > 0) {
				throw new Exception("End must be Period or less than Period.");
			} else if (end.compareTo(start) < 0) {
				throw new Exception("End must be Start or more than Start.");
			}
			return end;
		} else {
			return period;
		}
	}
	
	private static Duration parse(String str) {
		return Duration.ZERO
				.plusDays(getTimeParse(str, "d"))
				.plusHours(getTimeParse(str, "h"))
				.plusMinutes(getTimeParse(str, "m"));
	}

	private static int getTimeParse(final String target, final String search) {
		if (target == null) {
			return 0;
		}
		int n = 0;
		Matcher matcher = Pattern.compile("\\d+" + search).matcher(target);
		if (matcher.find()) {
			String match = matcher.group();
			n = Integer.parseInt(match.replace(search, ""));
		}
		return n;
	}
}
