package knjiznica.view;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import knjiznica.model.PostalCodeComboThread;
import knjiznica.model.ViewProvider;

public class AddBookView {
	
	@FXML
	private ComboBox<String> postalCodeCombo;
	
	private String nameCombo = "postalCodeComboAddBook";
	
	public void initialize() throws SQLException {
		ViewProvider.setView(nameCombo, postalCodeCombo);
		PostalCodeComboThread.setComboData(nameCombo);
	}
}
