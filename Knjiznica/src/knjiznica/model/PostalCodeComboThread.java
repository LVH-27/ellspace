package knjiznica.model;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class PostalCodeComboThread implements Runnable {
	
	private static String which;
	
	@SuppressWarnings("unchecked")
	public void run() {
		
		switch(which) {
		
		case "postalCodeComboAddUser":
			try {
				((ComboBox<String>) ViewProvider.getView(which)).getItems().addAll((ArrayList<String>) PostalCodeCombo.getData().get(0));
				break;
			} catch (SQLException e) {
				e.printStackTrace();
				break;
			}
			
		case "postalCodeComboAddLibrary":
			try {
				((ComboBox<String>) ViewProvider.getView(which)).getItems().addAll((ArrayList<String>) PostalCodeCombo.getData().get(0));
				break;
			} catch (SQLException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	
	public static void setComboData(String whichCombo) {
		which = whichCombo;
		(new Thread(new PostalCodeComboThread())).start();
	}
}
