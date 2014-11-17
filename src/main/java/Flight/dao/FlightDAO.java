package Flight.dao;

import java.util.List;
import javax.sql.DataSource;

import Flight.model.Flight;

public interface FlightDAO {
   /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the ScheduledFlights table.
    */
   public void create(Flight flight);
   /** 
    * This is the method to be used to list down
    * a record from the ScheduledFlights table corresponding
    * to a passed flight id.
    */
   public Flight getFlight(Integer id);
   /** 
    * This is the method to be used to list down
    * all the records from the ScheduledFlights table.
    */
   public List<Flight> listStudents();
   
}