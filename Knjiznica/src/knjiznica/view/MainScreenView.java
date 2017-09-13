package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.ViewProvider;

public class MainScreenView {
	
	@FXML
	private BorderPane mainScreen;
	
	@FXML
	private Button homeButton;
	
	public void initialize() throws IOException {
		Image imageHomeButton = new Image(getClass().getResourceAsStream("/resources/home-button.png"));
		homeButton.setGraphic(new ImageView(imageHomeButton));
		homeButton.setId("homeButton");
		BorderPane startScreen = (BorderPane) FXMLLoader.load(getClass().getResource("StartScreen-view.fxml"));
    	ViewProvider.setView("startScreen", startScreen);
    	ViewProvider.setView("homeButton", homeButton);
    	mainScreen.setCenter(startScreen);
	}
	
	public BorderPane getMainScreen() {
		return mainScreen;
	}
	
	@FXML
	private void activateHomeButoon() {
    	BorderPane home = (BorderPane) ViewProvider.getView("startScreen");
    	mainScreen.setCenter(home);
	}
}
