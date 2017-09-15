package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertGenreLinks {
	
	private static PreparedStatement pstmtGenre;
	
	public static void insert(Connection con, String isbn, Genre genre) throws SQLException {
		
		String queryGenre = "INSERT INTO public.\"GenreLinks\" VALUES(?, ?)";
		
		pstmtGenre = con.prepareStatement(queryGenre);
		
		pstmtGenre.setString(1, isbn);
		
		pstmtGenre.setInt(2, genre.getID());
			
		pstmtGenre.executeUpdate();
	}
}
