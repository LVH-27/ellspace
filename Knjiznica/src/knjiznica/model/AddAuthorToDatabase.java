package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import knjiznica.resources.ConnectionData;

public class AddAuthorToDatabase implements Runnable{
	
	private static String firstName;
	
	private static String middleName;
	
	private static String lastName;
	
	private static boolean isAlive;
	
	private static String yearOfBirth;
	
	private static String yearOfDeath;
	
	@Override
	public void run() {
		
		PreparedStatement pstmtAuthor = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String queryAuthor = "INSERT INTO public.\"Author\" VALUES(DEFAULT, ?, ?, ?, ?, ?, ?)";
			
			pstmtAuthor = con.prepareStatement(queryAuthor);
			
			if (middleName.isEmpty()) {
				middleName = null;
			}
			else {
				middleName = FormattingName.format(middleName);
			}
			
			firstName = FormattingName.format(firstName);
			lastName = FormattingName.format(lastName);
			
			pstmtAuthor.setString(1, firstName);
			pstmtAuthor.setString(2, middleName);
			pstmtAuthor.setString(3, lastName);
			pstmtAuthor.setBoolean(4, isAlive);
			pstmtAuthor.setString(5, yearOfBirth);
			pstmtAuthor.setString(6, yearOfDeath);
			
			pstmtAuthor.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addAuthor(String firstNameIn, String middleNameIn, String lastNameIn, boolean isAliveIn, String yearOfBirthIn, String yearOfDeathIn) {
		
		firstName = firstNameIn;
		middleName = middleNameIn;
		lastName = lastNameIn;
		isAlive = isAliveIn;
		yearOfBirth = yearOfBirthIn;
		yearOfDeath = yearOfDeathIn;
		
		(new Thread(new AddAuthorToDatabase())).start();
	}
}
