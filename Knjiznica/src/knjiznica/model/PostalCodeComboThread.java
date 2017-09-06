package knjiznica.model;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;


public class PostalCodeComboThread implements Runnable {
	
	private static String which;
	
	@SuppressWarnings("unchecked")
	public void run() {
		switch(which) {
		case "postalCodeComboAddBook":
			try {
				((ComboBox<String>) ViewProvider.getView(which)).getItems().addAll((ArrayList<String>) PostalCodeCombo.getData().get(0));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		case "postalCodeComboAddUser":
			try {
				((ComboBox<String>) ViewProvider.getView(which)).getItems().addAll((ArrayList<String>) PostalCodeCombo.getData().get(0));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void setComboData(String whichCombo) {
		which = whichCombo;
		(new Thread(new PostalCodeComboThread())).start();
	}

}
