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
	private Button addBook;
	
	@FXML
	private Button clients;
	
	@FXML
	private Button share;
	
	@FXML
	private Button directory;
	
	@FXML
	private Button search;
	
	@FXML
	private Button eventLog;
	
	public void initialize() throws IOException {
		
		addBook.  setId("transparentButton");
		clients.  setId("transparentButton");
		share.    setId("transparentButton");
		directory.setId("transparentButton");
		search.   setId("transparentButton");
		eventLog. setId("transparentButton");
		
		Image imageAddBook   = new Image(getClass().getResourceAsStream("../resources/addBook-button.png"));
		Image imageClients   = new Image(getClass().getResourceAsStream("../resources/clients-button.png"));
		Image imageShare     = new Image(getClass().getResourceAsStream("../resources/sharebook-button.png"));
		Image imageDirectory = new Image(getClass().getResourceAsStream("../resources/directory-button.png"));
		Image imageSearch    = new Image(getClass().getResourceAsStream("../resources/search-button.png"));
		Image imageEventLog  = new Image(getClass().getResourceAsStream("../resources/log-button.png"));
		
		addBook.  setGraphic(new ImageView(imageAddBook));
		clients.  setGraphic(new ImageView(imageClients));
		share.    setGraphic(new ImageView(imageShare));
		directory.setGraphic(new ImageView(imageDirectory));
		search.   setGraphic(new ImageView(imageSearch));
		eventLog. setGraphic(new ImageView(imageEventLog));
		
		BorderPane clientsMenu = (BorderPane) FXMLLoader.load(getClass().getResource("ClientsMenu-view.fxml"));
		BorderPane share       = (BorderPane) FXMLLoader.load(getClass().getResource("Share-view.fxml"));
		BorderPane directory   = (BorderPane) FXMLLoader.load(getClass().getResource("Directory-view.fxml"));
		BorderPane search      = (BorderPane) FXMLLoader.load(getClass().getResource("Search-view.fxml"));
		BorderPane eventLog    = (BorderPane) FXMLLoader.load(getClass().getResource("EventLog-view.fxml"));
		
		ViewProvider.setView("clientsMenu", clientsMenu);
		ViewProvider.setView("share", share);
		ViewProvider.setView("directory", directory);
		ViewProvider.setView("search", search);
		ViewProvider.setView("eventLog", eventLog);
	}
	
	@FXML
	private void activateAddBook() throws IOException {
		//TODO Change loaded FXML back to AddBook-view.fxml
		BorderPane addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddPublisher-view.fxml"));
    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
	}
	
	@FXML
	private void activateClientsMenu() throws IOException {
		BorderPane clientsMenu = (BorderPane) ViewProvider.getView("clientsMenu");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(clientsMenu);
	}
	
	@FXML
	private void activateShare() throws IOException {
		BorderPane share = (BorderPane) ViewProvider.getView("share");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(share);
	}
	
	@FXML
	private void activateDirectory() throws IOException {
		BorderPane directory = (BorderPane) ViewProvider.getView("directory");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(directory);
	}
	
	@FXML
	private void activateSearch() throws IOException {
		//TODO search replaced with AddAuthorTable 
		BorderPane search = (BorderPane) FXMLLoader.load(getClass().getResource("AddAuthorTable-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(search);
	}
	
	@FXML
	private void activateEventLog() throws IOException {
		BorderPane eventLog = (BorderPane) ViewProvider.getView("eventLog");
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(eventLog);
	}
}
