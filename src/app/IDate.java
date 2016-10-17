package app;

/**
 * 
 *
 */
public interface IDate {

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 * @throws IllegalArgumentException
	 */
	Date replace(int year, int month, int day) throws IllegalArgumentException;
	
	/**
	 * @param date
	 * @return
	 */
	int toOrdinal(Date date);
	
	/**
	 * @param date
	 * @return
	 */
	int weekday(Date date);
	
	/**
	 * @param date
	 * @return
	 */
	int isoWeekday(Date date);
	
	/**
	 * @param date
	 * @return
	 */
	IsoCalendar isoCalendar(Date date);
	
	/**
	 * @return
	 */
	String isoFormat();
	
	/**
	 * @return
	 */
	String toString();
	
	/**
	 * @param date
	 * @return
	 */
	String cTime(Date date);
}
