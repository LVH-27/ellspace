package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.GlobalCollection;
import knjiznica.model.ViewProvider;

public class ClientsMenuView {
	
	@FXML
	private Button addUserButton;
	
	@FXML
	private Button listUsersButton;
	
	@FXML
	private Button addLibraryButton;
	
	@FXML
	private Button listLibrariesButton;
	
	public void initialize() {
		
		addUserButton.      setId("transparentButton"); 
		listUsersButton.    setId("transparentButton");
		addLibraryButton.   setId("transparentButton");
		listLibrariesButton.setId("transparentButton");
		
		//TODO Find images (names specified) and uncomment 6 lines below.
		
		Image imageAddUser       = new Image(getClass().getResourceAsStream("../resources/addUser-button.png"));
//		Image imageListUsers     = new Image(getClass().getResourceAsStream("../resources/listUsers-button.png"));
//		Image imageAddLibrary    = new Image(getClass().getResourceAsStream("../resources/addLibrary-button.png"));
//		Image imageListLibraries = new Image(getClass().getResourceAsStream("../resources/listLibraries-button.png"));

		addUserButton.      setGraphic(new ImageView(imageAddUser));
//		listUsersButton.    setGraphic(new ImageView(imageListUsers));
//		addLibraryButton.   setGraphic(new ImageView(imageAddLibrary));
//		listLibrariesButton.setGraphic(new ImageView(imageListLibraries));
	}
	
	@FXML
	private void activateAddUser() throws IOException {
		BorderPane addUser = (BorderPane) FXMLLoader.load(getClass().getResource("AddUser-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addUser);
	}
	
	@FXML
	private void activateListUsers() throws IOException {
		BorderPane listUsers = (BorderPane) FXMLLoader.load(getClass().getResource("ListUsers-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(listUsers);
	}
	
	@FXML
	private void activateAddLibrary() throws IOException {
		BorderPane addLibrary = (BorderPane) FXMLLoader.load(getClass().getResource("AddLibrary-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addLibrary);
	}
	
	@FXML
	private void activateListLibraries() throws IOException {
		BorderPane listLibraries = (BorderPane) FXMLLoader.load(getClass().getResource("ListLibraries-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(listLibraries);
	}
}
