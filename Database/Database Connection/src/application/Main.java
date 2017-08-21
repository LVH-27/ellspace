package application;
	
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
	}
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		System.out.println("Program started!");
		UpdateAddress.UpdateAddressAdd("Croatia", 10000, "Jordanovac", "8");
		System.out.println("Query executed!");
	}

}