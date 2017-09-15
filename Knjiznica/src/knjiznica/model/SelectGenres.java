package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectGenres {
	
	private static ArrayList<Genre> genres;
	
	public static ArrayList<Genre> select() {
		
		genres = new ArrayList<Genre>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String query = "SELECT * FROM public.\"GenreList\"";
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				genres.add(new Genre(rs.getInt("GenreID"), rs.getString("GenreNameEN"), rs.getString("GenreNameHR"), rs.getString("GenreNameDE")));
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
		return genres;
	}
}
