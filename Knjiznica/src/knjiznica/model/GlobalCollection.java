package knjiznica.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalCollection {
	
	private static ObservableList<User> l;
	
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
}
