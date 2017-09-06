package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.AddAuthorToDatabase;
import knjiznica.model.CheckInputLetters;
import knjiznica.model.FormattingName;
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
	private void activateAdd() throws IOException {
		
		firstName = firstNameField.getText();
		middleName = middleNameField.getText();
		lastName = lastNameField.getText();
		isAlive = isAliveCheck.isSelected();
		yearOfBirth = yearOfBirthField.getText();
		yearOfDeath = yearOfDeathField.getText();
		
		firstName = firstName.trim();
		middleName = middleName.trim();
		lastName = lastName.trim();
		yearOfBirth = yearOfBirth.trim();
		yearOfDeath = yearOfDeath.trim();
		
		if(firstName.isEmpty() || lastName.isEmpty()) {
			errorLabelMiss.setVisible(true);
		}
		else {
			firstName = FormattingName.format(firstName);
			lastName = FormattingName.format(lastName);
			
			boolean birth = true;
			boolean death = true;
			
			try {
				Integer.parseInt(yearOfBirth);
				
			}catch(Exception e) {
				birth = false;
				
			}finally{
				if(yearOfBirth.isEmpty()) {
					birth = true;
				}
			}
			
			try {
				Integer.parseInt(yearOfDeath);
				
			}catch(Exception e) {
				death = false;
				
			}finally{
				if(yearOfDeath.isEmpty()) {
					death = true;
				}
			}
			
			if(!birth) {
				errorLabelTooMuch.setText("Please enter birth year\n"
						+ "in 4 digits (e.g. 1973).");
				errorLabelTooMuch.setVisible(true);
			}
			
			else if(!death) {
				errorLabelTooMuch.setText("Please enter death year\n"
						+ "in 4 digits (e.g. 1973).");
				errorLabelTooMuch.setVisible(true);
			}
			
			else if(!CheckInputLetters.check(firstName)) {
				errorLabelMiss.setText("Verify that you have entered the correct information.");
				errorLabelMiss.setVisible(true);
			}
			
			else if(!middleName.isEmpty() && !CheckInputLetters.check(middleName)) {
				errorLabelMiss.setText("Verify that you have entered the correct information.");
				errorLabelMiss.setVisible(true);
			}
			
			else if(!CheckInputLetters.check(lastName)) {
				errorLabelMiss.setText("Verify that you have entered the correct information.");
				errorLabelMiss.setVisible(true);
			}
			
			else if(isAlive && !yearOfDeath.isEmpty()) {
				errorLabelTooMuch.setText("You may only use one.");//////////// HIGHLIGHT BORDERLINE IN RED AND OTHERS ///////////////////////////
				errorLabelTooMuch.setVisible(true);
			}
			
			else if(!yearOfBirth.isEmpty() && !yearOfDeath.isEmpty() && Integer.parseInt(yearOfBirth) > Integer.parseInt(yearOfDeath)) {
				errorLabelTooMuch.setText("Year of birth cannot be\n"
						+ "larger than year of death.");
				errorLabelTooMuch.setVisible(true);
			}
			
			else {
				AddAuthorToDatabase.addAuthor(firstName, middleName, lastName, isAlive, yearOfBirth, yearOfDeath);
				BorderPane addAuthor = (BorderPane) FXMLLoader.load(getClass().getResource("AddAuthor-view.fxml"));
		    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addAuthor);
			}
		}
	}
}
