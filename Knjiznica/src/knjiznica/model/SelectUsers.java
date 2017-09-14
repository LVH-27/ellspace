package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectUsers {
	
	private static ArrayList<User> users;
	
	public static ArrayList<User> select() {
		users = new ArrayList<User>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String query = "SELECT * FROM public.\"User\" JOIN public.\"Address\" ON \"User\".\"AddressID\" = \"Address\".\"AddressID\" JOIN public.\"City\" ON \"Address\".\"PostalCode\" = \"City\".\"PostalCode\"";
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);

			String middleName = "-";
			String street = "-";
			String houseNumber = "-";
			String phoneNumber = "-";
			
			while (rs.next()) {
				
				middleName = "-";
				street = "-";
				houseNumber = "-"; 
				phoneNumber = "-";
				
				if (rs.getString("MiddleName") != null) {
					middleName = rs.getString("MiddleName");
				}
				if (rs.getString("Street") != null) {
					street = rs.getString("Street");
				}
				if (rs.getString("HouseNumber") != null) {
					houseNumber = rs.getString("HouseNumber");
				}
				if (rs.getString("PhoneNumber") != null) {
					phoneNumber = rs.getString("PhoneNumber");
				}
				
				users.add(new User(rs.getInt("UserID"), rs.getString("FirstName"), middleName, rs.getString("LastName"), rs.getString("Country"), rs.getInt("PostalCode"), street, houseNumber, phoneNumber, rs.getString("Email"), rs.getString("CityName"), rs.getInt("AddressID")));
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
		return users;
	}
}
