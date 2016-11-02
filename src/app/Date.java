package app;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Date implements IDate {

	private final static int MINYEAR = 1;
	private final static int MAXYEAR = 9999;

	private final static IDate MINDATE = new Date(MINYEAR, 1, 1);
	private final static IDate MAXDATE = new Date(MAXYEAR, 12, 31);

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
		} else if (day < 1 || day > Date.daysInMonth(year, month)) {
			throw new IllegalArgumentException("The month of a date must be between 1 and 12");
		}
		this.year = year;
		this.month = month;
		this.day = day;
	}

	/**
	 * 
	 * @return
	 */
	public static final Date today() {
		//changer ça, j'ai fait un truc vite fait  pour pouvoir tester fromTimeStamp, je sais pas si c'est bien
		//TODO
		Calendar calendar = new GregorianCalendar();
		return new Date(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
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

		int secondsInACommonYear = 31536000;
		int secondsInADay = 86400;

		int currentYear = 1970;
		int currentMonth = 1;
		int currentDay = 1;

		int elapsedSeconds = 0;

		//In a first time we check how many years are contained in the timestamp 
		while (elapsedSeconds < timestamp) {
			elapsedSeconds += secondsInACommonYear;
			if (isLeapYear(currentYear)) {
				elapsedSeconds += secondsInADay;
			}
			currentYear++;
		}

		//If we have gone over the timestamp, we rollback of 1 year
		if (elapsedSeconds > timestamp) {
			elapsedSeconds -= secondsInACommonYear;
			currentYear--;
			if (isLeapYear(currentYear)) {
				elapsedSeconds -= secondsInADay;
			}
		}

		//Then number of months
		while (elapsedSeconds < timestamp) {
			elapsedSeconds += (secondsInADay * daysInMonth(currentYear, currentMonth));
			currentMonth++;
		}

		//Possible rollback
		if (elapsedSeconds > timestamp) {
			currentMonth--;
			elapsedSeconds -= (secondsInADay * daysInMonth(currentYear, currentMonth));
		}

		//Number of days
		while (elapsedSeconds < timestamp) {
			elapsedSeconds += secondsInADay;
			currentDay++;
		}

		//Rollback
		if (elapsedSeconds > timestamp) {
			currentDay--;
		}

		return new Date(currentYear, currentMonth, currentDay);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final Date fromOrdinal(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gives the number of days in the given month of a given year
	 * 
	 * @param year
	 * @param month
	 * @return
	 * @throws IllegalArgumentException
	 *             if the year or month is invalid (e.g. month == 13)
	 */
	private static int daysInMonth(int year, int month) throws IllegalArgumentException {
		if (year < MINYEAR || year > MAXYEAR) {
			throw new IllegalArgumentException("A year must be between " + MINYEAR + " and " + MAXYEAR);
		} else if (month < 1 || month > 12) {
			throw new IllegalArgumentException("A month must be between 1 and 12");
		}

		List<Integer> longMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
		List<Integer> shortMonths = Arrays.asList(4, 6, 9, 11);

		if (longMonths.contains(month)) {
			return 31;
		} else if (shortMonths.contains(month)) {
			return 30;
		} else {
			// Only remaining case is february (month == 2)
			return isLeapYear(year) ? 29 : 28;
		}
	}

	/**
	 * Checks if a year is a leap year (bissextile)
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Leap_year">Leap year
	 *      (Wikipedia)</a>
	 * @param year
	 *            the year to be checked
	 * @return true if and only if the year in parameter is a leap year in the
	 *         Gregorian calendar
	 * @throws IllegalArgumentException
	 *             if the year in parameter is invalid
	 */
	private static boolean isLeapYear(int year) throws IllegalArgumentException {
		if (year < MINYEAR || year > MAXYEAR) {
			throw new IllegalArgumentException("A year must be between " + MINYEAR + " and " + MAXYEAR);
		}

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
	public Date replace(int year, int month, int day) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Number of days between this date and 1/1/1
	 * @return ordinal value
	 */
	@Override
	public int toOrdinal() {
		int ordinalOfCurrentYear = 0;
		int[] numberDaysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
		
		for (int i = 0; i < month - 1; i++){
			ordinalOfCurrentYear += numberDaysPerMonth[i];
		}
		
		if (month > 2 && isLeapYear(year))
			ordinalOfCurrentYear++;
		
		ordinalOfCurrentYear += day;
		
		return (year - 1) * 365 + ((year - 1) / 4) - (year -1) / 100 + (year - 1) / 400 + ordinalOfCurrentYear;
	}

	/* Java modulo operator (%) may result in negative integer.
	 * Example: -1 % 2 returns -1
	 * We could like to avoid this behavior.
	 */
	private int noNegativeModulo(int a, int b){
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

	@Override
	public IsoCalendar isoCalendar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isoFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		String res = "";
		res += this.day + "/";
		res += this.month + "/";
		res += this.year;
		return res;
	}

	@Override
	public String cTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true ; 
		}
		if(o == null) {
			return false ;
		}
		if (!(o instanceof Date)) {
			return false ;
		}
		Date date = (Date) o ;		
		boolean res = true;
		res &= this.year == date.year;
		res &= this.month == date.month;
		res &= this.day == date.day;
		return res;
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
