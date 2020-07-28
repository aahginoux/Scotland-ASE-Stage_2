/**
 * 
 */
package main;

import java.util.Observable;


/**Simulates the AutoKiosks
 * @author mehdi seddiq (ms256)
 *
 */
public class AutoKiosk extends Observable implements Runnable {
	private KioskStatusList kioskStatusList;
	private boolean checkinRunning;
	private boolean queueEmpty;
	private boolean bookingRefIsValid; //showing the validity of booking reference and surname
	private int pauseForPayment;
	private int pauseForEntryCheck;	
	private int pauseForBaggage;
	private RuntimeSpeedController speedController;
	private final int maxPauseForPayment=150;
	private final int maxPauseForBaggage=200;
	private Passenger passenger;
	private int kioskNumber;
	private Attempt attempt;
	private Luggage baggageInfo;
	private boolean planeIsAvailable;
	private String kioskEvent;
 
	/**
	 * Constructor for AutoKiosk
	 * @param
	 */
	public AutoKiosk(){
		checkinRunning=true;
		queueEmpty=true;
		bookingRefIsValid=false;
		kioskStatusList=KioskStatusList.WAITING_FOR_PASSENGER;
		pauseForPayment = 0;
		pauseForEntryCheck=0;
		pauseForBaggage=0;
		kioskNumber=0;
		attempt=null;
		baggageInfo=null;
		planeIsAvailable=true;
		kioskEvent="No event";
		speedController = RuntimeSpeedController.getInstance();
		
	}
	
	/** (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run(){
		while (checkinRunning){
			StayAvailable();
			CheckEntries();
			if (attempt.UseMannedKiosk()){
				kioskStatusList=KioskStatusList.SEND_TO_MANNED_KIOSK;
				setChanged();
				notifyObservers();
			}
			if (bookingRefIsValid){
				kioskStatusList=KioskStatusList.CHECK_PLANE;
				setChanged();
				notifyObservers();
				if (planeIsAvailable){
					kioskStatusList=KioskStatusList.GETTING_BAGGAGE;
					ReceiveBaggage();
					setChanged();
					notifyObservers();
					if (baggageInfo.getAdditionalFees()!=0){
						kioskStatusList=KioskStatusList.CHECK_PLANE;
						setChanged();
						notifyObservers();
						if (planeIsAvailable){
							kioskStatusList=KioskStatusList.DOING_PAYMENT;
							DoPayment();
							setChanged();
							notifyObservers();
						}
					}
					kioskStatusList=KioskStatusList.SEND_TO_PLANE;
					setChanged();
					notifyObservers();
				}
			}
		}//while (checkinRunning)
	}// run

	private void StayAvailable(){
		while (queueEmpty) {
			kioskStatusList=KioskStatusList.WAITING_FOR_PASSENGER;

			// Wait half a second.
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// Restore the interrupted status
			    Thread.currentThread().interrupt(); 
			    // TODO: Not sure if this is how I want this handled.
			}
		}
		kioskStatusList=KioskStatusList.HAS_PASSENGER;//This is just to say NOT WAITING_FOR_PASSENGER 
	}
	
	private void CheckEntries(){
		pauseForEntryCheck=speedController.TimeToEnterDetails();
		try {
			Thread.sleep(pauseForEntryCheck);
		} catch (InterruptedException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		kioskStatusList=KioskStatusList.CHECKING_ENTRIES;
		attempt=passenger.CheckInDetails();
		
		while(!VerifyBookingRef(attempt.BookingReference(),attempt.Surname()) 
				&& !attempt.UseMannedKiosk()) {
			attempt=passenger.CheckInDetails();
		}	
	}
	
	private void ReceiveBaggage(){
		pauseForBaggage=speedController.RandomWaitTime(maxPauseForBaggage);
		try {
			Thread.sleep(pauseForBaggage);
		} catch (InterruptedException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		baggageInfo=passenger.EnterBaggageDetails();
	}
	
	private void DoPayment(){
		pauseForPayment=speedController.RandomWaitTime(maxPauseForPayment);
		try {
			Thread.sleep(pauseForPayment);//pause due to payment process 
		} catch (InterruptedException e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public Luggage GetBaggageInfo(){
		return baggageInfo;
	}
	
	public Attempt GetAttempt(){
		return attempt;
	}
	

	public void SetPassenger(Passenger givenPassenger){
		passenger=givenPassenger; 
	}
	
	public void SetKioskStatus(KioskStatusList givenKioskStatus){
		kioskStatusList=givenKioskStatus;
	}

	public Passenger GetPassenger(){
		return passenger; 
	}
	
	public void SetCheckinRunning(boolean givenCheckinRunning){
		this.checkinRunning=givenCheckinRunning;
	}
	
	public void SetKioskEvent(String givenKioskEvent){
		kioskEvent=givenKioskEvent;
	}
	
	public String GetKioskEvent(){
		return kioskEvent;
	}
	
	public void SetBookingRefIsValid(boolean givenEntryIsValid){
		bookingRefIsValid=givenEntryIsValid;
	}
	
	public void SetPlaneIsAvailable(boolean givenPlaneIsAvailable){
		planeIsAvailable=givenPlaneIsAvailable;
	}
	
	public void SetQueueEmpty(boolean givenQueueEmpty){
		queueEmpty=givenQueueEmpty;
	}
	
	public KioskStatusList GetKioskStatus(){
		return kioskStatusList;
	}
	
	public int GetKioskNumber(){
		return kioskNumber;
	}
	
	public void SetKioskNumber(int givenKioskNumber){
		kioskNumber=givenKioskNumber;
	}
	
/*	*//**
	 * verifies if the given string matches the predefined format of booking references	
	 * @param refString is a string which is supposed to be a booking reference
	 * @returns true if the string matches the format, false otherwise
	 */
	private Boolean CheckBookingRefFormat (String refString) {
		final int referenceSize=7;
		String bookingRef=refString.trim();
		bookingRef=bookingRef.toUpperCase();
		if (refString == null || refString.isEmpty() || 
			refString.toCharArray().length <referenceSize ||
			refString.toCharArray().length >referenceSize ||
			!(Character.isLetter(bookingRef.charAt(0)) &&
			  Character.isLetter(bookingRef.charAt(1)) &&
			  Character.isDigit(bookingRef.charAt(2)) &&
			  Character.isDigit(bookingRef.charAt(3)) &&
			  Character.isLetter(bookingRef.charAt(4)) &&
			  Character.isLetter(bookingRef.charAt(5)) &&
			  Character.isDigit(bookingRef.charAt(6)))){
			return false;
		}else{
			return true;
		}
	}

/*
	 * verifies the given string to be potentially a surname 
	 * @param refString which is supposed to be a surname
	 * @return true if the input is deduced as a surname, false otherwise
	 */
	private Boolean CheckSurnameFormat(String surString) {
		String surname=surString.trim();
		String s1 = surname.substring(0, 1).toUpperCase();
		surname=s1 + surname.substring(1).toLowerCase();
		if (surString == null || surString.isEmpty()) {
			return false;
		}
		for(char c : surString.toCharArray()){
	        if(Character.isDigit(c) || Character.isSpaceChar(c)){
	        	return false;
	        }
		}
		return true;
	}

	private boolean VerifyBookingRef (String refString, String surString) {
		if (!CheckBookingRefFormat(refString)){
			return false;
		}
		if (!CheckSurnameFormat(surString)){
			return false;			
		}

		return true;
	}	
		
}


