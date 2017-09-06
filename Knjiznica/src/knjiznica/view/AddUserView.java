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
import knjiznica.model.AddUserToDatabase;
import knjiznica.model.CheckInputLetters;
import knjiznica.model.FormattingName;
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
	private TextField phoneNumberField;
	
	@FXML
	private TextField countryField;
	
	@FXML
	private TextField streetField;
	
	@FXML
	private TextField houseNumberField;
	
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
	private String phoneNumber;
	private String country;
	private String street;
	private String houseNumber;
	private String postalCode;
	
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
		postalCode = postalCodeCombo.getSelectionModel().getSelectedItem();
		firstName = firstNameField.getText();
		middleName = middleNameField.getText();
		lastName = lastNameField.getText();
		email = emailField.getText();
		phoneNumber = phoneNumberField.getText();
		country = countryField.getText();
		street = streetField.getText();
		houseNumber = houseNumberField.getText();
		
		firstName = firstName.trim();
		middleName = middleName.trim();
		lastName = lastName.trim();
		email = email.trim();
		phoneNumber = phoneNumber.trim();
		country = country.trim();
		street = street.trim();
		houseNumber = houseNumber.trim();
		
		/*
		 * DOUBLED EMAIL NOT UNIQUE
		 */
		/*
		 * FIX postalCode.isEmpty() /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		 */
		if(postalCode.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || country.isEmpty() || street.isEmpty() || houseNumber.isEmpty()) {
			errorLabel.setVisible(true);
		}
		else {
			firstName = FormattingName.format(firstName);
			lastName = FormattingName.format(lastName);
			
			if(!CheckInputLetters.check(firstName)) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
			}
			
			else if(!CheckInputLetters.check(middleName)) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
			}
			
			else if(!CheckInputLetters.check(lastName)) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
			}
			else {
				int postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
				AddUserToDatabase.addUser(firstName, middleName, lastName, email, phoneNumber, country, postalCodeInt, street, houseNumber);
			}
			
		}
	}
}
