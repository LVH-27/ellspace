package knjiznica.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class AddUsersPopup {
	
	@FXML
	private BorderPane addUsersPopup;
	
	@FXML
	private Button addUserButton;
	
	public void initialize() {
		Image imageAddUser = new Image(getClass().getResourceAsStream("../resources/addUser-button.png"));
		addUserButton.setGraphic(new ImageView(imageAddUser));
	}
}
