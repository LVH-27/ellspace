package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectBooks {
	
	private static ArrayList<Book> books;
	
	public static ArrayList<Book> select() {
		books = new ArrayList<Book>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String queryBook = "SELECT * FROM public.\"Book\"" + 
					"JOIN public.\"Edition\" ON \"Edition\".\"EditionID\"=\"Book\".\"EditionID\"" + 
					"JOIN public.\"IsbnLinks\" ON \"Book\".\"ISBN\"=\"IsbnLinks\".\"ISBN\"" +
					"JOIN public.\"Location\" ON \"Location\".\"LocationID\"=\"Book\".\"CurrentLocationID\"";
					/**"JOIN public.\"AuthorLinks\" ON \"AuthorLinks\".\"ISBN\"=\"IsbnLinks\".\"ISBN\"" + 
					"JOIN public.\"PublisherLinks\" ON \"PublisherLinks\".\"ISBN\"=\"IsbnLinks\".\"ISBN\"" + 
					"JOIN public.\"GenreLinks\" ON \"GenreLinks\".\"ISBN\"=\"IsbnLinks\".\"ISBN\"\r\n" + 
					"JOIN public.\"LanguageLinks\" ON \"LanguageLinks\".\"ISBN\"=\"IsbnLinks\".\"ISBN\"" + 
					"JOIN public.\"Author\" ON \"AuthorLinks\".\"AuthorID\"=\"Author\".\"AuthorID\"" + 
					"JOIN public.\"Publisher\" ON \"PublisherLinks\".\"PublisherID\"=\"Publisher\".\"PublisherID\"" + 
					"JOIN public.\"GenreList\" ON \"GenreLinks\".\"GenreID\"=\"GenreList\".\"GenreID\"" + 
					"JOIN public.\"LanguageList\" ON \"LanguageLinks\".\"LanguageID\"=\"LanguageList\".\"LanguageID\"" + 
					"JOIN public.\"Address\" ON \"Address\".\"AddressID\"=\"Publisher\".\"AddressID\"";**/
			
			stmt = con.createStatement();
			
			ResultSet rsBook = stmt.executeQuery(queryBook);
			
			ArrayList<Author> authors;
			ArrayList<Publisher> publishers;
			ArrayList<Language> languages;
			ArrayList<Genre> genres;
		
			while (rsBook.next()) {
				authors = new ArrayList<Author>();
				publishers = new ArrayList<Publisher>();
				languages = new ArrayList<Language>();
				genres = new ArrayList<Genre>();
				
				String query = "JOIN public.\"AuthorLinks\" ON \"AuthorLinks\".\"ISBN\"=?"
						+ "WHERE \"Book\".\"ISBN\"=?";
				PreparedStatement pstmtBook = con.prepareStatement(query);
				pstmtBook.setString(1, rsBook.getString("ISBN"));
				pstmtBook.setString(2, rsBook.getString("ISBN"));
				ResultSet rs = pstmtBook.executeQuery();
				while (rs.next()) {
					
					String middleName = "-";
					String yearOfBirth = "-";
					String yearOfDeath = "-"; 
					
					if (rs.getString("MiddleName") != null) {
						middleName = rs.getString("MiddleName");
					}
					if (rs.getString("yearOfBirth") != null) {
						yearOfBirth = rs.getString("yearOfBirth");
					}
					if (rs.getString("yearOfDeath") != null) {
						yearOfDeath = rs.getString("yearOfDeath");
					}
					authors.add(new Author(rs.getInt("AuthorID"), rs.getString("FirstName"), middleName, rs.getString("LastName"), rs.getBoolean("Alive"), yearOfBirth, yearOfDeath));
				}
				
				query = "JOIN public.\"PublisherLinks\" ON \"PublisherLinks\".\"ISBN\"=?"
						+ "WHERE \"Book\".\"ISBN\"=?";
				pstmtBook = con.prepareStatement(query);
				pstmtBook.setString(1, rsBook.getString("ISBN"));
				pstmtBook.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBook.executeQuery();
				while (rs.next()) {
					
					String street = "-";
					String houseNumber = "-"; 
					
					if (rs.getString("Street") != null) {
						street = rs.getString("Street");
					}
					if (rs.getString("HouseNumber") != null) {
						houseNumber = rs.getString("HouseNumber");
					}
					
					publishers.add(new Publisher(rs.getInt("PublisherID"), rs.getString("PublisherName"), rs.getString("Country"), Integer.toString(rs.getInt("PostalCode")), street, houseNumber, rs.getString("CityName"), rs.getInt("AddressID")));
				}
				
				query = "JOIN public.\"LanguageLinks\" ON \"LanguageLinks\".\"ISBN\"=?"
						+ "WHERE \"Book\".\"ISBN\"=?";
				pstmtBook = con.prepareStatement(query);
				pstmtBook.setString(1, rsBook.getString("ISBN"));
				pstmtBook.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBook.executeQuery();
				while (rs.next()) {
					languages.add(new Language(rs.getInt("LanguageID"), rs.getString("LanguageNameEN"), rs.getString("LanguageNameHR"), rs.getString("LanguageNameDE")));
				}
				
				query = "JOIN public.\"GenreLinks\" ON \"GenreLinks\".\"ISBN\"=?"
						+ "WHERE \"Book\".\"ISBN\"=?";
				pstmtBook = con.prepareStatement(query);
				pstmtBook.setString(1, rsBook.getString("ISBN"));
				pstmtBook.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBook.executeQuery();
				while (rs.next()) {
					genres.add(new Genre(rs.getInt("GenreID"), rs.getString("GenreNameEN"), rs.getString("GenreNameHR"), rs.getString("GenreNameDE")));
				}
				
				PreparedStatement pstmtLocation = null;
				Object currLocation;
				query = "SELECT * FROM \"public\".\"Location\" WHERE \"Location\".\"LocationID\"=?";
				pstmtLocation = con.prepareStatement(query);
				pstmtLocation.setInt(1, rsBook.getInt("LocationID"));
				ResultSet rsLocation = pstmtLocation.executeQuery();
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return books;
	}
	
	private Object getObject(ResultSet) {
		
		Object currLocation;
		
		if(rsBook.getInt("TypeID") == 1) {
			while (rsLocation.next()) {
				query = "SELECT * FROM \"public\".\"Library\" "
						+ "JOIN \"Address\" ON \"Address\".\"AddressID\"=\"Library\".\"AddressID\""
						+ "JOIN \"City\" ON \"City\".\"PostalCode\"=\"Address\".\"PostalCode\""
						+ "WHERE \"Library\".\'LIbraryID\"=?";
				PreparedStatement pstmtLibrary = con.prepareStatement(query);
				pstmtLibrary.setInt(1, rsLocation.getInt("LibraryID"));
				ResultSet rsLibrary = pstmtLibrary.executeQuery();
				while (rsLibrary.next()) {
					currLocation = new Library(rsLibrary.getInt("LibraryID"), rsLibrary.getString("LibraryName"), rsLibrary.getString("Country"), Integer.toString(rsLibrary.getInt("PostalCode")), rsLibrary.getString("Street"), rsLibrary.getString("HouseNumber"), rsLibrary.getString("PhoneNumber"), rsLibrary.getString("email"), rsLibrary.getString("Information"), rsLibrary.getString("City"), rsLibrary.getInt("AddressID"));
				}
			}
		} else {
			while (rsLocation.next()) {
				query = "SELECT * FROM \"public\".\"User\" "
						+ "JOIN \"Address\" ON \"Address\".\"AddressID\"=\"User\".\"AddressID\""
						+ "JOIN \"City\" ON \"City\".\"PostalCode\"=\"Address\".\"PostalCode\""
						+ "WHERE \"User\".\'UserID\"=?";
				PreparedStatement pstmtUser = con.prepareStatement(query);
				pstmtUser.setInt(1, rsLocation.getInt("UserID"));
				ResultSet rsUser = pstmtUser.executeQuery();
				while (rsUser.next()) {
					String middleName = "-";
					String street = "-";
					String houseNumber = "-"; 
					String phoneNumber = "-";
					
					if (rsUser.getString("MiddleName") != null) {
						middleName = rsUser.getString("MiddleName");
					}
					if (rsUser.getString("Street") != null) {
						street = rsUser.getString("Street");
					}
					if (rsUser.getString("HouseNumber") != null) {
						houseNumber = rsUser.getString("HouseNumber");
					}
					if (rsUser.getString("PhoneNumber") != null) {
						phoneNumber = rsUser.getString("PhoneNumber");
					}
					currLocation = new User(rsUser.getInt("UserID"), rsUser.getString("FirstName"), middleName, rsUser.getString("LastName"), rsUser.getString("Country"), rsUser.getInt("PostalCode"), street, houseNumber, phoneNumber, rsUser.getString("Email"), rsUser.getString("CityName"), rsUser.getInt("AddressID"));
				}
			}
		}
		return currLocation;
	}
}
