package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIsbn {
	
	public static void check(String isbn) {
		Connection con;

		try {
			con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			PreparedStatement pstmtLoad;
			String queryLoad = "SELECT * FROM \"public\".\"IsbnLinks\" WHERE \"IsbnLinks\".\"ISBN\"=?";
			pstmtLoad = con.prepareStatement(queryLoad);
			pstmtLoad.setString(1, isbn);
			ResultSet rs = pstmtLoad.executeQuery();
			String s = "";
			
			while (rs.next()) {
				s = rs.getString("ISBN");
			}
			if(!s.isEmpty()) {
				GlobalCollection.setFound(true);
			} else {
				GlobalCollection.setFound(false);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
