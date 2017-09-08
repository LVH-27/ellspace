package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
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
	
	public static boolean isInterrupted = false;
	public static boolean isReached = true;
	
	private String nameCombo = "postalCodeComboAddUser";
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String country;
	private String street;
	private String houseNumber;
	String postalCode;
	private SingleSelectionModel<String> postalCodeSingle;
	private boolean check;
	
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
	private void activateAdd() throws IOException {
		
		isInterrupted = false;
		isReached = true;
		
		errorLabel.setVisible(false);
		postalCodeSingle = postalCodeCombo.getSelectionModel();
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
		
		firstNameField.setStyle("");
		middleNameField.setStyle("");
		lastNameField.setStyle("");
		postalCodeCombo.setStyle("");
		emailField.setStyle("");
		phoneNumberField.setStyle("");
		countryField.setStyle("");
		streetField.setStyle("");
		houseNumberField.setStyle("");
		
		final String redBorder ="-fx-border-color: #ff0000;\n";
		
		/*
		 * DOUBLED EMAIL NOT UNIQUE
		 */
		
		if (postalCodeSingle.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || country.isEmpty() || street.isEmpty() || houseNumber.isEmpty()) {
			errorLabel.setText("Missing information");
			
			if (firstName.isEmpty()) {
				firstNameField.setStyle(redBorder);
				
			} if (lastName.isEmpty()) {
				lastNameField.setStyle(redBorder);
				
			} if (postalCodeSingle.isEmpty()) {
				postalCodeCombo.setStyle(redBorder);
				
			} if (email.isEmpty()) {
				emailField.setStyle(redBorder);
				
			} if (phoneNumber.isEmpty()) {
				phoneNumberField.setStyle(redBorder);
				
			} if (country.isEmpty()) {
				countryField.setStyle(redBorder);
				
			} if (street.isEmpty()) {
				streetField.setStyle(redBorder);
				
			} if (houseNumber.isEmpty()) {
				houseNumberField.setStyle(redBorder);
				
			}
			
			errorLabel.setVisible(true);
		}
		else {
			postalCode = postalCodeSingle.getSelectedItem();
			firstName = FormattingName.format(firstName);
			lastName = FormattingName.format(lastName);
			check = true;
			
			if (!CheckInputLetters.check(firstName)) {
				check = false;
				firstNameField.setStyle(redBorder);
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
				
			}
			
			if (!middleName.isEmpty() && !CheckInputLetters.check(middleName)) {
				check = false;
				middleNameField.setStyle(redBorder);
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
				
			}
			
			if (!CheckInputLetters.check(lastName)) {
				check = false;
				lastNameField.setStyle(redBorder);
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
				
			}
			if (check) {
				
				errorLabel.setVisible(false);
				
				int postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
				
				AddUserToDatabase.addUser(firstName, middleName, lastName, email, phoneNumber, country, postalCodeInt, street, houseNumber);
				
				if(!isInterrupted && isReached) {
					BorderPane addUser = (BorderPane) FXMLLoader.load(getClass().getResource("AddUser-view.fxml"));
			    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addUser);
			    	
				} else if (isInterrupted) {
					errorLabel.setText("Something went wrong!");
					errorLabel.setVisible(true);
					
				} else {
					errorLabel.setText("Couldn't reach database!");
					errorLabel.setVisible(true); 
					
				}
				
			}
			
		}
	}
}
