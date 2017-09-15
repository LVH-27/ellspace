package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertNewPublisher {
	
	private static PreparedStatement pstmtPublisher;
	
	public static void insert(Connection con, String name, int addressID) throws SQLException {
		
		String queryPublisher = "INSERT INTO public.\"Publisher\" VALUES(DEFAULT, ?, ?)";
		
		pstmtPublisher = con.prepareStatement(queryPublisher);
		
		pstmtPublisher.setString(1, name);
		if (addressID == -1) {
			pstmtPublisher.setNull(2, java.sql.Types.INTEGER);
			
		} else {
			pstmtPublisher.setInt(2, addressID);

		}
		
		pstmtPublisher.executeUpdate();
	}
}
