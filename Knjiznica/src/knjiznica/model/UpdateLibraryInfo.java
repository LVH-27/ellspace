package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.postgresql.util.PSQLException;

import knjiznica.view.UpdateLibraryView;


public class UpdateLibraryInfo implements Runnable {
	
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
	private static ArrayList<String> checkList;
	private static boolean onlineLibraryCheck;
	private static int addressID;
	private static int libraryID;
	
	
	@Override
	public void run() {
		
		PreparedStatement pstmtAddress = null;
		PreparedStatement pstmtUser = null;
		PreparedStatement pstmtBusiness = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			if(!onlineLibraryCheck) {
				if(addressID == -1) {
					
					addressID = InsertNewAddress.insert(con, country, postalCode, street, houseNumber);
					
				} else {
					String queryAddress = "UPDATE public.\"Address\" "
							+ "SET \"Country\"=?, \"PostalCode\"=?, \"Street\"=?, \"HouseNumber\"=? " 
							+ "WHERE \"AddressID\"=?;";
					
					pstmtAddress = con.prepareStatement(queryAddress);
				
					pstmtAddress.setString(1, country);
					pstmtAddress.setInt(2, postalCode);
					pstmtAddress.setString(3, street);
					pstmtAddress.setString(4, houseNumber);
					pstmtAddress.setInt(5, addressID);
					
					pstmtAddress.executeUpdate();
				}
				
				String queryLibrary = "UPDATE public.\"Library\" " 
						+ "SET \"LibraryName\"=?, \"AddressID\"=?, \"PhoneNumber\"=?, \"Email\"=?, \"Information\"=?" 
						+ "WHERE \"LibraryID\"=?";
				
				pstmtUser = con.prepareStatement(queryLibrary);
				pstmtUser.setString(1, firstName);
				pstmtUser.setInt(2, addressID);
				pstmtUser.setString(3, phoneNumber);
				pstmtUser.setString(4, email); 
				pstmtUser.setString(5, information);
				pstmtUser.setInt(6, libraryID);
				
				pstmtUser.executeUpdate();
				
			} else {
				String queryLibrary = "UPDATE public.\"Library\" " 
						+ "SET \"LibraryName\"=?, \"AddressID\"=?, \"PhoneNumber\"=?, \"Email\"=?, \"Information\"=?" 
						+ "WHERE \"LibraryID\"=?";
				
				pstmtUser = con.prepareStatement(queryLibrary);
				pstmtUser.setString(1, firstName);
				pstmtUser.setNull(2, java.sql.Types.INTEGER);
				pstmtUser.setString(3, phoneNumber);
				pstmtUser.setString(4, email); 
				pstmtUser.setString(5, information);
				pstmtUser.setInt(6, libraryID);
				
				pstmtUser.executeUpdate();
				
				String queryAddress = "DELETE FROM public.\"Address\""
						+ "WHERE \"Address\".\"AddressID\"=?";
				
				pstmtAddress = con.prepareStatement(queryAddress);
				pstmtAddress.setInt(1, addressID);
				
				pstmtAddress.executeUpdate();		
				
			}
			
			String queryBusinessHours;
			
			for(int i = 0; i < beginTime.size(); ++i) {
				
				queryBusinessHours = "UPDATE public.\"BusinessHours\" " 
						+ "SET \"Closed\"=?, \"OpenTime\"=?, \"CloseTime\"=?" 
						+ "WHERE \"LibraryID\"=? AND \"DayOfWeek\"=?";
				
				pstmtBusiness = con.prepareStatement(queryBusinessHours);
				if(checkList.get(i) == "Opened") {
					pstmtBusiness.setBoolean(1, false);
					
				} else {
					pstmtBusiness.setBoolean(1, true);

				}
				pstmtBusiness.setTime(2, java.sql.Time.valueOf(beginTime.get(i) + ":00"));
				pstmtBusiness.setTime(3, java.sql.Time.valueOf(endTime.get(i) + ":00"));
				pstmtBusiness.setInt(4, libraryID);
				pstmtBusiness.setInt(5, i + 1);
				
				pstmtBusiness.executeUpdate();
			}
			
		} catch (PSQLException e) {
			e.printStackTrace();
			UpdateLibraryView.isReached = false;
			
		} catch (SQLException e) {
			System.out.println(addressID + 1);
			UpdateLibraryView.isReached = false;
		} 
	}
	
	public static void updateLibrary(String firstNameIn, String phoneNumberIn, String emailIn, String informationIn, String countryIn, String streetIn, String houseNumberIn, int postalCodeIn, ArrayList<String> beginTimeIn, ArrayList<String>endTimeIn, ArrayList<String> checkListIn, boolean onlineLibraryCheckIn, int libraryIDIn, int addressIDIn) {
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
		checkList = checkListIn;
		onlineLibraryCheck = onlineLibraryCheckIn;
		libraryID = libraryIDIn;
		addressID = addressIDIn;
		
		Thread t = new Thread(new UpdateLibraryInfo());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			UpdateLibraryView.isInterrupted = true;
		}
	}
}
