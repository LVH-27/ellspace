package knjiznica.view;
 
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import knjiznica.model.LoginThread;
import knjiznica.model.ViewProvider;


public class LoginView {
	
	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private BorderPane localRoot;
	
	public static boolean isCorrect = true;
	
	public static String username;
	
	public static String password;
	
	public void initialize() {
		
	}
	
	@FXML
	private void activateLogin() throws IOException {
		errorLabel.setVisible(false);
		username = usernameText.getText();
		password = passwordText.getText();
		
		LoginThread.login();

		if(isCorrect) {
			errorLabel.setVisible(false);
			BorderPane mainScreen = (BorderPane) FXMLLoader.load(
        			getClass().getResource("MainScreen-view.fxml"));
        	
        	ViewProvider.setView("mainScreen", mainScreen);
        	MainView root = (MainView) ViewProvider.getView("main");	
        	root.setBorderPane(mainScreen);
		}
		else {
			errorLabel.setText("Username or password is incorrect. Please try again.");
			errorLabel.setVisible(true);
			passwordText.setText("");
			
		}
			
    	
    	//root.getRoot().setCenter(mainScreen);
        	
        
//		System.out.println(username + '\t' + password);
//		System.out.println(Strings.getPassword());
		
        //progress bar?
        
		//error za no internet?  SQLTimeoutException?
		
	}
	
}
