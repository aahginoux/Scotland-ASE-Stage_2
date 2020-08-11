package jUnitTesting;
import static org.junit.Assert.*;
import org.junit.Test;
import airportCheckIn.*;

/**
 * This JUnit Test class is used to test the Baggage class
 * @author Suraj Sivaprasad
 */
public class TestBaggage 
{
	/**
	 * Tests method that returns the Baggage Volume from Bag dimensions
	 * numbers < 10 need a leading 0 for day and month
	 */
	@Test
	public void testGetBagVolume() 
	{
		double expected1 = 40000.0;
		String message1 = "Failed for length=50cm, breadth=40cm, height=20cm, \n";
		Baggage bag1 = new Baggage(50.0,40.0,20.0,25);
		double actual1 = bag1.getBagVolume();
		assertEquals(message1, expected1, actual1,0);

		double expected2 = 13125.0;
		String message2 = "Failed for length=35cm, breadth=25cm, height=15cm, \n";
		Baggage bag2 = new Baggage(35.0,25.0,15.0,15.0);
		double actual2 = bag2.getBagVolume();
		assertEquals(message2, expected2, actual2,0);
	}


	/**
	 * Tests that IllegalArgumentException is thrown for invalid Bag parameters.
	 * Invalid entries include having negative dimensions for the bag.
	 */
	@Test(expected = IllegalArgumentException.class)
	public  void invalidBagDimensionsSupplied() 
	{
		Baggage bag3 = new Baggage(-35.0,25.0,15.0,15.0);
		System.out.println(bag3.getBagLength());
	}

	/**
	 * Tests the IllegalArgumentException thrown when a negative value is passed for the Bag weight.
	 */
	@Test
	public  void invalidBagWeightSupplied() 
	{
		try 
		{
			Baggage bag4 = new Baggage(30.0,20.0,10.0,-20.0);
			System.out.println(bag4.getBagWeight());
			fail("Invalid Bag Weight supplied - should throw an exception");
		}
		catch (IllegalArgumentException e) {
			assertTrue(e.getMessage().contains("-20"));
		}
	}
}