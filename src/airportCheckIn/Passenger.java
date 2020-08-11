package airportCheckIn;

/**
 * Passenger class is used to represent each individual Passenger.
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class Passenger 
{
	//instance variables
	private String passportNum;		//Unique passport number
	private Name paxName; 			//Passenger Name
	private String bookingRef;		//Booking Reference
	
	/**Constructor for creating a Passenger Object with the parameter values.
	 * 
	 * @param pptn	Passport Number
	 * @param pn	Passenger Name
	 * @param br	Booking Reference
	 */
	public Passenger(String pptn, Name pn, String br)
	{
		passportNum = pptn;	
		paxName = pn; 
		bookingRef = br;
	}

	//The get methods for the Passenger Class
	public String getPassportNum()
	{	return passportNum;	}
	public Name getPaxName()
	{	return paxName;	}
	public String getBookingRef()
	{	return bookingRef;	}

	//The get methods for the Passenger Class
	public void setPassportNum(String pptn)
	{	passportNum=pptn;	}
	public void setPaxName(Name pn)
	{	paxName=pn;	}
	public void setBookingRef(String br)
	{	bookingRef=br;	}
	
	/**
	 * @return	String listing all details of the Passenger
	 */
	public String paxDetails()
	{
		String report="";
		report += String.format("%-16s",passportNum);
		report += String.format("%-24s",paxName.getFullName());
		report += String.format("%-16s",bookingRef);
		report += "\n";
		return report;
	}

	/**
	 * @return	Formatted Column Header for Passenger details
	 */
	public String getPaxDetailsHeader()
	{
		String h="PASSPORTNUMBER  PASSENGERNAME           BOOKINGREFERENCE\n";
		h+=getPaxDetailsDashes();
		return h;
	}
	
	/**
	 * @return	Formatted Dashes for Passenger Details
	 */
	public String getPaxDetailsDashes()
	{
		String h="--------------------------------------------------------\n";
		return h;
	}
}