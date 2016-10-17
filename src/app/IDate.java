package app;

public interface IDate {
	
	public Date today();
	public Date fromTimeStamp();
	public Date fromOrdinal(int ordinal);
	public Date replace(int year, int month, int day); /* d.replace(0, 0, 21) return date(currentYear, currentDay, 21)*/
	public int toOrdinal(Date date);
	public int weekday(Date date);
	public int isoWeekday(Date date);
	public int [] isoCalendar(Date date);
	public String isoFormat();
	public String toString();
	public String cTime(Date date);
	
}
