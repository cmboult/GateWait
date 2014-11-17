package Flight.dao.impl;

import Flight.dao.*;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import Flight.model.Flight;


public class JdbcFlightDAO implements FlightDAO{
	private JdbcTemplate jdbcTemplateObject;
	   
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Flight flight) {
	    String SQL = "INSERT INTO `Scheduled Flights` (`AircraftType`, `Destination`, `DepartureDate`, " +
	    			 "`DepartureTime`, `Identifier`, `OriginCity`, `OriginName`, `totalPassengers`) values (?, ?, ?, ?, ?, ?, ?, ?)"; 
	    jdbcTemplateObject.update( SQL, new Object[] { flight.getAircraftType(), flight.getDestination(), 
	    			flight.getDepartureDate(), flight.getDepartureTime(), flight.getIdentifier(), 
	    			flight.getOriginCity(), flight.getOriginName(), flight.getTotalPassengers() });
	    return;
	}

	public Flight getFlight(Integer id) {
	    String SQL = "select * from Student where id = ?";
	    Flight flight = (Flight) jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new FlightMapper());
	    return flight;
	}

	public List<Flight> listStudents() {
		String SQL = "select * from Student";
	    List <Flight> flights = jdbcTemplateObject.query(SQL, new FlightMapper());
	    return flights;
	 }

}
