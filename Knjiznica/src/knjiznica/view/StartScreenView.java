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
	
	private MainView root = (MainView) ViewProvider.getView("main");
	
	@FXML
	private BorderPane startScreen;
	
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
		
		startScreen.setId("booklist");
		
		Image imageAddBook   = new Image(getClass().getResourceAsStream("../resources/add-book.png"));
		Image imageDirectory = new Image(getClass().getResourceAsStream("../resources/directory-icon.png"));
		Image imageSearch    = new Image(getClass().getResourceAsStream("../resources/search-icon.png"));
		Image imageSettings  = new Image(getClass().getResourceAsStream("../resources/settings-icon.png"));
		Image imageShare     = new Image(getClass().getResourceAsStream("../resources/sharebook-icon.png"));
		Image imageUsers     = new Image(getClass().getResourceAsStream("../resources/users-icon.png"));
		
		addBook.  setGraphic(new ImageView(imageAddBook));
		directory.setGraphic(new ImageView(imageDirectory));
		search.   setGraphic(new ImageView(imageSearch));
		settings. setGraphic(new ImageView(imageSettings));
		share.    setGraphic(new ImageView(imageShare));
		users.    setGraphic(new ImageView(imageUsers));
		
		BorderPane addBook   = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
		BorderPane directory = (BorderPane) FXMLLoader.load(getClass().getResource("Directory-view.fxml"));
		BorderPane search    = (BorderPane) FXMLLoader.load(getClass().getResource("Search-view.fxml"));
		BorderPane settings  = (BorderPane) FXMLLoader.load(getClass().getResource("Settings-view.fxml"));
		BorderPane share     = (BorderPane) FXMLLoader.load(getClass().getResource("Share-view.fxml"));
		BorderPane users     = (BorderPane) FXMLLoader.load(getClass().getResource("Users-view.fxml"));
		
		ViewProvider.setView("addBook", addBook);
		ViewProvider.setView("directory", directory);
		ViewProvider.setView("search", search);
		ViewProvider.setView("settings", settings);
		ViewProvider.setView("share", share);
		ViewProvider.setView("users", users);
		
	}
	
	@FXML
	private void activateAddBook() throws IOException {
		BorderPane addBook = (BorderPane) ViewProvider.getView("addBook");
    	root.getRoot().setCenter(addBook);

	}
	
	@FXML
	private void activateDirectory() throws IOException {
		BorderPane directory = (BorderPane) ViewProvider.getView("directory");
    	root.getRoot().setCenter(directory);

	}
	
	@FXML
	private void activateSearch() throws IOException {
		BorderPane search = (BorderPane) ViewProvider.getView("search");
    	root.getRoot().setCenter(search);

	}
	
	@FXML
	private void activateSettings() throws IOException {
		BorderPane settings = (BorderPane) ViewProvider.getView("settings");
    	root.getRoot().setCenter(settings);

	}
	
	@FXML
	private void activateShare() throws IOException {
		BorderPane share = (BorderPane) ViewProvider.getView("share");
    	root.getRoot().setCenter(share);

	}
	
	@FXML
	private void activateUsers() throws IOException {
		BorderPane users = (BorderPane) ViewProvider.getView("users");
    	root.getRoot().setCenter(users);

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
