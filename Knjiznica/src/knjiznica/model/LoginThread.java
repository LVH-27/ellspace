package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;
import knjiznica.resources.ConnectionData;
import knjiznica.view.LoginView;



public class LoginThread implements Runnable {
	

	@Override
	public void run() {
    	try {
			@SuppressWarnings("unused")
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), LoginView.username, LoginView.password);
		
			ConnectionData.setUsername(LoginView.username);
	    	ConnectionData.setPassword(LoginView.password);
	    	LoginView.isCorrect = true;
	    	
	    	
		} catch (PSQLException e) {
			LoginView.isCorrect = false;
			
		} catch (SQLException e) {
			LoginView.isCorrect = false;
			
		} 
	}
	
	public static void login() {
		Thread t = new Thread(new LoginThread());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			LoginView.isCorrect = false;
			System.out.println(ErrorLabelMessage.getFailReach());
		}
	}
	
}
