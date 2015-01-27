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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class L1Base {

	private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy/M/d");

	private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("H:m");
	
	private final LocalDateTime baseTime;
	
	public L1Base(Base base, LocalDate initDate) {
		if (base == null) {
			base = new Base();
		}
		this.baseTime = makeBaseTime(base.getDate(), base.getTime(), base.getDay(), initDate);
	}
	
	public LocalDateTime getBaseTime() {
		return baseTime;
	}
	
	private static LocalDateTime makeBaseTime(String dateStr, String timeStr, String dayStr, LocalDate initDate) {
		LocalDate date;
		try {
			date = LocalDate.parse(dateStr, DATE);
		} catch (NullPointerException | DateTimeParseException e) {
			date = initDate;
			if (dayStr != null) {
				DayOfWeek day = DayOfWeek.valueOf(dayStr.toUpperCase());
				DayOfWeek currentDay = date.getDayOfWeek();
				date = date.plusDays(day.getValue() - currentDay.getValue());
			}
		}
		
		LocalTime time;
		try {
			time = LocalTime.parse(timeStr, TIME);
		} catch (NullPointerException | DateTimeParseException e) {
			time = LocalTime.MIN;
		}
		
		return LocalDateTime.of(date, time);
	}
}
