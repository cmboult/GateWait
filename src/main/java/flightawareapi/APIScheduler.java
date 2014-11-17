package flightawareapi;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class APIScheduler {
	
	private int howMany;
	
	public APIScheduler(int howManyResults){
		this.howMany = howManyResults;
	}
	
	public void schedule(){
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new APICaller(howMany), 0, 24, TimeUnit.HOURS);
	}	
}
