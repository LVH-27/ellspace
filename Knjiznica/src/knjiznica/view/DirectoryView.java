package knjiznica.view;

import java.util.concurrent.Executor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import org.controlsfx.control.MaskerPane;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import knjiznica.model.Book;
import knjiznica.model.GlobalCollection;
import knjiznica.model.SelectUsers;
import knjiznica.model.User;
import knjiznica.model.ViewProvider;

public class DirectoryView {
	
	@FXML
	private TableView<Book> tableBookList;
	
	@FXML
	private TableColumn<Book, String> firstNameCol;
	
	@FXML
	private TableColumn<Book, String> middleNameCol;
	
	@FXML
	private TableColumn<Book, String> lastNameCol;
	
	@FXML
	private TableColumn<Book, String> countryCol;
	
	@FXML
	private TableColumn<Book, String> cityCol;
	
	@FXML
	private TableColumn<Book, Integer> postalCodeCol;
	
	@FXML
	private TableColumn<Book, String> streetCol;
	
	@FXML
	private TableColumn<Book, String> houseNumberCol;
	
	@FXML
	private TableColumn<Book, String> phoneNumberCol;
	
	@FXML
	private TableColumn<Book, String> emailCol;
	
	private static StackPane sp = (StackPane) ViewProvider.getView("stackPane");
	private static Executor exec;
	
	public void initialize() {
		
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
		
		sp.getChildren().add((MaskerPane) ViewProvider.getView("mask"));
		
		ArrayList<Book> books = new ArrayList<Book>();
		
		Task<ArrayList<Book>> getBooksTableTask = new Task<ArrayList<Book>>() {
            @Override
            public ArrayList<Book> call() throws Exception {
            	
    			Thread.sleep(600);
    			
    			return SelectBooks.select();  
            }
		};
		
		getBooksTableTask.setOnSucceeded(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			books.addAll(getBooksTableTask.getValue());
			populateTable(books);
		});
		
		//TODO after thread fails populate users table
		
	/*	getAuthorsTableTask.setOnFailed(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			afterThreadFails();
	    });*/
		
		exec.execute(getBooksTableTask);
	}
	
	public void populateTable(ArrayList<Book> books) {
		
		GlobalCollection.emptyList();
		
		for (int i = 0; i < books.size(); ++i) {
			GlobalCollection.getBooksList().add(books.get(i));
		} 
		
	/**	tableUserList. setItems(GlobalCollection.getUserList());
		firstNameCol.  setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		firstNameCol.  setStyle("-fx-alignment: CENTER;");
		middleNameCol. setCellValueFactory(new PropertyValueFactory<User, String>("middleName"));
		middleNameCol. setStyle("-fx-alignment: CENTER;");
		lastNameCol.   setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		lastNameCol.   setStyle("-fx-alignment: CENTER;");
		countryCol.    setCellValueFactory(new PropertyValueFactory<User, String>("country"));
		countryCol.    setStyle("-fx-alignment: CENTER;");
		cityCol.       setCellValueFactory(new PropertyValueFactory<User, String>("city"));
		cityCol.       setStyle("-fx-alignment: CENTER;");
		postalCodeCol. setCellValueFactory(new PropertyValueFactory<User, Integer>("postalCode"));
		postalCodeCol. setStyle("-fx-alignment: CENTER;");
		streetCol.     setCellValueFactory(new PropertyValueFactory<User, String>("street"));
		streetCol.     setStyle("-fx-alignment: CENTER;");
		houseNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("houseNumber"));
		houseNumberCol.setStyle("-fx-alignment: CENTER;");
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber")); 
		phoneNumberCol.setStyle("-fx-alignment: CENTER;");
		emailCol.      setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		emailCol.      setStyle("-fx-alignment: CENTER;");**/
		
		tableBookList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					//TODO click event
				}
			}
		});
	}
}
