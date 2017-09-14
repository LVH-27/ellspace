package knjiznica;
	
import org.controlsfx.control.MaskerPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import knjiznica.model.ViewProvider;
import knjiznica.view.MainView;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class KnjiznicaMain extends Application { 
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			primaryStage.hide();

			primaryStage.setMaximized(true);
			
			
			ViewProvider.setView("primaryStage", primaryStage);
			
			StackPane root = (StackPane) FXMLLoader.load(
					getClass().getResource("view/Main-view.fxml"));
			//FIXME Screen flashes during loading and switching to maximized window.
			//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Scene scene = new Scene(root);
					//screenSize.getWidth(), screenSize.getHeight());
			
			MaskerPane mask = new MaskerPane();
			ViewProvider.setView("mask", mask);
			ViewProvider.setView("stackPane", root);
			
			MainView main = (MainView) ViewProvider.getView("main");
			
			main.start();
			
			scene.getStylesheets().add(getClass().getResource(
					"../resources/BackgroundStyle.css").toExternalForm());
			
			

			primaryStage.setTitle("ICM SoC 17 - Library");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	//FIXME Close all statements
	public static void main(String[] args) {
		launch(args);
	}
}
