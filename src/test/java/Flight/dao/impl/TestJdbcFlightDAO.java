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
	public void testCreate() {
		Flight flight = new Flight("B738", "Krakow Int'l", new Date(System.currentTimeMillis()), 
				new Time(System.currentTimeMillis()), "RYR1724", "East Midlands, England", 
				"East Midlands, England", 198);
		assertEquals(0, flightJDBC.create(flight));
	}

}
