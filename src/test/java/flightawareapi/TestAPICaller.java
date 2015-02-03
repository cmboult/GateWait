package flightawareapi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestAPICaller {
	private APICaller caller;

	@Before
	public void setUp() throws Exception {
		caller = new APICaller(10);
	}

	@Test
	public void testCallAPI() {
		assertEquals(10, caller.callAPI());
	}

}
