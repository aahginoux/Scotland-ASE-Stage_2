package airportCheckIn;

/**
 * Name Class to hold first, middle and last name
 * Adapted using code from Monica Farrow created for the Course F21SF
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */
public class Name implements Comparable <Name>
{
	private String firstName;
	private String middleName;
	private String lastName;

	/**
	 * Constructor for Name class with first and last name 
	 * @param fName first name
	 * @param lName last name
	 */
	public Name(String fName, String lName)
	{
		firstName = fName;
		middleName = "";    //empty middlname
		lastName = lName;
	}

	/**
	 * Constructor for Name class with first, middle and last name
	 * The middle name can also be an empty string ""
	 * @param fName first Name
	 * @param mName middle name
	 * @param lName last Name
	 */
	public Name(String fName, String mName, String lName) 
	{
		firstName = fName;
		middleName = mName;
		lastName = lName;
	}

	/**
	 * Constructor for Name class from a full name parameter
	 * The full name String should have a proper single space between each of the names
	 * Example1: first name, then single space, then last name
	 * Example2: first name, space, middle name, space, last name
	 * @param fullName A string that contains the whole name
	 */
	public Name (String fullName) 
	{
		int spacePos1 = fullName.indexOf(' ');    //find position of first space after the first name
		firstName = fullName.substring(0, spacePos1); 
		int spacePos2 = fullName.lastIndexOf(' '); //find position of the last space
		if (spacePos1 == spacePos2)    // if the position of the last space and first space is the same
			middleName = "";           // the middlename is empty (doesn't exist)
		else 
			middleName = fullName.substring(spacePos1+1, spacePos2);
		lastName = fullName.substring(spacePos2 + 1);
	}

	/**
	 * A get method to return the first name only
	 * @return firstname
	 */
	public String getFirstName() 
	{	return firstName;	}

	/**
	 * A get method to return the last name only
	 * @return lastname
	 */
	public String getLastName()
	{	return lastName;	}

	/**
	 * A set-method for setting the last name
	 * @param lastname
	 */
	public void setLastName(String ln)
	{	lastName = ln;	}

	/**
	 * Returns the first name, a single space and the last name
	 * @return The First name and last name
	 */
	public String getFirstAndLastName() 
	{	return firstName + " " + lastName;	}

	/**
	 * Returns the last name, followed by a comma and space, and the first name
	 * @return lastname, firstname
	 */
	public String getLastCommaFirst() 
	{	return lastName + ", "+ firstName;	}

	/**
	 * Returns a string having the whole name with correct spacing
	 * Example1: first name, then single space, then last name
	 * Example2: first name, space, middle name, space, last name
	 * @return complete Name
	 */

	public String getFullName() 
	{
		String result = firstName + " ";
		if (!middleName.equals(""))  //if middlename is not empty
		{	result += middleName + " ";	}
		result += lastName;
		return result;
	}

	/**
	 * Returns name the all the initial characters of the name separated by a period.
	 * @return Initials of the Name
	 */
	public String getInitials()
	{
		String res = firstName.charAt(0) + ".";
		if (!middleName.equals(""))  //if middlename is not empty
		{	res += middleName.charAt(0) + ".";	}
		res+= lastName.charAt(0) + ".";;
		return res;
	}

	/**
	 * Implementation of Inherited Abstract method compareTo of the Comparable Interface
	 */
	public int compareTo(Name other) 
	{
		String thisName=this.getFullName();
		String othername = other.getFullName();
		return thisName.compareTo(othername);
	}
} //end of Name class.