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
		date = new Date(1,1,1);//1er janvier de l'an 1
	}

	@Test
	public void testToString() {
		assertEquals("1-1-1",date.toString());
	}
	
	@Test
	public void testFromTimestamp() throws Exception {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d1 = format.parse("2016-11-05");
		Date d2 = Date.fromTimeStamp(d1.getTime()/1000L);
		assertEquals(2016, d2.getYear());
		assertEquals(11, d2.getMonth());
		assertEquals(5, d2.getDay());
		
		d1 = format.parse("1985-06-30");
		d2 = Date.fromTimeStamp(d1.getTime()/1000L);
		assertEquals(1985, d2.getYear());
		assertEquals(06, d2.getMonth());
		assertEquals(30, d2.getDay());
		
		d1 = format.parse("2016-02-28");
		d2 = Date.fromTimeStamp(d1.getTime()/1000L);
		assertEquals(2016, d2.getYear());
		assertEquals(02, d2.getMonth());
		assertEquals(28, d2.getDay());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromTimestampBefore1970() {
		Date.fromTimeStamp(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromTimestampAfter2030() {
		Date.fromTimeStamp(Integer.MAX_VALUE + 1);	
	}
	
	@Test(timeout = 1000)
	public void testFromOrdinal() {
		IDate date1 = new Date(1, 1, 1);
		IDate date2 = new Date(2132, 8, 11);
		IDate date3 = new Date(1, 2, 1);
		IDate date4 = new Date(2008, 3, 13);
		IDate date5 = new Date(1826, 4, 9);
		assertEquals(date1, Date.fromOrdinal(1));
		assertEquals(date2, Date.fromOrdinal(778555));
		assertEquals(date3, Date.fromOrdinal(32));
		assertEquals(date4, Date.fromOrdinal(733114));
		assertEquals(date5, Date.fromOrdinal(666666));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromOrdinalBefore1() {
		IDate date1 = new Date(0, 0, 0);
		Date.fromOrdinal(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromOrdinalAfterMax() {
		IDate date1 = new Date(0, 0, 0);
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
	public void testToOrdinal(){
		IDate date1 = new Date(2016, 1, 1);
		IDate date2 = new Date(2000, 2, 20);
		IDate date3 = new Date(1993, 12, 31);
		IDate date4 = new Date(1000, 1, 1);
		IDate date5 = new Date(1000, 3, 11);
		assertEquals(1, date.toOrdinal());
		assertEquals(735964, date1.toOrdinal());
		assertEquals(730170, date2.toOrdinal());
		assertEquals(727928, date3.toOrdinal());
		assertEquals(364878, date4.toOrdinal());
		assertEquals(364947, date5.toOrdinal());

	}
	
	@Test
	public void testWeekDay(){
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
	public void testIsoWeekDay(){
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

}
