package app;

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

	/**
	 * 
	 * @return
	 */
	public static final Date today() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public static final Date fromTimeStamp() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static final Date fromOrdinal(Date date) throws IllegalArgumentException {
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
	public IsoCalendar isoCalendar(Date date) {
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
	public String cTime(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

    private boolean equals(Date date) {
        boolean res = true;
        res &= this.year == date.year;
        res &= this.month == date.month;
        res &= this.day == date.day;
        return res;
    }
}
