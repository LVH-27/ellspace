package knjiznica.view;
 
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.controlsfx.control.MaskerPane;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import knjiznica.model.ErrorLabelMessage;
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
	private static StackPane sp = (StackPane) ViewProvider.getView("stackPane");
	private static MaskerPane mask = (MaskerPane) ViewProvider.getView("mask");
	private static Executor exec;
	
	public void initialize() {
		loginButton.setId("loginButton");
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
	}
	
	@FXML
	private void activateLogin() throws IOException {
		
		errorLabel.setVisible(false);
		sp.getChildren().add(mask);
		Task<Void> loginTask = new Task<Void>() {
            @Override
            public Void call() throws Exception {
            	
    			Thread.sleep(600);
    			
    			username = usernameText.getText();
    			password = passwordText.getText();
    			
    			LoginThread.login();
    			
    			return null;  
            }
		};
		
		loginTask.setOnSucceeded(e -> {
			try {
				afterThreadFinishes();
				
			} catch (IOException e1) {
					e1.printStackTrace();
			}
		});
		loginTask.setOnFailed(e -> {
			afterThreadFails();
	    });

		exec.execute(loginTask);

		
	}
	
	private void afterThreadFinishes() throws IOException {
		sp.getChildren().remove(mask);
		if (isCorrect) {
			errorLabel.setVisible(false);
			
			MainView root = (MainView) ViewProvider.getView("main");	
			
			Image backgroundImage = new Image(getClass().getResourceAsStream("/resources/booklist-background.jpg"));
			root.setBackground(backgroundImage);	
			
			BorderPane mainScreen = (BorderPane) FXMLLoader.load(getClass().getResource("MainScreen-view.fxml"));
			root.setBorderPane(mainScreen);
        	
        	ViewProvider.setView("mainScreen", mainScreen);
		}
		
		else {
			errorLabel.setText("Username or password is incorrect. Please try again.");
			errorLabel.setVisible(true);
			passwordText.setText("");
		}
		//TODO SQLTimeoutException ?
	}
	
	private void afterThreadFails() {
		sp.getChildren().remove(mask);
		errorLabel.setText(ErrorLabelMessage.getSomething());
		errorLabel.setVisible(true);
	}
	
}
