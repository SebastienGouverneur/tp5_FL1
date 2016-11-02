package app;

/**
 * The ISO calendar is a widely used variant of the Gregorian calendar. 
 * The ISO year consists of 52 or 53 full weeks, and where a week starts on a Monday
 * and ends on a Sunday. The first week of an ISO year is the first (Gregorian) 
 * calendar week of a year containing a Thursday. This is called week number 1, 
 * and the ISO year of that Thursday is the same as its Gregorian year.
 *  
 * For example, 2004 begins on a Thursday, so the first week of ISO year 2004 
 * begins on Monday, 29 Dec 2003 and ends on Sunday, 4 Jan 2004, so that
 *  date(2003, 12, 29).isocalendar() == (2004, 1, 1) and 
 *  date(2004, 1, 4).isocalendar() == (2004, 1, 7).
 * 
 * @see <a href="http://www.phys.uu.nl/~vgent/calendar/isocalendar.htm">ISO Calendar</a>
 *
 */
public class IsoCalendar {
	int day, week, year;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}