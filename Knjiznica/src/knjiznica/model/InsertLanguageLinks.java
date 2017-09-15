package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertLanguageLinks {
	
	private static PreparedStatement pstmtLanguage;
	
	public static void insert(Connection con, String isbn, Language language) throws SQLException {
		
		String queryLanguage = "INSERT INTO public.\"LanguageLinks\" VALUES(?, ?)";
		
		pstmtLanguage = con.prepareStatement(queryLanguage);
		
		pstmtLanguage.setString(1, isbn);
		
		pstmtLanguage.setInt(2, language.getID());
			
		pstmtLanguage.executeUpdate();
	}
}
