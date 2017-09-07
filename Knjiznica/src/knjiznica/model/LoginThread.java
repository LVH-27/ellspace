package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import knjiznica.resources.ConnectionData;


public class LoginThread implements Runnable {
	
	private static String username;
	
	private static String password;

	@Override
	public void run() {
    	try {
			@SuppressWarnings("unused")
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), username, password);
			ConnectionData.setUsername(username);
	    	ConnectionData.setPassword(password);
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			
		}   
		
	}
	
	public static void login(String usernameIn, String passwordIn) {
		
		username = usernameIn;
		password = passwordIn;
		
		(new Thread(new LoginThread())).start();
		
	}
}
