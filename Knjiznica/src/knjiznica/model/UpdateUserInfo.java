package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;
import knjiznica.resources.ConnectionData;
import knjiznica.view.AddUserView;

public class UpdateUserInfo implements Runnable {
private static String firstName;
	
	private static String middleName;
	
	private static String lastName;
	
	private static String email;
	
	private static String phoneNumber;
	
	private static String country;
	
	private static int postalCode;
	
	private static String street;
	
	private static String houseNumber;
	
	private static int addressID;
	
	private static int userID;
	
	@Override
	public void run() {
		
		PreparedStatement pstmtAddress = null;
		PreparedStatement pstmtUser = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			System.out.println("0");
			String queryAddress = "UPDATE public.\"Address\" "
					+ "SET \"Country\"=?, \"PostalCode\"=?, \"Street\"=?, \"HouseNumber\"=? " 
					+ "WHERE \"AddressID\"=?;";
			System.out.println("0");
			pstmtAddress = con.prepareStatement(queryAddress);
			System.out.println("0");
			pstmtAddress.setString(1, country);
			pstmtAddress.setInt(2, postalCode);
			pstmtAddress.setString(3, street);
			pstmtAddress.setString(4, houseNumber);
			pstmtAddress.setInt(5, addressID);
			pstmtAddress.executeUpdate();
			System.out.println("1");
			String queryUser = "UPDATE public.\"User\" " 
					+ "SET \"FirstName\"=?, \"MiddleName\"=?, \"LastName\"=?, \"PhoneNumber\"=?, \"Email\"=?" 
					+ "WHERE \"UserID\"=?";
			
			pstmtUser = con.prepareStatement(queryUser);
			
			pstmtUser.setString(1, firstName);
			pstmtUser.setString(2, middleName);
			pstmtUser.setString(3, lastName);
			pstmtUser.setString(4, phoneNumber);
			pstmtUser.setString(5, email);
			pstmtUser.setInt(6, userID);
			pstmtUser.executeUpdate();
			System.out.println("2");
		} catch (PSQLException e) {
			e.printStackTrace();
			AddUserView.isReached = false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			AddUserView.isReached = false;
			
		}
	}
	
	public static void updateUser(String firstNameIn, String middleNameIn, String lastNameIn, String emailIn, String phoneNumberIn, String countryIn, int postalCodeIn, String streetIn, String houseNumberIn, int addressIDIn, int userIDIn) {
		
		firstName = firstNameIn;
		middleName = middleNameIn;
		lastName = lastNameIn;
		email = emailIn;
		phoneNumber = phoneNumberIn;
		country = countryIn;
		postalCode = postalCodeIn;
		street = streetIn;
		houseNumber = houseNumberIn;
		addressID = addressIDIn;
		userID = userIDIn;
		
		Thread t = new Thread(new UpdateUserInfo());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			AddUserView.isInterrupted = true;
		}
	}
}
