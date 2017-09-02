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
import knjiznica.model.ViewProvider;
import knjiznica.resources.Strings;

public class LoginView {

	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	 
	@FXML
	private Label errorLabel;
		
	@FXML
	private BorderPane localRoot;
	
	public void initialize() {
		localRoot.setId("login");
	}
	
	@FXML
	private void activateLogin() throws IOException {
		String username = usernameText.getText();
		String password = passwordText.getText();
		
        try {
        	@SuppressWarnings("unused")
			Connection con = DriverManager.getConnection(
					Strings.getLink(), username, password);
        	Strings.setUsername(username);
        	Strings.setPassword(password);
        	BorderPane startScreen = (BorderPane) FXMLLoader.load(
        			getClass().getResource("StartScreen-view.fxml"));
        	MainView root = (MainView) ViewProvider.getView("main");
        	root.getRoot().setCenter(startScreen);
        	
        } catch (PSQLException e) {
			errorLabel.setVisible(true);
			passwordText.setText("");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		System.out.println(username + '\t' + password);
		System.out.println(Strings.getPassword());
		
		//errorLabel.setText("Successful login!");
		//errorLabel.setVisible(true);
		
		//error za no internet?
		
	}
	
}
