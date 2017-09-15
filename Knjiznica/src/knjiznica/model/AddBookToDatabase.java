package knjiznica.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import knjiznica.view.AddLibraryView;

public class AddBookToDatabase {
	
	public static void add(String isbn, String title, String summary, String info, String editionNumber, String publicationYear, String numberOfpages) {
		try {
			Connection con = DriverManager.getConnection(
					ConnectionData.getLink(), ConnectionData.getUsername(), ConnectionData.getPassword());
			
			InsertIsbn.insert(con, isbn, title, summary);
			int editionID = InsertEditionNumber.insert(con, editionNumber, publicationYear, numberOfpages);
			
			int locationID = -1;
			
			if (!GlobalCollection.getAddedUsers().isEmpty()) {
				String queryUser = "SELECT * FROM public.\"Location\" WHERE \"UserID\"=?";
				PreparedStatement pstmtUser = con.prepareStatement(queryUser);
				pstmtUser.setInt(1, GlobalCollection.getAddedUsers().get(0).getID());
				ResultSet rs = pstmtUser.executeQuery();
				while (rs.next()) {
					locationID = rs.getInt("LocationID");
				}
				
			} else {
				String queryLibrary = "SELECT * FROM public.\"Location\" WHERE \"LibraryID\"=?";
				PreparedStatement pstmtLibrary = con.prepareStatement(queryLibrary);
				pstmtLibrary.setInt(1, GlobalCollection.getAddedLibraries().get(0).getID());
				ResultSet rs = pstmtLibrary.executeQuery();
				while (rs.next()) {
					locationID = rs.getInt("LocationID");
				}
			}
			
			InsertNewBook.insert(con, isbn, editionID, locationID, locationID, info);
			
			for (int i = 0; i < GlobalCollection.getAddedAuthors().size(); ++i) {
				InsertAuthorLinks.insert(con, isbn, GlobalCollection.getAddedAuthors().get(i));
			}
			
			for (int i = 0; i < GlobalCollection.getAddedPublishers().size(); ++i) {
				InsertPublisherLinks.insert(con, isbn, GlobalCollection.getAddedPublishers().get(i));
			}
			
			for (int i = 0; i < GlobalCollection.getAddedLanguages().size(); ++i) {
				InsertLanguageLinks.insert(con, isbn, GlobalCollection.getAddedLanguages().get(i));
			}
			
			for (int i = 0; i < GlobalCollection.getAddedGenres().size(); ++i) {
				InsertGenreLinks.insert(con, isbn, GlobalCollection.getAddedGenres().get(i));
			}
			
		} catch (PSQLException e) {
			e.printStackTrace();
			AddLibraryView.isReached = false;
			
		} catch (SQLException e) {
			AddLibraryView.isReached = false;	
		} 
	}
}
