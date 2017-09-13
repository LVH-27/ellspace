package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.postgresql.util.PSQLException;

public class UpdateAddress {

    public static void UpdateAddressAdd(String country, int postal, String street, String houseNum) throws SQLException, FileNotFoundException, PSQLException {
    	
        Connection con = null;
        PreparedStatement pstmt = null;
        
        String username = "postgres";
        
        System.out.println("Enter password:");
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        

        try {
			con = DriverManager.getConnection(
                      "jdbc:postgresql://localhost:5432/TestFill", username, password);
			  
		    String importPath = new File("").getAbsoluteFile().getParentFile().getAbsolutePath();

	        File file = new File(importPath + "\\PopulateTableEventTypeList.sql");
	        @SuppressWarnings("resource")
			Scanner sc = new Scanner(file);
	        while(sc.hasNextLine()) {
	        	try {
		        	pstmt = con.prepareStatement(sc.nextLine());
		        	pstmt.executeQuery();

				} catch (PSQLException duplicateKey) {
					System.out.println(duplicateKey);
					}
	        }
	        
//            pstmt = con.prepareStatement(
//                        "INSERT INTO \"Address\" " +
//                        "VALUES(DEFAULT, ?, ?, ?, ?)");
//            
//            pstmt.setString(1, country);
//            pstmt.setInt(2, postal);
//            pstmt.setString(3, street);
//            pstmt.setString(4, houseNum);
//            
//            pstmt.executeUpdate();

		}
        finally {
            if (pstmt != null) pstmt.close();
        }
    }
}