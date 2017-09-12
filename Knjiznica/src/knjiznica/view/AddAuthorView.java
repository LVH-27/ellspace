package knjiznica.view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.AddAuthorToDatabase;
import knjiznica.model.AlertWindowOpen;
import knjiznica.model.Author;
import knjiznica.model.CheckInputLetters;
import knjiznica.model.ErrorLabelMessage;
import knjiznica.model.GlobalCollection;
import knjiznica.model.SelectAuthors;
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
	
	@FXML
	private TableView<Author> tableAuthorList;
	
	@FXML
	private TableColumn<Author, String> firstNameCol;
	
	@FXML
	private TableColumn<Author, String> middleNameCol;
	
	@FXML
	private TableColumn<Author, String> lastNameCol;
	
	@FXML
	private TableColumn<Author, String> yearOfBirthCol;
	
	@FXML
	private TableColumn<Author, String> yearOfDeathCol;
	
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
		
		//FIXME implement error when year has more than 4 digits and it should work when year is < 0
		//XXX Comment: Should be 5 digits for e.g. "-1649" as in "1649 B.C."
		
		Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("transparentButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("../resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("transparentButton");
		
		ArrayList<Author> authors = SelectAuthors.select(); 
		
		GlobalCollection.emptyList();
		
		for (int i = 0; i < authors.size(); ++i) {
			GlobalCollection.getAuthorList().add(authors.get(i));
		} 
		
		tableAuthorList. setItems(GlobalCollection.getAuthorList());
		firstNameCol.  setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
		firstNameCol.  setStyle("-fx-alignment: CENTER;");
		middleNameCol.  setCellValueFactory(new PropertyValueFactory<Author, String>("middleName"));
		middleNameCol.  setStyle("-fx-alignment: CENTER;");
		lastNameCol.  setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
		lastNameCol.  setStyle("-fx-alignment: CENTER;");
		yearOfBirthCol.  setCellValueFactory(new PropertyValueFactory<Author, String>("yearOfBirth"));
		yearOfBirthCol.  setStyle("-fx-alignment: CENTER;");
		yearOfDeathCol.  setCellValueFactory(new PropertyValueFactory<Author, String>("yearOfDeath"));
		yearOfDeathCol.  setStyle("-fx-alignment: CENTER;");
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
