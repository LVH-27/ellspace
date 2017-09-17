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
	
	private static boolean currLoc = true;
	
	private static String locationName;
	private static String ownerName;
	
	public static ArrayList<Book> select() {
		books = new ArrayList<Book>();
		
		Statement stmt = null;
		
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			String queryBook = "SELECT * FROM public.\"Book\"" + 
					"JOIN public.\"Edition\" ON \"Edition\".\"EditionID\"=\"Book\".\"EditionID\"" + 
					"JOIN public.\"IsbnLinks\" ON \"Book\".\"ISBN\"=\"IsbnLinks\".\"ISBN\"";
			
			stmt = con.createStatement();
			
			ResultSet rsBook = stmt.executeQuery(queryBook);
			
			ArrayList<Author> authors;
			ArrayList<Publisher> publishers;
			ArrayList<Language> languages;
			ArrayList<Genre> genres;
		
			while (rsBook.next()) {
				currLoc = true;
				authors = new ArrayList<Author>();
				publishers = new ArrayList<Publisher>();
				languages = new ArrayList<Language>();
				genres = new ArrayList<Genre>();
				
				PreparedStatement pstmtBookPublisher;
				PreparedStatement pstmtBookLanguage;
				PreparedStatement pstmtBookGenre;
				
				String query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN public.\"AuthorLinks\" ON \"AuthorLinks\".\"ISBN\"=?"
						+ "JOIN public.\"Author\" ON \"Author\".\"AuthorID\"=\"AuthorLinks\".\"AuthorID\""
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
				
				query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN public.\"PublisherLinks\" ON \"PublisherLinks\".\"ISBN\"=?"
						+ "JOIN public.\"Publisher\" ON \"Publisher\".\"PublisherID\"=\"PublisherLinks\".\"PublisherID\""
						+ "JOIN public.\"Address\" ON \"Address\".\"AddressID\"=\"Publisher\".\"AddressID\""
						+ "JOIN public.\"City\" ON \"Address\".\"PostalCode\"=\"City\".\"PostalCode\""
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
				
				query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN public.\"PublisherLinks\" ON \"PublisherLinks\".\"ISBN\"=?"
						+ "JOIN public.\"Publisher\" ON \"Publisher\".\"PublisherID\"=\"PublisherLinks\".\"PublisherID\" AND \"Publisher\".\"AddressID\" IS NULL "
						+ "WHERE \"Book\".\"ISBN\"=?";
				
				PreparedStatement pstmtBookPublisherNull = con.prepareStatement(query);
				pstmtBookPublisherNull.setString(1, rsBook.getString("ISBN"));
				pstmtBookPublisherNull.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBookPublisherNull.executeQuery();
				while (rs.next()) {			
					publishers.add(new Publisher(rs.getInt("PublisherID"), rs.getString("PublisherName"), "-", "-", "-", "-", "-", -1));
				}
				
				query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN public.\"LanguageLinks\" ON \"LanguageLinks\".\"ISBN\"=?"
						+ "JOIN public.\"LanguageList\" ON \"LanguageList\".\"LanguageID\"=\"LanguageLinks\".\"LanguageID\""
						+ "WHERE \"Book\".\"ISBN\"=?";
				
				pstmtBookLanguage = con.prepareStatement(query);
				pstmtBookLanguage.setString(1, rsBook.getString("ISBN"));
				pstmtBookLanguage.setString(2, rsBook.getString("ISBN"));
				rs = pstmtBookLanguage.executeQuery();
				while (rs.next()) {
					languages.add(new Language(rs.getInt("LanguageID"), rs.getString("LanguageNameEN"), rs.getString("LanguageNameHR"), rs.getString("LanguageNameDE")));
				}
				
				query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN public.\"GenreLinks\" ON \"GenreLinks\".\"ISBN\"=?"
						+ "JOIN public.\"GenreList\" ON \"GenreList\".\"GenreID\"=\"GenreLinks\".\"GenreID\""
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
				currLoc = false;
				Object owner;
				query = "SELECT * FROM \"public\".\"Book\""
						+ "JOIN \"public\".\"Location\" ON \"Book\".\"OwnerID\"=\"Location\".\"LocationID\""
						+ "WHERE \"Book\".\"BookID\"=?";
				
				pstmtOwner = con.prepareStatement(query);
				pstmtOwner.setInt(1, rsBook.getInt("BookID"));
				ResultSet rsOwner = pstmtOwner.executeQuery();
				
				owner = getObject(rsOwner, con);
				
				String authorsName = "";
				for(int i = 0; i < authors.size(); ++i) {
					authorsName += authors.get(i).getFirstName() + " ";
					authorsName += authors.get(i).getMiddleName() + " ";
					authorsName += authors.get(i).getLastName();
					if(i < authors.size() - 1) {
						authorsName += ", ";
					}
				}
				
				String publishersName = "";
				for(int i = 0; i < publishers.size(); ++i) {
					publishersName += publishers.get(i).getName();
					if(i < publishers.size() - 1) {
						publishersName += ", ";
					}
				}
				
				String languagesName = "";
				for(int i = 0; i < languages.size(); ++i) {
					languagesName += languages.get(i).getName();
					if(i < languages.size() - 1) {
						languagesName += ", ";
					}
				}
				
				String genresName = "";
				for(int i = 0; i < genres.size(); ++i) {
					genresName += genres.get(i).getName();
					if(i < genres.size() - 1) {
						genresName += ", ";
					}
				}
				
				books.add(new Book(rsBook.getInt("BookID"), rsBook.getString("ISBN"), rsBook.getString("Title"), rsBook.getString("Summary"), rsBook.getInt("EditionID"), rsBook.getInt("Number"), rsBook.getString("Year"), rsBook.getInt("NumberOfPages"), currLocation, owner, rsBook.getBoolean("Available"), rsBook.getDate("ReturnDate"), rsBook.getString("Information"), authors, publishers, languages, genres, locationName, ownerName, authorsName, publishersName, languagesName, genresName));
				
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
		
		while(rsLocation.next()) {
		//	System.out.println(rsLocation.getInt("TypeID"));
			if(rsLocation.getInt("TypeID") == 1) {
				String query = "SELECT * FROM \"public\".\"Library\""
						+ "JOIN \"Address\" ON \"Address\".\"AddressID\"=\"Library\".\"AddressID\""
						+ "JOIN \"City\" ON \"City\".\"PostalCode\"=\"Address\".\"PostalCode\""
						+ "WHERE \"Library\".\"LibraryID\"=?";
				PreparedStatement pstmtLibrary = con.prepareStatement(query);
				pstmtLibrary.setInt(1, rsLocation.getInt("LibraryID"));
				ResultSet rsLibrary = pstmtLibrary.executeQuery();
				while (rsLibrary.next()) {
					if(currLoc) {
						locationName = rsLibrary.getString("LibraryName");
					} else {
						ownerName = rsLibrary.getString("LibraryName");					
					}
					currLocation = new Library(rsLibrary.getInt("LibraryID"), rsLibrary.getString("LibraryName"), rsLibrary.getString("Country"), Integer.toString(rsLibrary.getInt("PostalCode")), rsLibrary.getString("Street"), rsLibrary.getString("HouseNumber"), rsLibrary.getString("PhoneNumber"), rsLibrary.getString("email"), rsLibrary.getString("Information"), rsLibrary.getString("CityName"), rsLibrary.getInt("AddressID"));
				}
				if(currLocation == null) {
					query = "SELECT * FROM \"public\".\"Library\" "
							+ "WHERE \"Library\".\"LIbraryID\"=?";
					PreparedStatement pstmtLibraryNull = con.prepareStatement(query);
					pstmtLibrary.setInt(1, rsLocation.getInt("LibraryID"));
					ResultSet rsLibraryNull = pstmtLibraryNull.executeQuery();
					while (rsLibraryNull.next()) {
						if(currLoc) {
							locationName = rsLibraryNull.getString("LibraryName");
						} else {
							ownerName = rsLibraryNull.getString("LibraryName");					
						}
						currLocation = new Library(rsLibraryNull.getInt("LibraryID"), rsLibraryNull.getString("LibraryName"), "-", "-", "-", "-", rsLibraryNull.getString("PhoneNumber"), rsLibraryNull.getString("email"), rsLibraryNull.getString("Information"), "-", -1);
					}
				}
					
				
			} else {
				String query = "SELECT * FROM \"public\".\"User\""
						+ "JOIN \"Address\" ON \"Address\".\"AddressID\"=\"User\".\"AddressID\""
						+ "JOIN \"City\" ON \"City\".\"PostalCode\"=\"Address\".\"PostalCode\""
						+ "WHERE \"User\".\"UserID\"=?";
				PreparedStatement pstmtUser = con.prepareStatement(query);
				pstmtUser.setInt(1, rsLocation.getInt("UserID"));
				ResultSet rsUser = pstmtUser.executeQuery();
				while (rsUser.next()) {
					String middleName = "";
					String street = "-";
					String houseNumber = "-"; 
					String phoneNumber = "-";
					
					if (rsUser.getString("MiddleName") != null) {
						middleName = rsUser.getString("MiddleName") + " ";
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
					if(currLoc) {
						locationName = rsUser.getString("FirstName") + " " + middleName + " " + rsUser.getString("LastName");
					} else {
						ownerName = rsUser.getString("FirstName") + " " + middleName + rsUser.getString("LastName");
					}
					currLocation = new User(rsUser.getInt("UserID"), rsUser.getString("FirstName"), middleName, rsUser.getString("LastName"), rsUser.getString("Country"), rsUser.getInt("PostalCode"), street, houseNumber, phoneNumber, rsUser.getString("Email"), rsUser.getString("CityName"), rsUser.getInt("AddressID"));
				}
				
			}
		}
		
		return currLocation;
	}
}
