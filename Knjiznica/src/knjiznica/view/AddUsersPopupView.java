package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.ViewProvider;

public class AddUsersPopupView {
	
	@FXML
	private BorderPane addUsersPopup;
	
	@FXML
	private Button addUserButton;
	
	public void initialize() {
		Image imageAddUser = new Image(getClass().getResourceAsStream("../resources/addUser-button.png"));
		addUserButton.setGraphic(new ImageView(imageAddUser));
	}
	
	@FXML
	private void activateAddUser() throws IOException {
		BorderPane addUser    = (BorderPane) FXMLLoader.load(getClass().getResource("AddUser-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addUser);
	}
}
