package net.wyun.lottery;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import net.wyun.lottery.ScheduledLotteryTasks;
import net.wyun.util.DateUtils;

import org.junit.Before;
import org.junit.Test;

public class ScheduledLotteryTasks_Test {
	
	private DateUtils du;

	@Before
	public void before() {
		du = new DateUtils();
	}

	@Test
	public void test_blockedHours() {

		Set<String> blocked = ScheduledLotteryTasks.generateBlockingHours();
		DateUtils du = new DateUtils();
		
		Date d = du.time("2007-01-01 00:05:00"); // 5AM
		String hour = DateUtils.getHour(d);
		assertEquals("00", hour);
		
		d = du.time("2007-01-01 05:00:00"); // 5AM
	    hour = DateUtils.getHour(d);
		assertEquals("05", hour);
		
		d = du.time("2007-01-01 07:00:00");
		hour = DateUtils.getHour(d);
		assertEquals("07", hour);
		
		d = du.time("2007-01-01 14:07:00");
		hour = DateUtils.getHour(d);
		assertEquals("14", hour);
		
	}
	
	@Test
	public void test_midnightHour(){
		Date d = du.time("2007-01-01 01:05:00"); // 5AM
		String hour = DateUtils.getHour(d);
		assertEquals("01", hour);
		
		String[] hms = du.getTimeStr(d).split("-");
		for(String s:hms){
			System.out.println(s);
		}
	}

}
