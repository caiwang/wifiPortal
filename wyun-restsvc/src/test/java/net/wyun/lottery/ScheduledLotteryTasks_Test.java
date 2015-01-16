package net.wyun.lottery;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import net.wyun.lottery.ScheduledLotteryTasks;
import net.wyun.util.DateUtils;

import org.junit.Test;

public class ScheduledLotteryTasks_Test {

	@Test
	public void test_blockedHours() {

		Set<String> blocked = ScheduledLotteryTasks.generateBlockingHours();
		DateUtils du = new DateUtils();
		
		Date d = du.time("2007-01-01 05:00:00"); // 5AM
		String hour = DateUtils.getHour(d);
		assertEquals("05", hour);
		
		d = du.time("2007-01-01 07:00:00");
		hour = DateUtils.getHour(d);
		assertEquals("07", hour);
		
		d = du.time("2007-01-01 14:07:00");
		hour = DateUtils.getHour(d);
		assertEquals("14", hour);
		
	}

}
