package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewBook {
	
	private static PreparedStatement pstmtBook;
	
	public static void insert(Connection con, String isbn, int editionID, int ownerID, int currentLocationID, String information) throws SQLException {
		
		String queryBook = "INSERT INTO public.\"Book\" VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?)";
		
		pstmtBook = con.prepareStatement(queryBook);
		
		pstmtBook.setString(1, isbn);
		
		pstmtBook.setInt(2, editionID);
		pstmtBook.setInt(3, ownerID);
		pstmtBook.setInt(4, currentLocationID);
		pstmtBook.setBoolean(5, true);
		pstmtBook.setDate(6, null);
		if (information.equals("-")) {
			pstmtBook.setString(7, null);
		} else {
			pstmtBook.setString(7, information);
		}
		
		pstmtBook.executeUpdate();
	}
}
