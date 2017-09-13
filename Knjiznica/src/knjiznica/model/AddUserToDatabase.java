package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

import knjiznica.view.AddUserView;


public class AddUserToDatabase implements Runnable{
	
	private static String firstName;
	private static String middleName;
	private static String lastName;
	private static String email;
	private static String phoneNumber;
	private static String country;
	private static int postalCode;
	private static String street;
	private static String houseNumber;
	
	@Override
	public void run() {
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			int addressID = -1;
			
			addressID = InsertNewAddress.insert(con, country, postalCode, street, houseNumber);
			
			int userID = -1;
			
			userID = InsertNewUser.insert(con, firstName, middleName, lastName, addressID, phoneNumber, email);
			
			InsertNewLocation.insert(con, userID, 2);
			
		} catch (PSQLException e) {
			e.printStackTrace();
			AddUserView.isReached = false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			AddUserView.isReached = false;
		} 
	}
	
	public static void addUser(String firstNameIn, String middleNameIn, String lastNameIn, String emailIn, String phoneNumberIn, String countryIn, int postalCodeIn, String streetIn, String houseNumberIn) {
		
		firstName = firstNameIn;
		middleName = middleNameIn;
		lastName = lastNameIn;
		email = emailIn;
		phoneNumber = phoneNumberIn;
		country = countryIn;
		postalCode = postalCodeIn;
		street = streetIn;
		houseNumber = houseNumberIn;
		
		Thread t = new Thread(new AddUserToDatabase());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			AddUserView.isInterrupted = true;
		}
	}
}
