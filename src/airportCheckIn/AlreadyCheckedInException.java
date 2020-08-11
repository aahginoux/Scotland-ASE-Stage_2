package airportCheckIn;

/**
 * Custom exception class that throws an error for a passenger who has already checked-In.
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class AlreadyCheckedInException extends Exception
{
	private static final long serialVersionUID = 1L;

	public  AlreadyCheckedInException (String b)
	{
		super("Duplicate Booking Reference "+b);
	}
}
