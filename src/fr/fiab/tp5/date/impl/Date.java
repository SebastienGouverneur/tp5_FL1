package fr.fiab.tp5.date.impl;

import fr.fiab.tp5.date.IDate;
import fr.fiab.tp5.date.IsoCalendar;

public class Date implements IDate {

	private final static int MINYEAR = 1;
	private final static int MAXYEAR = 9999;

	private final static int[] numberDaysPerMonthUnLeap = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private final static int[] numberDaysPerMonthLeap = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private final static String[] monthsName = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov",
			"Dec" };
	private final static String[] daysName = { "Mon", "Thu", "Wed", "Tue", "Wed", "Sat", "Sun" };

	public final static IDate MINDATE = new Date(MINYEAR, 1, 1);
	public final static IDate MAXDATE = new Date(MAXYEAR, 12, 31);

	private int year;
	private int month;
	private int day;

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @throws IllegalArgumentException
	 */
	public Date(int year, int month, int day) throws IllegalArgumentException {
		if (year < MINYEAR || year > MAXYEAR) {
			throw new IllegalArgumentException("The year of a date must be between " + MINYEAR + " and " + MAXYEAR);
		} else if (month < 1 || month > 12) {
			throw new IllegalArgumentException("The month of a date must be between 1 and 12");
		} else if (day < 1 || day > daysInMonth(year, month)) {
			throw new IllegalArgumentException("The day is incorrect");
		}
		this.year = year;
		this.month = month;
		this.day = day;
	}

	private int daysInMonth(int year, int month) {
		if(isLeapYear(year)) return numberDaysPerMonthLeap[month-1];
		else return numberDaysPerMonthUnLeap[month-1];
	}
	
	/**
	 * 
	 * @return
	 */
	public static final Date today() {
		return Date.fromTimeStamp(System.currentTimeMillis() / 1000L);
	}

	/**
	 * Returns the date corresponding to a timestamp (only between 1970 and
	 * 2038, this limitation is probably because of the usage of a 32 bits
	 * integer in the original python library but has been kept)
	 * 
	 * @param timestamp
	 *            can be obtained via long unixTime = System.currentTimeMillis()
	 *            / 1000L;
	 * @return the local date corresponding to the POSIX timestamp
	 * @throws IllegalArgumentException
	 *             if the timestamp is invalid
	 */
	public static final Date fromTimeStamp(long timestamp) throws IllegalArgumentException {
		
		if (timestamp < 0 || timestamp > Integer.MAX_VALUE) {
			throw new IllegalArgumentException("A timestamp must be between 0 and " + Integer.MAX_VALUE);
		}
		
		int day = (int) (timestamp / (24 * 60 * 60));
		int year = (((day * 4) + 2) / 1461);
		int resultYear = year + 1970;
		int leap = (resultYear & 3L) > 0L ? 0 : 1;
		day -= ((year * 1461) + 1) / 4;
		day += (day > 58 + leap) ? ((leap == 1) ? 1 : 2) : 0;
		int resultMonth = ((day * 12) + 6) / 367 + 1;
		int resultDay = day + 1 - (((resultMonth-1) * 367) + 5) / 12;
		return new Date(resultYear, resultMonth, resultDay);
	}

	/**
	 * Return the date corresponding to the proleptic Gregorian ordinal, where
	 * January 1 of year 1 has ordinal 1
	 * 
	 * @param ordinal
	 * @return the local date corresponding to the ordinal specified
	 * @throws IllegalArgumentException
	 *             if ordinal < 1 or if ordinal > date.MAXDATE.toOrdinal()
	 */
	public static IDate fromOrdinal(int ordinal) throws IllegalArgumentException {
		if (ordinal < 1 || ordinal > Date.MAXDATE.toOrdinal()) {
			throw new IllegalArgumentException("Ordinal must be between 1 and date.max.toOrdinal()");
		}
		int i = 0;
		int currentYear = 1;
		int currentMonth = 1;
		int currentDay = 1;

		int currentDayInYearUnLeap = 0;
		int currentDayInYearLeap = 0;

		int auxCurrentDayUnLeap = numberDaysPerMonthUnLeap[i];
		int auxCurrentDayLeap = numberDaysPerMonthLeap[i];

		/* calcul year */
		currentYear += (int) (ordinal / 365.25);

		/* calcul month and day not leap year */
		if (!isLeapYear(currentYear)) {

			currentDayInYearUnLeap = ordinal - new Date(currentYear, 1, 1).toOrdinal() + 1;
			if(currentDayInYearUnLeap <= numberDaysPerMonthUnLeap[0]) {
				currentMonth = i + 1;
				currentDay = currentDayInYearUnLeap;
			}
			else {
				while (auxCurrentDayUnLeap < currentDayInYearUnLeap) {
					i++;
					auxCurrentDayUnLeap += numberDaysPerMonthUnLeap[i];

				}
				currentMonth += i;
				currentDay += ordinal - new Date(currentYear, currentMonth, 1).toOrdinal();
			}
		}
		/* calcul month and day leap year */
		else {
			currentDayInYearLeap = ordinal - new Date(currentYear, 1, 1).toOrdinal() + 1;
			if(currentDayInYearLeap <= numberDaysPerMonthLeap[0]) {
				currentMonth = i + 1;
				currentDay = currentDayInYearLeap;
			}
			else {
				while (auxCurrentDayLeap <= currentDayInYearLeap) {
					i++;
					auxCurrentDayLeap += numberDaysPerMonthLeap[i];

				}
				currentMonth += i;
				currentDay += ordinal - new Date(currentYear, currentMonth, 1).toOrdinal();
			}
		}

		return new Date(currentYear, currentMonth, currentDay);
	}

	/**
	 * Checks if a year is a leap year (bissextile)
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Leap_year">Leap year
	 *      (Wikipedia)</a>
	 * @param year
	 *            the year to be checked
	 * @return true if and only if the year in parameter is a leap year in the
	 *         Gregorian calendar*/
	private static boolean isLeapYear(int year) throws IllegalArgumentException {
		if (year % 4 != 0) {
			return false;
		} else if (year % 100 != 0) {
			return true;
		} else if (year % 400 != 0) {
			return false;
		} else {
			return true;
		}
	}

	
	@Override
	public IDate replace(int year, int month, int day) {
		return new Date(year == 0 ? this.year: year,
						month == 0 ? this.month:month,
						day == 0 ? this.day: day);

	}

	/**
	 * Number of days between this date and 1/1/1
	 * 
	 * @return ordinal value
	 */
	@Override
	public int toOrdinal() {
		int ordinalOfCurrentYear = 0;

		for (int i = 0; i < month - 1; i++) {
			ordinalOfCurrentYear += numberDaysPerMonthUnLeap[i];
		}

		if (month > 2 && isLeapYear(year))
			ordinalOfCurrentYear++;

		ordinalOfCurrentYear += day;

		return (year - 1) * 365 + ((year - 1) / 4) - (year - 1) / 100 + (year - 1) / 400 + ordinalOfCurrentYear;
	}

	/*
	 * Java modulo operator (%) may result in negative integer. Example: -1 % 2
	 * returns -1 We could like to avoid this behavior.
	 */
	private static int noNegativeModulo(int a, int b) {
		return (a % b + b) % b;
	}

	/**
	 * @return the day of the week from 0 (Monday) to 6 (Sunday)
	 */
	@Override
	public int weekDay() {
		return noNegativeModulo(noNegativeModulo(this.toOrdinal(), 7) - 1, 7);
	}

	/**
	 * 
	 * @return the day of the week from 1 (Monday) to 7 (Sunday)
	 */
	@Override
	public int isoWeekDay() {
		return weekDay() + 1;
	}

	private static final int pWeek(int year) {
		return noNegativeModulo(year + year / 4 - year / 100 + year / 400, 7);
	}

	private final int numberOfWeeks(int year) {
		if (pWeek(year) == 5 || pWeek(year - 1) == 3)
			return 53;
		else
			return 52;
	}

	@Override
	public IsoCalendar isoCalendar() {
		IsoCalendar cal = new IsoCalendar();
		int year = this.year;
		cal.setDay(this.isoWeekDay());
		int relativeOrdinal = this.toOrdinal() - new Date(this.year, 1, 1).toOrdinal();
		int week = (relativeOrdinal - isoWeekDay() + 10) / 7;
		if (week == 0) {
			year--;
			week = numberOfWeeks(year);
		} else if (week > numberOfWeeks(this.year)) {
			week = 1;
			year++;
		}
		cal.setWeek(week);
		cal.setYear(year);
		return cal;
	}

	@Override
	public String isoFormat() {
		return this.year + "-" + this.month + "-" + this.day;
	}

	@Override
	public String toString() {
		return isoFormat();
	}

	@Override
	public String cTime() {
		return daysName[this.weekDay()] + " " + monthsName[this.month - 1] + " " 
				+ this.day + " " + "00:00:00" + " " + this.year;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Date)) {
			return false;
		}
		Date other = (Date) obj;
		if (day != other.day) {
			return false;
		}
		if (month != other.month) {
			return false;
		}
		if (year != other.year) {
			return false;
		}
		return true;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}
}
