package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import knjiznica.model.ViewProvider;

public class MainView {

	@FXML 
	private BorderPane main;
	
	public void initialize() throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Login-view.fxml"));
		main.setCenter(root);
		ViewProvider.setView("main", this);
	}

	public BorderPane getMain() {
		return main;
	}
}
