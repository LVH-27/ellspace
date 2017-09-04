package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import knjiznica.model.ViewProvider;

public class StartScreenView {
	
	private MainScreenView mainScreen;
	
	private MainView root = (MainView) ViewProvider.getView("main");
	
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
	private Button settings;
	
	@FXML
	private Button share;
	
	@FXML
	private Button users;
	
	public void initialize() throws IOException {
		Image imageAddBook   = new Image(getClass().getResourceAsStream("../resources/addBook-button.png"));
		Image imageDirectory = new Image(getClass().getResourceAsStream("../resources/directory-button.png"));
		Image imageSearch    = new Image(getClass().getResourceAsStream("../resources/search-button.png"));
		Image imageSettings  = new Image(getClass().getResourceAsStream("../resources/settings-button.png"));
		Image imageShare     = new Image(getClass().getResourceAsStream("../resources/sharebook-button.png"));
		Image imageUsers     = new Image(getClass().getResourceAsStream("../resources/users-button.png"));
		
		addBook.  setGraphic(new ImageView(imageAddBook));
		directory.setGraphic(new ImageView(imageDirectory));
		search.   setGraphic(new ImageView(imageSearch));
		settings. setGraphic(new ImageView(imageSettings));
		share.    setGraphic(new ImageView(imageShare));
		users.    setGraphic(new ImageView(imageUsers));
		
		BorderPane addBook    = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
		BorderPane directory  = (BorderPane) FXMLLoader.load(getClass().getResource("Directory-view.fxml"));
		BorderPane search     = (BorderPane) FXMLLoader.load(getClass().getResource("Search-view.fxml"));
		BorderPane settings   = (BorderPane) FXMLLoader.load(getClass().getResource("Settings-view.fxml"));
		BorderPane share      = (BorderPane) FXMLLoader.load(getClass().getResource("Share-view.fxml"));
		BorderPane usersPopup = (BorderPane) FXMLLoader.load(getClass().getResource("AddUsersPopup-view.fxml"));
		BorderPane users      = (BorderPane) FXMLLoader.load(getClass().getResource("Users-view.fxml"));
		
		ViewProvider.setView("addBook", addBook);
		ViewProvider.setView("directory", directory);
		ViewProvider.setView("search", search);
		ViewProvider.setView("settings", settings);
		ViewProvider.setView("share", share);
		ViewProvider.setView("usersPopup", usersPopup);
		ViewProvider.setView("users", users);
		
	}
	
	@FXML
	private void activateAddBook() throws IOException {
		//MainScreenView mainScreen = (MainScreenView) ViewProvider.getView("mainScreen");
		BorderPane addBook = (BorderPane) ViewProvider.getView("addBook");
    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);

	}
	
	@FXML
	private void activateDirectory() throws IOException {
		BorderPane directory = (BorderPane) ViewProvider.getView("directory");
		//BorderPane proba = mainScreen.getMainScreen();
    	//mainScreen.getMainScreen().setCenter(null);

	}
	
	@FXML
	private void activateSearch() throws IOException {
		mainScreen = (MainScreenView) ViewProvider.getView("mainScreen");
		BorderPane search = (BorderPane) ViewProvider.getView("search");
		System.out.println(mainScreen);
		System.out.println(search);
		//System.out.println(mainScreen);
    	//mainScreen.getMainScreen().setCenter(search);

	}
	
	@FXML
	private void activateSettings() throws IOException {
		BorderPane settings = (BorderPane) ViewProvider.getView("settings");
    	//mainScreen.getMainScreen().setCenter(settings);

	}
	
	@FXML
	private void activateShare() throws IOException {
		BorderPane share = (BorderPane) ViewProvider.getView("share");
    	//mainScreen.getMainScreen().setCenter(share);

	}
	
	@FXML
	private void activateUsersPopup() throws IOException {
		BorderPane usersPopup = (BorderPane) ViewProvider.getView("usersPopup");
    	//mainScreen.getMainScreen().setCenter(usersPopup);

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
