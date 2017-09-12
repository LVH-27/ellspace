package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import knjiznica.model.AddPublisherToDatabase;
import knjiznica.model.AlertWindowOpen;
import knjiznica.model.ErrorLabelMessage;
import knjiznica.model.FormattingName;
import knjiznica.model.PostalCodeComboThread;
import knjiznica.model.ViewProvider;

public class AddPublisherView { 
	
	@FXML
	private TextField nameField;
	
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
	
	@FXML
	private CheckBox addressCheck;
	
	@FXML
	private GridPane addressPane;
	
	public static boolean isInterrupted = false;
	public static boolean isReached = true;
	
	private String nameCombo = "postalCodeComboAddPublisher";
	private String publisherName;
	private String country;
	private String street;
	private String houseNumber;
	private String postalCode;
	private SingleSelectionModel<String> postalCodeSingle;
	private boolean check;
	
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
	private void toggleCheck() {
		if (addressCheck.isSelected()) {
			addressPane.setManaged(true);
			addressPane.setVisible(true);
		} else {
			addressPane.setManaged(false);
			addressPane.setVisible(false);
		}
	}
	
	@FXML
	private void activateBack() throws IOException {
		//TODO We need to return to AddBook but without resetting TextFields
//		BorderPane clientsMenu = (BorderPane) ViewProvider.getView("clientsMenu");
//		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(clientsMenu);
	}
	
	@FXML
	private void activateAdd() throws IOException {
		
		isInterrupted = false;
		isReached = true;
		
		errorLabel.setVisible(false);
		postalCodeSingle = postalCodeCombo.getSelectionModel();
		publisherName = nameField.getText();
		country = countryField.getText();
		street = streetField.getText();
		houseNumber = houseNumberField.getText();
		
		publisherName = publisherName.trim();
		country = country.trim();
		street = street.trim();
		houseNumber = houseNumber.trim();
		
		nameField.setStyle("");
		postalCodeCombo.setStyle("");
		countryField.setStyle("");
		streetField.setStyle("");
		houseNumberField.setStyle("");
		
		final String redBorder = "-fx-border-color: #ff0000;\n";
		
		check = true;
		
		
		if (publisherName.isEmpty()) {
			check = false;
			nameField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);	
		}
		
		if (postalCodeSingle.isEmpty() && addressCheck.isSelected()) {
			check = false;
			postalCodeCombo.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
		} 
		
		if (country.isEmpty() && addressCheck.isSelected()) {
			check = false;
			countryField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
		} 
		
		if (houseNumber.length() > 6) {
			check = false;
			houseNumberField.setStyle(redBorder);
			if (!errorLabel.isVisible()) {
				errorLabel.setText(ErrorLabelMessage.getWrongFormat());
				errorLabel.setVisible(true);
			}
		}
		
		if (check) {
			
			if (street.isEmpty()) {
				street = null;
			} 
			
			if (houseNumber.isEmpty()) {
				houseNumber = null;
			}
			
			postalCode = postalCodeSingle.getSelectedItem();
			publisherName = FormattingName.format(publisherName);
			
			errorLabel.setVisible(false);
			
			int postalCodeInt = -1;
			
			if(addressCheck.isSelected()) {
				postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
			}
			
			
			isInterrupted = false;
			isReached = true;
			
			AddPublisherToDatabase.addPublisher(publisherName, country, postalCodeInt, street, houseNumber, addressCheck.isSelected());
			
			if (!isInterrupted && isReached) {
				AlertWindowOpen.openWindow("Publisher successfully added!");
				
	    		//TODO We need to return to AddBook but without resetting TextFields, inserting this publisher
//				BorderPane addUser = (BorderPane) FXMLLoader.load(getClass().getResource("AddUser-view.fxml"));
//		    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addUser);
		    	
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
