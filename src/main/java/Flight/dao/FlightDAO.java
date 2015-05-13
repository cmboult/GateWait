package Flight.dao;

import javax.sql.DataSource;

import Flight.model.Flight;
import Flight.model.UserNotification;

/**
 * The FlightDAO interface. Declares methods for interacting with the GateWait database.
 */
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
   public int create(Flight flight);  
   /** 
    * This is the method to be used to query for the
    * total number of passengers flying on a specific date and 
    * time range.
    */
   public int getTotalPassengers(String date, String time1, String time2);
   /** 
    * This is the method to insert data into the
    * User Notifications table
    */
   public int insertUserNotification(String notifyDate, String notifyTime, String userID, String waitTime, String flightNumber, String departureDate, String departureTime);
   /** 
    * This is the method to check the database for any users that need
    * to be notified
    */
   public UserNotification checkForNotify();
}