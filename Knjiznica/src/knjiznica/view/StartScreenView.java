package knjiznica.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class StartScreenView {
	
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
	
	public void initialize() {
		startScreen.setId("booklist");
		Image imageAddBook   = new Image(getClass().getResourceAsStream("../resources/add-book.png"));
		Image imageDirectory = new Image(getClass().getResourceAsStream("../resources/directory-icon.png"));
		Image imageSearch    = new Image(getClass().getResourceAsStream("../resources/search-icon.png"));
		Image imageSettings  = new Image(getClass().getResourceAsStream("../resources/settings-icon.png"));
		Image imageShare     = new Image(getClass().getResourceAsStream("../resources/sharebook-icon.png"));
		Image imageUsers     = new Image(getClass().getResourceAsStream("../resources/users-icon.png"));
		
		addBook.setGraphic(new ImageView(imageAddBook));
		directory.setGraphic(new ImageView(imageDirectory));
		search.setGraphic(new ImageView(imageSearch));
		settings.setGraphic(new ImageView(imageSettings));
		share.setGraphic(new ImageView(imageShare));
		users.setGraphic(new ImageView(imageUsers));
		
		/*startScreenPane.setId("booklist");
		tabBookList.setId("booklist");
		tabAddBook.setId("addBookTab");
		tableBookList.setItems(GlobalCollection.getList());
		titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		titleCol.prefWidthProperty().bind(tableBookList.widthProperty().divide(2));
		authorCol.prefWidthProperty().bind(tableBookList.widthProperty().divide(2));*/
	}
}
