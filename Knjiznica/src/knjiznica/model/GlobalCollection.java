package knjiznica.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalCollection {
	
	private static ObservableList<User> lUser;
	private static ObservableList<Library> lLibrary;
	private static ObservableList<Author> lAuthor;
	private static ObservableList<Publisher> lPublisher;
	private static ObservableList<BusinessHours> lBusinessHours;
	private static ObservableList<Author> addedAuthors;
	private static ObservableList<Publisher> addedPublishers;
	
	private static User user;
	private static Library library;
	private static BusinessHours businessHours;
	private static boolean isEditable;
	
	static {
		lUser = FXCollections.observableArrayList();
		lLibrary = FXCollections.observableArrayList();
		lBusinessHours = FXCollections.observableArrayList();
		lAuthor = FXCollections.observableArrayList();
		lPublisher = FXCollections.observableArrayList();
		addedAuthors = FXCollections.observableArrayList(); 
		addedPublishers = FXCollections.observableArrayList(); 
	}
	
	public static void emptyList() {
		lUser = FXCollections.observableArrayList();	
		lLibrary = FXCollections.observableArrayList();	
		lBusinessHours = FXCollections.observableArrayList();
		lAuthor = FXCollections.observableArrayList();
		lPublisher = FXCollections.observableArrayList();
		addedAuthors = FXCollections.observableArrayList(); 
		addedPublishers = FXCollections.observableArrayList(); 
	}
	
	public static ObservableList<User> getUserList() {
		return lUser;
	}
	
	public static ObservableList<Library> getLibraryList() {
		return lLibrary;
	}

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		GlobalCollection.user = user;
	}

	public static boolean isEditable() {
		return isEditable;
	}

	public static void setEditable(boolean isEditable) {
		GlobalCollection.isEditable = isEditable;
	}

	public static Library getLibrary() {
		return library;
	}

	public static void setLibrary(Library library) {
		GlobalCollection.library = library;
	}

	public static ObservableList<BusinessHours> getBusinessHoursList() {
		return lBusinessHours;
	}

	public static void setBusinessHours(ObservableList<BusinessHours> lBusinessHours) {
		GlobalCollection.lBusinessHours = lBusinessHours;
	}

	public static void setBusinessHours(BusinessHours businessHours) {
		GlobalCollection.businessHours = businessHours;
	}	
	
	public static BusinessHours getBusinessHours() {
		return businessHours;
	}

	public static ObservableList<Author> getAuthorList() {
		return lAuthor;
	}

	public static void setAuthorList(ObservableList<Author> lAuthor) {
		GlobalCollection.lAuthor = lAuthor;
	}

	public static ObservableList<Author> getAddedAuthors() {
		return addedAuthors;
	}

	public static void setAddedAuthors(ObservableList<Author> addedAuthors) {
		GlobalCollection.addedAuthors = addedAuthors;
	}

	public static ObservableList<Publisher> getPublisherList() {
		return lPublisher;
	}

	public static void setPublisherList(ObservableList<Publisher> lPublisher) {
		GlobalCollection.lPublisher = lPublisher;
	}

	public static ObservableList<Publisher> getAddedPublishers() {
		return addedPublishers;
	}

	public static void setAddedPublishers(ObservableList<Publisher> addedPublishers) {
		GlobalCollection.addedPublishers = addedPublishers;
	}	
	
}

//TODO Add new column with time/date when user was added
