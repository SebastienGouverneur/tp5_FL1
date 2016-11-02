package fr.fiab.tp5.date;

import static org.junit.Assert.*;

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
		assertEquals("1/1/1",date.toString());
	}
	
	@Test
	public void testFromTimestamp() {
		assertEquals(Date.today(),Date.fromTimeStamp(System.currentTimeMillis() / 1000L));
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
		IDate date2 = new Date(2, 5, 14);
		assertEquals(date2, date2.fromOrdinal(733114));
		//IDate date1 = new Date(1, 1, 1);
		//assertEquals(date1, date1.fromOrdinal(1));
		//System.out.println(new Date(1, 1, 1).fromOrdinal(499));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromOrdinalBefore1() {
		IDate date1 = new Date(0, 0, 0);
		date1.fromOrdinal(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromOrdinalAfterMax() {
		IDate date1 = new Date(0, 0, 0);
		date1.fromOrdinal(Date.MAXDATE.toOrdinal() + 1);
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
