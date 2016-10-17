package app;

public class Date implements IDate {
	
	private final static int MINYEAR = 1;
	private final static int MAXYEAR = 9999;
	private final static IDate MINDATE = new Date(MINYEAR, 1, 1);
	private final static IDate MAXDATE = new Date(MAXYEAR, 12, 31);
	
	
	private int year;
	private int month;
	private int day;
	
	public Date(int year, int month, int day){
		this.year = year;
		this.month = month;
		this.day = day;
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

	@Override
	public Date today() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date fromTimeStamp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date fromOrdinal(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date replace(int year, int month, int day) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int toOrdinal(Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int weekday(Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int isoWeekday(Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] isoCalendar(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String isoFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cTime(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
