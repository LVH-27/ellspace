package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ComboBox;
import knjiznica.resources.Strings;


public class PostalCodeComboBox {
    
    private static List<String> comboData = new ArrayList<String>();
	
	public static void setComboData(ComboBox<String> postalCodeCombo) throws SQLException {
		Connection con = DriverManager.getConnection(
				Strings.getLink(), Strings.getUsername(), Strings.getPassword());
		
	    String query = "SELECT * FROM public.\"City\"";
	    Statement stmt = con.createStatement();
	    
		try {
			ResultSet result = stmt.executeQuery(query);
			
			while (result.next()) {
				comboData.add(result.getInt(1) + " - " + result.getString(2));
			}
			
		} finally {
			stmt.close();
		}
		
		postalCodeCombo.getItems().addAll(comboData);

	}

}
