import java.text.ParseException;

import server.GateWaitGCMServer;
import waittimecalculator.WaitTimeCalculator;
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
		
		APIScheduler scheduler = new APIScheduler(2); // schedule new API call with how many results to be returned
		scheduler.schedule();

	    System.out.println("Starting server...");
	    GateWaitGCMServer client = new GateWaitGCMServer();
	    client.start();
	    
	    System.out.println("Server running and waiting for connections.");
		
		WaitTimeCalculator calculator = new WaitTimeCalculator();
		try {
			System.out.println("Wait time: " + calculator.calculate("2015-04-20", "21:30:00"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
