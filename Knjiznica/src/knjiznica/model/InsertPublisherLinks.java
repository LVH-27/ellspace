package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPublisherLinks {
	
	private static PreparedStatement pstmtPublisher;
	
	public static void insert(Connection con, String isbn, Publisher publisher) throws SQLException {
		
		String queryPublisher = "INSERT INTO public.\"PublisherLinks\" VALUES(?, ?)";
		
		pstmtPublisher = con.prepareStatement(queryPublisher);
		
		pstmtPublisher.setString(1, isbn);
		
		pstmtPublisher.setInt(2, publisher.getID());
			
		pstmtPublisher.executeUpdate();
	}
}
