package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * This class loads a flight text file from Resources folder and 
 * produces a collection of flights.
 *
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */

public class FlightLoader {
	
	private TreeMap<String, Flight> flightMap;
	private ArrayList<String> inputList;
	private String path;
	
	
	public FlightLoader(String path) {
		flightMap = new TreeMap<String, Flight>();
		inputList = new ArrayList<String>();
		this.path = path;
	}
	
	
	// ******************************
	// Methods
	// ******************************
	
	
	/**
	* Get all data from <b>FlightFile.txt</b> and add it to the list <b>inputList</b>.
	*/
	private void getFlightInput() throws NullPointerException, IOException, MyException {
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
			throw e;
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
	
	private void ConvertInput() {
		String[] entryList;
		Flight newFlight;
		
		for (String entry : inputList) {
			entryList = entry.split(",");
			
			newFlight = new Flight(entryList[0], entryList[1], entryList[2], Integer.parseInt(entryList[3]), 
					Double.parseDouble(entryList[4]), Double.parseDouble(entryList[5]));
			flightMap.put(newFlight.getFlightCode(), newFlight);
		}
	}
	
	public TreeMap<String, Flight> LoadFlights() throws NullPointerException, IOException, MyException {
		getFlightInput();
		ConvertInput();
		
		return flightMap;
	}

}
