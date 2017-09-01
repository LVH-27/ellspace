package knjiznica;
	
import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import knjiznica.model.Book;
import knjiznica.model.GlobalCollection;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class KnjiznicaMain extends Application { 
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(
					getClass().getResource("view/Main-view.fxml"));
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			Scene scene = new Scene(root,
					screenSize.getWidth() / 2, screenSize.getHeight() / 2);
	
			scene.getStylesheets().add(getClass().getResource(
					"resources/BackgroundStyle.css").toExternalForm());
			
			primaryStage.setTitle("Knjižnica");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		InitializeBooks();
	}
	
	public void InitializeBooks(){
		Book book = new Book("AAA", "BBB");
		GlobalCollection.getList().add(book);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
