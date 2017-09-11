package knjiznica.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalCollection {
	
	private static ObservableList<User> lUser;
	private static ObservableList<Library> lLibrary;
	private static User user;
	private static boolean isEditable;
	
	static {
		lUser = FXCollections.observableArrayList();
		lLibrary = FXCollections.observableArrayList();
	}
	
	private GlobalCollection() {
		 
	}
	
	public static void emptyList() {
		lUser = FXCollections.observableArrayList();	
		lLibrary = FXCollections.observableArrayList();	
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
}

//TODO Add new column with time/date when user was added
