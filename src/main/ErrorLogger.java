package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
* The class is an error storage.
* It receive errors from Bookingloader and KioskSearch GUI and store them in a text file
* @author Panagiotis Tsamoutalis (pt45)
*
*/
public class ErrorLogger {
	
	private ArrayList <String> Error_List;
	private ArrayList <String> Incorrect_Entries;
	private ArrayList<String> unexpected_Errors;

	/**
	 * Constructor of the ErrorLogger class.
	 * It creates 2 ArrayLists.
	 * Error_List is the list for the KioskSearch errors.
	 * Incorrect_Entries is the list for the incorrect entries.
	 */
	public ErrorLogger(){
		Error_List = new ArrayList<String>() ;
		Incorrect_Entries =new ArrayList <String>();
		unexpected_Errors = new ArrayList<String>();
	}
	
	 public int getSizeofEntry() {
	    	return Incorrect_Entries.size();
	    }
	 
	 public void addError(String person) 
	    {
		 Error_List.add(person);
	    }
	 public void addUnexpectedError(String er) 
	    {
		 unexpected_Errors.add(er);
	    }
	 
	 public void addEntry(String booking) 
	    {
		 Incorrect_Entries.add(booking);
	    }
	 
	// write the Arraylist in a file
		public  void WriteToFile(String FileName) {
			
			 //FileWriter DataFile;
			 try {
			    //DataFile = new FileWriter(FileName);
			    //DataFile.write(this.setTabletoFile());
			 	//DataFile.close();
			 	
			 	PrintWriter writer = new PrintWriter(
			                     new File(this.getClass().getResource(FileName).getPath()));
			 	writer.println(setTabletoFile());
			 	writer.close();
			 }
			 //message and stop if file not found
			 catch (FileNotFoundException fnf){
				 System.out.println(FileName + " can not be found ");
				 System.exit(0);
			 }
			 //stack trace here because we don't expect to come here
			 catch (IOException ioe){
			    ioe.printStackTrace();
			    System.exit(1);
			 }
		}
	    
		// create a table to save into a file
		public String setTabletoFile()
		{
			String instance = "Logged: " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(0)) 
					+ " \r\r\n" + "Incorrect Entries ("+ this.getSizeofEntry()+ ") \r\r\n";
			for (String c  : Incorrect_Entries){
				instance += String.format("%-2s", c);
				instance += "\r\n";
			}
			instance += " \r\n";
			instance += " \r\n";
			instance += String.format("%s", "Unsuccessful tries \r\r\n");
			for (String c  : Error_List){
				instance += String.format("%-2s", c);
				instance += "\r\n";
			}
			
			instance += " \r\n";
			instance += " \r\n";
			instance += String.format("%s", "Unexpected Errors \r\r\n");
			for (String c  : unexpected_Errors){
				instance += String.format("%-2s", c);
				instance += "\r\n";
			}
			return instance;
		}
}
