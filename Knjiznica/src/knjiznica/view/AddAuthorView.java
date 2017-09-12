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
import knjiznica.model.AlertWindowOpen;
import knjiznica.model.CheckInputLetters;
import knjiznica.model.ErrorLabelMessage;
import knjiznica.model.ViewProvider;

public class AddAuthorView {
	
	@FXML
	private TextField searchField;
	
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
	
	public static boolean checkIndeterminate;
	
	public static boolean isInterrupted = false;
	public static boolean isReached = true; 
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String yearOfBirth;
	private String yearOfDeath;
	private boolean isAlive;
	
	public void initialize() {
		
		//TODO implement error when year has more than 4 digits and it should work when year is < 0
		
		Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("transparentButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("../resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("transparentButton");
		
//		searchField.setOnKeyReleased(value);
	}
	
	@FXML 
	private void activateBack() throws IOException {
		BorderPane startScreenView = (BorderPane) ViewProvider.getView("startScreen");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(startScreenView);
	}
	
	@FXML
	private void activateAdd() throws IOException {
		
		checkIndeterminate = false;
		
		isInterrupted = false;
		isReached = true;
		
		errorLabelMiss.   setVisible(false);
		errorLabelTooMuch.setVisible(false);
		
		firstName  = firstNameField. getText();
		middleName = middleNameField.getText();
		lastName   = lastNameField.  getText();
		isAlive    = isAliveCheck.   isSelected();
		
		if (!isAlive) {
			isAlive = isAliveCheck.isIndeterminate();
			if (isAlive) {
				checkIndeterminate = true;
			}
		}
		
		yearOfBirth = yearOfBirthField.getText();
		yearOfDeath = yearOfDeathField.getText();
		
		firstName   = firstName.  trim();
		middleName  = middleName. trim();
		lastName    = lastName.   trim();
		yearOfBirth = yearOfBirth.trim();
		yearOfDeath = yearOfDeath.trim();
		
		firstNameField.  setStyle("");
		middleNameField. setStyle("");
		lastNameField.   setStyle("");
		isAliveCheck.    setStyle("");
		yearOfBirthField.setStyle("");
		yearOfDeathField.setStyle("");
		
		boolean birth = true;
		boolean death = true;
		
		try {
			Integer.parseInt(yearOfBirth);
			
		} catch(Exception e) {
			birth = false;
			
		} finally {
			if (yearOfBirth.isEmpty()) {
				birth = true;
			}
		}
		
		try {
			Integer.parseInt(yearOfDeath);
			
		} catch(Exception e) {
			death = false;
			
		} finally {
			if (yearOfDeath.isEmpty()) {
				death = true;
			}
		}
		
		boolean showMiss = false;
		boolean showTooMuch = false;
		
		final String redBorder = "-fx-border-color: #ff0000;\n";
		
		if (firstName.isEmpty()) {
			firstNameField.setStyle(redBorder);
			showMiss = true;
			errorLabelMiss.setText(ErrorLabelMessage.getInfoMiss());
			errorLabelMiss.setVisible(true);
		}
		
		if (lastName.isEmpty()) {
			lastNameField.setStyle(redBorder);
			showMiss = true;
			errorLabelMiss.setText(ErrorLabelMessage.getInfoMiss());
			errorLabelMiss.setVisible(true);
		}
		
		if (!firstName.isEmpty() && !CheckInputLetters.check(firstName)) {
			firstNameField.setStyle(redBorder);
			showMiss = true;
			errorLabelMiss.setText(ErrorLabelMessage.getWrongFormat());
			errorLabelMiss.setVisible(true);
		}
		
		if (!middleName.isEmpty() && !CheckInputLetters.check(middleName)) {
			middleNameField.setStyle(redBorder);
			
			if (!showMiss) {
				showMiss = true;
				errorLabelMiss.setText(ErrorLabelMessage.getWrongFormat());
				errorLabelMiss.setVisible(true);
			}
		}
		
		if (!lastName.isEmpty() && !CheckInputLetters.check(lastName)) {
			lastNameField.setStyle(redBorder);
			
			if (!showMiss) {
				showMiss = true;
				errorLabelMiss.setText(ErrorLabelMessage.getWrongFormat());
				errorLabelMiss.setVisible(true);
			}
		}
		
		if (!birth) {
			yearOfBirthField.setStyle(redBorder);
			
			showTooMuch = true;
			errorLabelTooMuch.setText("Please enter birth year\n"
					+ "in 4 digits (e.g. 1973).");
			errorLabelTooMuch.setVisible(true);
		}
		
		if (!death && !isAliveCheck.isSelected() && !isAliveCheck.isIndeterminate()) {
			yearOfDeathField.setStyle(redBorder);
			
			if (!showTooMuch) {
				showTooMuch = true;
				errorLabelTooMuch.setText("Please enter death year\n"
						+ "in 4 digits (e.g. 1973).");
				errorLabelTooMuch.setVisible(true);
			}
		}			
		
		if (!yearOfBirth.isEmpty() && !yearOfDeath.isEmpty() && birth && death && Integer.parseInt(yearOfBirth) > Integer.parseInt(yearOfDeath)) {
			yearOfBirthField.setStyle(redBorder);
			yearOfDeathField.setStyle(redBorder);
			
			if (!showTooMuch) {
				showTooMuch = true;
				errorLabelTooMuch.setText("Year of birth cannot be\n"
						+ "larger than year of death.");
				errorLabelTooMuch.setVisible(true);
			}
		}
		
		if (!showMiss && !showTooMuch) {
			
			if (isAliveCheck.isSelected() || isAliveCheck.isIndeterminate() || yearOfDeath.isEmpty()) {
    			yearOfDeath = null;
    		}
			
			if (yearOfBirth.isEmpty()) {
				yearOfBirth = null;
			}
			
			AddAuthorToDatabase.addAuthor(firstName, middleName, lastName, isAlive, yearOfBirth, yearOfDeath);
			errorLabelMiss.setVisible(false);
			errorLabelTooMuch.setVisible(false);
			
	    	if (!isInterrupted && isReached) { 
	    		AlertWindowOpen.openWindow("Author successfully added!");
	    		
	    		BorderPane addAuthor = (BorderPane) FXMLLoader.load(getClass().getResource("AddAuthor-view.fxml"));
		    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addAuthor);
	    		
	    	} else if (isInterrupted) {
	    		errorLabelMiss.setText(ErrorLabelMessage.getSomething());
	    		errorLabelMiss.setVisible(true);
	    	} else {
	    		errorLabelMiss.setText(ErrorLabelMessage.getFailReach());
	    		errorLabelMiss.setVisible(true);
	    	}
		}
	}
	
	@FXML
	public void disableYearOfDeath() {
		if (isAliveCheck.isSelected() || isAliveCheck.isIndeterminate()) {
			yearOfDeathField.setStyle("");
			yearOfDeathField.setDisable(true);
		} else {
			yearOfDeathField.setDisable(false);
		}
	}
}
