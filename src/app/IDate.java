package app;

/**
 * Java Conversion of the Python class date
 * @see <a href="https://docs.python.org/3.3/library/datetime.html#datetime.date">Python doc</a>
 */
public interface IDate {
	
	/**
	 * Modifies and returns the date according to the arguments (an argument's value of 0 means that the old value is kept)
	 * @param year the year of the modified date
	 * @param month the month of the modified date
	 * @param day the modified date
	 * @return the modified date
	 * @throws IllegalArgumentException if one of the arguments is impossible to satisfy (month == 13 by example)
	 */
	IDate replace(int year, int month, int day) throws IllegalArgumentException;
	
	/**
	 * @return the date corresponding to the proleptic Gregorian ordinal,
	 * where January 1 of year 1 has ordinal 1
	 */
	Date fromOrdinal(int ordinal) throws IllegalArgumentException;
	
	/**
	 * @return the proleptic Gregorian ordinal of the date, 
	 * where January 1 of year 1 has ordinal 1
	 */
	int toOrdinal();
	
	/**
	 * @return the day of the week as an integer, where Monday is 0 and Sunday is 6
	 */
	int weekDay();
	
	/**
	 * @return the day of the week as an integer, where Monday is 1 and Sunday is 7
	 */
	int isoWeekDay();
	
	/**
	 * @return a conversion of the date as an IIsoCalendar
	 */
	IsoCalendar isoCalendar();
	
	/**
	 * @return a string representing the date in ISO 8601 format, �YYYY-MM-DD�
	 */
	String isoFormat();
	
	/**
	 * Same as{@link #isoFormat() isoFormat}
	 * @return a string representing the date in ISO 8601 format, �YYYY-MM-DD�
	 */
	String toString();
	
	/**
	 * @return a string representing the date, for example 
	 * date(2002, 12, 4).ctime() == 'Wed Dec 4 00:00:00 2002'.
	 */
	String cTime();
}
