package knjiznica.view;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
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
		errorLabel.setVisible(false);
		
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
		
		/*
		 * DOUBLED EMAIL NOT UNIQUE
		 */
		checkBoxList.add(check1); checkBoxList.add(check2); checkBoxList.add(check3); checkBoxList.add(check4); checkBoxList.add(check5); checkBoxList.add(check6); checkBoxList.add(check7);
		beginTimeList.add(begin1); beginTimeList.add(begin2); beginTimeList.add(begin3); beginTimeList.add(begin4); beginTimeList.add(begin5); beginTimeList.add(begin6); beginTimeList.add(begin7);
		endTimeList.add(end1); endTimeList.add(end2); endTimeList.add(end3); endTimeList.add(end4); endTimeList.add(end5); endTimeList.add(end6); endTimeList.add(end7);

		
		if (postalCodeSingle.isEmpty() || firstName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || country.isEmpty() || street.isEmpty() || houseNumber.isEmpty()) {
			errorLabel.setText("Missing information");
			errorLabel.setVisible(true);
		}
		else {
			firstName = FormattingName.format(firstName);
			
			if (!CheckInputLetters.check(firstName)) {
				errorLabel.setText("Verify that you have entered the correct information.");
				errorLabel.setVisible(true);
			}
			
			else {
				if(information == "") {
					information = null;
				}
				if(email == "") {
					email = null;
				}
				boolean check = true;
				beginTime = new ArrayList<String>();
				for(int i = 0; i < checkBoxList.size(); ++i) {
					if(checkBoxList.get(i).isSelected()) {
						if(!isValid(beginTimeList.get(i).getText()) || !isValid(endTimeList.get(i).getText())) {
							check = false;
							break;
						}
						beginTime.add(beginTimeList.get(i).getText());
						endTime.add(endTimeList.get(i).getText());
						
					}else {
						beginTime.add("00:00");
						endTime.add("00:00");
					}
				}
				if(check) {
					int postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
					AddLibraryToDatabase.addLibrary(firstName, phoneNumber, email, information, country, street, houseNumber, postalCodeInt, beginTime, endTime, checkBoxList);
				}
				else {
					System.out.println("Input is wrong");
				}
			}
			
		}
	}
	private static boolean isValid(String time) {
		String[] timeSplit = time.split(":");
		if(timeSplit.length != 2) {
			return false;
			
		}else if(timeSplit[0].length() > 2 || timeSplit[0].length() < 1 || timeSplit[1].length() != 2){
			return false;
			
		}
		
		boolean check = true;
		int checkHours = 0;
		int checkMin = 0;

		for(int i = 0; i < timeSplit.length; ++i) {
			try {
				if(i == 0) {
					checkHours = Integer.parseInt(timeSplit[i]);
				}else {
					checkMin = Integer.parseInt(timeSplit[i]);
				}
			}catch(Exception e) {
				check = false;
				break;
			}
		}
		if(!check) {
			return false;
			
		}else if(checkHours > 23 || checkHours < 0 || checkMin > 59 || checkMin < 0) {
			return false;
			
		}else {
			return true;
		}
	}
}
