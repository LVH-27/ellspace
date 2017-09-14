package knjiznica.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalCollection {
	
	private static ObservableList<User> lUser;
	private static ObservableList<Library> lLibrary;
	private static ObservableList<Author> lAuthor;
	private static ObservableList<Publisher> lPublisher;
	private static ObservableList<BusinessHours> lBusinessHours;
	private static ObservableList<Language> lLanguages;
	private static ObservableList<Author> addedAuthors;
	private static ObservableList<Publisher> addedPublishers;
	private static ObservableList<User> addedUsers;
	private static ObservableList<Library> addedLibraries;
	private static ObservableList<Language> addedLanguages;
	
	private static User user;
	private static Library library;
	private static BusinessHours businessHours;
	private static boolean isEditable;
	private static boolean add;
	private static boolean isPotentialOwner;
	private static String ISBN;
	private static String title;
	private static String summary;
	private static String bookInfo;
	private static String edition;
	private static String publictionYear;
	private static String numberOfPages;
	
	static {
		lUser = FXCollections.observableArrayList();
		lLibrary = FXCollections.observableArrayList();
		lBusinessHours = FXCollections.observableArrayList();
		lAuthor = FXCollections.observableArrayList();
		lPublisher = FXCollections.observableArrayList();
		lLanguages = FXCollections.observableArrayList();
		addedAuthors = FXCollections.observableArrayList(); 
		addedPublishers = FXCollections.observableArrayList(); 
		addedUsers = FXCollections.observableArrayList(); 
		addedLibraries = FXCollections.observableArrayList(); 
		addedLanguages = FXCollections.observableArrayList();
	}
	
	public static void emptyList() {
		lUser = FXCollections.observableArrayList();	
		lLibrary = FXCollections.observableArrayList();	
		lBusinessHours = FXCollections.observableArrayList();
		lAuthor = FXCollections.observableArrayList();
		lPublisher = FXCollections.observableArrayList();
		lLanguages = FXCollections.observableArrayList();
	}

	public static void resetFields() {
		ISBN = "";
		title = "";
		summary = "";
		bookInfo = "";
		edition = "";
		publictionYear = "";
		numberOfPages = "";
	}

	public static void emptyAddedPublishersList() {
		addedPublishers = FXCollections.observableArrayList(); 
	}
	
	public static void emptyAddedAuthorsList() {
		addedAuthors = FXCollections.observableArrayList(); 
	}
	
	public static void emptyAddedUsersList() {
		addedUsers = FXCollections.observableArrayList(); 
	}
	
	public static void emptyAddedLibrariesList() {
		addedLibraries = FXCollections.observableArrayList(); 
	}
	
	public static void emptyAddedLanguagesList() {
		addedLanguages = FXCollections.observableArrayList(); 
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

	public static boolean isAdd() {
		return add;
	}

	public static void setAdd(boolean add) {
		GlobalCollection.add = add;
	}

	public static ObservableList<User> getAddedUsers() {
		return addedUsers;
	}

	public static void setAddedUsers(ObservableList<User> addedUsers) {
		GlobalCollection.addedUsers = addedUsers;
	}

	public static ObservableList<Library> getAddedLibraries() {
		return addedLibraries;
	}

	public static void setAddedLibraries(ObservableList<Library> addedLibraries) {
		GlobalCollection.addedLibraries = addedLibraries;
	}

	public static boolean isPotentialOwner() {
		return isPotentialOwner;
	}

	public static void setPotentialOwner(boolean isPotentialOwner) {
		GlobalCollection.isPotentialOwner = isPotentialOwner;
	}

	public static String getISBN() {
		return ISBN;
	}

	public static void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public static String getSummary() {
		return summary;
	}

	public static void setSummary(String summary) {
		GlobalCollection.summary = summary;
	}

	public static String getBookInfo() {
		return bookInfo;
	}

	public static void setBookInfo(String bookInfo) {
		GlobalCollection.bookInfo = bookInfo;
	}

	public static String getEdition() {
		return edition;
	}

	public static void setEdition(String edition) {
		GlobalCollection.edition = edition;
	}

	public static String getPublictionYear() {
		return publictionYear;
	}

	public static void setPublictionYear(String publictionYear) {
		GlobalCollection.publictionYear = publictionYear;
	}

	public static String getNumberOfPages() {
		return numberOfPages;
	}

	public static void setNumberOfPages(String numberOfPages) {
		GlobalCollection.numberOfPages = numberOfPages;
	}

	public static String getTitle() {
		return title;
	}

	public static void setTitle(String title) {
		GlobalCollection.title = title;
	}

	public static ObservableList<Language> getLanguageList() {
		return lLanguages;
	}

	public static void setLanguageList(ObservableList<Language> lLanguages) {
		GlobalCollection.lLanguages = lLanguages;
	}

	public static ObservableList<Language> getAddedLanguages() {
		return addedLanguages;
	}

	public static void setAddedLanguages(ObservableList<Language> addedLanguages) {
		GlobalCollection.addedLanguages = addedLanguages;
	}
	
}

//TODO Add new column with time/date when user was added
