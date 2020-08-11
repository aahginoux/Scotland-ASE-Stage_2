package airportCheckIn;
import java.util.ArrayList;

public class CheckInDeskList 
{
	private ArrayList<CheckInDesk> cidList;

	public CheckInDeskList() 
	{	this.cidList = new ArrayList<CheckInDesk>();	}
	
	public void add(CheckInDesk l) 
	{	cidList.add(l);	}
	
	public CheckInDesk find(int id) 
	{
		for (CheckInDesk c : cidList)
		{
			if (c.getDeskID()== id)
			{	return c;	}
		}
		return null;
	}
	
	public CheckInDesk get(int i) 
	{	return cidList.get(i);	}
	
	public int getSize()
	{	return cidList.size();	}
}