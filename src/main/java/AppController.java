import flightawareapi.APIScheduler;


public class AppController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		APIScheduler scheduler = new APIScheduler(2);
		scheduler.schedule();

	}

}
