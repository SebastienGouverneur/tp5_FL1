package app.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.Date;
import app.IDate;

public class DateTest {

	private IDate date;

	
	@Before
	public void setUp() throws Exception {
		date = new Date(1,1,1);//1er janvier de l'an 1
	}

	@After
	public void tearDown() throws Exception {
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
	
	@Test
	public void testToOrdinal(){
		IDate date1 = new Date(2016, 1, 1);
		IDate date2 = new Date(2000, 2, 20);
		IDate date3 = new Date(1993, 12, 31);
		assertEquals(1, date.toOrdinal());
		assertEquals(735964, date1.toOrdinal());
		assertEquals(730170, date2.toOrdinal());
		assertEquals(727928, date3.toOrdinal());

	}

}
