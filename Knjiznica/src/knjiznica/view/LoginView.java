package knjiznica.view;
 
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.postgresql.util.PSQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
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
	
	public void login() {

	}
	
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
        	Strings.setPassword(password);
        	TabPane root = (TabPane) FXMLLoader.load(getClass().getResource("StartScreen-view.fxml"));
        	MainView mv = (MainView) ViewProvider.getView("main");
        	mv.getMain().setCenter(root);
        	
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
		
		//close window / view

		
	}
	
}
