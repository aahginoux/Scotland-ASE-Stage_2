package main;

import java.util.ArrayList;
import java.util.Observable;


/**
 * A class to represent a plane for the simulation. One that can have a departure time for 
 * the flight, log the passengers that board the plane and can be delayed if less than half 
 * the passengers are aboard.
 * @author Alan Spence (as294)
 *
 */
public class Plane extends Observable {
	private Flight flightDetails;
	private String flightCode;
	private String departureTime;
	private SimulationClock clock;
	private ArrayList<String> boarded;
	private boolean delayed;
	
	/**
	 * Constructor for plane class.
	 * @param flightCode The unique code for the plane.
	 * @param flightDetails The flight record for this plane.
	 */
	public Plane(String flightCode, Flight flightDetails) {
		this.flightCode = flightCode;
		this.flightDetails = flightDetails;
		delayed = false;

		// Get an instance of the simulation clock and use capacity to calculate departure time.
		clock = SimulationClock.GetInstance();
		departureTime = clock.AddDepartureTime(flightDetails.getPassengerCapacity());
	}
	
	/**
	 * Gives the plane's flight code.
	 * @return Flight code (String).
	 */
	public String FlightCode() {
		return flightCode;
	}
	
	/**
	 * Gives the departure time for the plane.
	 * @return String representing time as HH:mm.
	 */
	public String DepartureTime() {
		return departureTime;
	}
	
	/**
	 * A passenger has checked in, have them board flight.
	 * @param Passenger The passenger boarding (toString).
	 */
	public void BoardFlight(String Passenger) {
		boarded.add(Passenger);
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Gives the total of passengers that have boarded the plane.
	 * @return Number of passengers currently on the plane.
	 */
	public int TotalOfPassengersThatHaveBoarded() {
		return boarded.size();
	}
	
	/**
	 * Checks if the departure time has been reached or even exceeded.
	 * @return True: Not yet departed. False: Departure has been.
	 */
	public boolean StillAvailableForBoarding() {
		return !clock.DepartureCheck(departureTime);
	}
	
	/**
	 * Indicates if the plane has been delayed or not.
	 * @return True: Departure delayed. False: Departing on time.
	 */
	public boolean HasPlaneBeenDelayed() {
		return delayed;
	}
	
	/**
	 * Checks how many have boarded the plane and if there is not enough, delay departure. 
	 * Note: Only works at (or past) departure time.
	 * @return True: Plane can depart. False: Not enough passengers on the plane.
	 */
	public boolean ReadyToDepart() {
		if(clock.DepartureCheck(departureTime)) {
			if(boarded.size() >= (flightDetails.getPassengerCapacity() / 2)) {
				return true;
			}
			
			departureTime = clock.AddDepartureTime((flightDetails.getPassengerCapacity() / 2));
			delayed = true;
			setChanged();
			notifyObservers();
		}
		
		return false;
	}
	
	/**
	 * The plane is departing, give it the final details.
	 * @param flightDetails Details accumulated from Kiosks. 
	 */
	public void PlaneDeparts(Flight flightDetails) {
		this.flightDetails = flightDetails;
	}
	
	/**
	 * Generates the summary of the departed plane.
	 * @return String containing the flight details and the passengers that boarded.
	 */
	public String DepartureDetails() {
		String departure = "";
		
		// Summary string of flight.
		departure += flightDetails.CreateSummary();
		
		// Add each passenger.
		departure += "Passnegers which boarded this flight are :\n";
		for (String passenger : boarded) {
			departure += passenger + "\n";
		}
		
		return departure;
	}
	
	
}
