package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

import knjiznica.view.LoginView;

public class LoginThread {

	public static void login() {
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
}
