package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectAuthors implements Runnable {
	private static ArrayList<Author> authors;

	@Override
	public void run() {
		
		authors = new ArrayList<Author>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String query = "SELECT * FROM public.\"Author\"";
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);

			String middleName = "-";
			String yearOfDeath = "-";
			String yearOfBirth = "-";
			
			while (rs.next()) {
				
				middleName = "-";
				yearOfBirth = "-";
				yearOfDeath = "-"; 
				
				if (rs.getString("MiddleName") != null) {
					middleName = rs.getString("MiddleName");
				}
				if (rs.getString("yearOfBirth") != null) {
					yearOfBirth = rs.getString("yearOfBirth");
				}
				if (rs.getString("yearOfDeath") != null) {
					yearOfDeath = rs.getString("yearOfDeath");
				}
				
				authors.add(new Author(rs.getInt("AuthorID"), rs.getString("FirstName"), middleName, rs.getString("LastName"), rs.getBoolean("Alive"), yearOfBirth, yearOfDeath));
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
	}
	
	public static ArrayList<Author> select() {
		Thread t = new Thread(new SelectAuthors());
		t.start();
		try {
			t.join();
			return authors;
		} catch (InterruptedException e) {
			System.out.println("Something went wrong!");
			return null;
		}
	}
}
