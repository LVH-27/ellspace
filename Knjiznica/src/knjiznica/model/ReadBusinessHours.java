package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadBusinessHours {
	
	private static ArrayList<String> check;
	private static ArrayList<String> beginTime;
	private static ArrayList<String> endTime;
	
	public static void read(Connection con, int libraryID) {
		
		check = new ArrayList<String>();
		beginTime = new ArrayList<String>();
		endTime = new ArrayList<String>();
		
		beginTime = new ArrayList<String>();
		
		String query = "SELECT * FROM public.\"BusinessHours\" WHERE \"BusinessHours\".\"LibraryID\" = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, libraryID);
			
			ResultSet businessHoursSet = pstmt.executeQuery();
		
			while (businessHoursSet.next()) {
	
				if (!businessHoursSet.getBoolean("Closed")) {
					check.add("Opened");
				} else {
					check.add("Closed");
				}
				String[] time = businessHoursSet.getTime("OpenTime").toString().split(":");
				beginTime.add(time[0] + ":" + time[1]);
				time = businessHoursSet.getTime("CloseTime").toString().split(":");
				endTime.add(time[0] + ":" + time[1]);
				
			}
			
			GlobalCollection.getBusinessHoursList().add(new BusinessHours(libraryID, check, beginTime, endTime));
			
		} catch (SQLException e) {
			System.out.println("Something went wrong!");
		} finally {
			try {
				pstmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		
	}
}
