package knjiznica.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class LoginView {

	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	
	@FXML
	private Label errorLabel;
		
	@FXML 
	private BorderPane root;
	
	public void login() {

	}
	
	public void initialize() {
		root.setId("pane");
	}
	
	@FXML
	private void activateLogin() {
		String username = usernameText.getText();
		String password = passwordText.getText();
		
		System.out.println(username + '\t' + password);
		
		errorLabel.setText("Successful login!");
		errorLabel.setVisible(true);
		
		//clear passwordText ako faila
		
		//close window / view
		
	}
	
}
