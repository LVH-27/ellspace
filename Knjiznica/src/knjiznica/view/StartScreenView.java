package knjiznica.view;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import knjiznica.model.Book;
import knjiznica.model.GlobalCollection;

public class StartScreenView {
	
	@FXML
	private BorderPane bp;
	
	@FXML
	private BorderPane bpp;
	
	@FXML 
	private TabPane tab;
	
	@FXML 
	private Tab tabb;
	
	@FXML 
	private Tab tabbb;
	
	@FXML
	private TableView<Book> list; 
	
	@FXML
	private TableColumn<Book, String> titleCol;
	
	@FXML
	private TableColumn<Book, String> authorCol;
	
	public void initialize() {
		//titleCol.setId("booklist");
		
		//authorCol.setId("booklist");
		tabb.setId("booklist");
		tabbb.setId("booklist");
		//list.setId("booklist");
	    //titleCol.setId("booklist");
		tab.setId("booklist");
		list.setItems(GlobalCollection.getList());
		titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
		
	}
}
