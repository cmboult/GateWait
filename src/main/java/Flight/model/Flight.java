package Flight.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Model of a flight and it's information. Used to insert and retrieve information from the database.
 */
public class Flight {
	
	private String aircraftType;
	private String destination;
	private Date departureDate;
	private Time departureTime;
	private String identifier;
	private String originCity;
	private String originName;
	private int totalPassengers;
	
	/**
	 * Empty constructor
	 */
	public Flight(){}
	
	/**
	 * Constructor for creating flights from the database
	 */
	public Flight(String aircraftType, String destination, Date departureDate, Time departureTime, String identifier, String originCity, String originName, int totalPassengers){
		this.setAircraftType(aircraftType);
		this.setDestination(destination);
		this.setDepartureDate(departureDate);
		this.setDepartureTime(departureTime);
		this.setIdentifier(identifier);
		this.setOriginCity(originCity);
		this.setOriginName(originName);
		this.setTotalPassengers(totalPassengers);
	}
	
	/**
	 * Constructor for create flights from an API call
	 */
	public Flight(String aircraftType, String destination, long epochTime, String identifier, String originCity, String originName){
		this.setAircraftType(aircraftType);
		this.setDestination(destination);
		this.setDepartureDate(new Date(epochTime*1000L)); //convert unix times to a standard date and time
		this.setDepartureTime(new Time(epochTime*1000L));
		this.setIdentifier(identifier);
		this.setOriginCity(originCity);
		this.setOriginName(originName);
		this.setTotalPassengers(aircraftType);
	}

	/**
	 * @return the aircraftType
	 */
	public String getAircraftType() {
		return aircraftType;
	}

	/**
	 * @param aircraftType the aircraftType to set
	 */
	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the departureDate
	 */
	public Date getDepartureDate() {
		return departureDate;
	}

	/**
	 * @param departureDate the departureDate to set
	 */
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	/**
	 * @return the departureTime
	 */
	public Time getDepartureTime() {
		return departureTime;
	}

	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the originCity
	 */
	public String getOriginCity() {
		return originCity;
	}

	/**
	 * @param originCity the originCity to set
	 */
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	/**
	 * @return the originName
	 */
	public String getOriginName() {
		return originName;
	}

	/**
	 * @param originName the originName to set
	 */
	public void setOriginName(String originName) {
		this.originName = originName;
	}

	/**
	 * @return the totalPassengers
	 */
	public int getTotalPassengers() {
		return totalPassengers;
	}

	/**
	 * @param totalPassengers the totalPassengers to set
	 */
	public void setTotalPassengers(String totalPassengers) {
		//this.totalPassengers = totalPassengers;
	}
	
	/**
	 * @param totalPassengers the totalPassengers to set
	 */
	public void setTotalPassengers(int totalPassengers) {
		this.totalPassengers = totalPassengers;
	}

}
