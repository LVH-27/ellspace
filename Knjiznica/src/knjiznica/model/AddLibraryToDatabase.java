package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import org.postgresql.util.PSQLException;
import javafx.scene.control.CheckBox;
import knjiznica.view.AddLibraryView;


public class AddLibraryToDatabase implements Runnable{
	
	private static String firstName;
	private static String phoneNumber;
	private static String email;
	private static String information;
	private static String country;
	private static String street;
	private static String houseNumber;
	private static int postalCode;
	private static ArrayList<String> beginTime;
	private static ArrayList<String> endTime;
	private static ArrayList<CheckBox> checkBoxList;
	private static boolean onlineLibraryCheck;
	
	@Override
	public void run() {
		
		int libraryID = -1; 
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			int addressID = -1;
			
			if (!onlineLibraryCheck) {
				addressID = InsertNewAddress.insert(con, country, postalCode, street, houseNumber);
			}
			
			libraryID = InsertNewLibrary.insert(con, firstName, phoneNumber, email, information, addressID);
			
			InsertNewBusinessHours.insert(con, libraryID, beginTime, endTime, checkBoxList);
			
			InsertNewLocation.insert(con, libraryID, 1);
			
		} catch (PSQLException e) {
			e.printStackTrace();
			AddLibraryView.isReached = false;
			
		} catch (SQLException e) {
			AddLibraryView.isReached = false;	
		} 
	}
	
	public static void addLibrary(String firstNameIn, String phoneNumberIn, String emailIn, String informationIn, String countryIn, String streetIn, String houseNumberIn, int postalCodeIn, ArrayList<String> beginTimeIn, ArrayList<String>endTimeIn, ArrayList<CheckBox> checkBoxListIn, boolean onlineLibraryCheckIn) {
		
		firstName = firstNameIn;
		phoneNumber = phoneNumberIn;
		email = emailIn;
		information = informationIn;
		country = countryIn;
		street = streetIn;
		houseNumber = houseNumberIn;
		postalCode = postalCodeIn;
		beginTime = beginTimeIn;
		endTime = endTimeIn;
		checkBoxList = checkBoxListIn;
		onlineLibraryCheck = onlineLibraryCheckIn;
		
		Thread t = new Thread(new AddLibraryToDatabase());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			AddLibraryView.isInterrupted = true;
		}
	}
}
