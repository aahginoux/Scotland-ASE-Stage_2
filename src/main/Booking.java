package main;

/**
 * This class holds all the passenger's booking informations.
 *
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */


public class Booking implements Comparable<Booking>{
	
	private String bookingReference, flightCode, firstName, surname;
	private Luggage luggageInfo;
	private boolean checkInStatus;
	
	public Booking(String bookingReference, String flightCode, String firstName, String surname, boolean checkedIn) {
		this.bookingReference = bookingReference;
		this.flightCode = flightCode;
		this.firstName = firstName;
		this.surname = surname;
		this.checkInStatus = checkedIn;
		luggageInfo = null;
	}
	
	
	
	// ******************************
	// Methods
	// ******************************
	
	// Getters
	
	public String getBookingReference() {
		return bookingReference;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getSurname(){
		return surname;
	}
	public boolean getCheckInStatus(){
		return checkInStatus;
	}
	public Luggage getLuggageInfo(){
		return luggageInfo;
	}
	
	// Setters
	public void setBookingReference(String newBookingReference){
		this.bookingReference = newBookingReference;
	}
	public void setFlightCode(String newFlightCode){
		this.flightCode = newFlightCode;
	}
	public void setFirstName(String newFirstName){
		this.firstName = newFirstName;
	}
	public void setSurname(String newSurname){
		this.surname = newSurname;
	}
	public void setLuggageInfo(Luggage newLuggageInfo){
		this.luggageInfo = newLuggageInfo;
	}
	
	public void Checking() {
		this.checkInStatus = true;
	}
	
	
	@Override
	public int compareTo(Booking anotherBooking) {
		return bookingReference.compareTo(anotherBooking.getBookingReference());
	}
	
	@Override
	public String toString() {
		String status = "";
		if(this.checkInStatus)
			status = ", Checked-in.";
		if(!this.checkInStatus)
			status = ", not Checked-in.";
		
		return ("Booking: " + bookingReference + ", " + flightCode + ", " + firstName + ", " + surname + status);	
	}

	
	public String SummaryString() {
		String status = "";
		if(this.checkInStatus)
			status = ", Checked-in.";
		if(!this.checkInStatus)
			status = ", not Checked-in.";
		return ("" + bookingReference + "," + flightCode + "," + firstName + "," + surname + "," + status + "," + luggageInfo.weight + "," + luggageInfo.length + "," + luggageInfo.width + "," + luggageInfo.height);
	}
	
}
