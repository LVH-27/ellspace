package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertEditionNumber {
	
	private static PreparedStatement pstmtEdition;
	private static int editionID = -1;
	
	public static int insert(Connection con, String number, String year, String numberOfPages) throws SQLException {
		
		String queryEdition = "INSERT INTO public.\"Edition\" VALUES(DEFAULT, ?, ?, ?) RETURNING \"Edition\".\"EditionID\"";
		
		pstmtEdition = con.prepareStatement(queryEdition, new String[] {"Edition.EditionID"});
		
		if (number.equals("-")) {
			pstmtEdition.setNull(1, java.sql.Types.INTEGER);
		} else {
			pstmtEdition.setInt(1, Integer.parseInt(number));
		}
		
		if (year.equals("-")) {
			pstmtEdition.setNull(2, java.sql.Types.INTEGER);
		} else {
			pstmtEdition.setInt(2, Integer.parseInt(year));
		}
		
		if (numberOfPages.equals("-")) {
			pstmtEdition.setNull(3, java.sql.Types.INTEGER);
		} else {
			pstmtEdition.setInt(3, Integer.parseInt(numberOfPages));
		}
		
		int i = pstmtEdition.executeUpdate();
	
		if (i > 0) {
			ResultSet rs = pstmtEdition.getGeneratedKeys();
			while (rs.next()) { 
				editionID = Integer.parseInt(rs.getString(1));
			}
		}
		
		return editionID;
	}
}
