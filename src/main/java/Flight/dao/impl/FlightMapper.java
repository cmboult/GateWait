package Flight.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import Flight.model.Flight;

/**
 * Maps results from the SQL Query to a flight object
 */
public class FlightMapper implements RowMapper {
   public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
      Flight flight = new Flight();
      flight.setAircraftType(rs.getString("AircraftType"));
      flight.setDepartureDate(rs.getDate("DepartureDate"));
      flight.setDestination(rs.getString("Destination"));
      flight.setDepartureTime(rs.getTime("DepartureTime"));
      flight.setIdentifier(rs.getString("Identifier"));
      flight.setOriginCity(rs.getString("OriginCity"));
      flight.setOriginName(rs.getString("OriginName"));
      flight.setTotalPassengers(rs.getInt("totalPassengers"));
      return flight;
   }
}
