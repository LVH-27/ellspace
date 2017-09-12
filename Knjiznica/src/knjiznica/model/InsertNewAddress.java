package knjiznica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertNewAddress {
	private static Connection con;
	
	public static int insert(Connection conIn, String country, int postalCode, String street, String houseNumber) throws SQLException {
		
		int addressID = -1;
		
		PreparedStatement pstmtAddress = null;
		
		con = conIn;
		
		String queryAddress = "INSERT INTO public.\"Address\" VALUES(DEFAULT, ?, ?, ?, ?) RETURNING \"Address\".\"AddressID\"";
		
		pstmtAddress = con.prepareStatement(queryAddress, new String[]{"Address.AddressID"});
		
		pstmtAddress.setString(1, country);
		pstmtAddress.setInt(2, postalCode);
		pstmtAddress.setString(3, street);
		pstmtAddress.setString(4, houseNumber);
		
		int i = pstmtAddress.executeUpdate();
		
		if (i > 0) {
			ResultSet rs = pstmtAddress.getGeneratedKeys();
			while (rs.next()) {
				addressID = Integer.parseInt(rs.getString(1));
			}
		}
		return addressID;
	}
}
