package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import knjiznica.resources.ConnectionData;

public class AddLibraryToDatabase implements Runnable{
	
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
		
		PreparedStatement pstmtAddress = null;
		PreparedStatement pstmtLibrary = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String queryAddress = "INSERT INTO public.\"Address\" VALUES(DEFAULT, ?, ?, ?, ?) RETURNING \"Address\".\"AddressID\"";
			
			pstmtAddress = con.prepareStatement(queryAddress, new String[]{"Address.AddressID"});
			
			pstmtAddress.setString(1, country);
			pstmtAddress.setInt(2, postalCode);
			pstmtAddress.setString(3, street);
			pstmtAddress.setString(4, houseNumber);
			
			String addressID = null;
			
			int i = pstmtAddress.executeUpdate();
			if (i > 0) {
				ResultSet rs = pstmtAddress.getGeneratedKeys();
				while (rs.next()) {
					addressID = rs.getString(1);
				}
			}
						
			String queryLibrary = "INSERT INTO public.\"Library\" VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
			
			pstmtLibrary = con.prepareStatement(queryLibrary);
			
			pstmtLibrary.setString(1, firstName);
			pstmtLibrary.setString(2, middleName);
			pstmtLibrary.setString(3, lastName);
			pstmtLibrary.setInt(4, Integer.parseInt(addressID));
			pstmtLibrary.setString(5, phoneNumber);
			pstmtLibrary.setString(6, email);
			
			pstmtLibrary.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//FOR LOOP WITH 7 DATABASE INSERTS "BusinessHours"

		
	}
	
	public static void addLibrary(String firstNameIn, String emailIn, String phoneNumberIn, String countryIn, int postalCodeIn, String streetIn, String houseNumberIn) {
		
		firstName = firstNameIn;
		email = emailIn;
		phoneNumber = phoneNumberIn;
		country = countryIn;
		postalCode = postalCodeIn;
		street = streetIn;
		houseNumber = houseNumberIn;
		
		(new Thread(new AddLibraryToDatabase())).start();
	}
}
