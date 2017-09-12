package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertNewUser {
	
	private static PreparedStatement pstmtUser;
	
	public static int insert(Connection con, String firstName, String middleName, String lastName, int addressID, String phoneNumber, String email) throws SQLException {
		
		String queryUser = "INSERT INTO public.\"User\" VALUES(DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING \"User\".\"UserID\"";
		
		pstmtUser = con.prepareStatement(queryUser, new String[]{"User.UserID"});
		
		pstmtUser.setString(1, firstName);
		pstmtUser.setString(2, middleName);
		pstmtUser.setString(3, lastName);
		pstmtUser.setInt(4, addressID);
		pstmtUser.setString(5, phoneNumber);
		pstmtUser.setString(6, email);
		
		int userID = -1;
		
		int j = pstmtUser.executeUpdate();
		if (j > 0) {
			ResultSet rs = pstmtUser.getGeneratedKeys();
			while (rs.next()) {
				userID = Integer.parseInt(rs.getString(1));
			}
		}
		return userID;
	}
}
