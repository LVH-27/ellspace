package knjiznica;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class KnjiznicaMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(
					getClass().getResource("view/Login-viewTEST.fxml"));
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource(
					"resources/BackgroundStyle.css").toExternalForm());
			primaryStage.setTitle("Knjižnica");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
