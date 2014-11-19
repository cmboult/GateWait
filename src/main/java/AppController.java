import org.jivesoftware.smack.XMPPException;

import server.GateWaitGCMServer;
import flightawareapi.APIScheduler;


public class AppController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		APIScheduler scheduler = new APIScheduler(2);
		scheduler.schedule();
		
		final String userName = "201748315501" + "@gcm.googleapis.com";
	    final String password = "AIzaSyC5YZGgZZPo7KHmHbWetrFmtWrkFYIMRkI";

	    GateWaitGCMServer client = new GateWaitGCMServer();

	    try {
	      client.connect(userName, password);
	    } catch (XMPPException e) {
	      e.printStackTrace();
	    }

	}

}
