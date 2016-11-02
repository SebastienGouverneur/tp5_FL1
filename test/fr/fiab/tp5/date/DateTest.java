package fr.fiab.tp5.date;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import fr.fiab.tp5.date.IDate;
import fr.fiab.tp5.date.impl.Date;

public class DateTest {

	private IDate date;

	@Before
	public void setUp() throws Exception {
		date = new Date(1, 1, 1);// 1er janvier de l'an 1
	}

	
	@Test
	public void testConstructor(){
		new Date(1993, 2, 22);
		new Date(2004, 2, 29);
		new Date(2003, 2, 28);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorMinYearOutOfBound(){
		new Date(0, 1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorMinMonthOutOfBound(){
		new Date(1, 0, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorMinDayOutOfBound(){
		new Date(1, 1, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorMaxYearOutOfBound(){
		new Date(10000, 1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNoLeapYearWith29Feb(){
		new Date(2014, 2, 29);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorLeapYearWith30Feb(){
		new Date(2016, 2, 30);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorMaxMonthOutOfBound(){
		new Date(2014, 13, 31);
	}
	
	@Test
	public void testToString() {
		assertEquals("1-1-1", date.toString());
	}

	@Test
	public void testFromTimestamp() throws Exception {
<<<<<<< HEAD
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d1 = format.parse("2016-11-05");
		Date d2 = Date.fromTimeStamp(d1.getTime() / 1000L);
		assertEquals(2016, d2.getYear());
		assertEquals(11, d2.getMonth());
		assertEquals(5, d2.getDay());

		d1 = format.parse("1985-06-30");
		d2 = Date.fromTimeStamp(d1.getTime() / 1000L);
		assertEquals(1985, d2.getYear());
		assertEquals(06, d2.getMonth());
		assertEquals(30, d2.getDay());

		d1 = format.parse("2016-02-28");
		d2 = Date.fromTimeStamp(d1.getTime() / 1000L);
		assertEquals(2016, d2.getYear());
		assertEquals(02, d2.getMonth());
		assertEquals(28, d2.getDay());
=======
		
		//TODO
		Date d = Date.fromTimeStamp(0);
		assertEquals(1970, d.getYear());
		assertEquals(1, d.getMonth());
		assertEquals(1, d.getDay());


>>>>>>> c5227f3e7b5d631e8f0ea2f0879938f747787115
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromTimestampBefore1970() {
		Date.fromTimeStamp(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromTimestampAfter2030() {
		Date.fromTimeStamp(Integer.MAX_VALUE + 1);
	}

	@Test
	public void testFromOrdinal() {
		IDate date1 = new Date(1, 1, 1);
		IDate date2 = new Date(2132, 8, 11);
		IDate date3 = new Date(1, 2, 1);
		IDate date4 = new Date(2008, 3, 13);
		IDate date5 = new Date(1826, 4, 9);
		IDate date6 = new Date(2004, 1, 20);
		assertEquals(date1, Date.fromOrdinal(1));
		assertEquals(date2, Date.fromOrdinal(778555));
		assertEquals(date3, Date.fromOrdinal(32));
		assertEquals(date4, Date.fromOrdinal(733114));
		assertEquals(date5, Date.fromOrdinal(666666));
		assertEquals(date6, Date.fromOrdinal(731600));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromOrdinalBefore1() {
		Date.fromOrdinal(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFromOrdinalAfterMax() {
		Date.fromOrdinal(Date.MAXDATE.toOrdinal() + 1);
	}

	@Test(timeout = 1000)
	public void testIsoCalendar() {
		IsoCalendar cal_2016_11_02 = new IsoCalendar();
		cal_2016_11_02.setDay(3);
		cal_2016_11_02.setWeek(44);
		cal_2016_11_02.setYear(2016);
		Date d1 = new Date(2016, 11, 02);
		IsoCalendar actual = d1.isoCalendar();

		assertEquals(cal_2016_11_02, actual);

		IsoCalendar cal_2017_01_01 = new IsoCalendar();
		cal_2017_01_01.setDay(7);
		cal_2017_01_01.setWeek(52);
		cal_2017_01_01.setYear(2016);
		Date d2 = new Date(2017, 01, 01);
		IsoCalendar actual2 = d2.isoCalendar();

		assertEquals(cal_2017_01_01, actual2);
	}

	@Test
	public void testToOrdinal() {
		IDate date1 = new Date(2016, 1, 1);
		IDate date2 = new Date(2000, 2, 20);
		IDate date3 = new Date(1993, 12, 31);
		IDate date4 = new Date(1000, 1, 1);
		IDate date5 = new Date(1000, 3, 11);
		IDate date6 = new Date(2008, 2, 29);
		assertEquals(1, date.toOrdinal());
		assertEquals(735964, date1.toOrdinal());
		assertEquals(730170, date2.toOrdinal());
		assertEquals(727928, date3.toOrdinal());
		assertEquals(364878, date4.toOrdinal());
		assertEquals(364947, date5.toOrdinal());
		assertEquals(733101, date6.toOrdinal());
		assertEquals(date3, Date.fromOrdinal(date3.toOrdinal()));

	}

	@Test
	public void testWeekDay() {
		IDate date0 = new Date(1000, 3, 11);
		IDate date1 = new Date(1000, 3, 12);
		IDate date2 = new Date(1000, 3, 13);
		IDate date3 = new Date(1000, 3, 14);
		IDate date4 = new Date(1000, 3, 15);
		IDate date5 = new Date(1000, 3, 16);

		assertEquals(0, date.weekDay());
		assertEquals(1, date0.weekDay());
		assertEquals(2, date1.weekDay());
		assertEquals(3, date2.weekDay());
		assertEquals(4, date3.weekDay());
		assertEquals(5, date4.weekDay());
		assertEquals(6, date5.weekDay());

	}

	@Test
	public void testIsoWeekDay() {
		IDate date0 = new Date(2016, 11, 1);
		IDate date1 = new Date(2016, 11, 2);
		IDate date2 = new Date(2016, 11, 3);
		IDate date3 = new Date(2016, 11, 4);
		IDate date4 = new Date(2016, 11, 5);
		IDate date5 = new Date(2016, 11, 6);

		assertEquals(1, date.isoWeekDay());
		assertEquals(2, date0.isoWeekDay());
		assertEquals(3, date1.isoWeekDay());
		assertEquals(4, date2.isoWeekDay());
		assertEquals(5, date3.isoWeekDay());
		assertEquals(6, date4.isoWeekDay());
		assertEquals(7, date5.isoWeekDay());

	}

	@Test
	public void testReplaceYear() { // Test : year has to be replaced
		Date newDate;
		newDate = d1.replace(2003, 0, 0);
		assertNotEquals(d1.getYear(), newDate.getYear()); // years have to be
															// different
		assertEquals(2003, newDate.getYear());
		assertEquals(d1.getMonth(), newDate.getMonth()); // months have to be
															// the same
		assertEquals(d1.getDay(), newDate.getDay()); // days have to be the same
	}

	@Test
	public void testReplaceMonth() { // Test : month has to be replaced
		Date newDate;
		newDate = d1.replace(0, 3, 0);
		assertEquals(d1.getYear(), newDate.getYear()); // years have to be the
														// same
		assertNotEquals(d1.getMonth(), newDate.getMonth()); // months have to be
															// different
		assertEquals(3, newDate.getMonth());
		assertEquals(d1.getDay(), newDate.getDay()); // days have to be the same
	}

	@Test
	public void testReplaceDay() { // Test : day has to be replaced
		Date newDate;
		newDate = d1.replace(0, 0, 29);
		assertEquals(d1.getMonth(), newDate.getMonth()); // years have to be the
															// same
		assertEquals(d1.getMonth(), newDate.getMonth()); // months have to be
															// the same
		assertNotEquals(d1.getDay(), newDate.getDay()); // days have to be
														// different
		assertEquals(newDate.getDay(), 29);
	}

	@Test
	public void testNothingToReplace() { // Test : nothing to replace
		Date newDate;
		newDate = d1.replace(0, 0, 0);
		assertEquals(d1.getYear(), newDate.getYear()); // years have to be the
														// same
		assertEquals(d1.getMonth(), newDate.getMonth()); // months have to be
															// the same
		assertEquals(d1.getDay(), newDate.getDay()); // days have to be the same

	}

	@Test
	public void testFromDayToString() {

		assertEquals(d1.fromDayToString(1), "Monday");
		assertNotEquals(d1.fromDayToString(2), "Thursday");
	}

	@Test
	public void testFromMonthToString() {

		assertEquals(d1.fromMonthToString(1), "January");
		assertNotEquals(d1.fromMonthToString(6), "July");
	}

	@Test
	public void testCTime() {

		// comparing two Strings
		assertEquals(d1.cTime(), "Saturday February 2 00:00:00 2002");
		assertNotEquals(d1.cTime(), "Sunday June 26 00:01:02 2007");
	}

}
