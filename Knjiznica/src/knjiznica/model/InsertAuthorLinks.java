package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertAuthorLinks {
	
	private static PreparedStatement pstmtAuthor;
	
	public static void insert(Connection con, String isbn, Author author) throws SQLException {
		
		String queryAuthor = "INSERT INTO public.\"AuthorLinks\" VALUES(?, ?)";
		
		pstmtAuthor = con.prepareStatement(queryAuthor);
		
		pstmtAuthor.setString(1, isbn);
		
		pstmtAuthor.setInt(2, author.getID());
			
		pstmtAuthor.executeUpdate();
	}
}
