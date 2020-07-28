package main;

/**
 * @author Alan Spence (as294)
 *
 */
public class Passenger {
	private Booking passengersDetails;
	private Boolean makeSecondAttempt, makeThirdAttempt, alterReference, alterSurname;
	private int attemptCount;
	
	/**
	 * Constructor for Passenger.
	 * @param passengerBooking Initial details to produce passenger with.
	 */
	public Passenger(Booking passengerBooking) {
		passengersDetails = passengerBooking;
		// Create random baggage details for passenger.
		double weight = Math.random() * 21 + 5;
		double length = Math.random() * 39 + 10;
		double width = Math.random() * 39 + 10;
		double height = Math.random() * 39 + 10;
		Luggage baggageDetails = new Luggage(weight, length, width, height);
		
		// Add baggage details to passenger's details.
		passengersDetails.setLuggageInfo(baggageDetails);
		
		attemptCount = 1;
		
		// Default set up of attempt booleans.
		makeSecondAttempt = false;
		makeThirdAttempt = false; 
		alterReference = false;
		alterSurname = false;
		
		// Have the passenger make multiple attempts at checking in.
		if(Math.random() > 0.8) {
			makeSecondAttempt = true;
			
			// 50/50 chance to make a third attempt.
			if(Math.random() > 0.5) {
				makeThirdAttempt = true;
			} 
			
			// 50/50 chance to alter booking reference.
			if(Math.random() > 0.5) {
				alterReference = true;
				
				// 50/50 chance to alter surname too.
				if(Math.random() > 0.5) {
					alterSurname = true;
				} 				
			} else {
				// Make sure an alteration is made.
				alterSurname = true;
			}
		} 
	}
	
	/**
	 * Gives check in details for this passenger.
	 * For the simulation, this passenger may get the details wrong and have to retry.
	 * Note: Gets it wrong by missing out last char from reference or surname.
	 * @return An Attempt which holds the check in details for this passenger.
	 */
	public Attempt CheckInDetails() {
		String reference = passengersDetails.getBookingReference();
		String name = passengersDetails.getSurname();
		
		// If 3 attempts have been made, request manned kiosk.
		if(attemptCount > 3) {
			return new Attempt(reference, name, true);
		}
		
		// Check if a second attempt is to be made.
		if(makeSecondAttempt) {
			// Check this is detected and done on first attempt.
			if(attemptCount == 1) {
				reference = AlterReferenceCheck(reference);
				name = AlterSurnameCheck(name);
			// Check if a third attempt is to be made and this is the second attempt.
			} else if(makeThirdAttempt && attemptCount == 2) {
				reference = AlterReferenceCheck(reference);
				name = AlterSurnameCheck(name);
			}
		}
		
		attemptCount += 1;
		return new Attempt(reference, name, false);
	}
	
	/**
	 * Checks in booking reference is to be altered for attempt and alters if required.
	 * @param bookingReference Reference to check.
	 * @return Reference entered for check (alter/not altered).
	 */
	private String AlterReferenceCheck(String bookingReference) {
		if(alterReference) {
			bookingReference.substring(0, (bookingReference.length() - 1));
		}
		
		return bookingReference;
	}
	
	/**
	 * Checks in surname is to be altered for attempt and alters if required.
	 * @param surname Name to check.
	 * @return Name entered for check (alter/not altered).
	 */
	private String AlterSurnameCheck(String surname) {
		if(alterSurname) {
			surname += "1";
		}
		
		return surname;
	}
	
	/**
	 * Gets the passenger's baggage details for completing check in.
	 * @return Passenger's baggage details.
	 */
	public Luggage EnterBaggageDetails() {
		return passengersDetails.getLuggageInfo();
	}
	
	/**
	 * Gives the Passenger's details (comma separated, file format).
	 */
	public String toString() {
		return passengersDetails.SummaryString();
	}
	
	/**
	 * Gives the passenger information needed for queue display in GUI.
	 * @return An Object array of passenger's booking reference, full name & flight code.
	 */
	public Object[] QueueDisplayInformation() {
		Object[] info = new Object[3];
		
		info[0] = passengersDetails.getBookingReference();
		info[1] = passengersDetails.getFirstName() + " " + passengersDetails.getSurname();
		info[2] = passengersDetails.getFlightCode();
		
		return info;
	}
 }
