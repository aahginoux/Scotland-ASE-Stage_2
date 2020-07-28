package main;

/**
 * Used to keep track of the running speed rate of the simulation. 
 * This can give a random wait time or convert a given wait time into 
 * milliseconds and factored into the simulation speed.
 * @author Alan Spence (as294)
 *
 */
public final class RuntimeSpeedController {
	private int speedSetting = 1;
	private double maxTimeToEnterDetails = 10;
	private static RuntimeSpeedController instance = null;
	
	private RuntimeSpeedController() {}
	
	/**
	 * Get an instance of RuntimeSpeedController (singleton).
	 * @return An instance of the RuntimeSpeedController.
	 */
	public static RuntimeSpeedController getInstance() {
		if(instance == null) {
			instance = new RuntimeSpeedController();
		}
		
		return instance;
	}
	
	/**
	 * Change the speed the simulation is going at.
	 * @param speed Rate of simulation. 2: double speed, 4: four times the speed and 
	 * all other entries set the speed to normal rate.
	 */
	public void SetRuntimeSpeed(int speed) {
		if(speed == 20 || speed == 100) {
			speedSetting = speed;
		} else {
			speedSetting = 1;
		}
	}
	
	/**
	 * Give the current speed of the simulation.
	 * @return 1 (normal), 2 (twice as fast) or 4 (four times faster). 
	 */
	public int CurrentSpeedSetting() {
		return speedSetting;
	}
	
	/**
	 * Get a random wait time between 0 and maxTime.
	 * @param maxTime Maximum value the wait time can be in seconds.
	 * @return A wait time in milliseconds.
	 */
	public int RandomWaitTime(double maxTime) {
		return (int) (((Math.random() * maxTime) / speedSetting) * 1000);
	}
	
	/**
	 * Makes a wait time match the speed of the simulation.
	 * @param waitTime Time to be converted in seconds.
	 * @return Wait time now in milliseconds.
	 */
	public int MatchSimulationSpeed(double waitTime) {
		if(speedSetting != 1) {
			return (int) ((waitTime / speedSetting) * 1000);
		}
		
		return (int) waitTime * 1000;
	}
	
	/**
	 * Gives a random wait time that can cover a passenger typing in details. 
	 * @return A wait time in milliseconds.
	 */
	public int TimeToEnterDetails() {
		return (int) (((Math.random() * maxTimeToEnterDetails) / speedSetting) * 1000); 
	}
}
