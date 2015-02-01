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
		
		final String userName = "201748315501" + "@gcm.googleapis.com";
	    final String password = "AIzaSyC5YZGgZZPo7KHmHbWetrFmtWrkFYIMRkI";

	    GateWaitGCMServer client = new GateWaitGCMServer();

	    try {
	      client.connect(userName, password); // connect to Google Cloud Severs
	    } catch (XMPPException e) {
	      e.printStackTrace();
	    }

	}

}
