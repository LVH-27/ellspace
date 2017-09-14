package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectPublishers {
	
	private static ArrayList<Publisher> publishers;
	
	public static ArrayList<Publisher> select() {
		
		publishers = new ArrayList<Publisher>();
		
		Statement stmt = null;
		Statement stmt2 = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String query = "SELECT * FROM public.\"Publisher\" JOIN public.\"Address\" ON \"Publisher\".\"AddressID\" = \"Address\".\"AddressID\" JOIN public.\"City\" ON \"Address\".\"PostalCode\" = \"City\".\"PostalCode\"";
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);

			String street = "-";
			String houseNumber = "-";
			
			while (rs.next()) {
				
				street = "-";
				houseNumber = "-"; 
				
				if (rs.getString("Street") != null) {
					street = rs.getString("Street");
				}
				if (rs.getString("HouseNumber") != null) {
					houseNumber = rs.getString("HouseNumber");
				}
				
				publishers.add(new Publisher(rs.getInt("PublisherID"), rs.getString("PublisherName"), rs.getString("Country"), Integer.toString(rs.getInt("PostalCode")), street, houseNumber, rs.getString("CityName"), rs.getInt("AddressID")));
			}
			
			query = "SELECT * FROM public.\"Publisher\" WHERE \"Publisher\".\"AddressID\" IS NULL";
			
			stmt2 = con.createStatement();
			
			rs = stmt2.executeQuery(query);
			
			while (rs.next()) {		
				publishers.add(new Publisher(rs.getInt("PublisherID"), rs.getString("PublisherName"), "-", "-", "-", "-", "-", -1));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return publishers;
	}
}
