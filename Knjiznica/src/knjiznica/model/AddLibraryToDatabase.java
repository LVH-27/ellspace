package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import org.postgresql.util.PSQLException;

import javafx.scene.control.CheckBox;
import knjiznica.resources.ConnectionData;
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
	private static Time timeBegin;
	private static Time timeEnd;
	private static boolean onlineLibraryCheck;
	
	@Override
	public void run() {
		
		PreparedStatement pstmtAddress = null;
		PreparedStatement pstmtLibrary = null;
		PreparedStatement pstmtBusiness = null;
		PreparedStatement pstmtLocation = null;
		
		String libraryID = null; 
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String addressID = null;
			
			if (!onlineLibraryCheck) {
				String queryAddress = "INSERT INTO public.\"Address\" VALUES(DEFAULT, ?, ?, ?, ?) RETURNING \"Address\".\"AddressID\"";
				
				pstmtAddress = con.prepareStatement(queryAddress, new String[]{"Address.AddressID"});
				
				pstmtAddress.setString(1, country);
				pstmtAddress.setInt(2, postalCode);
				pstmtAddress.setString(3, street);
				pstmtAddress.setString(4, houseNumber);
				
				int i = pstmtAddress.executeUpdate();
				
				if (i > 0) {
					ResultSet rs = pstmtAddress.getGeneratedKeys();
					while (rs.next()) {
						addressID = rs.getString(1);
					}
				}
			}
			
			String queryLibrary = "INSERT INTO public.\"Library\" VALUES(DEFAULT, ?, ?, ?, ?, ?) RETURNING \"Library\".\"LibraryID\"";
			
			pstmtLibrary = con.prepareStatement(queryLibrary, new String[] {"Library.LibraryID"});
			
			pstmtLibrary.setString(1, firstName);
			
			if (addressID == null) {
				pstmtLibrary.setNull(2, java.sql.Types.INTEGER);
			} else {
				pstmtLibrary.setInt(2, Integer.parseInt(addressID));
			}
			
			pstmtLibrary.setString(3, phoneNumber);
			pstmtLibrary.setString(4, email);
			pstmtLibrary.setString(5, information);
			
			int i = pstmtLibrary.executeUpdate();
		
			if (i > 0) {
				ResultSet rs = pstmtLibrary.getGeneratedKeys();
				while (rs.next()) { 
					libraryID = rs.getString(1);
				}
			}
			
			String queryBusiness = "INSERT INTO public.\"BusinessHours\" VALUES(?, ?, ?, ?, ?)";
			
			pstmtBusiness = con.prepareStatement(queryBusiness);
			
			for (int j = 0; j < 7; ++j) {
				timeBegin = java.sql.Time.valueOf(beginTime.get(j) + ":00");
				timeEnd = java.sql.Time.valueOf(endTime.get(j) + ":00");
				
				pstmtBusiness.setInt(1, Integer.parseInt(libraryID));
				pstmtBusiness.setInt(2, j + 1);
				
				if (checkBoxList.get(j).isSelected()) {
					pstmtBusiness.setBoolean(3, false);
				} else {
					pstmtBusiness.setBoolean(3, true);
				}
				
				pstmtBusiness.setTime(4, timeBegin);
				pstmtBusiness.setTime(5, timeEnd);
				
				pstmtBusiness.executeUpdate(); 
			}
			
			String queryLocation = "INSERT INTO public.\"Location\" VALUES(DEFAULT, ?, ?, null)";
			
			pstmtLocation = con.prepareStatement(queryLocation);
			
			pstmtLocation.setInt(1, 1);
			pstmtLocation.setInt(2, Integer.parseInt(libraryID));
			
			pstmtLocation.executeUpdate();
			
		} catch (PSQLException e) {
			e.printStackTrace();
			AddLibraryView.isReached = false;
			
		} catch (SQLException e) {
			AddLibraryView.isReached = false;	
		} finally {
			try {
				if(pstmtAddress != null) {
					pstmtAddress.close();
				}
				pstmtLibrary.close();
				if(pstmtBusiness != null) {
					pstmtBusiness.close();
				}
				pstmtLocation.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
