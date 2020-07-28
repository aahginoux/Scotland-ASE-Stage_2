/**
 * 
 */
package main;

/** main class
 * @author mehdi seddiq (ms256)
 *
 */
public class AirportCheckin {
	/**
	 * @param args
	 * @throws MyException 
	 */
	public static void main(String[] args) throws MyException {
		CheckinController controller = new CheckinController();
		controller.StartCheckin();
		
	}

}
