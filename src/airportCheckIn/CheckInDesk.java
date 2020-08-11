package airportCheckIn;

import java.util.ArrayList;
//import java.util.Observable;
//import java.util.Observer;

/**
 * CheckInDesk class is used to simulates CheckIns happening at a desk.
 * It implements Runnable to enable multi-threading
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class CheckInDesk  implements Runnable
{
	private int checkInTime;   		//Processing Time at CheckIn Desk for each passenger try
	private boolean closed;			//Boolean closed is initially false. It is later set to true, when the Check-In Desk closes.
	private int deskID;    			//CheckIn Desk ID
	private ArrayList<CheckIn> checkInsAtThisDesk;  //list of checkIns processed at this desk
	private AirportModel airport;
	private String report;
	int count=0;//The Airport Model, where the CheckInDesks are located

	public CheckInDesk(int id, AirportModel a)
	{
		closed=false;
		report="";
		deskID=id;
		checkInsAtThisDesk = new ArrayList<CheckIn>();
		airport=a;

		//If speed is totally variable, we don't seem to get any processing clashes or failures
		//Setting 2 different waiting times will give some clashes as well as some variation
		int random0to100 =(int)(Math.random() * 101);
		checkInTime = 200 + 100*(random0to100%2);
	}

	//add a CheckIn in the list of this desk to be processed
	public void addCheckIn(CheckIn c) 
	{
		checkInsAtThisDesk.add(c);
	}

	//Returns the deskID of the current desk
	public int getDeskID() 
	{
		return deskID;
	}

	//Returns the report of all CheckIns at this desk
	public String getCidList()
	{
		String d = "";
		if (checkInsAtThisDesk.size() == 0) 
		{
			d += "\n No CheckIns have happened at Desk "+deskID+"\n";
		}
		else 
		{
			d += "\n********************CheckIns at Desk "+deskID+"********************\n";
			d = "FULLNAME                    BOOKINGREF    FLIGHTCODE  EXCESSFEES($)  CHECKED-IN\n";
			d+= "-------------------------------------------------------------------------------\n";
			for (CheckIn s  : checkInsAtThisDesk)
			{
				d += String.format("%-28s", s.getPassenger().getPaxName().getFullName());
				d += String.format("%-14s", s.getPassenger().getBookingRef() );
				d += String.format("%-12s", s.getFlight().getFlightCode());
				d += String.format("%-15.2f", s.getExcessFee());
				d += String.format("%-10s", s.getCheckedIn()?"Yes":"No");
				d += "\n";
			}
			d+= "-------------------------------------------------------------------------------\n";
		}
		return d;		
	}

	/**
	 * Implementing run method() under Runnable Interface for Threading
	 * Until the end of the Airport Time, the CheckIns at this desk are processed
	 */
	public void run() 
	{
		//Keep processing until airport time is not finished
		System.out.println("Starting Thread for Check-In Desk "+deskID);
		
		try 
		{
			while (count<15) 
			{
				count++;
		
				if (deskID%2 == 0) //Sleep first for even-numbered Check-In Desks
				{  
					//Introduce variation in sleeping pattern, so CheckIns have different times
					Thread.sleep(checkInTime);
				}
				
				if(!closed)
				{
					CheckIn c=airport.getFrontOfQueue(deskID);
				
					if(c==null)
					{
						report="\nCHECK-IN DESK "+deskID+" is OPEN but not processing any Passengers.";
						System.out.println(report);
					}
					else
					{
						String bRef=c.getPassenger().getBookingRef();
						airport.CheckInNowStage2(bRef,deskID);
						System.out.println(printDetails(c));
					}
				}
				
				if (deskID%2 != 0) //Sleep later for odd-numbered Check-In Desks
				{
					//set a pause before the bid
					Thread.sleep(checkInTime);
				}
			}//End of while loop for passengers per CheckIn Desk
			
			Thread.sleep(checkInTime);
			System.out.println("\nCLOSING CHECK-IN DESK NUMBER "+ deskID+" since it's schedule is over.\n");
			closed=true;
			FinishReport();
			airport.Finish();
		}
			
		catch (InterruptedException e)
		{
			System.out.println("\nCheck-In Desk "+deskID+ " Interrupted");		
		}
		catch (Exception e) 
		{
			System.out.println("\nException in Check-In Desk "+ deskID + e.getStackTrace());
		}
	}	

	public String printDetails(CheckIn c1)
	{
		report="\nCHECK-IN DESK NUMBER "+deskID+"\n\n";
		report+=airport.CheckInStatusInUse(c1);
		return report;
	}

	public String getReport()
	{
		return report;
	}
	
	public void FinishReport()
	{
		report="\n CLOSING CHECK-IN DESK NUMBER "+deskID+"\n since it's schedule is over.";
	}
	public void changeSpeedFactor(int f)
	{	
		int random0to100 =(int)(Math.random() * 101);
		checkInTime = f * (200 + 100*(random0to100%2));
	}
}