package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertIsbn {
	
	private static PreparedStatement pstmtIsbn;
	
	public static void insert(Connection con, String isbn, String title, String summary) throws SQLException {
		
		String queryIsbn = "INSERT INTO public.\"IsbnLinks\" VALUES(?, ?, ?)";
		
		pstmtIsbn = con.prepareStatement(queryIsbn);
		
		pstmtIsbn.setString(1, isbn);
		pstmtIsbn.setString(2, title);
		if (summary.equals("-")) {
			pstmtIsbn.setString(3, null);
		} else {
			pstmtIsbn.setString(3, summary);
		}
		
		pstmtIsbn.executeUpdate();
	
	}
}
