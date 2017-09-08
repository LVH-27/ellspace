package knjiznica.view;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	private CheckBox  onlineLibraryCheck;
	
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
	
	@FXML
	private Label errorLabelTime;

	@FXML
	private HBox countryHBox;
		
	@FXML
	private HBox streetHBox;
		
	@FXML
	private HBox houseNumberHBox;
	
	public static boolean isInterrupted = false;
	public static boolean isReached = true;
	
	private CheckBox testCheck;
	private String nameCombo = "postalCodeComboAddLibrary";
	private String firstName;
	private String phoneNumber;
	private String email;
	private String information;
	private String country;
	private String street;
	private String houseNumber;
	private String postalCode;
	private SingleSelectionModel<String> postalCodeSingle;
	private ArrayList<String> beginTime;
	private ArrayList<String> endTime;
	private ArrayList<TextField> beginTimeList;
	private ArrayList<TextField> endTimeList;
	private ArrayList<CheckBox> checkBoxList;
	
	public void initialize() {
		Image imageAddButton = new Image(getClass().getResourceAsStream("../resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("transparentButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("../resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("transparentButton");
		
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
		} else if (testCheck == check2) {
			if (check2.isSelected()) {
				begin2.setDisable(false);
				end2.setDisable(false);
			} else {
				begin2.setDisable(true);
				end2.setDisable(true);
			}
		} else if (testCheck == check3) {
			if (check3.isSelected()) {
				begin3.setDisable(false);
				end3.setDisable(false);
			} else {
				begin3.setDisable(true);
				end3.setDisable(true);
			}
		} else if (testCheck == check4) {
			if (check4.isSelected()) {
				begin4.setDisable(false);
				end4.setDisable(false);
			} else {
				begin4.setDisable(true);
				end4.setDisable(true);
			}
		} else if (testCheck == check5) {
			if (check5.isSelected()) {
				begin5.setDisable(false);
				end5.setDisable(false);
			} else {
				begin5.setDisable(true);
				end5.setDisable(true);
			}
		} else if (testCheck == check6) {
			if (check6.isSelected()) {
				begin6.setDisable(false);
				end6.setDisable(false);
			} else {
				begin6.setDisable(true);
				end6.setDisable(true);
			}
		} else if (testCheck == check7) {
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
	private void activateAdd() throws IOException {
		
		isInterrupted = false;
		isReached = true;
		final String redBorder ="-fx-border-color: #ff0000;\n";
		
		firstNameField.setStyle("");
		phoneNumberField.setStyle("");
		emailField.setStyle("");
		informationField.setStyle("");
		countryField.setStyle("");
		streetField.setStyle("");
		houseNumberField.setStyle("");
		postalCodeCombo.setStyle("");
		
		errorLabel.setVisible(false);
		errorLabelTime.setVisible(false);
		
		postalCodeSingle = postalCodeCombo.getSelectionModel();
		postalCode = postalCodeSingle.getSelectedItem();
		
		firstName = firstNameField.getText();
		phoneNumber = phoneNumberField.getText();
		email = emailField.getText();
		information = informationField.getText();
		country = countryField.getText();
		street = streetField.getText();
		houseNumber = houseNumberField.getText();
		
		firstName = firstName.trim();
		phoneNumber = phoneNumber.trim();
		email = email.trim();
		information = information.trim();
		country = country.trim();
		street = street.trim();
		houseNumber = houseNumber.trim();
		
		checkBoxList = new ArrayList<CheckBox>();
		beginTimeList = new ArrayList<TextField>();
		endTimeList = new ArrayList<TextField>();
		
		beginTime = new ArrayList<String>();
		endTime = new ArrayList<String>();
		
		boolean errorInfo = false;
		
		 //TODO DOUBLED EMAIL NOT UNIQUE
		 
		checkBoxList.add(check1); checkBoxList.add(check2); checkBoxList.add(check3); checkBoxList.add(check4); checkBoxList.add(check5); checkBoxList.add(check6); checkBoxList.add(check7);
		beginTimeList.add(begin1); beginTimeList.add(begin2); beginTimeList.add(begin3); beginTimeList.add(begin4); beginTimeList.add(begin5); beginTimeList.add(begin6); beginTimeList.add(begin7);
		endTimeList.add(end1); endTimeList.add(end2); endTimeList.add(end3); endTimeList.add(end4); endTimeList.add(end5); endTimeList.add(end6); endTimeList.add(end7);
		
		for(int i = 0; i < beginTimeList.size(); ++i) {
			beginTimeList.get(i).setStyle("");
			endTimeList.get(i).setStyle("");
		}

		if (firstName.isEmpty()) {
			errorLabel.setText("Missing some information!");
			errorLabel.setVisible(true);
			firstNameField.setStyle(redBorder);
			errorInfo = true;
		}
		
		if (phoneNumber.isEmpty()) {
			errorLabel.setText("Missing some information!");
			errorLabel.setVisible(true);
			phoneNumberField.setStyle(redBorder);
			errorInfo = true;
		}
		
		if (!onlineLibraryCheck.isSelected() && (country.isEmpty() || street.isEmpty() || houseNumber.isEmpty() || postalCodeSingle.isEmpty())) {
			
			errorLabel.setText("Missing some information!");
			errorLabel.setVisible(true);

			if (country.isEmpty()) {
				countryField.setStyle(redBorder);
			}
			if (street.isEmpty()) {
				streetField.setStyle(redBorder);
			}
			if (houseNumber.isEmpty()) {
				houseNumberField.setStyle(redBorder);
			}
			if (postalCodeSingle.isEmpty()) {
				postalCodeCombo.setStyle(redBorder);
			}
			
			errorInfo = true;
		}
			
		if (information.isEmpty()) {
			information = null;
		}
		
		if (email.isEmpty()) {
			email = null;
		}
		
		if (!firstName.isEmpty()) {
			firstName = FormattingName.format(firstName);
		}
		
		int postalCodeInt;
		try{
			postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
		} catch (Exception e) {
			postalCodeInt = 0;
		}
		if (!firstName.isEmpty() && !CheckInputLetters.check(firstName)) {
			firstNameField.setStyle(redBorder);
			if (!errorInfo) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
			}
			
			errorInfo = true;
		}
		
		if (houseNumber.length() > 6) {
			
			houseNumberField.setStyle(redBorder);
			
			if (!errorInfo) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);	
			}
			
			errorInfo = true;
			
		}
		
		if (phoneNumber.length() > 20) {
			
			phoneNumberField.setStyle(redBorder);
			
			if (!errorInfo) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);	
			}
			
			errorInfo = true;
			
		}
		boolean check = true;
		
		beginTime = new ArrayList<String>();
		
		for (int i = 0; i < checkBoxList.size(); ++i) {
			if (checkBoxList.get(i).isSelected()) {
				if (!isValid(beginTimeList.get(i).getText()) || !isValid(endTimeList.get(i).getText())) {
					if (!isValid(beginTimeList.get(i).getText())) {
						beginTimeList.get(i).setStyle(redBorder);
						
					} if (!isValid(endTimeList.get(i).getText())) {
						endTimeList.get(i).setStyle(redBorder);
					}
					check = false;
			
				} else {
					beginTime.add(beginTimeList.get(i).getText());
					endTime.add(endTimeList.get(i).getText());
				}
				
			} else {
				beginTime.add("00:00");
				endTime.add("00:00");
			}
		}
		if (check && !errorInfo) {
			
			errorLabel.setVisible(false); 
			errorLabelTime.setVisible(false);
			
			AddLibraryToDatabase.addLibrary(firstName, phoneNumber, email, information, country, street, houseNumber, postalCodeInt, beginTime, endTime, checkBoxList, onlineLibraryCheck.isSelected());
			
	    	if(!isInterrupted && isReached) {
	    		
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Information Dialog");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Library successfully added!");
	    		
	    		alert.initModality(Modality.APPLICATION_MODAL);
	    		alert.initOwner((Stage) ViewProvider.getView("primaryStage"));  
	    		
	    		alert.showAndWait();
	    		
	    		BorderPane addLibrary = (BorderPane) FXMLLoader.load(getClass().getResource("AddLibrary-view.fxml"));
	    		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addLibrary);
	    		
	    	} else if (isInterrupted) {
	    		errorLabel.setText("Something went wrong!");
	    		errorLabel.setVisible(true);
	    		
	    	} else {
	    		errorLabel.setText("Couldn't reach database!");
	    		errorLabel.setVisible(true);
	    	}
		}
		
		if (!check) {
			errorLabelTime.setText("Please enter time between\n"
					+ "00:00 and 23:59.");
			errorLabelTime.setVisible(true);
		}
	}
	
	private static boolean isValid(String time) {
		String[] timeSplit = time.split(":");
		if (timeSplit.length != 2) {
			return false;
			
		} else if (timeSplit[0].length() > 2 || timeSplit[0].length() < 1 || timeSplit[1].length() != 2) {
			return false;
			
		}
		
		boolean check = true;
		int checkHours = 0;
		int checkMin = 0;

		for (int i = 0; i < timeSplit.length; ++i) {
			try {
				if (i == 0) {
					checkHours = Integer.parseInt(timeSplit[i]);
				} else {
					checkMin = Integer.parseInt(timeSplit[i]);
				}
				
			} catch (Exception e) {
				check = false;
				break;
			}
		}
		
		if (!check) {
			return false;
		} else if (checkHours > 23 || checkHours < 0 || checkMin > 59 || checkMin < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@FXML
	private void onlineLibrary() {
		if (onlineLibraryCheck.isSelected()) {
			countryHBox.setVisible(false);
			countryField.setVisible(false);
			streetHBox.setVisible(false);
			streetField.setVisible(false);
			houseNumberHBox.setVisible(false);
			houseNumberField.setVisible(false);
			postalCodeCombo.setVisible(false);
			
		} else if (!onlineLibraryCheck.isSelected()) {
			countryHBox.setVisible(true);
			countryField.setVisible(true);
			streetHBox.setVisible(true);
			streetField.setVisible(true);
			houseNumberHBox.setVisible(true);
			houseNumberField.setVisible(true);
			postalCodeCombo.setVisible(true);
		}
	}
}
