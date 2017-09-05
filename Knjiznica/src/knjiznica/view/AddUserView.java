package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.PostalCodeComboBox;
import knjiznica.model.ViewProvider;

public class AddUserView {
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private ComboBox<String> postalCodeCombo;
	
	private String nameCombo = "postalCodeComboAddUser";
	
	public void initialize() {
		Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("homeButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("../resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("homeButton");
		
		ViewProvider.setView(nameCombo, postalCodeCombo);
		PostalCodeComboBox.setComboData(nameCombo);
		
	}
	
	@FXML
	private void activateBack() throws IOException {
		BorderPane usersPopup = (BorderPane) ViewProvider.getView("usersPopup");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(usersPopup);

	}
}
