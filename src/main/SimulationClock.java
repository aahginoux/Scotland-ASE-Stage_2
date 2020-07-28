package main;

import java.util.ArrayList;
import java.util.Observable;

/**
 * As the simulation can run at different speeds and we have departure times for planes. 
 * This clock will start at 08:00 and keep time according to the RuntimeSpeedController 
 * speed setting. Additionally, can add departure times and will notify you of a flight 
 * is due to depart.
 * @author Alan Spence (as294)
 *
 */
public final class SimulationClock extends Observable implements Runnable {
	private int hours = 8;
	private int minutes = 0;
	private int seconds = 0;
	private RuntimeSpeedController simulationSpeed = RuntimeSpeedController.getInstance();
	private static SimulationClock clock = null; 
	private boolean keepTicking = true;
	private boolean departureDue = false;
	private ArrayList<String> departureTimes = new ArrayList<String>();
	
	/**
	 * Private constructor as using Singleton pattern.
	 */
	private SimulationClock() {}
	
	/**
	 * Get an instance to use in the simulation.
	 * @return An instance of the simulation clock.
	 */
	public static SimulationClock GetInstance() {
		if(clock == null) {
			clock = new SimulationClock();
		}
		
		return clock;
	}
	
	/**
	 * Gives the simulation time in hours and minutes (24hr format).
	 * @return String giving time as HH:mm
	 */
	public String CurrentTime() {
		return GiveTimeAsString(hours, minutes);
	}
	
	/**
	 * Given hours and minutes, will give a string of the time.
	 * @param hoursValue Hours of time 0 - 23
	 * @param minutesValue Minutes of time 0 - 60
	 * @return String giving time as HH:mm
	 */
	private String GiveTimeAsString(int hoursValue, int minutesValue) {
		String time = "";
		
		if(hoursValue < 10) {
			time += "0";
		}
		
		time += hoursValue + ":";
		
		if(minutesValue < 10) {
			time += "0";
		}
		
		time += minutesValue;
		
		return time;
	}
	
	/**
	 * Use to stop the simulation clock from running.
	 */
	public void StopTheClock() {
		keepTicking = false;
	}
	
	/**
	 * Will take the time till departure to notify of a departure due and will give 
	 * the time of the departure.
	 * @param minutesTillDeparture How many minutes until the plane will depart.
	 * @return String giving time as HH:mm
	 */ 
	public String AddDepartureTime(int minutesTillDeparture) {	
		int departureMinutes = (minutes + minutesTillDeparture) % 60;
		int hoursToAdd = ((minutes + minutesTillDeparture) - departureMinutes) / 60;
		int departureHours = (hours + hoursToAdd) % 24;	
		
		String departureTime = GiveTimeAsString(departureHours, departureMinutes);
		departureTimes.add(departureTime);
		return departureTime;
	}
	
	/**
	 * Check if a departure time has been.
	 * @param departureTime String of time as HH:mm.
	 * @return True: Departure has been. False: Not yet departed.
	 */
	public boolean DepartureCheck(String departureTime) {
		String[] departure = departureTime.split("\\:");
		int hrs = Integer.parseInt(departure[0]);
		int mins = Integer.parseInt(departure[1]);
		
		if(hrs < hours) {
			return false;
		}
		
		if(hrs == hours) {
			if(mins <= minutes) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * A check to see if notification is a departure update or time update.
	 * @return True: Departure due. False: No planes departing.
	 */
	public boolean IsThereADepartureDue() {
		return departureDue;
	}
	
	/**
	 * Resets departure indicator after planes have been checked.
	 */
	public void PlanesChecked() {
		departureDue = false;
	}
	
	/**
	 * Checks if a departure is due and will remove from list and notify change to check with planes.
	 */
	private void DepartureDue() {
		String currentTime = CurrentTime();
		
		if(departureTimes.contains(currentTime)) {
			departureTimes.remove(currentTime);
			departureDue = true;
			
			setChanged();
			notifyObservers();
		}
	}
	
	/**
	 * Increases the seconds. If increase reaches 60 seconds, increments the minutes.
	 * @param numberOfIncreaments How many seconds to increase by.
	 */
	private void IncreaseSeconds(int numberOfIncreaments) {
		for(int c = 0; c < numberOfIncreaments; c++) {
			seconds++;
			if(seconds == 60) {
				seconds = 0;
				IncreaseMinutes(1);
			}
		}
	}
	
	/**
	 * Increases the minutes. If increase reaches 60 minutes, increments the hours.
	 * @param numberOfIncreaments How many minutes to increase by.
	 */
	private void IncreaseMinutes(int numberOfIncreaments) {
		for(int c = 0; c < numberOfIncreaments; c++) {
			minutes++;
				
			setChanged();
			notifyObservers();
			
			if(minutes == 60) {
				minutes = 0;
				IncreaseHours(1);
			}
		}
	}
	
	/**
	 * Increase the hours. If increase reaches 24 hours, flips back to zero.
	 * @param numberOfIncreaments How many hours to increase by.
	 */
	private void IncreaseHours(int numberOfIncreaments) {
		for(int c = 0; c < numberOfIncreaments; c++) {
			hours++;
			if(hours == 24) {
				hours = 0;
			}
		}
	}

	@Override
	public void run() {
		while(keepTicking) {
			try {
				Thread.sleep(1000/simulationSpeed.CurrentSpeedSetting());
			} catch (InterruptedException e) {
				// Restore the interrupted status
			    Thread.currentThread().interrupt(); 
			    // TODO: Not sure if this is how I want this handled.
			}
			IncreaseSeconds(1);
			DepartureDue();
		}
	}
}