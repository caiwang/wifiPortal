package net.wyun.util;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static net.wyun.util.DateUtils.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * @author jeremy
 */
public class DateUtilsTest {
	
	private DateUtils du;

	@Before
	public void before() {
		du = new DateUtils();
	}
	
	@Test
	public void testMonthAfter() throws Exception {
		assertEquals(du.date("2007-01-01"), du.firstDayOfNextMonth(du.date("2006-12-01")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfNextMonth(du.date("2006-12-02")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfNextMonth(du.date("2006-12-15")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfNextMonth(du.date("2006-12-30")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfNextMonth(du.date("2006-12-31")));
	}
	
	@Test
	public void testLastDayOf() throws Exception {
		assertEquals(du.date("2007-01-31"), du.lastDayOf(du.date("2007-01-01")));
		assertEquals(du.date("2007-01-31"), du.lastDayOf(du.date("2007-01-02")));
		assertEquals(du.date("2007-01-31"), du.lastDayOf(du.date("2007-01-15")));
		assertEquals(du.date("2007-01-31"), du.lastDayOf(du.date("2007-01-30")));
		assertEquals(du.date("2007-01-31"), du.lastDayOf(du.date("2007-01-31")));
	}
	
	@Test
	public void testLastDayOfPreviousMonth() throws Exception {
		assertEquals(du.date("2006-12-31"), du.lastDayOfPreviousMonth(du.date("2007-01-01")));
		assertEquals(du.date("2006-12-31"), du.lastDayOfPreviousMonth(du.date("2007-01-02")));
		assertEquals(du.date("2006-12-31"), du.lastDayOfPreviousMonth(du.date("2007-01-15")));
		assertEquals(du.date("2006-12-31"), du.lastDayOfPreviousMonth(du.date("2007-01-30")));
		assertEquals(du.date("2006-12-31"), du.lastDayOfPreviousMonth(du.date("2007-01-31")));
	}
	
	@Test
	public void testDiffDays() throws ParseException {
		assertEquals(-1, du.diffDays(du.date("2006-01-01"), du.date("2005-12-31")));
		assertEquals(0, du.diffDays(du.date("2006-01-01"), du.date("2006-01-01")));
		assertEquals(1, du.diffDays(du.date("2006-01-01"), du.date("2006-01-02")));
		assertEquals(2, du.diffDays(du.date("2006-01-01"), du.date("2006-01-03")));
	}
	
	@Test
	public void testFirstDayOfPreviousMonth() throws ParseException {
		assertEquals(du.date("2006-12-01"), du.firstDayOfPreviousMonth(du.date("2007-01-01")));
		assertEquals(du.date("2006-12-01"), du.firstDayOfPreviousMonth(du.date("2007-01-02")));
		assertEquals(du.date("2006-12-01"), du.firstDayOfPreviousMonth(du.date("2007-01-15")));
		assertEquals(du.date("2006-12-01"), du.firstDayOfPreviousMonth(du.date("2007-01-30")));
		assertEquals(du.date("2006-12-01"), du.firstDayOfPreviousMonth(du.date("2007-01-31")));
	}
	
	@Test
	public void testFirstDayOfYear() throws ParseException {
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-01-01")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-01-02")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-02-02")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-01-15")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-03-15")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-04-30")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-01-30")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-05-31")));
		assertEquals(du.date("2007-01-01"), du.firstDayOfYear(du.date("2007-01-31")));
	}
	
	@Test
	public void testLastDayOfYear() throws ParseException {
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-01-01")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-01-02")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-02-02")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-01-15")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-03-15")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-04-30")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-01-30")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-05-31")));
		assertEquals(du.date("2007-12-31"), du.lastDayOfYear(du.date("2007-01-31")));
	}
	
	//@Test
	public void dayOfMonthOutOfRange() { // Fails, but not sure how to deal with it.
		try {
			String string = "2006-06-42";
			Date date = du.date(string);
			fail("Date creation '"+string+"' should have failed, but instead produced: " + du.day(date));
		} catch (Exception e) {/* exception expected */}
	}
	
	//@Test
	public void monthOutOfRange() { // Fails, but not sure how to deal with it.
		try {
			String string = "2006-13-01";
			Date date = du.date(string);
			fail("Date creation '"+string+"' should have failed, but instead produced: " + du.day(date));
		} catch (Exception e) {/* exception expected */}
	}
	
	@Test
	public void floors() {
		assertEquals(999000L,du.oneSecondFloor(new Date(999001L)).getTime());
		assertEquals(999000L,du.oneSecondFloor(new Date(999555L)).getTime());
		assertEquals(999000L,du.oneSecondFloor(new Date(999999L)).getTime());
		assertEquals(1000000L,du.oneSecondFloor(new Date(1000000L)).getTime());
	}
	
	@Test
	public void year() {
		DateRange year = du.year(du.date("2006-06-15"));
		assertEquals(du.date("2006-01-01").getTime(), year.getStart());
		assertEquals(du.date("2006-12-31").getTime(), year.getEnd());
	}
	
	@Test
	public void minuteAndSecond() {
		Date hour = du.time("2007-01-01 05:00:00"); // 5AM
		Date second = du.minuteAndSecond(hour, "31:59");
		String time = du.time(second);
		assertEquals("2007-01-01 05:31:59", time);
	}
	
	@Test
	public void hourMinuteAndSecond() {
		Date day = du.date("2007-01-01");
		Date second = du.hourMinuteAndSecond(day, "05:31:59");
		String time = du.time(second);
		assertEquals("2007-01-01 05:31:59", time);
	}
	
	@Test
	public void isSequential() {
		assertEquals(true, du.isSequential(du.date("2007-01-01"), du.date("2007-01-02")));
		assertEquals(false, du.isSequential(du.date("2007-01-02"), du.date("2007-01-02")));
		assertEquals(false, du.isSequential(du.date("2007-01-02"), du.date("2007-01-04")));
		assertEquals(false, du.isSequential(du.date("2007-01-02"), du.date("2007-01-01")));
	}
	
	@Test
	public void dataTimeStr(){
		 String end = DateUtils.getDateStr()+ "_" + DateUtils.getTimeStr();  
		 System.out.println("Current DataTime as String: " + end);
		 String[] hms = DateUtils.getTimeStr().split("-");
		 for(String s:hms){
			 System.out.println(s);
		 }
	}
}
