package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReadBusinessHours {
	
	private static Connection con;
	private static int libraryID;
	private static ArrayList<String> times;
	
	public static void read(Connection conIn, int libraryIDIn) {
		con = conIn;
		libraryID = libraryIDIn;
		
		times = new ArrayList<String>();
		
		String query = "SELECT * FROM public.\"BusinessHours\" WHERE \"BusinessHours\".\"LibraryID\" = ?";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, libraryID);
			
			times.add(Integer.toString(libraryID));
			
			ResultSet businessHoursSet = pstmt.executeQuery();
		
			while (businessHoursSet.next()) {
	
				if(!businessHoursSet.getBoolean("Closed")) {
					times.add("Opened");
				} else {
					times.add("Closed");
				}
				
				String[] time = businessHoursSet.getTime("OpenTime").toString().split(":");
				times.add(time[0] + ":" + time[1]);
				time = businessHoursSet.getTime("CloseTime").toString().split(":");
				times.add(time[0] + ":" + time[1]);
				
			}
			
			GlobalCollection.getBusinessHoursList().add(new BusinessHours(Integer.parseInt(times.get(0)), times.get(1), times.get(2), times.get(3), times.get(4), times.get(5), times.get(6), times.get(7), times.get(8), times.get(9), times.get(10), times.get(11), times.get(12), times.get(13), times.get(14), times.get(15), times.get(16), times.get(17), times.get(18), times.get(19), times.get(20), times.get(21)));
			
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
