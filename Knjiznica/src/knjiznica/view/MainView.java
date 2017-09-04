package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import knjiznica.model.ViewProvider;

public class MainView {

	@FXML 
	private BorderPane root;
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private HBox menuBox;
	
	@FXML
	private Button homeButton;
	
	public void initialize() throws IOException {
		root.setId("booklist");
		menuBox.setManaged(false);
		Image imageHomeButton = new Image(getClass().getResourceAsStream("../resources/home-button.png"));
		homeButton.setGraphic(new ImageView(imageHomeButton));

		BorderPane main = (BorderPane) FXMLLoader.load(getClass().getResource("Login-view.fxml"));
		root.setCenter(main);
		ViewProvider.setView("main", this);
	}
	
	public BorderPane getRoot() {
		return root;
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}
	
	public HBox getMenuBox() {
		return menuBox;
	}
	
	@FXML
	private void activateHomeButoon() {
    	BorderPane home = (BorderPane) ViewProvider.getView("startScreen");
    	root.setCenter(home);
	}
	
}
