package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindIsbn {
	
	public static void find(String isbn) {
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			PreparedStatement pstmtLoad;
			String queryLoad = "SELECT * FROM \"public\".\"IsbnLinks\" WHERE \"IsbnLinks\".\"ISBN\"=?";
			pstmtLoad = con.prepareStatement(queryLoad);
			pstmtLoad.setString(1, isbn);
			ResultSet rs = pstmtLoad.executeQuery();
			
			GlobalCollection.resetIsbnFields();
			
			while (rs.next()) {
				GlobalCollection.setTitle(rs.getString("Title"));
				GlobalCollection.setSummary(rs.getString("Summary"));
			}

			GlobalCollection.emptyAddedAuthorsList();
			GlobalCollection.emptyAddedPublishersList();
			GlobalCollection.emptyAddedGenresList();
			GlobalCollection.emptyAddedLanguagesList();
			PreparedStatement pstmtAuthor;
			String queryAuthor = "SELECT * FROM \"public\".\"AuthorLinks\" "
					+ "JOIN \"public\".\"Author\" ON \"AuthorLinks\".\"AuthorID\"=\"Author\".\"AuthorID\" "
					+ "WHERE \"AuthorLinks\".\"ISBN\"=?";
			pstmtAuthor = con.prepareStatement(queryAuthor);
			pstmtAuthor.setString(1, isbn);
			ResultSet rsAuthor = pstmtAuthor.executeQuery();
			
			while (rsAuthor.next()) {
				GlobalCollection.getAddedAuthors().add(new Author(
						rsAuthor.getInt("AuthorID"), 
						rsAuthor.getString("FirstName"), 
						rsAuthor.getString("MiddleName"), 
						rsAuthor.getString("LastName"), 
						rsAuthor.getBoolean("Alive"), 
						rsAuthor.getString("YearOfBirth"), 
						rsAuthor.getString("YearOfDeath")
						));
			}
			
			PreparedStatement pstmtPublisher;
			String queryPublisher = "SELECT * FROM \"public\".\"PublisherLinks\" "
					+ "JOIN \"public\".\"Publisher\" ON \"PublisherLinks\".\"PublisherID\"=\"Publisher\".\"PublisherID\" "
					+ "JOIN \"public\".\"Address\" ON \"Publisher\".\"AddressID\"=\"Address\".\"AddressID\" "
					+ "JOIN \"public\".\"City\" ON \"City\".\"PostalCode\"=\"Address\".\"PostalCode\" "
					+ "WHERE \"PublisherLinks\".\"ISBN\"=?";
			
			pstmtPublisher = con.prepareStatement(queryPublisher);
			pstmtPublisher.setString(1, isbn);
			ResultSet rsPublisher = pstmtPublisher.executeQuery();
			
			while (rsPublisher.next()) {
				GlobalCollection.getAddedPublishers().add(new Publisher(rsPublisher.getInt("PublisherID"), rsPublisher.getString("PublisherName"), rsPublisher.getString("Country"), Integer.toString(rsPublisher.getInt("PostalCode")), rsPublisher.getString("Street"), rsPublisher.getString("HouseNumber"), rsPublisher.getString("CityName"), rsPublisher.getInt("AddressID")));
			}
			
			PreparedStatement pstmtLanguage;
			String queryLanguage = "SELECT * FROM \"public\".\"LanguageLinks\" "
					+ "JOIN \"public\".\"LanguageList\" ON \"LanguageLinks\".\"LanguageID\"=\"LanguageList\".\"LanguageID\" "
					+ "WHERE \"LanguageLinks\".\"ISBN\"=?";
			
			pstmtLanguage = con.prepareStatement(queryLanguage);
			pstmtLanguage.setString(1, isbn);
			ResultSet rsLanguage = pstmtLanguage.executeQuery();
			
			while (rsLanguage.next()) {
				GlobalCollection.getAddedLanguages().add(new Language(rsLanguage.getInt("LanguageID"), rsLanguage.getString("LanguageNameEN"), rsLanguage.getString("LanguageNameHR"), rsLanguage.getString("LanguageNameDE")));
			}
			
			PreparedStatement pstmGenre;
			String queryGenre = "SELECT * FROM \"public\".\"GenreLinks\" "
					+ "JOIN \"public\".\"GenreList\" ON \"GenreLinks\".\"GenreID\"=\"GenreList\".\"GenreID\" "
					+ "WHERE \"GenreLinks\".\"ISBN\"=?";
			
			pstmGenre = con.prepareStatement(queryGenre);
			pstmGenre.setString(1, isbn);
			ResultSet rsGenre = pstmGenre.executeQuery();
			
			while (rsGenre.next()) {
				GlobalCollection.getAddedGenres().add(new Genre(rsGenre.getInt("GenreID"), rsGenre.getString("GenreNameEN"), rsGenre.getString("GenreNameHR"), rsGenre.getString("GenreNameDE")));
			}
			
			GlobalCollection.setISBN(isbn);
				
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
