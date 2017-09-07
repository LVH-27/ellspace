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
	
	public void initialize() {
		localRoot.setId("login");
	}
	
	@FXML
	private void activateLogin() throws IOException {
		String username = usernameText.getText();
		String password = passwordText.getText();
		
    	LoginThread.login(username, password);
    	BorderPane mainScreen = (BorderPane) FXMLLoader.load(
    			getClass().getResource("MainScreen-view.fxml"));
    	
    	ViewProvider.setView("mainScreen", mainScreen);
    	MainView root = (MainView) ViewProvider.getView("main");
    	root.getRoot().setCenter(mainScreen);   	
        	
        
//		System.out.println(username + '\t' + password);
//		System.out.println(Strings.getPassword());
		
        //progress bar?
        
		//error za no internet?  SQLTimeoutException?
		
	}
	
}
