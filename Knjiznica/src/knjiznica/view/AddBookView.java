package knjiznica.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import knjiznica.model.GlobalCollection;
import knjiznica.model.ViewProvider;

public class AddBookView {
	
	@FXML
	private TextField isbnField;
	
	@FXML
	private TextField titleField;
	
	@FXML
	private TextArea summaryArea;
	
	@FXML
	private TextArea informationArea;
	
	@FXML
	private TextField editionNumberField;
	
	@FXML
	private TextField publicationYearField;
	
	@FXML
	private TextField numberOfPagesField;
	
	@FXML
	private StackPane ownerStackPane;
	
	@FXML
	private HBox ownerButtonsHBox;
	
	@FXML
	private HBox ownerLabelHBox;
	
	@FXML
	private Button ownerUserButton;
	
	@FXML
	private Button ownerLibraryButton;
	
	@FXML
	private Label ownerNameLabel;
	
	@FXML
	private Button ownerClearButton;
	
	@FXML
	private Button authorsEditButton;
	
	@FXML
	private GridPane authorsListGrid;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label errorLabel;
	
	public void initialize() throws SQLException {
		setText();
		if(GlobalCollection.getAddedUsers().size() > 0) {
			ownerButtonsHBox.setVisible(false);
			ownerLabelHBox.setVisible(true);
			String middleName = "";
			if(!GlobalCollection.getAddedUsers().get(0).getMiddleName().equals("-")) {
				middleName = GlobalCollection.getAddedUsers().get(0).getMiddleName() + " ";
			}
			ownerNameLabel.setText(GlobalCollection.getAddedUsers().get(0).getFirstName() + " " + middleName + GlobalCollection.getAddedUsers().get(0).getLastName());
			
		} else if(GlobalCollection.getAddedLibraries().size() > 0) {
			ownerButtonsHBox.setVisible(false);
			ownerLabelHBox.setVisible(true);
			ownerNameLabel.setText(GlobalCollection.getAddedLibraries().get(0).getFirstName());
			
		} else {
			ownerButtonsHBox.setVisible(true);
			ownerLabelHBox.setVisible(false);
			ownerNameLabel.setText("");
		}
		Image imageAddButton = new Image(getClass().getResourceAsStream("/resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("transparentButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("/resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("transparentButton");
		
		Image imageOwnerUserButton = new Image(getClass().getResourceAsStream("/resources/addUser-button-small.png"));
		ownerUserButton.setGraphic(new ImageView(imageOwnerUserButton));
		ownerUserButton.setId("smallButton");
		
		Image imageOwnerLibraryButton = new Image(getClass().getResourceAsStream("/resources/addLibrary-button-small.png"));
		ownerLibraryButton.setGraphic(new ImageView(imageOwnerLibraryButton));
		ownerLibraryButton.setId("smallButton");
		
		Image imageOwnerClearButton = new Image(getClass().getResourceAsStream("/resources/remove-button.png"));
		ownerClearButton.setGraphic(new ImageView(imageOwnerClearButton));
		ownerClearButton.setId("smallButton");
	}
	
	@FXML
	private void activateOwnerAddUser() throws IOException {
		getText();
		GlobalCollection.setPotentialOwner(true);
		BorderPane listUsers = (BorderPane) FXMLLoader.load(getClass().getResource("ListUsers-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(listUsers);

	}
	
	private void setText() {
		isbnField.setText(GlobalCollection.getISBN());
		titleField.setText(GlobalCollection.getTitle());
		summaryArea.setText(GlobalCollection.getSummary());
		informationArea.setText(GlobalCollection.getBookInfo());
		editionNumberField.setText(GlobalCollection.getEdition());
		publicationYearField.setText(GlobalCollection.getPublictionYear());
		numberOfPagesField.setText(GlobalCollection.getNumberOfPages());
	}
	
	private void getText() {
		GlobalCollection.setISBN(isbnField.getText());
		GlobalCollection.setTitle(titleField.getText());
		GlobalCollection.setSummary(summaryArea.getText());
		GlobalCollection.setBookInfo(informationArea.getText());
		GlobalCollection.setEdition(editionNumberField.getText());
		GlobalCollection.setPublictionYear(publicationYearField.getText());
		GlobalCollection.setNumberOfPages(numberOfPagesField.getText());
	}
	
	@FXML
	private void activateOwnerAddLibrary() throws IOException {
		getText();
		GlobalCollection.setPotentialOwner(true);
		BorderPane listLibraries = (BorderPane) FXMLLoader.load(getClass().getResource("ListLibraries-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(listLibraries);

	}
	
	@FXML
	private void activateOwnerClear() throws IOException {
		getText();
		GlobalCollection.emptyAddedUsersList();
		GlobalCollection.emptyAddedLibrariesList();
		BorderPane addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);

	}
	
	@FXML
	private void activateAuthorsEditButton() {
		// TODO Auto-generated method stub

	}
	
	@FXML
	private void activateBack() throws IOException {
		BorderPane startScreen = (BorderPane) ViewProvider.getView("startScreen");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(startScreen);
	}
	
	@FXML
	private void activateAdd() {
		//TODO Replace "\n" with spaces, but not if there is also a space next to "\n".
	}
}
