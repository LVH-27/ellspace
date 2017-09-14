package knjiznica.view;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
	private HBox ownerClearHBox;
	
	@FXML
	private Button ownerClearButton;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Label errorLabel;
	
	public void initialize() throws SQLException {
		Image imageAddButton = new Image(getClass().getResourceAsStream("/resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("transparentButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("/resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("transparentButton");
	}
	
	@FXML
	private void activateOwnerAddUser() {
		// TODO Auto-generated method stub

	}
	
	@FXML
	private void activateOwnerAddLibrary() {
		// TODO Auto-generated method stub

	}
	
	@FXML
	private void activateOwnerClear() {
		// TODO Auto-generated method stub

	}
	
	@FXML
	private void activateBack() throws IOException {
		BorderPane clientsMenu = (BorderPane) ViewProvider.getView("clientsMenu");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(clientsMenu);
	}
	
	@FXML
	private void activateAdd() {
		//TODO Replace "\n" with spaces, but not if there is also a space next to "\n".
	}
}
