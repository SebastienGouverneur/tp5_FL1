package app.test;

import static org.junit.Assert.fail;
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
		//TODO : Alexandre
		fail("Not yet implemented");
	}

}
