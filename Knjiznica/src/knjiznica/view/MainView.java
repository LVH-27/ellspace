package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import knjiznica.model.ViewProvider;

public class MainView {
	
	@FXML 
	private BorderPane root;
	
	@FXML
	private MenuBar menuBar;
	
	public void initialize() throws IOException {
		root.setId("booklist");		
		BorderPane main = (BorderPane) FXMLLoader.load(getClass().getResource("Login-view.fxml"));
		root.setCenter(main);
		ViewProvider.setView("main", this);
	}
	
	public BorderPane getRoot() {
		return root;
	}
	
}
