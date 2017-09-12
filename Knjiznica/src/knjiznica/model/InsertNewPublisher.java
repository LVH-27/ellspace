package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertNewPublisher {
	
	private static PreparedStatement pstmtPublisher;
	
	public static int insert(Connection con, String name, int addressID) throws SQLException {
		
		String queryPublisher = "INSERT INTO public.\"Publisher\" VALUES(DEFAULT, ?, ?) RETURNING \"Publisher\".\"PublisherID\"";
		
		pstmtPublisher = con.prepareStatement(queryPublisher, new String[]{"Publisher.PublisherID"});
		
		pstmtPublisher.setString(1, name);
		if(addressID == -1) {
			pstmtPublisher.setNull(2, java.sql.Types.INTEGER);
			
		} else {
			pstmtPublisher.setInt(2, addressID);

		}
		
		int publisherID = -1;
		
		int i = pstmtPublisher.executeUpdate();
		
		if (i > 0) {
			ResultSet rs = pstmtPublisher.getGeneratedKeys();
			while (rs.next()) {
				publisherID = Integer.parseInt(rs.getString(1));
			}
		}

		return publisherID;
	}
}
