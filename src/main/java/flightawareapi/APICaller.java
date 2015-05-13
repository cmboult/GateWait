package flightawareapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Flight.dao.impl.JdbcFlightDAO;
import Flight.model.Flight;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Calls the FlightAware API via a HTTP GET Request and parses the JSON response. Inserts the
 * information into the Scheduled flights database.
 */
public class APICaller extends Thread{
	
	private int howManyResults;
	private JdbcFlightDAO flightJDBC;
	ArrayList<Flight> scheduledFlights;
	
	public APICaller(int results){
		this.howManyResults = results;
		this.scheduledFlights = new ArrayList<Flight>();
		//Create a JDBC instance to execute commands on the database.
		this.flightJDBC = (JdbcFlightDAO)new ClassPathXmlApplicationContext("Spring-Module.xml").getBean("FlightDAO");
	}
	
	public void run(){
		//Make API call and determine if it was a success. 0 for fail, > 0 for success and number of results
		System.out.println("Calling API at  " + new Date(System.currentTimeMillis()) + "...");
		int results = callAPI();
		if( results > 0){
			
			System.out.println("Success. Adding " + results + " results to the database.");
			//Add each flight as separate entries in the Scheduled Flights database
			for(int i = 0; i < scheduledFlights.size(); i++){
				flightJDBC.create(scheduledFlights.get(i));
			}
		}
		else{
			System.out.println("API call failed.");
		}
		
	}
	
	public int callAPI(){
		//The rest API URL used to make the HTTP request
				String url = "http://markmcspadden:67335dec3f3b762a4fb496e9f17c66594ad903a4@flightxml.flightaware.com/json/FlightXML2/Scheduled?airport=EMA&filter=%27%27&howMany=" + howManyResults + "&offset=0";
				try {
					//HTTP GET request. Trap response as JSON
					HttpResponse<JsonNode> response = Unirest.get(url).asJson();
					//Parse the JSON into a JSON array of results
					JSONArray results = response.getBody().getObject().getJSONObject("ScheduledResult").getJSONArray("scheduled");
					
					//Retrieve each result and add it to the array list of flights
					for(int i = 0; i < results.length(); i++){
						JSONObject object = results.getJSONObject(i);
						scheduledFlights.add(new Flight(object.getString("aircrafttype"), object.getString("destinationName"), object.getLong("filed_departuretime"), object.getString("ident"), object.getString("originCity"), object.getString("originName")));
					}
					//close HTTP connection
					Unirest.shutdown();
					return scheduledFlights.size();
				} catch (UnirestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return 0;
				}
	}
}


