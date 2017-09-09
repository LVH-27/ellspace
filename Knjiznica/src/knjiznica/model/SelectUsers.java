package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import knjiznica.resources.ConnectionData;

public class SelectUsers implements Runnable {
	
	private static ArrayList<User> users;

	@Override
	public void run() {
		users = new ArrayList<User>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			String query = "SELECT * FROM public.\"User\" JOIN public.\"Address\" ON \"User\".\"AddressID\" = \"Address\".\"AddressID\"";
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				users.add(new User(rs.getInt("UserID"), rs.getString("FirstName"), rs.getString("MiddleName"), rs.getString("LastName"), rs.getString("Country"), rs.getInt("PostalCode"), rs.getString("Street"), rs.getString("HouseNumber"), rs.getString("PhoneNumber")));
			}
			
			query = "SELECT * FROM public.\"User\" WHERE \"User\".\"AddressID\" IS NULL";
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				users.add(new User(rs.getInt("UserID"), rs.getString("FirstName"), rs.getString("MiddleName"), rs.getString("LastName"), "", 0, "", "", rs.getString("PhoneNumber")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<User> select() {
		Thread t = new Thread(new SelectUsers());
		t.start();
		try {
			t.join();
			return users;
		} catch (InterruptedException e) {
			System.out.println("Something went wrong!");
			return null;
		}
		
	}
	
}
