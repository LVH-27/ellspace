package knjiznica.view;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.util.PSQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import knjiznica.model.ViewProvider;
import knjiznica.resources.ConnectionData;

public class LoginView {

	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	 
	@FXML
	private Label errorLabel;
		
	@FXML
	private BorderPane localRoot;
	
	/*@FXML
	private StackPane stackPane;*/
	
	public void initialize() {
		localRoot.setId("login");
//		stackPane.setId("login");
	}
	
	@FXML
	private void activateLogin() throws IOException {
		String username = usernameText.getText();
		String password = passwordText.getText();
		
        try {
        	@SuppressWarnings("unused")
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), username, password);
        	ConnectionData.setUsername(username);
        	ConnectionData.setPassword(password);
        	BorderPane mainScreen = (BorderPane) FXMLLoader.load(
        			getClass().getResource("MainScreen-view.fxml"));
        
        	ViewProvider.setView("mainScreen", mainScreen);
        	MainView root = (MainView) ViewProvider.getView("main");
        	root.getRoot().setCenter(mainScreen);
        	
        } catch (PSQLException e) {
			errorLabel.setVisible(true);
			passwordText.setText("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
//		System.out.println(username + '\t' + password);
//		System.out.println(Strings.getPassword());
		
        //progress bar?
        
		//error za no internet?  SQLTimeoutException?
		
	}
	
}
