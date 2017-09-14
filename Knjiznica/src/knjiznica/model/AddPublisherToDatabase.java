package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;
import knjiznica.view.AddPublisherTableView;

public class AddPublisherToDatabase {
	
	private static String name;
	private static String country;
	private static int postalCode;
	private static String street;
	private static String houseNumber;
	private static boolean isKnown;
	private static int publisherID;
	
	public static void addPublisher(String firstNameIn, String countryIn, int postalCodeIn, String streetIn, String houseNumberIn, boolean isKnownIn) {
		
		name = firstNameIn;
		country = countryIn;
		postalCode = postalCodeIn;
		street = streetIn;
		houseNumber = houseNumberIn;
		isKnown = isKnownIn;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			int addressID = -1;
			
			if (isKnown) {
				addressID = InsertNewAddress.insert(con, country, postalCode, street, houseNumber);
			}
			
			publisherID = InsertNewPublisher.insert(con, name, addressID);
			
			
		} catch (PSQLException e) {
			e.printStackTrace();
			AddPublisherTableView.isReached = false;
			
		} catch (SQLException e) {
			AddPublisherTableView.isReached = false;
		} 
	}
}
