package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewLocation {
	
	private static PreparedStatement pstmtLocation; 
	
	public static void insert(Connection con, int ID, int type) throws SQLException {
		
		String queryLocation = null;
		
		if (type == 1) {
			queryLocation = "INSERT INTO public.\"Location\" VALUES(DEFAULT, ?, ?, null)";
		} else {
			queryLocation = "INSERT INTO public.\"Location\" VALUES(DEFAULT, ?, null, ?)";
		}
		
		pstmtLocation = con.prepareStatement(queryLocation);
		
		pstmtLocation.setInt(1, type);
		pstmtLocation.setInt(2, ID);
		
		pstmtLocation.executeUpdate();
	}
}
