package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.PostalCodeComboThread;
import knjiznica.model.ViewProvider;

public class AddUserView {
	
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField middleNameField;

	@FXML
	private TextField lastNameField;
	
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField adressField;
	
	@FXML
	private TextField phoneNumberField;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private ComboBox<String> postalCodeCombo;
	
	@FXML
	private Label errorLabel;
	
	private String nameCombo = "postalCodeComboAddUser";
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String email;
	
	private String adress;
	
	private String phoneNumber;
	
	public void initialize() {
		Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("homeButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("../resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("homeButton");
		
		ViewProvider.setView(nameCombo, postalCodeCombo);
		PostalCodeComboThread.setComboData(nameCombo);
	}
	
	@FXML
	private void activateBack() throws IOException {
		BorderPane usersPopup = (BorderPane) ViewProvider.getView("usersPopup");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(usersPopup);
	}
	
	@FXML
	private void activateAdd() {
		firstName = firstNameField.getText();
		middleName = middleNameField.getText();
		lastName = lastNameField.getText();
		email = emailField.getText();
		adress = adressField.getText();
		phoneNumber = phoneNumberField.getText();
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || adress.isEmpty() || phoneNumber.isEmpty()) {
			errorLabel.setVisible(true);
		}
		else {
			System.out.println(middleName);
		}
	}
}
