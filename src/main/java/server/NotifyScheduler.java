package server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotifyScheduler {

	private GateWaitGCMServer gcm;
	
	public NotifyScheduler(GateWaitGCMServer gcm){
		this.gcm = gcm;
	}
	
	public void schedule(){
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new NotifyChecker(gcm), 0, 5, TimeUnit.MINUTES);
	}
}
