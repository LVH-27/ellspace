package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import knjiznica.view.AddAuthorTableView;

public class InsertNewAuthor {
	
	private static PreparedStatement pstmtAuthor = null;
	
	public static void insert(Connection con, String firstName, String middleName, String lastName, boolean isAlive, String yearOfBirth, String yearOfDeath) throws SQLException {
		String queryAuthor = "INSERT INTO public.\"Author\" VALUES(DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING \"Author\".\"AuthorID\"";
		
		pstmtAuthor = con.prepareStatement(queryAuthor, new String[]{"Author.AuthorID"});
		
		if (middleName.isEmpty()) {
			middleName = null;
		}
		else {
			middleName = FormattingName.format(middleName);
		}
		
		firstName = FormattingName.format(firstName);
		lastName = FormattingName.format(lastName);
		
		pstmtAuthor.setString(1, firstName);
		pstmtAuthor.setString(2, middleName);
		pstmtAuthor.setString(3, lastName);
		
		if (!AddAuthorTableView.checkIndeterminate) {
			pstmtAuthor.setBoolean(4, isAlive);
		} else {
			pstmtAuthor.setNull(4, java.sql.Types.BOOLEAN);
		}
		pstmtAuthor.setString(5, yearOfBirth);
		pstmtAuthor.setString(6, yearOfDeath);
		
		pstmtAuthor.executeUpdate();
	}
}
