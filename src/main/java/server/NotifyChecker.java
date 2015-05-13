package server;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import Flight.dao.impl.JdbcFlightDAO;
import Flight.model.UserNotification;

public class NotifyChecker extends Thread{
	
	private GateWaitGCMServer gcm;
	private JdbcFlightDAO flightJDBC;
	static Random random = new Random();
	
	public NotifyChecker(GateWaitGCMServer gcm){
		this.gcm = gcm;
		this.flightJDBC = (JdbcFlightDAO)new ClassPathXmlApplicationContext("Spring-Module.xml").getBean("FlightDAO");
	}
	
	public String getRandomMessageId()
    {
	return "m-" + Long.toString(random.nextLong());
    }
	
	public void run(){
		
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("action", "com.dissertation.cmboult.gatewaitapp.NOTIFY_WAIT_TIME");
		System.out.println("CHECKING FOR USERS...");
		UserNotification user = flightJDBC.checkForNotify();
		
		if(user != null){

			payload.put("dateOfFlight", user.getDepartureDate());
			payload.put("timeOfFlight", user.getDepartureTime());
			payload.put("waitTime", user.getWaitTime());
			payload.put("flightNumber", user.getFlightNumber());
			
			System.out.println(payload.get("waitTime"));
			//String message = gcm.createJsonMessage(user.getUserID(), getRandomMessageId(), payload, null, null, false);
			//gcm.send(message);
			
		}	
		
	}

}
