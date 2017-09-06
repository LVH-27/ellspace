package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.AddUserToDatabase;
import knjiznica.model.PostalCodeComboThread;
import knjiznica.model.ViewProvider;

public class AddAuthorView {
	
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField middleNameField;

	@FXML
	private TextField lastNameField;
	
	@FXML
	private CheckBox isAliveCheck;
	
	@FXML
	private TextField yearOfBirthField;
	
	@FXML
	private TextField yearOfDeathField;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label errorLabelMiss;
	
	@FXML
	private Label errorLabelTooMuch;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private boolean isAlive;
	private String yearOfBirth;
	private String yearOfDeath;
	
	public void initialize() {
		Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("homeButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("../resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("homeButton");
		
	}
	
	@FXML
	private void activateBack() throws IOException {
		BorderPane startScreenView = (BorderPane) ViewProvider.getView("startScreen");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(startScreenView);
	}
	
	@FXML
	private void activateAdd() {
		firstName = firstNameField.getText();
		middleName = middleNameField.getText();
		lastName = lastNameField.getText();
		isAlive = isAliveCheck.isArmed();
		yearOfBirth = yearOfBirthField.getText();
		yearOfDeath = yearOfDeathField.getText();
		
		if(firstName.isEmpty() || lastName.isEmpty()) {
			errorLabelMiss.setVisible(true);
		}
		else {
			if(middleName.isEmpty()) {
				middleName = null;
			}
			if(isAlive && !yearOfDeath.isEmpty()) {
				errorLabelTooMuch.setVisible(true);
			}
		}
	}
}
