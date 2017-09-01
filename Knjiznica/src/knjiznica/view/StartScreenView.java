package knjiznica.view;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import knjiznica.model.Book;
import knjiznica.model.GlobalCollection;

public class StartScreenView {
	
	@FXML 
	private TabPane startScreenPane;
	
	@FXML 
	private Tab tabBookList;
	
	@FXML 
	private Tab tabAddBook;
	
	@FXML
	private TableView<Book> tableBookList;
	
	@FXML
	private TableColumn<Book, String> titleCol;
	
	@FXML
	private TableColumn<Book, String> authorCol;
	
	public void initialize() {
		startScreenPane.setId("booklist");
		tabBookList.setId("booklist");
		tabAddBook.setId("booklist");
		
		tableBookList.setItems(GlobalCollection.getList());
		titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		
	}
}
