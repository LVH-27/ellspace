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
					"JOIN public.\"IsbnLinks\" ON \"Book\".\"ISBN\"=\"IsbnLinks\".\"ISBN\"";
				//	"JOIN public.\"Location\" ON \"Location\".\"LocationID\"=\"Book\".\"CurrentLocationID\"";
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
				
				PreparedStatement pstmtBookPublisher;
				PreparedStatement pstmtBookLanguage;
				PreparedStatement pstmtBookGenre;
				
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
				pstmtBookPublisher = con.prepareStatement(query);
				pstmtBookPublisher.setString(1, rsBook.getString("ISBN"));
				pstmtBookPublisher.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBookPublisher.executeQuery();
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
				pstmtBookLanguage = con.prepareStatement(query);
				pstmtBookLanguage.setString(1, rsBook.getString("ISBN"));
				pstmtBookLanguage.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBookLanguage.executeQuery();
				while (rs.next()) {
					languages.add(new Language(rs.getInt("LanguageID"), rs.getString("LanguageNameEN"), rs.getString("LanguageNameHR"), rs.getString("LanguageNameDE")));
				}
				
				query = "JOIN public.\"GenreLinks\" ON \"GenreLinks\".\"ISBN\"=?"
						+ "WHERE \"Book\".\"ISBN\"=?";
				pstmtBookGenre = con.prepareStatement(query);
				pstmtBookGenre.setString(1, rsBook.getString("ISBN"));
				pstmtBookGenre.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBookGenre.executeQuery();
				while (rs.next()) {
					genres.add(new Genre(rs.getInt("GenreID"), rs.getString("GenreNameEN"), rs.getString("GenreNameHR"), rs.getString("GenreNameDE")));
				}
				
				PreparedStatement pstmtLocation = null;
				PreparedStatement pstmtOwner = null;
				
				Object currLocation;
				query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN \"public\".\"Location\" ON \"Book\".\"CurrentLocationID\"=\"Location\".\"LocationID\""
						+ "WHERE \"Book\".\"BookID\"=?";
				pstmtLocation = con.prepareStatement(query);
				pstmtLocation.setInt(1, rsBook.getInt("BookID"));
				ResultSet rsLocation = pstmtLocation.executeQuery();
				currLocation = getObject(rsLocation, con);
				
				Object owner;
				query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN \"public\".\"Location\" ON \"Book\".\"OwnerID\"=\"Location\".\"LocationID\""
						+ "WHERE \"Book\".\"BookID\"=?";
				pstmtOwner = con.prepareStatement(query);
				pstmtOwner.setInt(1, rsBook.getInt("BookID"));
				ResultSet rsOwner = pstmtLocation.executeQuery();
				
				owner = getObject(rsOwner, con);
				
				GlobalCollection.getBooksList().add(new Book(rsBook.getInt("BookID"), rsBook.getString("ISBN"), rsBook.getString("Title"), rsBook.getString("Summary"), rsBook.getInt("EditionID"), rsBook.getInt("EditionNumber"), rsBook.getString("EditionYear"), rsBook.getInt("NumberOfPages"), currLocation, owner, rsBook.getBoolean("Avaliable"), rsBook.getDate("ReturnDate"), rs.getString("Information"), authors, publishers, languages, genres));
				
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
	
	private static Object getObject(ResultSet rsLocation, Connection con) throws SQLException {
		
		Object currLocation = null;
		
		if(rsLocation.getInt("TypeID") == 1) {
			while (rsLocation.next()) {
				String query = "SELECT * FROM \"public\".\"Library\" "
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
				String query = "SELECT * FROM \"public\".\"User\" "
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
