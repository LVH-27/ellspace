package knjiznica.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalCollection {
private static ObservableList<Book> l;
	
	static {
		l = FXCollections.observableArrayList();
	}
	
	private GlobalCollection() {
		 
	}

	public static ObservableList<Book> getList() {
		return l; 
	}
}
