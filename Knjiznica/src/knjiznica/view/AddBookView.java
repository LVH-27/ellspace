package knjiznica.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import knjiznica.model.PostalCodeComboBox;
import knjiznica.model.ViewProvider;

public class AddBookView {
	
	@FXML
	private ComboBox<String> postalCodeCombo;
	
	public void initialize() {
		ViewProvider.setView("postalCodeComboAddBook", postalCodeCombo);
		PostalCodeComboBox.setComboData();
	}
	
}
