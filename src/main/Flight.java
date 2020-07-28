package main;
/**
 * This class holds all flight's informations.
 *
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */


public class Flight implements Comparable<Flight>{
	
	private String flightCode, destination, carrier;
	private int maxPassengerCapacity;
	private double totalVolume, maxVolume, totalWeight, maxWeight, totalFeeAccumulated;
	
	
	public Flight(String flightCode, String destination, String carrier, int maxPassengerCapacity, double maxWeight, double maxVolume) {
		this.flightCode = flightCode;
		this.destination = destination;
		this.carrier = carrier;
		this.maxPassengerCapacity = maxPassengerCapacity;
		this.maxWeight = maxWeight;
		this.maxVolume = maxVolume;
		totalWeight = 0.0;
		totalVolume = 0.0;
		totalFeeAccumulated = 0.0;
	}
	
	
	// ******************************
	// Methods
	// ******************************
	
	// Getters

	public String getFlightCode() {
		return this.flightCode;
	}
	public String getDestination() {
		return this.destination;
	}
	public String getCarrier() {
		return this.carrier;
	}
	public int getPassengerCapacity() {
		return this.maxPassengerCapacity;
	}
	public double getVolume() {
		return this.totalVolume;
	}
	public double getMaxVolume() {
		return this.maxVolume;
	}
	public double getWeight() {
		return this.totalWeight;
	}
	public double getMaxWeight() {
		return this.maxWeight;
	}
	public double getTotalFeeAccumulated() {
		return this.totalFeeAccumulated;
	}
	
	// Setters

	public void setVolume(double volume) {
		totalVolume = volume;
	}
	public void setWeight(double weight) {
		totalWeight = weight;
	}
	
	
	/**
	* Check whether the volume is small enough according to comapny's legislation.
	*
	* @return True if it is.
	*/
	public boolean isVolumeAcceptable() {
		return totalVolume <= maxVolume;
	}
	
	/**
	* Check whether the weight is light enough according to comapny's legislation.
	*
	* @return True if it is.
	*/
	public boolean isWeightAcceptable() {
		return totalWeight <= maxWeight;
	}
	
	/**
	 * Generate a summary string displayed at the end of checking-in.
	 * 
	 * @return Summary information.
	 */
	public String CreateSummary() {
		StringBuffer registers = new StringBuffer();
		registers.append("FlightCode:  ");
		registers.append(flightCode + "\n");
		
        registers.append("Destination: ");
        registers.append(destination + "\n");
        
        registers.append("Carrier:  ");
        registers.append(carrier + "\n");
        
        registers.append("Max Passenger Capacity:  ");
        registers.append(maxPassengerCapacity + "\n");

        registers.append("Weight: " + totalWeight + " / " + maxWeight + " kg" + "\n");
        
        registers.append("Volume: " + totalVolume + " / " + maxVolume + " cm3" + "\n");

        registers.append("Total Fee Accumulated:  ");
        registers.append(totalFeeAccumulated);
        registers.append("  £" + "\n\n");

		return registers.toString();
	}
	
	
	
	@Override
	public int compareTo(Flight anotherFlight) {
		return flightCode.compareTo(anotherFlight.getFlightCode());
	}

	@Override
	public String toString() {
		return ("Flieght: " + flightCode + ", " + destination + ", " + carrier + ", " + maxPassengerCapacity + ", "
	+ totalVolume + " / " + maxVolume + ", "+ totalWeight + " / " + maxWeight);
	}
	

}
