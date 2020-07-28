package main;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class loads a booking text file from Resources folder and 
 * produces a collection of bookings after checking if the booking reference is 
 * in the correct format.
 *
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */

public class BookingLoader {
	
	private TreeMap<String, Booking> bookingMap;
	private ArrayList<String> inputList;
	private String path;
	

	public BookingLoader(String path) {
		bookingMap = new TreeMap<String, Booking>();
		inputList = new ArrayList<String>();
		this.path = path;
	}
	
	
	// ******************************
	// Methods
	// ******************************
	
	/**
	* Get all data from <b>BookingFile.txt</b> and add it to the list <b>inputList</b>.
	*/
	private void getBookingInput() throws NullPointerException, IOException, MyException {
		InputStream input = null;
		InputStreamReader streamReader = null;
		BufferedReader bufferedReader = null;
		
		// taken from https://stackoverflow.com/questions/15749192/how-do-i-load-a-file-from-resource-folder
		try {
			input = getClass().getResourceAsStream(path);
			streamReader = new InputStreamReader(input);
			bufferedReader = new BufferedReader(streamReader);
			for (String line; (line = bufferedReader.readLine()) != null;) {
				inputList.add(line);
			}
		} catch (NullPointerException e) {
			throw new MyException("Can't reach the file!");
		} finally {
			 try {
                if (bufferedReader != null) {
                	bufferedReader.close();
                }
                if (streamReader != null) {
                	streamReader.close();
                }
                if (input != null) {
                	input.close();
                }
            } catch (IOException ex) {
                throw ex;
            }
		}
	}
	
	/**
	* Fill the TreeMap <b>bookingMap</b> with the inputs from <b>BookingFile.txt</b>.
	*
	* @return The TreeMap <b>bookingMap</b>.
	*/
	public TreeMap<String, Booking> LoadBookings() throws NullPointerException, IOException, MyException {
		getBookingInput();
		
		String[] entryList;
		for (String i : inputList) {
			entryList = i.split(",");
			if(ValidBooking(entryList[0])) {
				AddToValidBooking(entryList);
			} else {
				// create an error
			}
		}
		return bookingMap;
	}
	
	private void AddToValidBooking(String[] entryList) {
		Booking newBooking = new Booking(entryList[0], entryList[1], entryList[2], entryList[3], Boolean.parseBoolean(entryList[4]));
		bookingMap.put(newBooking.getBookingReference(), newBooking);
	}

	private boolean ValidBooking(String bookingReference) {
		if(bookingReference.length() == 8) {
			if(Character.isLetter(bookingReference.charAt(0))) 
				if(Character.isLetter(bookingReference.charAt(1)))
					if(Character.isLetter(bookingReference.charAt(2)))
						if(Character.isDigit(bookingReference.charAt(3)))
							if(Character.isDigit(bookingReference.charAt(4)))
								if(Character.isDigit(bookingReference.charAt(5)))
									if(Character.isLetter(bookingReference.charAt(6)))
										if(Character.isLetter(bookingReference.charAt(7)))
										return true;
		}
		return false;
	}	
}
