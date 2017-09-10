package knjiznica.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalCollection {
	
	private static ObservableList<User> l;
	
	private static User user;
	
	private static boolean isEditable;
	
	static {
		l = FXCollections.observableArrayList();
	}
	
	private GlobalCollection() {
		 
	}
	
	public static void emptyList() {
		l = FXCollections.observableArrayList();
		
	}
	
	public static ObservableList<User> getList() {
		return l;
		
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