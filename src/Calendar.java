import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	

/**
	* @author  Chathuranga Muthukumarana.
	* @date   2018-08-18
	*/
	private static Calendar self;
        //Change variable cal as calendar
	private static java.util.Calendar calendar;
	
	
	private Calendar() {
		//Change variable cal as calendar
		calendar = java.util.Calendar.getInstance();
	}
	
	public static Calendar getInstance() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		//Change variable cal as calendar
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) {
		try {
		//Change variable cal as calendar
		calendar.setTime(date);		
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
		//Change variable cal as calendar
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        calendar.set(java.util.Calendar.MINUTE, 0);  
	        calendar.set(java.util.Calendar.SECOND, 0);  
	        calendar.set(java.util.Calendar.MILLISECOND, 0);
			return cal.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) {

		//Change variable now as date
		Date date = Date();

		//Change variable cal as calendar
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = cal.getTime();
		//Change variable cal as calendar

		calendar.setTime(date );

		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) {
		long diffMillis = Date().getTime() - targetDate.getTime();
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}