package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import knjiznica.resources.ConnectionData;

public class PostalCodeCombo {
	
	private static List<String> comboData = new ArrayList<String>();
	
	public static ArrayList<String> getData() throws SQLException {
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());

			String query = "SELECT * FROM public.\"City\" ORDER BY \"PostalCode\" ASC";
			
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery(query);
			
			while (result.next()) {
				comboData.add(result.getInt(1) + " - " + result.getString(2));
			}
			return (ArrayList<String>) comboData;
				
		} catch(SQLException e){
			e.printStackTrace();
			
		} finally {
			stmt.close();
		}
		
		return null;
	}
}
