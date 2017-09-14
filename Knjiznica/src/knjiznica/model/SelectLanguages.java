package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectLanguages {
	
	private static ArrayList<Language> languages;
	
	public static ArrayList<Language> select() {
		
		languages = new ArrayList<Language>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String query = "SELECT * FROM public.\"LanguageList\"";
			
			stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				languages.add(new Language(rs.getInt("LanguageID"), rs.getString("LanguageName")));
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
		return languages;
	}
}
