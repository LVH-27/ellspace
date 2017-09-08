package knjiznica.view;
 
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import knjiznica.model.LoginThread;
import knjiznica.model.ViewProvider;


public class LoginView {
	
	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private BorderPane localRoot;
	
	public static boolean isCorrect = true;
	
	public static String username;
	
	public static String password;
	
	public void initialize() {
		loginButton.setId("loginButton");
	}
	
	@FXML
	private void activateLogin() throws IOException {
		errorLabel.setVisible(false);
		username = usernameText.getText();
		password = passwordText.getText();
		
		LoginThread.login();
		
		// REMOVE !!!!
		//isCorrect = true;
		// REMOVE !!!

		if(isCorrect) {
			errorLabel.setVisible(false);
			
			MainView root = (MainView) ViewProvider.getView("main");	
			
			Image backgroundImage = new Image(getClass().getResourceAsStream("../resources/booklist-background.jpg"));
			root.setBackground(backgroundImage);	
			
			BorderPane mainScreen = (BorderPane) FXMLLoader.load(
        			getClass().getResource("MainScreen-view.fxml"));
			root.setBorderPane(mainScreen);
        	
        	ViewProvider.setView("mainScreen", mainScreen);
        	
        	
		}
		else {
			errorLabel.setText("Username or password is incorrect. Please try again.");
			errorLabel.setVisible(true);
			passwordText.setText("");
			
		}

        	
        
//		System.out.println(username + '\t' + password);
//		System.out.println(Strings.getPassword());
		
        //progress bar?
        
		//error za no internet?  SQLTimeoutException?
		
	}
	
}
