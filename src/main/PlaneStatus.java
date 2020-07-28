package main;

/**
 * This is used to pass information on the status of the plane, used to update update plane observers on passenger 
 * boarding or plane departing.
 * @author Alan Spence (as294)
 *
 */
public class PlaneStatus {
	private String flightCode, destination, depatureTime;
	private int boarded, capacity;
	private double currentVolume, maxVolume, currentWeight, maxWeight;
	private boolean departing, delayed;
	
	/**
	 * Create a status update for a plane.
	 * @param flightCode Plane's flight code.
	 * @param destination Where the plane is going.
	 * @param depatureTime When the plane is due to depart.
	 * @param boarded How many passengers have boarded the plane.
	 * @param capacity How many passengers the plane holds.
	 * @param currentVolume Current volume taken up by baggage.
	 * @param maxVolume Maximum volume all the baggage can take up.
	 * @param currentWeight Current weight taken up by baggage.
	 * @param maxWeight Maximum weight all the baggage can weigh.
	 * @param departing True: Plane is starting journey. False: Plane still taking passengers.
	 * @param delayed True: Plane's departure delayed. False: Plane departing on time.
	 */
	public PlaneStatus(String flightCode, String destination, String depatureTime, int boarded, int capacity, 
			double currentVolume, double maxVolume, double currentWeight, double maxWeight, boolean departing,
			boolean delayed) {
		this.flightCode = flightCode;
		this.destination = destination;
		this.depatureTime = depatureTime;
		this.boarded = boarded;
		this.capacity = capacity;
		this.currentVolume = currentVolume;
		this.maxVolume = maxVolume;
		this.currentWeight = currentWeight;
		this.maxWeight = maxWeight;
		this.departing = departing;
		this.delayed = delayed;
	}
	
	/**
	 * Gives flight code of plane.
	 * @return String that represents flight code.
	 */
	public String FlightCode() {
		return flightCode;
	}
	
	/**
	 * Gives where the flight goes to.
	 * @return String stating destination.
	 */
	public String Destination() {
		return destination;
	}
	
	/**
	 * Gives when the plane is due to depart.
	 * @return String stating time as HH:mm.
	 */
	public String DepartureTime() {
		return depatureTime;
	}
	
	/**
	 * Gives how many passengers are currently on the plane.
	 * @return Number of passengers on-board.
	 */
	public int PassengersOnPlane() {
		return boarded;
	}
	
	/**
	 * Gives the maximum number of passengers that can travel on this plane.
	 * @return Capacity of the plane.
	 */
	public int PlaneCapacity() {
		return capacity;
	}
	
	/**
	 * Gives a percent value stating how much volume has been taken up.
	 * @return String stating percentage to one decimal place.
	 */
	public String VolumeStatus() {
		return String.format("%.1f", ((currentVolume / maxVolume) * 100)) + "%";
	}
	
	/**
	 * Gives a percent value stating how much weight out of maximum weight has been loaded on the plane.
	 * @return String stating percentage to one decimal place.
	 */
	public String WeightStatus() {
		return String.format("%.1f", ((currentWeight / maxWeight) * 100)) + "%";
	}
	
	/**
	 * Indicates if the plane is boarding or departing.
	 * @return True: Plane is starting journey. False: Plane still taking passengers.
	 */
	public boolean HasThePlaneDeparted() {
		return departing;
	}
	
	/**
	 * Indicates if the plane's departure time has been set back to give passengers more time.
	 * @return True: Plane's departure delayed. False: Plane departing on time.
	 */
	public boolean HasPlaneBeenDelayed() {
		return delayed;
	}
	
	/**
	 * it creates a string to be use as a flight title in the simulationGUI.
	 * @return a string with flight code and destination.
	 */
	public String flighttitle() {
		String flightname="";
		flightname+= flightCode+" to ";
		flightname+= destination;
		
		return flightname;
		
	}
	
	/**
	 * Gives an entry for the event logger for this update.
	 * @return A string stating what this update is.
	 */
	public String EventEntry() {
		if(departing) {
			return "Flight " + flightCode + " is now departing.\n";
		} 
		
		String updateEntry = "Update: Flight " + flightCode + " for " + destination;
		updateEntry += ". Currently has: " + boarded + " passengers out of " + capacity + " aboard.";
		updateEntry += " Baggage volume is at " + VolumeStatus() + " of capacity";
		updateEntry += " and baggage weighs " + WeightStatus() + " of capacity.\n";
	
		return updateEntry;
	}
}
