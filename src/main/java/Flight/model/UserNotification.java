package Flight.model;

import java.sql.Date;
import java.sql.Time;

public class UserNotification {
	
	private String userID, waitTime, flightNumber, departureTime, departureDate;
	private Date notifyDate;
	private Time notifyTime;
	
	public UserNotification(){}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(String waitTime) {
		this.waitTime = waitTime;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public Date getNotifyDate() {
		return notifyDate;
	}
	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	public Time getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(Time notifyTime) {
		this.notifyTime = notifyTime;
	}

}
