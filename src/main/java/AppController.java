import org.jivesoftware.smack.XMPPException;

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
		
		APIScheduler scheduler = new APIScheduler(2); // schedule new API call with how many results to be returned
		scheduler.schedule();

	    System.out.println("Starting server...");
	    GateWaitGCMServer client = new GateWaitGCMServer();
	    client.start();
	    
	    System.out.println("Server running and waiting for connections.");
	}

}
