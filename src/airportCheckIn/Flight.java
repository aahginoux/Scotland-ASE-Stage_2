package airportCheckIn;

/**
 * Flight class is used to represent details of the Flight.
 * The Comparable interface is implemented in order to sort Flights by their name.
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class Flight implements Comparable<Flight>
{
	//instance variables
	private String flightCode;	
	private String airlineName; 	
	private int maxNumOfPax;		//Maximum capacity of passengers in the flight
	private double maxBagVolume;	//Maximum Baggage Volume of the flight
	private double maxBagWeight;	//Maximum Baggage Weight of the flight
	private String destination; 
	private static final double WEIGHTPENALTY = 50.00;  //Excess Baggage Weight Penalty fees in USD per KG
	private static final double VOLUMEPENALTY = 1.50;	//Excess Baggage Volume Penalty fees in USD per CM3

	/**Constructor for creating a Flight Object with the parameter values.
	 * 
	 * @param fc	Flight Code	
	 * @param an	Airline Name
	 * @param mnop	Maximum number of passengers in the flight
	 * @param mbv	Maximum Baggage Volume of the flight
	 * @param mbw	Maximum Baggage Weight of the flight
	 * @param d		Destination
	 */
	public Flight(String fc, String an, int mnop, double mbv, double mbw, String d)
	{
		flightCode=fc;	
		airlineName=an; 
		maxNumOfPax=mnop;
		maxBagVolume=mbv;
		maxBagWeight=mbw;
		destination=d;
	}
	
	//The get methods for the Flight Class
	public String getFlightCode()
	{	return flightCode;	}
	public String getAirlineName()
	{	return airlineName;	}
	public int getMaxNumOfPax()
	{	return maxNumOfPax;	}
	public double getMaxBagVolume()
	{	return maxBagVolume;	}
	public double getMaxBagWeight()
	{	return maxBagWeight;	}
	public String getDestination()
	{	return destination;	}
	public double getWeightPenalty() 
	{	return WEIGHTPENALTY;	}
	public double getVolumePenalty() 
	{	return VOLUMEPENALTY;	}	
	
	//The set methods for the Flight Class
	public void setFlightCode(String fc)
	{	flightCode=fc;	}
	public void setAirlineName(String an)
	{	airlineName=an;	}
	public void setMaxNumOfPax(int mnop)
	{	maxNumOfPax=mnop;	}
	public void setMaxBagVolume(double mbv)
	{	maxBagVolume=mbv;	}
	public void setMaxBagWeight(double mbw)
	{	maxBagWeight=mbw;	}
	public void setDestination(String d)
	{	destination=d;	}
	
	/**
	 * Compare this Flight object against another, for the sorting by Airline name.
	 * @param other The other Flight object to be compared against.
	 * @return a negative integer, if this Flight's name comes before the other Flight's name
	 *         zero, if they are both the same
	 *         a positive integer, if this Flight's name comes after the other Flight's name
	 */
	public int compareTo(Flight other)
	{
		return flightCode.compareTo(other.getFlightCode());
	} 

	/**
	 * Override toString() method of parent Object Class
	 */
	public String toString()
	{
		return String.format("%-6s",flightCode)+String.format("%-26s",airlineName);
	}
	
	/**
	 * Override equals() method of parent Object Class
	 */
	public boolean equals(Flight other)
	{
		return flightCode.equals(other.getFlightCode());
	}
	
	/**
	 * @return String listing all details of the flight
	 */
	public String flightDetails()
	{
		String report="";
		report += String.format("%-12s",flightCode);
		report += String.format("%-13s",airlineName);
		report += String.format("%-11d",maxNumOfPax);
		report += String.format("%-19.2f",maxBagVolume);
		report += String.format("%-18.2f",maxBagWeight);
		report += String.format("%-11s",destination);
		report += "\n";
		return report;
	}
	
	/**
	 * @return	Formatted Column Header for Flight details
	 */
	public String getFlightDetailsHeader()
	{
		String h="FLIGHTCODE  AIRLINENAME  MAXNUMPAX  MAXBAGVOLUME(cm3)  MAXBAGWEIGHT(kg)  DESTINATION\n";
		h+=getFlightDetailsDashes();
		return h;
	}
	
	/**
	 * @return	Formatted Dashes for Flight Details
	 */
	public String getFlightDetailsDashes()
	{
		String h="------------------------------------------------------------------------------------\n";
		return h;
	}
}