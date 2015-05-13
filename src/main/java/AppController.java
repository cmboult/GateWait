import org.springframework.context.support.ClassPathXmlApplicationContext;

import Flight.dao.impl.JdbcFlightDAO;
import Flight.model.UserNotification;
import server.GateWaitGCMServer;
import flightawareapi.APIScheduler;

/**
 * The main controller for the backend. Creates and runs the server and schedules new flights to 
 * be added to the database every 24 hours.
 */
public class AppController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		APIScheduler scheduler = new APIScheduler(65); // schedule new API call with how many results to be returned
		scheduler.schedule();

		JdbcFlightDAO flightJDBC = (JdbcFlightDAO)new ClassPathXmlApplicationContext("Spring-Module.xml").getBean("FlightDAO");
//		flightJDBC.insertUserNotification("2015-05-13", "12:20:00", "userID", "waitTime", "flightNumber", "departureDate", "departureTime");
		
	    System.out.println("Starting server...");
	    GateWaitGCMServer client = new GateWaitGCMServer();
	    client.start(flightJDBC);
	    System.out.println("Server running and waiting for connections.");
		
				
	}

}
