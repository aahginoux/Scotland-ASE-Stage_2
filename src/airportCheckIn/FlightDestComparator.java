package airportCheckIn;
import java.util.Comparator;

/**
 * This is the Comparator Class used to sort Flight objects based on their Flight Name.
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class FlightDestComparator implements Comparator<Flight>
{
	public int compare(Flight f1, Flight f2) 
	{
		return f1.getDestination().compareTo(f2.getDestination());
	}
}
