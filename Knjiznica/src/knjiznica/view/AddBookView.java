package knjiznica.view;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import knjiznica.model.PostalCodeComboBox;

public class AddBookView {
	
	@FXML
	private ComboBox<String> postalCodeCombo;
	
	public void initialize() throws SQLException {
		PostalCodeComboBox.setComboData(postalCodeCombo);
	}
	
}
