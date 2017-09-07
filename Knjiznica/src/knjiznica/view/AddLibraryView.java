package knjiznica.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.AddLibraryToDatabase;
import knjiznica.model.CheckInputLetters;
import knjiznica.model.FormattingName;
import knjiznica.model.PostalCodeComboThread;
import knjiznica.model.ViewProvider;

public class AddLibraryView {
	
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField phoneNumberField;
	
	@FXML
	private TextField emailField;
	
	@FXML
	private TextField informationField;
	
	@FXML
	private TextField countryField;
	
	@FXML
	private TextField streetField;
	
	@FXML
	private TextField houseNumberField;
	
	@FXML
	private ComboBox<String> postalCodeCombo;
	
	@FXML
	private CheckBox  check1;
	
	@FXML
	private CheckBox  check2;
	
	@FXML
	private CheckBox  check3;
	
	@FXML
	private CheckBox  check4;
	
	@FXML
	private CheckBox  check5;
	
	@FXML
	private CheckBox  check6;
	
	@FXML
	private CheckBox  check7;
	
	@FXML
	private TextField  begin1;
	
	@FXML
	private TextField  begin2;
	
	@FXML
	private TextField  begin3;
	
	@FXML
	private TextField  begin4;
	
	@FXML
	private TextField  begin5;
	
	@FXML
	private TextField  begin6;
	
	@FXML
	private TextField  begin7;

	@FXML
	private TextField  end1;
	
	@FXML
	private TextField  end2;
	
	@FXML
	private TextField  end3;
	
	@FXML
	private TextField  end4;
	
	@FXML
	private TextField  end5;
	
	@FXML
	private TextField  end6;
	
	@FXML
	private TextField  end7;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label errorLabel;
	
	private CheckBox testCheck;
	private String nameCombo = "postalCodeComboAddLibrary";
	private String firstName;
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
	private void toggleCheck(ActionEvent e) {
		testCheck = (CheckBox) e.getSource();
		if (testCheck == check1) {
			if (check1.isSelected()) {
				begin1.setDisable(false);
				end1.setDisable(false);
			} else {
				begin1.setDisable(true);
				end1.setDisable(true);
			}
		} else if(testCheck == check2) {
			if (check2.isSelected()) {
				begin2.setDisable(false);
				end2.setDisable(false);
			} else {
				begin2.setDisable(true);
				end2.setDisable(true);
			}
		} else if(testCheck == check3) {
			if (check3.isSelected()) {
				begin3.setDisable(false);
				end3.setDisable(false);
			} else {
				begin3.setDisable(true);
				end3.setDisable(true);
			}
		} else if(testCheck == check4) {
			if (check4.isSelected()) {
				begin4.setDisable(false);
				end4.setDisable(false);
			} else {
				begin4.setDisable(true);
				end4.setDisable(true);
			}
		} else if(testCheck == check5) {
			if (check5.isSelected()) {
				begin5.setDisable(false);
				end5.setDisable(false);
			} else {
				begin5.setDisable(true);
				end5.setDisable(true);
			}
		} else if(testCheck == check6) {
			if (check6.isSelected()) {
				begin6.setDisable(false);
				end6.setDisable(false);
			} else {
				begin6.setDisable(true);
				end6.setDisable(true);
			}
		} else if(testCheck == check7) {
			if (check7.isSelected()) {
				begin7.setDisable(false);
				end7.setDisable(false);
			} else {
				begin7.setDisable(true);
				end7.setDisable(true);
			}
		}
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
		email = emailField.getText();
		phoneNumber = phoneNumberField.getText();
		country = countryField.getText();
		street = streetField.getText();
		houseNumber = houseNumberField.getText();
		
		firstName = firstName.trim();
		email = email.trim();
		phoneNumber = phoneNumber.trim();
		country = country.trim();
		street = street.trim();
		houseNumber = houseNumber.trim();
		
		/*
		 * DOUBLED EMAIL NOT UNIQUE
		 */
		/*
		 * FIX postalCode.isEmpty() /////////////////////////////////////////////////////////////////////////////////////////////////////////
		 */
		
		if (postalCode.isEmpty() || firstName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || country.isEmpty() || street.isEmpty() || houseNumber.isEmpty()) {
			errorLabel.setVisible(true);
		}
		else {
			firstName = FormattingName.format(firstName);
			
			if (!CheckInputLetters.check(firstName)) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
			}
			
			else {
				int postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
				AddLibraryToDatabase.addLibrary(firstName, email, phoneNumber, country, postalCodeInt, street, houseNumber);
			}
			
		}
	}
}
