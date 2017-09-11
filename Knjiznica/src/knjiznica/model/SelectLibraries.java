package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import knjiznica.resources.ConnectionData;

public class SelectLibraries implements Runnable {
	
	private static ArrayList<Library> libraries;

	@Override
	public void run() {
		libraries = new ArrayList<Library>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String query = "SELECT * FROM public.\"Library\" JOIN public.\"Address\" ON \"Library\".\"AddressID\" = \"Address\".\"AddressID\" JOIN public.\"City\" ON \"Address\".\"PostalCode\" = \"City\".\"PostalCode\"";
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);

			String information = "-";
			String email = "-";
			
			while (rs.next()) {
				
				email = "-";
				information = "-";
				
				if (rs.getString("Email") != null) {
					email = rs.getString("Email");
				}
				if (rs.getString("Information") != null) {
					information = rs.getString("Information");
				}
				
				libraries.add(new Library(rs.getInt("LibraryID"), rs.getString("LibraryName"), rs.getString("Country"), Integer.toString(rs.getInt("PostalCode")), rs.getString("Street"), rs.getString("HouseNumber"), rs.getString("PhoneNumber"), email, information, rs.getString("CityName"), rs.getInt("AddressID")));
			}
			
			query = "SELECT * FROM public.\"Library\" WHERE \"Library\".\"AddressID\" IS NULL";
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				email = "-";
				information = "-";
				
				if (rs.getString("Email") != null) {
					email = rs.getString("Email");
				}
				if (rs.getString("Information") != null) {
					information = rs.getString("Information");
				}
				
				libraries.add(new Library(rs.getInt("LibraryID"), rs.getString("LibraryName"), "-", "-", "-", "-", rs.getString("PhoneNumber"), email, information, "-", -1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static ArrayList<Library> select() {
		Thread t = new Thread(new SelectLibraries());
		t.start();
		try {
			t.join();
			return libraries;
		} catch (InterruptedException e) {
			System.out.println("Something went wrong!");
			return null;
		}
	}
}
