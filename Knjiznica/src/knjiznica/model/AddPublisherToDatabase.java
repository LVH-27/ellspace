package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

import knjiznica.view.AddPublisherView;
import knjiznica.view.AddUserView;

public class AddPublisherToDatabase implements Runnable{
	
	private static String name;
	private static String country;
	private static int postalCode;
	private static String street;
	private static String houseNumber;
	private static boolean isKnown;
	private static int publisherID;
	
	@Override
	public void run() {
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			int addressID = -1;
			
			if(isKnown) {
				addressID = InsertNewAddress.insert(con, country, postalCode, street, houseNumber);
			}
			
			publisherID = InsertNewPublisher.insert(con, name, addressID);
			
		} catch (PSQLException e) {
			e.printStackTrace();
			AddPublisherView.isReached = false;
			
		} catch (SQLException e) {
			AddPublisherView.isReached = false;
		} 
	}
	
	public static void addPublisher(String firstNameIn, String countryIn, int postalCodeIn, String streetIn, String houseNumberIn, boolean isKnownIn) {
		
		name = firstNameIn;
		country = countryIn;
		postalCode = postalCodeIn;
		street = streetIn;
		houseNumber = houseNumberIn;
		isKnown = isKnownIn;
		
		Thread t = new Thread(new AddPublisherToDatabase());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			AddUserView.isInterrupted = true;
		}
	}
}
