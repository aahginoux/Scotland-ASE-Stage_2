package jUnitTesting;
import static org.junit.Assert.*;
import org.junit.Test;
import airportCheckIn.*;

/**
 * This JUnit Test class is used to test the Flight class
 * @author Suraj Sivaprasad
 */
public class TestFlight 
{
	/**
	 * Tests whether 2 Flight objects are equal. 
	 * Test all components diff, 2 same, all same
	 */
	@Test
	public void testEquals() {
		 Flight flight1 = new Flight("EK806","Emirates",210,14262780.0,8820.0,"Sialkot Airport");  //Main flight class to be compared against
		 Flight flight2 = new Flight("LH866","Lufthansa",495,19565370.0,19305.0,"Dubai Airport");  //all parameters different
		 assertFalse(flight1.equals(flight2));
		 
		 Flight flight3 = new Flight("EK806","Emirates",210,14262780.0,8820.0,"Sialkot Airport");  //all parameters same
		 assertTrue(flight1.equals(flight3));
		 
		 Flight flight4 = new Flight("EK222","Emirates",210,14262780.0,8820.0,"Sialkot Airport");  //different Flight Code
		 assertFalse(flight1.equals(flight4));		 
	}
	
	/**
	 * Test the compareTo() method of the Flight class, which alphabetically compares the Airline names.
	 */
	@Test
	public void testCompareTo() {
		 Flight flight1 = new Flight("EK806","Emirates",210,14262780.0,8820.0,"Sialkot Airport");  //Main flight class to be compared against
		 Flight flight2 = new Flight("LH866","Lufthansa",495,19565370.0,19305.0,"Dubai Airport");  //all parameters different
		 assertTrue("Should come before", flight1.compareTo(flight2)<0.0);
		 assertTrue("Should come after", flight2.compareTo(flight1)>0.0);
		 Flight flight3 = new Flight("EK806","Emirates",210,14262780.0,8820.0,"Sialkot Airport");  //all parameters same
		 assertEquals("Should be the same", 0, flight1.compareTo(flight3));
	}
}