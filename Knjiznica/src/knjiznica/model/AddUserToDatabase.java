package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import knjiznica.resources.ConnectionData;

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
		
		PreparedStatement pstmtAddress = null;
		PreparedStatement pstmtUser = null;
		
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
			

			
			
			String queryUser = "INSERT INTO public.\"User\" VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
			
			pstmtUser = con.prepareStatement(queryUser);
			
			pstmtUser.setString(1, firstName);
			pstmtUser.setString(2, middleName);
			pstmtUser.setString(3, lastName);
			pstmtUser.setInt(4, Integer.parseInt(addressID));
			pstmtUser.setString(5, phoneNumber);
			pstmtUser.setString(6, email);

			pstmtUser.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		(new Thread(new AddUserToDatabase())).start();
	}
}
