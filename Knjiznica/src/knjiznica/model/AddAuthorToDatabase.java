package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.util.PSQLException;

import knjiznica.view.AddAuthorView;

public class AddAuthorToDatabase implements Runnable{
	
	private static String firstName;
	private static String middleName;
	private static String lastName;
	private static boolean isAlive;
	private static String yearOfBirth;
	private static String yearOfDeath;
	
	@Override
	public void run() {
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			InsertNewAuthor.insert(con, firstName, middleName, lastName, isAlive, yearOfBirth, yearOfDeath);
			
		} catch (PSQLException e) {
			e.printStackTrace();
			AddAuthorView.isReached = false;
			
		} catch (SQLException e) {
			AddAuthorView.isReached = false;
		} 
	}
	
	public static void addAuthor(String firstNameIn, String middleNameIn, String lastNameIn, boolean isAliveIn, String yearOfBirthIn, String yearOfDeathIn) {
		
		firstName = firstNameIn;
		middleName = middleNameIn;
		lastName = lastNameIn;
		isAlive = isAliveIn;
		yearOfBirth = yearOfBirthIn;
		yearOfDeath = yearOfDeathIn;
		
		Thread t = new Thread(new AddAuthorToDatabase());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			AddAuthorView.isInterrupted = true;
		}
	}
}
