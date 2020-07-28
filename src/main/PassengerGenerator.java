package main;

import java.util.ArrayList;
import java.util.Map.Entry;

import java.util.Observable;
import java.util.TreeMap;

/**
 * @author Alan Spence (as294)
 *
 */
public class PassengerGenerator extends Observable implements Runnable  {
	private ArrayList<Passenger> passengers;
	private ArrayList<Passenger> goingToQueue; 
	private int maxInitialNumber, maxJoinAmount, maxWaitTime;
	private RuntimeSpeedController simulationSpeed;
	
	/**
	 * Constructor which makes a collection of passengers from all the entries from the booking file. 
	 * Also sets the maximum initial list size of 30, maximum join queue of 5 and maximum wait time of 60seconds.
	 * @param Bookings The valid bookings loaded from the bookings file.
	 * @param invalidFormats The invalid bookings loaded from the booking file.
	 */
	public PassengerGenerator(TreeMap<String, Booking> Bookings, ArrayList<String> invalidFormats) {
		passengers = new ArrayList<Passenger>();
		goingToQueue = new ArrayList<Passenger>();
		maxInitialNumber = 30;
		maxJoinAmount = 5;
		maxWaitTime = 60;
		simulationSpeed = RuntimeSpeedController.getInstance();
		Passenger newPassenger;
		
		// Create passengers for all valid bookings.
		for(Entry<String, Booking> entry : Bookings.entrySet()) {
		  Booking value = entry.getValue();

		  newPassenger = new Passenger(value);
		  passengers.add(newPassenger);
		}
		
		// Create passenger for all the invalid bookings.
		String[] entryList;
		for(String invalidFormatBooking : invalidFormats) {
			entryList = invalidFormatBooking.split(",");
			Booking newBooking = new Booking(entryList[0], entryList[1], entryList[2], entryList[3], Boolean.parseBoolean(entryList[4]));
			
			newPassenger = new Passenger(newBooking);
			passengers.add(newPassenger);
		}
	}
	
	/**
	 * Get a random number of passengers from 0 to 30 (Can change in constructor).
	 * @return An ArrayList of Passengers. Note: could have no passengers.
	 */
	public ArrayList<Passenger> InitialPassengersForQueue() {
		ArrayList<Passenger> initialPassengers = new ArrayList<Passenger>();
		int add = (int) (Math.random() * maxInitialNumber);
		
		while(add != 0 && passengers.size() != 0) {
			initialPassengers.add(RandomlySelectAPassenger());
			add--;
		}
		
		return initialPassengers;
	}
	
	/**
	 * Gives a list of passengers that are waiting to join the queue. 
	 * @return An ArrayList of Passengers.
	 */
	public ArrayList<Passenger> PassengersToJoinTheQueue() {
		return goingToQueue;
	}
	
	/**
	 * Once passengers have been added to the queue, 
	 * this clears them from the list of those waiting to join queue. 
	 */
	public void PassengersAreNowInQueue() {
		goingToQueue = new ArrayList<Passenger>();
	}
	
	/**
	 * Gets a single passenger from the list of passengers.
	 * @return Randomly picked passenger.
	 */
	private Passenger RandomlySelectAPassenger() {
		int index = (int) (Math.random() * passengers.size());
		
		return passengers.remove(index);
	}

	@Override
	public void run() {
		int waitTime = 0;
		int howManyWantToJoin = 0;
		
		while(passengers.size() != 0) {
			// Check if passengers have yet to be added to the queue.
			while(goingToQueue.size() != 0) {
				// Wait half a second to see if passengers have gone to the queue.
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// Restore the interrupted status
				    Thread.currentThread().interrupt(); 
				    // TODO: Not sure if this is how I want this handled.
				}
			}
			
			// Work out wait time then wait.
			waitTime = simulationSpeed.RandomWaitTime((double)maxWaitTime);
			
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				// Restore the interrupted status
			    Thread.currentThread().interrupt();
			    // TODO: Not sure if this is how I want this handled.
			}
			
			// Work out randomly how many should join queue, at least 1 to maxJoinAmount. 
			howManyWantToJoin = (int) (Math.random() * (maxJoinAmount - 1)) + 1;
			
			// Get them ready for queue, ensuring I don't try to add passengers not there.
			while(howManyWantToJoin > 0 && passengers.size() != 0) {
				goingToQueue.add(RandomlySelectAPassenger());
			}
			
			setChanged();
			notifyObservers();
		}
	}
}
