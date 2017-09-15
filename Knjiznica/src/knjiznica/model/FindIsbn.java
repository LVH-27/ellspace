package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sun.print.PSStreamPrintService;

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
			String s = "";
			while (rs.next()) {
				s = rs.getString("ISBN");
			}
			
			if (!s.isEmpty()) {
				
				GlobalCollection.resetFields();
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
							rs.getInt("AuthorID"), 
							rs.getString("FirstName"), 
							rs.getString("MiddleName"), 
							rs.getString("LastName"), 
							rs.getBoolean("isAlive"), 
							rs.getString("YearOfBirth"), 
							rs.getString("YearOfDeath")
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
					GlobalCollection.getAddedPublishers().add(new Publisher(rs.getInt("PublisherID"), rs.getString("PublisherName"), rs.getString("Country"), Integer.toString(rs.getInt("PostalCode")), rs.getString("Street"), rs.getString("HouseNumber"), rs.getString("CityName"), rs.getInt("AddressID")));
				}
				
				PreparedStatement pstmtLanguage;
				//String queryLanguage = 
				
				GlobalCollection.setISBN(isbn);
//				GlobalCollection.setTitle(title);
//				GlobalCollection.setSummary(summary);
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
