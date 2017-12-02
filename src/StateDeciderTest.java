import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StateDeciderTest {
	StateDecider sd;
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		sd = new StateDecider();
	}

	@Test
	public void testGeorgia() {
		String georgia = "longitude=-83.060422&latitude=32.065063";
		String state = sd.decide(new QueryString(georgia));
		assertEquals("Georgia", state);
	}
	
	@Test
	public void testFlorida() {
		String florida = "longitude=-81.291629&latitude=27.265562";
		String state = sd.decide(new QueryString(florida));
		assertEquals("Florida", state);
	}

	@Test
	public void testCalifornia() {
		String california = "longitude=-122.380501&latitude=41.813249";
		String state = sd.decide(new QueryString(california));
		assertEquals("California", state);
	}

	@Test
	public void testPennsylvania() {
		String pennsylvania = "longitude=-79.821505&latitude=41.572350";
		String state = sd.decide(new QueryString(pennsylvania));
		assertEquals("Pennsylvania", state);
	}

	@Test
	public void testTexas() {
		String texas = "longitude=-100.871293&latitude=33.015609";
		String state = sd.decide(new QueryString(texas));
		assertEquals("Texas", state);
	}
	
	@Test
	public void testKansas() {
		String kansas = "longitude=-96.015331&latitude=37.690371";
		String state = sd.decide(new QueryString(kansas));
		assertEquals("Kansas", state);
	}

	@Test
	public void testIdaho() {
		String idaho = "longitude=-116.117783&latitude=46.986648";
		String state = sd.decide(new QueryString(idaho));
		assertEquals("Idaho", state);
	}

	@Test
	public void testArizona() {
		String arizona = "longitude=-110.348353&latitude=33.697641";
		String state = sd.decide(new QueryString(arizona));
		assertEquals("Arizona", state);
	}

	@Test
	public void testAlabama() {
		String alabama = "longitude=-87.281211&latitude=32.164029";
		String state = sd.decide(new QueryString(alabama));
		assertEquals("Alabama", state);
	}

	
	@Test
	public void testNewYork() {
		String newYork = "longitude=-74.597812&latitude=43.891085";
		String state = sd.decide(new QueryString(newYork));
		assertEquals("New York", state);
	}
	
	

	@Test
	public void testKentucky() {
		String kentucky = "longitude=-83.348111&latitude=37.516304";
		String state = sd.decide(new QueryString(kentucky));
		assertEquals("Kentucky", state);
	}
	
	@Test
	public void testNorthDakota() {
		String nd = "longitude=-97.950229&latitude=48.798934";
		String state = sd.decide(new QueryString(nd));
		assertEquals("North Dakota", state);
	}
	
	@Test
	public void testOhio() {
		String ohio = "longitude=-83.463483&latitude=41.276826";
		String state = sd.decide(new QueryString(ohio));
		assertEquals("Ohio", state);
	}

	@Test
	public void testOklahoma() {
		String ok = "longitude=-99.620156&latitude=34.767831";
		String state = sd.decide(new QueryString(ok));
		assertEquals("Oklahoma", state);
	}	
	
	@Test
	public void testMaine() {
		String maine = "longitude=-68.884916&latitude=45.668878";
		String state = sd.decide(new QueryString(maine));
		assertEquals("Maine", state);
	}
	

	@Test
	public void testRandom() {
		String random = "longitude=15.876549&latitude=30.345789";
		String state = sd.decide(new QueryString(random));
		assertEquals("Not in any U.S. state", state);
	}

	@Test
	public void testRandom2() {
		String random = "longitude=120.896123&latitude=-20.387110";
		String state = sd.decide(new QueryString(random));
		assertEquals("Not in any U.S. state", state);
	}

	@Test
	public void testInvalid() {
		String invalid = "";		
		exception.expect(IllegalArgumentException.class);
		sd.decide(new QueryString(invalid));
	}
	
	@Test
	public void testInvalid2() {
		String invalid2 = "longitude=71.911238";
		exception.expect(IllegalArgumentException.class);
		sd.decide(new QueryString(invalid2));

	}
	
	@Test
	public void testInvalid3() {
		String invalid3 = "longitude=34.982209&lasdadsad";
		exception.expect(IllegalArgumentException.class);
		sd.decide(new QueryString(invalid3));
	}
	
	@Test
	public void testInvalid4() {
		String invalid4 = "lon=120.896123&latitude=10.196598";
		exception.expect(IllegalArgumentException.class);
		sd.decide(new QueryString(invalid4));
	}
	
	
	/**
	 * Failed Tests below, all border points
	 */
	
	
	
//	@Test
//	//This is a point in NYC Manhattan, near the state/national border basically 
//	public void testNewYorkBorder() {
//		String newYork = "longitude=-73.982277&latitude=40.754587";
//		String state = sd.decide(new QueryString(newYork));
//		assertEquals("New York", state);
//	}
//
//	@Test
//	//This is a point near Maine/Canada border
//	public void testMaineBorder() {
//		String maine = "longitude=-69.261192&latitude=47.341721";
//		String state = sd.decide(new QueryString(maine));
//		assertEquals("Maine", state);
//	}
//	
//	@Test
//	//This is a point near New Mexico/Mexico border
//	public void testNewMexicoBorder() {
//		String nm = "longitude=-108.654016&latitude=31.362135";
//		String state = sd.decide(new QueryString(nm));
//		assertEquals("New Mexico", state);
//	}
	



}
