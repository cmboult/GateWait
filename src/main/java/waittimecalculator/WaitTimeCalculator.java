package waittimecalculator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public double calculate(String departureDate, String departureTime) throws ParseException{
		
		//Takes a departure time and splits it into a time range to perform a database query on
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date date = formatter.parse(departureTime);
		String timeRangeBase = formatter.format(new Date(date.getTime() - this.TIME_SLICE));
		String timeRangeTop = formatter.format(new Date(date.getTime() + this.TIME_SLICE));
		//Returns the number of passengers flying between the desired time range and date
		int passengers = flightJDBC.getTotalPassengers(departureDate, timeRangeBase, timeRangeTop);
		
		//Runs a simulation between the start and end time
		simulator = new QueueSimulation(passengers);
	    simulator.addEvent(new Arrival(SIM_START_TIME));
	    return simulator.run(SIM_START_TIME, SIM_END_TIME);
		
	}

}
