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
	private static List<Integer> postalCodes = new ArrayList<Integer>();
	private static List<String> names = new ArrayList<String>();
	private static ArrayList<ArrayList<?>> returnData = new ArrayList<ArrayList<?>>();
	
	public static List<ArrayList<?>> getData() throws SQLException {
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());

			String query = "SELECT * FROM public.\"City\" ORDER BY \"PostalCode\" ASC";
			
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery(query);
			
			while (result.next()) {
				int tempPostalCode = result.getInt(1);
				String tempName = result.getString(2);
				postalCodes.add(tempPostalCode);
				names.add(tempName);
				comboData.add(tempPostalCode + " - " + tempName);
			}
			
			returnData.add((ArrayList<String>) comboData);
			returnData.add((ArrayList<Integer>) postalCodes);
			returnData.add((ArrayList<String>) names);
			
			return returnData;
				
		} catch(SQLException e){
			e.printStackTrace();
			
		} finally {
			stmt.close();
		}
		
		return null;
	}
}
