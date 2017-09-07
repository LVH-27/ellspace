package knjiznica;
	
import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import knjiznica.model.ViewProvider;
import knjiznica.view.MainView;
import javafx.scene.Group;
import javafx.scene.Scene;

public class KnjiznicaMain extends Application { 
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Group root = (Group) FXMLLoader.load(
					getClass().getResource("view/Main-view.fxml"));
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			Scene scene = new Scene(root,
					screenSize.getWidth() / 2, screenSize.getHeight() / 2);
			
			MainView main = (MainView) ViewProvider.getView("main");
			
			main.start();
			
			scene.getStylesheets().add(getClass().getResource(
					"resources/BackgroundStyle.css").toExternalForm());
			
			primaryStage.setTitle("ICM SoC 17 - Library");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		InitializeBooks();
	}
	
//	public void InitializeBooks(){
//		Book book = new Book("AAA", "BBB");
//		GlobalCollection.getList().add(book);
//	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
