/**
 * 
 */
package main;

import java.util.Observable;


/**
 * @author mehdi seddiq (ms256)
 *
 */
public class MannedKiosk extends Observable implements Runnable {
	private int pauseInMannedKiosk;
	private Passenger passenger;
	private final int maxPauseInMannedKiosk=300;
	private RuntimeSpeedController speedController;
	private Luggage baggageInfo;
	private Attempt attempt;
	private KioskStatusList kioskStatusList;
	private KioskStatusList kioskStatus;
	private String kioskEvent;


	/**
	 * 
	 */
	public MannedKiosk() {
		// TODO Auto-generated constructor stub
		pauseInMannedKiosk=0;
		passenger=null;
		kioskEvent="";
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (passenger!=null){
			pauseInMannedKiosk=speedController.RandomWaitTime(maxPauseInMannedKiosk);
			try {
				Thread.sleep(pauseInMannedKiosk);
			} catch (InterruptedException e) {
				//System.out.println(e.getMessage());
				e.printStackTrace();
			}
			attempt=passenger.CheckInDetails() ;
			kioskStatus=KioskStatusList.SEND_TO_PLANE;
		}
		

	}

	
	public void SetPassenger(Passenger givenPassenger){
		passenger=givenPassenger;
	}
	
	public Luggage GetBaggageInfo(){
		return baggageInfo;
	}
	
	public Attempt GetAttempt(){
		return attempt;
	}
	
	public KioskStatusList GetKioskStatus(){
		return kioskStatusList;
	}

	public void SetKioskEvent(String givenKioskEvent){
		kioskEvent=givenKioskEvent;
	}
	
	public String GetKioskEvent(){
		return kioskEvent;
	}

	
	public void SetKioskStatus(KioskStatusList givenKioskStatus){
		kioskStatusList=givenKioskStatus;
	}	
}
