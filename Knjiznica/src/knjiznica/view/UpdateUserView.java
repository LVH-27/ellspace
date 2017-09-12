package knjiznica.view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import knjiznica.model.AlertWindowOpen;
import knjiznica.model.CheckInputLetters;
import knjiznica.model.ErrorLabelMessage;
import knjiznica.model.FormattingName;
import knjiznica.model.GlobalCollection;
import knjiznica.model.PostalCodeComboThread;
import knjiznica.model.UpdateUserInfo;
import knjiznica.model.User;
import knjiznica.model.ViewProvider;


public class UpdateUserView {

		
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
	
	private static boolean isEditable;
	
	public static User user;
	
	public static boolean isInterrupted = false;
	public static boolean isReached = true;
	
	private String nameCombo = "postalCodeComboAddUser";
	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String email = "";
	private String phoneNumber = "";
	private String country = "";
	private String street = "";
	private String houseNumber = "";
	private String postalCode = "";
	private SingleSelectionModel<String> postalCodeSingle;
	private boolean check;
	
	public void initialize() {
		
		isEditable = GlobalCollection.isEditable();
		user = GlobalCollection.getUser();
		
		ViewProvider.setView(nameCombo, postalCodeCombo); 
		PostalCodeComboThread.setComboData(nameCombo);
		
		if (!isEditable) {
			firstNameField.setEditable(false);
			middleNameField.setEditable(false);
			lastNameField.setEditable(false);
			emailField.setEditable(false);
			phoneNumberField.setEditable(false);
			countryField.setEditable(false);
			streetField.setEditable(false);
			houseNumberField.setEditable(false);
			postalCodeCombo.setDisable(true);
			postalCodeCombo.setStyle("-fx-opacity: 1;");
			
			Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/edit-button.png"));
			addButton.setGraphic(new ImageView(imageAddButton));
			addButton.setId("transparentButton");
			addButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	GlobalCollection.setEditable(true);
			    	BorderPane updateUser;
					try {
						updateUser = (BorderPane) FXMLLoader.load(getClass().getResource("UpdateUser-view.fxml"));
						((BorderPane) ViewProvider.getView("mainScreen")).setCenter(updateUser);
						
					} catch (IOException e1) {
						e1.printStackTrace();
						
					}
					
			    }
			});
			
		} else {
			Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/editAccept-button.png"));
			addButton.setGraphic(new ImageView(imageAddButton));
			addButton.setId("transparentButton");
		}
		
		
		firstNameField.setText(user.getFirstName());
		
		if (user.getMiddleName() != null && !user.getMiddleName().equals("-")) {
			middleNameField.setText(user.getMiddleName());
		} else {
			middleNameField.setText("");
		}
		
		lastNameField.setText(user.getLastName());
		emailField.setText(user.getEmail());
		
		if (user.getPhoneNumber() != null && !user.getPhoneNumber().equals("-")) {
			phoneNumberField.setText(user.getPhoneNumber());
		} else {
			phoneNumberField.setText("");
		}
		
		countryField.setText(user.getCountry());
		
		if (user.getStreet() != null && !user.getStreet().equals("-")) {
			streetField.setText(user.getStreet());
		} else {
			streetField.setText("");
		}
		
		if (user.getHouseNumber() != null && !user.getHouseNumber().equals("-")) {
			houseNumberField.setText(user.getHouseNumber());
		} else {
			houseNumberField.setText("");
		}
	
		postalCodeCombo.getSelectionModel().select(Integer.toString(user.getPostalCode()) + " - " + user.getCity());
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("../resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("transparentButton");
		
		
	}
	
	@FXML
	private void activateBack() throws IOException {
		GlobalCollection.setEditable(true);
		
		BorderPane listUsers = (BorderPane) FXMLLoader.load(getClass().getResource("ListUsers-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(listUsers);

	}
	
	@FXML
	private void activateUpdate() throws IOException {

		isInterrupted = false;
		isReached = true;
		
		final String redBorder ="-fx-border-color: #ff0000;\n";
		
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

		/*
		 * DOUBLED EMAIL NOT UNIQUE
		 */
		
		check = true;
			
			
		if (firstName.isEmpty()) {
			check = false;
			firstNameField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
			
		} if (lastName.isEmpty()) {
			check = false;
			lastNameField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
			
		} if (postalCodeSingle.isEmpty()) {
			check = false;
			postalCodeCombo.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
			
		} if (email.isEmpty()) {
			check = false;
			emailField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
			
		} if (country.isEmpty()) {
			check = false;
			countryField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
			
		} 
		
		if (!firstName.isEmpty() && !CheckInputLetters.check(firstName)) {
			check = false;
			firstNameField.setStyle(redBorder);
			if(!errorLabel.isVisible()) {
				errorLabel.setText(ErrorLabelMessage.getWrongFormat());
				errorLabel.setVisible(true);
				
			}
			
		}
		
		if (!middleName.isEmpty() && !CheckInputLetters.check(middleName)) {
			check = false;
			middleNameField.setStyle(redBorder);
			if(!errorLabel.isVisible()) {
				errorLabel.setText(ErrorLabelMessage.getWrongFormat());
				errorLabel.setVisible(true);
				
			}
			
		}
		
		if (!lastName.isEmpty() && !CheckInputLetters.check(lastName)) {
			check = false;
			lastNameField.setStyle(redBorder);
			if(!errorLabel.isVisible()) {
				errorLabel.setText(ErrorLabelMessage.getWrongFormat());
				errorLabel.setVisible(true);
				
			}
			
		}
		
		if (houseNumber.length() > 6) {
			check = false;
			houseNumberField.setStyle(redBorder);
			if(!errorLabel.isVisible()) {
				errorLabel.setText(ErrorLabelMessage.getWrongFormat());
				errorLabel.setVisible(true);
				
			}
			
		}
		
		if (phoneNumber.length() > 20) {
			check = false;
			phoneNumberField.setStyle(redBorder);
			if(!errorLabel.isVisible()) {
				errorLabel.setText(ErrorLabelMessage.getWrongFormat());
				errorLabel.setVisible(true);
				
			}
			
		}
		
		if (check) {
			
			if (street.isEmpty()) {
				street = null;
				
			} if (houseNumber.isEmpty()) {
				houseNumber = null;
				
			}
			
			postalCode = postalCodeSingle.getSelectedItem();
			firstName = FormattingName.format(firstName);
			lastName = FormattingName.format(lastName);
			
			if (middleName.isEmpty()) {
				middleName = null;
				
			} else {
				middleName = FormattingName.format(middleName);
			}
			
			if (phoneNumber.isEmpty()) {
				phoneNumber = null;
			}
			
			errorLabel.setVisible(false);
			
			int postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
			
			UpdateUserInfo.updateUser(firstName, middleName, lastName, email, phoneNumber, country, postalCodeInt, street, houseNumber, user.getAddressID(), user.getID());
			
			if(!isInterrupted && isReached) {
				GlobalCollection.getUser().setFirstName(firstName);
				GlobalCollection.getUser().setMiddleName(middleName);
				GlobalCollection.getUser().setLastName(lastName);
				GlobalCollection.getUser().setEmail(email);
				GlobalCollection.getUser().setPhoneNumber(phoneNumber);
				GlobalCollection.getUser().setCountry(country);
				GlobalCollection.getUser().setPostalCode(postalCodeInt);
				GlobalCollection.getUser().setStreet(street);
				GlobalCollection.getUser().setHouseNumber(houseNumber);
				AlertWindowOpen.openWindow("User successfully updated!");
	    		GlobalCollection.setEditable(false);
				BorderPane updateUser = (BorderPane) FXMLLoader.load(getClass().getResource("UpdateUser-view.fxml"));
		    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(updateUser);
		    	
			} else if (isInterrupted) {
				errorLabel.setText(ErrorLabelMessage.getSomething());
				errorLabel.setVisible(true);
				
			} else {
				errorLabel.setText(ErrorLabelMessage.getFailReach());
				errorLabel.setVisible(true); 
				
			}
			
		}	
		
	}
}
