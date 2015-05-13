package Flight.dao.impl;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Flight.dao.*;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import Flight.model.Flight;
import Flight.model.UserNotification;


/**
 * This class implements the FlightDAO interface. It provides methods for creating entries 
 * in the GateWait database and queries for retrieving flights from it.
 * 
 * @author Christian
 *
 */
public class JdbcFlightDAO implements FlightDAO{
	private final long TIME_SLICE = 5 * 60 * 1000L;
	private JdbcTemplate jdbcTemplateObject;
	   
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int create(Flight flight) {
	    String SQL = "INSERT INTO `Scheduled Flights` (`AircraftType`, `Destination`, `DepartureDate`, " +
	    			 "`DepartureTime`, `Identifier`, `OriginCity`, `OriginName`, `TotalPassengers`) values (?, ?, ?, ?, ?, ?, ?, ?)";
	    try{
	    	jdbcTemplateObject.update( SQL, new Object[] { flight.getAircraftType(), flight.getDestination(), 
	    	flight.getDepartureDate(), flight.getDepartureTime(), flight.getIdentifier(), 
	    	flight.getOriginCity(), flight.getOriginName(), flight.getTotalPassengers() });
	    }
	    catch(DataAccessException e){
	    	return 1;
	    }
	    return 0;
	}
	
	public int getTotalPassengers(String date, String time1, String time2){
		String SQL = "SELECT SUM( TotalPassengers ) FROM  `Scheduled Flights` " +
				"WHERE DepartureDate = ? AND DepartureTime BETWEEN ? AND ?";
		return jdbcTemplateObject.queryForInt(SQL, new Object[]{date, time1, time2});
	}

	@Override
	public int insertUserNotification(String notifyDate, String notifyTime,
			String userID, String waitTime, String flightNumber,
			String departureDate, String departureTime) {
		String SQL = "INSERT INTO `User Notifications` (`notify_date`, `notify_time`, `user_id`, " +
				"`wait_time`, `flight_number`, `departure_time`, `departure_date`) values (?, ?, ?, ?, ?, ?, ?)"; 
		
		try{
		jdbcTemplateObject.update(SQL, new Object[] { notifyDate, notifyTime, userID, waitTime, flightNumber, departureDate, 
				departureTime });
		}
		catch(DataAccessException e){
			return 0;
		}
		return 1;
	}

	@Override
	public UserNotification checkForNotify() {
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date todaysDate = Calendar.getInstance().getTime();	
		String currentDate = dateFormatter.format(todaysDate);
		Time currentTime = new Time(System.currentTimeMillis());
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date time2 = new Date(todaysDate.getTime() + this.TIME_SLICE);
		String time1 = formatter.format(currentTime);
		String time3 = formatter.format(time2);
		
		UserNotification user;
		
		String SQL = "SELECT * FROM `User Notifications` " +
				"WHERE notify_date = ? AND notify_time BETWEEN ? AND ?";
		
		try{
			user = (UserNotification) jdbcTemplateObject.queryForObject(SQL, new Object[] {currentDate, time1, time3}, new UserMapper());
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
		return user;
	}

}
