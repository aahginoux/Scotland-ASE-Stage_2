package main;

/**
 * A simple class to be used to return information for check in attempts at kiosks.
 * @author Alan Spence (as294)
 *
 */
public class Attempt {
	private String reference, surname;
	private Boolean useMannedKiosk;
	
	/**
	 * Constructor for Attempt
	 * @param bookingReference The booking reference to search system for booking with.
	 * @param surname The surname to search system for booking with.
	 * @param useMannedKiosk After 3 attempts the passenger will want to go to a manned kiosk.
	 */
	public Attempt(String bookingReference, String surname, Boolean useMannedKiosk) {
		reference = bookingReference;
		this.surname = surname;
		this.useMannedKiosk = useMannedKiosk;
	}
	
	/**
	 * Booking reference to enter.
	 * @return Booking reference for check in.
	 */
	public String BookingReference() {
		return reference;
	}
	
	/**
	 * Surname to enter.
	 * @return Surname for check in.
	 */
	public String Surname() {
		return surname;
	}
	
	/**
	 * Whether passenger wants to go to a manned kiosk.
	 * @return True -> Go to manned. False -> Make a check in attempt.
	 */
	public Boolean UseMannedKiosk() {
		return useMannedKiosk;
	}
	

}