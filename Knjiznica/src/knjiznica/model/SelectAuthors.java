package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectAuthors {
	private static ArrayList<Author> authors;
	
	public static ArrayList<Author> select() {
		
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
		return authors;
	}
}
