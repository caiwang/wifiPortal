package net.wyun.util;

import java.util.Date;

public class DateRange {

    private  long start;
    private  long end;

    private long selectEarliestDate(long date1, long date2) {
    	return (date1 < date2) ? date1:date2;
    }
    
    private long selectLatestDate(long date1, long date2) {
    	return (date1 > date2) ? date1 : date2;
    }
    
    public DateRange(DateRange a, DateRange b) {
        setStart(selectEarliestDate(a.getStart(),b.getStart()));
        setEnd(selectLatestDate(a.getEnd(),b.getEnd()));
    }

    public DateRange(Date start, Date end) {
        this(start == null ? 0: start.getTime(),
                end == null ? 0:end.getTime());
    }

    public DateRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getEnd() {
        return end;
    }

    
	public long getStart() {
        return start;
    }

    private void setStart(long start) {
    	this.start = start;
    }
    
    private void setEnd(long end) {
    	this.end = end;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (end ^ (end >>> 32));
		result = prime * result + (int) (start ^ (start >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final DateRange other = (DateRange) obj;
		if (end != other.end)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
}
