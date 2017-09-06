package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.ViewProvider;

public class StartScreenView {
		
	@FXML
	private BorderPane startScreen;
	
	@FXML
	private BorderPane startScreenPopup;
	
	@FXML
	private Button addBook;
	
	@FXML
	private Button directory;
	
	@FXML
	private Button search;
	
	@FXML
	private Button eventLog;
	
	@FXML
	private Button share;
	
	@FXML
	private Button users;
	
	public void initialize() throws IOException {
		
		Image imageAddBook   = new Image(getClass().getResourceAsStream("../resources/addBook-button.png"));
		Image imageDirectory = new Image(getClass().getResourceAsStream("../resources/directory-button.png"));
		Image imageSearch    = new Image(getClass().getResourceAsStream("../resources/search-button.png"));
		Image imageEventLog  = new Image(getClass().getResourceAsStream("../resources/log-button.png"));
		Image imageShare     = new Image(getClass().getResourceAsStream("../resources/sharebook-button.png"));
		Image imageUsers     = new Image(getClass().getResourceAsStream("../resources/users-button.png"));
		
		addBook.  setGraphic(new ImageView(imageAddBook));
		directory.setGraphic(new ImageView(imageDirectory));
		search.   setGraphic(new ImageView(imageSearch));
		eventLog. setGraphic(new ImageView(imageEventLog));
		share.    setGraphic(new ImageView(imageShare));
		users.    setGraphic(new ImageView(imageUsers));
		
		BorderPane directory  = (BorderPane) FXMLLoader.load(getClass().getResource("Directory-view.fxml"));
		BorderPane search     = (BorderPane) FXMLLoader.load(getClass().getResource("Search-view.fxml"));
		BorderPane eventLog   = (BorderPane) FXMLLoader.load(getClass().getResource("EventLog-view.fxml"));
		BorderPane share      = (BorderPane) FXMLLoader.load(getClass().getResource("Share-view.fxml"));
		BorderPane usersPopup = (BorderPane) FXMLLoader.load(getClass().getResource("AddUsersPopup-view.fxml"));
		BorderPane users      = (BorderPane) FXMLLoader.load(getClass().getResource("Users-view.fxml"));
		
		ViewProvider.setView("directory", directory);
		ViewProvider.setView("search", search);
		ViewProvider.setView("eventLog", eventLog);
		ViewProvider.setView("share", share);
		ViewProvider.setView("usersPopup", usersPopup);
		ViewProvider.setView("users", users);
	}
	
	@FXML
	private void activateAddBook() throws IOException {
		BorderPane addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddAuthor-view.fxml"));///////////////////////////////////////////////
    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
	}
	
	@FXML
	private void activateDirectory() throws IOException {
		BorderPane directory = (BorderPane) ViewProvider.getView("directory");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(directory);
	}
	
	@FXML
	private void activateSearch() throws IOException {
		BorderPane search = (BorderPane) ViewProvider.getView("search");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(search);
	}
	
	@FXML
	private void activateEventLog() throws IOException {
		BorderPane eventLog = (BorderPane) ViewProvider.getView("eventLog");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(eventLog);
	}
	
	@FXML
	private void activateShare() throws IOException {
		BorderPane share = (BorderPane) ViewProvider.getView("share");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(share);
	}
	
	@FXML
	private void activateUsersPopup() throws IOException {
		BorderPane usersPopup = (BorderPane) ViewProvider.getView("usersPopup");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(usersPopup);
	}
	
	/*startScreenPane.setId("booklist");
	tabBookList.setId("booklist");
	tabAddBook.setId("addBookTab");
	tableBookList.setItems(GlobalCollection.getList());
	titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
	authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
	titleCol.prefWidthProperty().bind(tableBookList.widthProperty().divide(2));
	authorCol.prefWidthProperty().bind(tableBookList.widthProperty().divide(2));*/

}
