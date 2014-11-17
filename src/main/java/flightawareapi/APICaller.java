package flightawareapi;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Flight.dao.impl.JdbcFlightDAO;
import Flight.model.Flight;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class APICaller extends Thread{
	
	private int howManyResults;
	private JdbcFlightDAO flightJDBC;
	
	public APICaller(int results){
		this.howManyResults = results;
		this.flightJDBC = (JdbcFlightDAO)new ClassPathXmlApplicationContext("Spring-Module.xml").getBean("FlightDAO");
	}
	
	public void run(){
		ArrayList<Flight> scheduledFlights = new ArrayList<Flight>();
		String url = "http://markmcspadden:67335dec3f3b762a4fb496e9f17c66594ad903a4@flightxml.flightaware.com/json/FlightXML2/Scheduled?airport=EMA&filter=%27%27&howMany=" + howManyResults + "&offset=0";
		try {
			HttpResponse<JsonNode> response = Unirest.get(url).asJson();
			JSONArray results = response.getBody().getObject().getJSONObject("ScheduledResult").getJSONArray("scheduled");
			
			for(int i = 0; i < results.length(); i++){
				JSONObject object = results.getJSONObject(i);
				scheduledFlights.add(new Flight(object.getString("aircrafttype"), object.getString("destinationName"), object.getLong("filed_departuretime"), object.getString("ident"), object.getString("originCity"), object.getString("originName")));
			}
			Unirest.shutdown();
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(int i = 0; i < scheduledFlights.size(); i++){
			flightJDBC.create(scheduledFlights.get(i));
		}
		
	}

}


