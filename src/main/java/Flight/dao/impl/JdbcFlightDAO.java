package Flight.dao.impl;

import Flight.dao.*;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import Flight.model.Flight;


/**
 * This class implements the FlightDAO interface. It provides methods for creating entries 
 * in the GateWait database and queries for retrieving flights from it.
 * 
 * @author Christian
 *
 */
public class JdbcFlightDAO implements FlightDAO{
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

	public Flight getFlight(Integer id) {
	    String SQL = "select * from Student where id = ?";
	    Flight flight = (Flight) jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new FlightMapper());
	    return flight;
	}

	public List<Flight> listFlights() {
		String SQL = "select * from Student";
	    List <Flight> flights = jdbcTemplateObject.query(SQL, new FlightMapper());
	    return flights;
	 }
	
	public int getTotalPassengers(String date, String time1, String time2){
		String SQL = "SELECT SUM( TotalPassengers ) FROM  `Scheduled Flights` WHERE DepartureDate = ? AND DepartureTime BETWEEN ? AND ?";
		return jdbcTemplateObject.queryForInt(SQL, new Object[]{date, time1, time2});
	}

}
