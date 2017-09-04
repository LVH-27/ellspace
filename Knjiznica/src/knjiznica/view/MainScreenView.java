package knjiznica.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import knjiznica.model.ViewProvider;

public class MainScreenView {
	
	@FXML
	private BorderPane mainScreen;
	
	public void initialize() throws IOException {
		mainScreen.setId("booklist");
		BorderPane startScreen = (BorderPane) FXMLLoader.load(
    			getClass().getResource("StartScreen-view.fxml"));
    	ViewProvider.setView("startScreen", startScreen);
    	mainScreen.setCenter(startScreen);
	}
	
	public BorderPane getMainScreen() {
		return mainScreen;
	}
}
