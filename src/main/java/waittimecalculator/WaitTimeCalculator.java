package waittimecalculator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import Flight.dao.impl.JdbcFlightDAO;

/**
 * This class takes calculates security wait times by running a simulation using the number of passengers 
 * and the time of day
 */
public class WaitTimeCalculator {
	
	private JdbcFlightDAO flightJDBC;
	private Simulation simulator;
	private final double SIM_START_TIME = 1 * 60;
	private final double SIM_END_TIME = 4 * 60;
	private final long TIME_SLICE = 75 * 60 * 1000L;
	
	public WaitTimeCalculator(){
		//Create a JDBC instance to execute commands on the database.
		this.flightJDBC = (JdbcFlightDAO)new ClassPathXmlApplicationContext("Spring-Module.xml").getBean("FlightDAO");
	}
	
	public String calculate(String departureDate, String departureTime) throws ParseException{
		
		//Call getHistoricalDate() to return a date to use for historical data if the date is in the future
		String updatedDepartureDate = getHistoricalDate(departureDate);
		
		//Takes a departure time and splits it into a time range to perform a database query on
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = formatter.parse(departureTime);
		String timeRangeBase = formatter.format(new Date(date.getTime() - this.TIME_SLICE));
		String timeRangeTop = formatter.format(new Date(date.getTime() + this.TIME_SLICE));
		
		//Returns the number of passengers flying between the desired time range and date
		int passengers = flightJDBC.getTotalPassengers(updatedDepartureDate, timeRangeBase, timeRangeTop);
		
		//Runs a simulation between the start and end time
		simulator = new QueueSimulation(passengers);
	    simulator.addEvent(new Arrival(SIM_START_TIME));
	    int waitTime = (int) simulator.run(SIM_START_TIME, SIM_END_TIME);
	    
	    //format the string to be sent to the application
	    if(waitTime < 5){
	    	return "1 - 5 mins";
	    }
	    else if(waitTime < 10){
	    	return "5 - 10 mins";
	    }
	    else if(waitTime < 15){
	    	return "10 - 15 mins";
	    }
	    else if(waitTime < 20){
	    	return "15 - 20 mins";
	    }
	    else{
	    	return "20 + mins";
	    }
		
	}
	
	@SuppressWarnings("deprecation")
	//Return a string with the date to use for historical data.
	public String getHistoricalDate(String departureDate) throws ParseException{
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date originalDate = dateFormatter.parse(departureDate);
		Date todaysDate = Calendar.getInstance().getTime();		
		Calendar c = Calendar.getInstance();		
		
		// if date is in the future, get the day of the week it is on, the day of the week on todays date,
		// and move todays date to the previous day of the week to match the future date.
		if(originalDate.after(todaysDate)){
			int dayOfWeek1 = originalDate.getDay();
			int dayOfWeek2 = todaysDate.getDay();
			int difference = Math.abs(dayOfWeek1 - dayOfWeek2);
			
			//if day of week in future is after day of week today, subtract a week, then add the difference
			if(dayOfWeek1 > dayOfWeek2)
				c.add(Calendar.DATE, (- 7) + difference);
			//else just subtract the difference in days
			else
				c.add(Calendar.DATE, - difference);
			
		}
		//if date is not in future, then do nothing
		else{
			return departureDate;
		}
		
		//Return the new date as a string
		return dateFormatter.format(c.getTime());
		
	}

}
