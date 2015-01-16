package net.wyun.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author jeremy
 *
 */
public class DateUtils {
	
	public final DateFormat MS_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
	public final static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMdd-HHmmss");
	public final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static DateFormat TIME_SIMPLE = new SimpleDateFormat( "hh-mm-ss" ) ; 
	
	private DiffCalendar calendar = new DiffCalendar();
	private DiffCalendar calendar2 = new DiffCalendar();
	
	public DateUtils() { }
	
	public String day(Date day) {
		if (day != null) {
			String dayFormattedDate = DAY_FORMAT.format(day);
			return dayFormattedDate; 
		} else {
			return "NULL";
		}
	}
	    
	public  final static String getDateStr(  )   {    
		return ( DAY_FORMAT.format( new Date(  )  )  ) ;  
	}  
	
	public  final static String getTimeStr(  )   {  
		return ( TIME_SIMPLE.format( new Date(  )  )  ) ;  
	}  
	
	public final static String getTimeStr(Date d){
		return ( TIME_SIMPLE.format( d )  );
	}
	
	/*
	 * //formatting hour in HH (00-23) format like 00, 01..23.
	 */
	private static String strHHDateFormat = "HH";
	public final static String getHour(Date d){
		 SimpleDateFormat  sdf = new SimpleDateFormat(strHHDateFormat);
		 return sdf.format(d);
	}
	
	public final static String getHour(){
		return getHour(new Date());
	}
	
	/**
	 * @param string
	 * @return
	 * @throws ParseException 
	 */
	public Date date(String string) {
		try {
			return DAY_FORMAT.parse(string);
		} catch (Exception e) {
			throw new RuntimeException("Unable to parse date.",e);
		}
	}
	
	/**
	 * @param month
	 * @return
	 */
	public Date firstDayOfNextMonth(Date month) {
		calendar.setTime(month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public Date lastDayOf(Date month) {
		calendar.setTime(month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	/**
	 * @param date
	 * @return
	 */
	public Date firstDayOfMonth(Date date) {
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();
		}
		return date;
	}

	public Date lastDayOfPreviousMonth(Date month) {
		calendar.setTime(month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public String timestamp(Date startTime) {
		return TIMESTAMP_FORMAT.format(startTime);
	}

	public int diffDays(Date d1, Date d2) {
		calendar.setTime(d1);
		calendar2.setTime(d2);
		return (int)calendar.diffDayPeriods(calendar2);
	}

	public boolean isFirstOfMonth(Date day) {
		calendar.setTime(day);
		return calendar.get(Calendar.DATE) == 1;
	}

	public Date firstDayOfPreviousMonth(Date day) {
		calendar.setTime(day);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public boolean isSameMonth(Date a, Date b) {
		calendar.setTime(a);
		calendar2.setTime(b);
		return calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
			&& calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH); 
	}

	/*
	 * Compares the date with current date and sets back to current with a margin if date param is in the future
	 */
	public Date getTrimmedDate(Date onOrBefore, int hourMargin) {
		
		Date newDate = onOrBefore;
		Date incrementedDate = getHourIncrementedDate(new Date(), hourMargin);
		
		if(onOrBefore.after(incrementedDate)) {
			newDate = incrementedDate;
		}
		
		return newDate;
	}
	
	public Date getHourIncrementedDate(Date aDate, int hourIncrement) {
		Date incrementedDate = add(Calendar.HOUR, hourIncrement, aDate);
		return incrementedDate;
	}
	
	public Date getDayIncrementedDate(Date aDate, int dayIncrement) {
		Date incrementedDate = add(GregorianCalendar.DATE, dayIncrement, aDate);
		return incrementedDate;
	}
	
	public Date daysBefore(int numDays, Date date) {
		return add(GregorianCalendar.DATE, -1*numDays,date);
	}
	
	public Date daysAfter(int numDays, Date date) {
		return add(GregorianCalendar.DATE, numDays,date);
	}
	
	public Date add(int calendarField, int count, Date date) {
		
		calendar.setTime(date);
		//Log.debug(DateUtils.class, "Original date before incrementing by " + calendarField + " is : " + MS_DATE_FORMAT.format(calendar.getTime()));
		calendar.add(calendarField, count);
		//Log.debug(DateUtils.class, "Date incremented by " + count + " " + calendarField + " is : " + MS_DATE_FORMAT.format(calendar.getTime()));
		return calendar.getTime();
	}
	
	public int getFieldValue(int calendarField, Date date) {
		calendar.setTime(date);
		return calendar.get(calendarField);
	}
	
	public Date oneSecondFloor(Date date) {
		return new Date((date.getTime() / 1000) * 1000);
	}

	public Date firstDayOfYear(Date date) {
		calendar.setTime(date);
		if (calendar.get(Calendar.DAY_OF_MONTH) != 1) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);
		}
		if (calendar.get(Calendar.MONTH) != 0) {
			calendar.set(Calendar.MONTH, 0);
		}
		return calendar.getTime();
	}

	public DateRange year(Date date) {
		Date firstOfYear = firstDayOfYear(date);
		Date lastOfYear = lastDayOfYear(date);
		return new DateRange(firstOfYear,lastOfYear);
	}

	public Date lastDayOfYear(Date date) {
		date = firstDayOfYear(date);
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	public Date time(String string) {
		try {
			return TIME_FORMAT.parse(string);
		} catch (Exception e) {
			throw new RuntimeException("Cannot parse time '"+string+"'.",e);
		}
	}
	
	public Date minuteAndSecond(Date hour, String minuteAndSecond) {
		String formatted = TIME_FORMAT.format(hour);
		return time(formatted.substring(0,14) + minuteAndSecond);
	}
	
	public Date hourMinuteAndSecond(Date day, String hourMinuteAndSecond) {
		String formatted = TIME_FORMAT.format(day);
		return time(formatted.substring(0,11) + hourMinuteAndSecond);
	}

	public String time(Date time) {
		return TIME_FORMAT.format(time);
	}
	

	public boolean isSequential(Date dayA, Date dayB) {
		return diffDays(dayA, dayB) == 1;
	}
}
