package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ComboBox;
import knjiznica.resources.ConnectionData;


public class PostalCodeComboBox implements Runnable {
	
    private static List<String> comboData = new ArrayList<String>();
	
	@SuppressWarnings("unchecked")
	public void run() {
		
		Connection con;
		Statement stmt = null;
		
		try {
			con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
		
			String query = "SELECT * FROM public.\"City\"";
			stmt = con.createStatement();
	    
			ResultSet result = stmt.executeQuery(query);
			
			while (result.next()) {
				comboData.add(result.getInt(1) + " - " + result.getString(2));
			}
			
			((ComboBox<String>) ViewProvider.getView("postalCodeComboAddBook")).getItems().addAll(comboData);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void setComboData() {
		(new Thread(new PostalCodeComboBox())).start();
		
	}

}
