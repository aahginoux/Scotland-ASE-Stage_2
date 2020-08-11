package airportCheckIn;

/**
 * Baggage class is used to represent individual luggage of the passengers.
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class Baggage 
{
	//instance variables
	private double bagLength;
	private double bagBreadth;
	private double bagHeight;
	private double bagWeight;

	/**Constructor for creating a Baggage Object with the parameter values.
	 * 
	 * @param bl	Length of the Bag
	 * @param bb	Breadth of the Bag
	 * @param bh	Height of the Bag
	 * @param bw	Weight of the Bag
	 */
	public Baggage(double bl, double bb, double bh, double bw)
	{
		if(bl<0.0 || bb<0.0 || bh<0.0)
			throw new IllegalArgumentException("Negative values for bag dimensions are invalid!");
		if(bw<0.0)
			throw new IllegalArgumentException("Bag weight can't be negative! Invalid Entry "+ bw);
	 
		bagLength=bl;
		bagBreadth=bb;
		bagHeight=bh;
		bagWeight=bw;
	}
	
	//The get methods for the Baggage Class
	public double getBagLength()
	{	return bagLength;	}
	public double getBagBreadth()
	{	return bagBreadth;	}
	public double getBagHeight()
	{	return bagHeight;	}
	public double getBagWeight()
	{	return bagWeight;	}
	
	//The set methods for the Baggage Class	
	public void setBagLength(double bl)
	{	bagLength=bl;	}
	public void setBagBreadth(double bb)
	{	bagBreadth=bb;	}
	public void setBagHeight(double bh)
	{	bagHeight=bh;	}
	public void setBagWeight(double bw)
	{	bagWeight=bw;	}
	
	/**
	 * @return Volume of the Bag
	 */
	public double getBagVolume()
	{
		double volume=0.0;
		volume=bagLength*bagBreadth*bagHeight;
		return volume;
	}
	
	/**
	 * @return String listing all details of the Baggage
	 */
	public String getBagDetails()
	{
		String report="";
		report += String.format("%-12.2f",bagLength);
		report += String.format("%-13.2f",bagBreadth);
		report += String.format("%-12.2f",bagHeight);
		double vol=getBagVolume();
		report += String.format("%-13.2f",vol);
		report += String.format("%-10.2f",bagWeight);
		report += "\n";
		return report;
	}
	
	/**
	 * @return	Formatted Column Header for Baggage details
	 */
	public String getBagDetailsHeader()
	{
		String h="BAGID  LENGTH(cm)  BREADTH(cm)  HEIGHT(cm)  VOLUME(cm3)  WEIGHT(kg)\n";
		h+=getBagDetailsDashes();
		return h;
	}
	
	/**
	 * @return	Formatted Dashes for Baggage Details
	 */
	public String getBagDetailsDashes()
	{
		String h="-------------------------------------------------------------------\n";
		return h;
	}
}