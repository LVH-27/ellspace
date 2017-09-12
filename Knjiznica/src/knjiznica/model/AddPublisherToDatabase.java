package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import knjiznica.resources.ConnectionData;
import knjiznica.view.AddUserView;

public class AddPublisherToDatabase implements Runnable{
	
	private static String name;
	private static String country;
	private static int postalCode;
	private static String street;
	private static String houseNumber;
	
	@Override
	public void run() {
		
		PreparedStatement pstmtAddress = null;
		PreparedStatement pstmtPublisher = null;
		PreparedStatement pstmtLocation = null;
		
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
			
			String queryPublisher = "INSERT INTO public.\"Publisher\" VALUES(DEFAULT, ?, ?) RETURNING \"Publisher\".\"PublisherID\"";
			
			pstmtPublisher = con.prepareStatement(queryPublisher);
			
			pstmtPublisher.setString(1, name);
			pstmtPublisher.setInt(2, Integer.parseInt(addressID));
			
			String publisherID = null;
			
			int j = pstmtPublisher.executeUpdate();
			if (j > 0) {
				ResultSet rs = pstmtPublisher.getGeneratedKeys();
				while (rs.next()) {
					publisherID = rs.getString(1);
				}
			}
			
			//TODO See if we should return the key here for PublisherLinks table, or get it again from AddBook
			String queryLocation = "INSERT INTO public.\"Location\" VALUES(DEFAULT, ?, null, ?)";
			
			pstmtLocation = con.prepareStatement(queryLocation);
			
			pstmtLocation.setInt(1, 2);
			pstmtLocation.setInt(2, Integer.parseInt(publisherID));
			
//			pstmtLocation.executeUpdate();
			
		} catch (PSQLException e) {
			AddUserView.isReached = false;
			
		} catch (SQLException e) {
			AddUserView.isReached = false;
		} finally {
			try {
				pstmtAddress.close();
				pstmtPublisher.close();
				pstmtLocation.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addPublisher(String firstNameIn, String countryIn, int postalCodeIn, String streetIn, String houseNumberIn) {
		
		name = firstNameIn;
		country = countryIn;
		postalCode = postalCodeIn;
		street = streetIn;
		houseNumber = houseNumberIn;
		
		Thread t = new Thread(new AddPublisherToDatabase());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			AddUserView.isInterrupted = true;
		}
	}
}
