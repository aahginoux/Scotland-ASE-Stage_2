package main;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Passenger queue class, notifies when passenger enters queue and when a passenger goes 
 * to a kiosk. Has booleans to indicate which has happened.
 * @author Mehdi Seddiq (ms256) & Alan Spence (as294)
 *
 */
public class PassengerQueue extends Observable {
	private boolean queueDisplayChange, passengerJoinsQueue;
	private ArrayList<Passenger> queue;
	
	/**
	 * Constructor for Passenger Queue. 
	 * Get queue ready for passengers, set indicators to false.
	 */
	public PassengerQueue() {
		queue = new ArrayList<Passenger>();
		queueDisplayChange = false;
		passengerJoinsQueue = false;
	}
	
	/**
	 * Find out if the queue is empty.
	 * @return True: Empty. False: Passengers in queue.
	 */
	public boolean IsQueueEmpty() {
		return queue.isEmpty();
	}
	
	/**
	 * Allow a passenger to join the end of the queue.
	 * @param newPassenger Passenger for the end of the queue.
	 */
	public synchronized void PassengerJoiningQueue(Passenger newPassenger) {
		queue.add(newPassenger);
		passengerJoinsQueue = true;
		
		// If adding to an empty or small queue, notify new display details for GUI.
		if(queue.size() < 11) {
			queueDisplayChange = true;
		}
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Indicates if a passenger has joined the end of the queue.
	 * @return True: Passenger has joined the queue. False: No change.
	 */
	public boolean HasAPassengerJoinedTheQueue() {
		return passengerJoinsQueue;
	}
	
	/**
	 * Tells how many passengers are in the queue.
	 * @return How many are in the queue.
	 */
	public int SizeOfQueue() {
		return queue.size();
	}
	
	/**
	 * Once a passenger has gone to a kiosk, use this to reset so updates only happen 
	 * when needed.
	 */
	public void ResetPassengerJoinedIndicator() {
		passengerJoinsQueue = false;
	}
	
	/**
	 * Get the passenger from the front of the queue and notify observers.
	 * @return The passenger at the front of the queue.
	 */
	public synchronized Passenger HeadToKiosk() {
		queueDisplayChange = true;

		setChanged();
		notifyObservers();
		
		return queue.remove(0);
	}
	
	/**
	 * See if the front of the queue has changed to know if GUI needs updated.
	 * @return True: Passengers at front have changed. False: No change to front of queue.
	 */
	public boolean HasChangeToQueueDisplayInfoBeenMade() {
		return queueDisplayChange;
	}
	
	/**
	 * Gives the information of the passengers at the front of the queue, up to 10.
	 * @return An object 2D array holding each booking reference, name & flight code.
	 */
	public Object[][] HeadOfTheQueue() {
		int numberOfPassngers = queue.size();
		
		// We only want to display up to first 10 in queue.
		if(numberOfPassngers > 10) {
			numberOfPassngers = 10;
		}
		
		Object[][] headOfQueue = new Object[numberOfPassngers][3];
		Passenger gettingInfoFrom;
		Object[] info;
		
		// Go through getting information from passengers.
		for(int i = 0; i < numberOfPassngers; i++) {
			gettingInfoFrom = queue.get(i);
			info = gettingInfoFrom.QueueDisplayInformation();
			
			headOfQueue[i][0] = info[0];
			headOfQueue[i][1] = info[1];
			headOfQueue[i][2] = info[2];
		}
		
		return headOfQueue;
	}
	
	/**
	 * Once the new display information has been taken, use this to reset so updates 
	 * only happen when needed.
	 */
	public void ResetChangeToQueueDisplayIndicator() {
		queueDisplayChange = false;
	}
}
