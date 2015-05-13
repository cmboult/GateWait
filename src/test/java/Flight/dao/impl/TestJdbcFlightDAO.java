package Flight.dao.impl;

import static org.junit.Assert.*;

import java.sql.Time;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Flight.model.Flight;

public class TestJdbcFlightDAO {
	
	private JdbcFlightDAO flightJDBC;
	private boolean doSetup = true;
	
	@Before
	public void setUp(){
		if(doSetup){
			this.flightJDBC = (JdbcFlightDAO)
					new ClassPathXmlApplicationContext("Spring-Module.xml").getBean("FlightDAO");
		}	
		doSetup = false;
	}

	@Test
	public void testCreate1() {
		Flight flight = new Flight("B738", "Krakow Int'l", new Date(System.currentTimeMillis()), 
				new Time(System.currentTimeMillis()), "RYR1724", "East Midlands, England", 
				"East Midlands, England", 198);
		assertEquals(0, flightJDBC.create(flight));
	}
	
	@Test
	public void testCreate2() {
		Flight flight = new Flight("A555", "Paris", new Date(System.currentTimeMillis()), 
				new Time(System.currentTimeMillis()), "BA5114", "London Heathrow, England", 
				"LHR, England", 73);
		assertEquals(0, flightJDBC.create(flight));
	}
	
	@Test
	public void testCreate3() {
		Flight flight = new Flight("C222", "Brussels", new Date(System.currentTimeMillis()), 
				new Time(System.currentTimeMillis()), "RYR1724", "Lyon, France", 
				"Lyon, France", 132);
		assertEquals(0, flightJDBC.create(flight));
	}
	
	@Test
	public void testGetTotalPassengers(){
		String departureDate = "2015-05-07";
		String departureTimeBase = "13:00:00";
		String departureTimeTop = "14:00:00";
		assertEquals(403, flightJDBC.getTotalPassengers(departureDate, departureTimeBase, departureTimeTop));
	}

}
