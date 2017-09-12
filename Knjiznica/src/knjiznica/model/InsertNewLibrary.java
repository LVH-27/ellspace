package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertNewLibrary {
	
	private static PreparedStatement pstmtLibrary;
	private static int libraryID = -1;
	
	public static int insert(Connection con, String firstName, String phoneNumber, String email, String information, int addressID) throws SQLException {
		
		String queryLibrary = "INSERT INTO public.\"Library\" VALUES(DEFAULT, ?, ?, ?, ?, ?) RETURNING \"Library\".\"LibraryID\"";
		
		pstmtLibrary = con.prepareStatement(queryLibrary, new String[] {"Library.LibraryID"});
		
		pstmtLibrary.setString(1, firstName);
		
		if (addressID == -1) {
			pstmtLibrary.setNull(2, java.sql.Types.INTEGER);
		} else {
			pstmtLibrary.setInt(2, addressID);
		}
		
		pstmtLibrary.setString(3, phoneNumber);
		pstmtLibrary.setString(4, email);
		pstmtLibrary.setString(5, information);
		
		int i = pstmtLibrary.executeUpdate();
	
		if (i > 0) {
			ResultSet rs = pstmtLibrary.getGeneratedKeys();
			while (rs.next()) { 
				libraryID = Integer.parseInt(rs.getString(1));
			}
		}
		
		return libraryID;
	}
}
