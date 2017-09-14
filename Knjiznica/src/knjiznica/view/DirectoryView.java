package knjiznica.view;
//
//import java.util.concurrent.Executor;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.concurrent.Executors;
//import org.controlsfx.control.MaskerPane;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Task;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TablePosition;
//import javafx.scene.control.TableView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.StackPane;
//import knjiznica.model.Book;
//import knjiznica.model.GlobalCollection;
//import knjiznica.model.SelectUsers;
//import knjiznica.model.User;
//import knjiznica.model.ViewProvider;
//
public class DirectoryView {
//	
//	@FXML
//	private TableView<Book> tableUserList;
//	
//	@FXML
//	private TableColumn<Book, String> firstNameCol;
//	
//	@FXML
//	private TableColumn<Book, String> middleNameCol;
//	
//	@FXML
//	private TableColumn<Book, String> lastNameCol;
//	
//	@FXML
//	private TableColumn<Book, String> countryCol;
//	
//	@FXML
//	private TableColumn<Book, String> cityCol;
//	
//	@FXML
//	private TableColumn<Book, Integer> postalCodeCol;
//	
//	@FXML
//	private TableColumn<Book, String> streetCol;
//	
//	@FXML
//	private TableColumn<Book, String> houseNumberCol;
//	
//	@FXML
//	private TableColumn<Book, String> phoneNumberCol;
//	
//	@FXML
//	private TableColumn<Book, String> emailCol;
//	
//	private static StackPane sp = (StackPane) ViewProvider.getView("stackPane");
//	private static Executor exec;
//	//FIXME BS CLASS, COPIED LISTUSERS
	public void initialize() {
//		
//		exec = Executors.newCachedThreadPool(runnable -> {
//            Thread t = new Thread(runnable);
//            t.setDaemon(true);
//            return t ;
//        });
//		
//		sp.getChildren().add((MaskerPane) ViewProvider.getView("mask"));
//		
//		ArrayList<User> users = new ArrayList<User>();
//		
//		Task<ArrayList<User>> getUsersTableTask = new Task<ArrayList<User>>() {
//            @Override
//            public ArrayList<User> call() throws Exception {
//            	
//    			Thread.sleep(600);
//    			
//    			return SelectUsers.select();  
//            }
//		};
//		
//		getUsersTableTask.setOnSucceeded(e -> {
//			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
//			users.addAll(getUsersTableTask.getValue());
//			populateTable(users);
//		});
//		
//		//TODO after thread fails populate users table
//		
//		/*getAuthorsTableTask.setOnFailed(e -> {
//			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
//			afterThreadFails();
//	    });*/
//		
//		exec.execute(getUsersTableTask);
	}
//	
//	public void populateTable(ArrayList<User> users) {
//		
//		GlobalCollection.emptyList();
//		
//		for (int i = 0; i < users.size(); ++i) {
//			GlobalCollection.getUserList().add(users.get(i));
//		} 
//		
////		tableUserList. setItems(GlobalCollection.getUserList());
////		firstNameCol.  setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
////		firstNameCol.  setStyle("-fx-alignment: CENTER;");
////		middleNameCol. setCellValueFactory(new PropertyValueFactory<User, String>("middleName"));
////		middleNameCol. setStyle("-fx-alignment: CENTER;");
////		lastNameCol.   setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
////		lastNameCol.   setStyle("-fx-alignment: CENTER;");
////		countryCol.    setCellValueFactory(new PropertyValueFactory<User, String>("country"));
////		countryCol.    setStyle("-fx-alignment: CENTER;");
////		cityCol.       setCellValueFactory(new PropertyValueFactory<User, String>("city"));
////		cityCol.       setStyle("-fx-alignment: CENTER;");
////		postalCodeCol. setCellValueFactory(new PropertyValueFactory<User, Integer>("postalCode"));
////		postalCodeCol. setStyle("-fx-alignment: CENTER;");
////		streetCol.     setCellValueFactory(new PropertyValueFactory<User, String>("street"));
////		streetCol.     setStyle("-fx-alignment: CENTER;");
////		houseNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("houseNumber"));
////		houseNumberCol.setStyle("-fx-alignment: CENTER;");
////		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber")); 
////		phoneNumberCol.setStyle("-fx-alignment: CENTER;");
////		emailCol.      setCellValueFactory(new PropertyValueFactory<User, String>("email"));
////		emailCol.      setStyle("-fx-alignment: CENTER;");
//		
//		tableUserList.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				if (event.getClickCount() > 1) {
//					@SuppressWarnings("rawtypes")
//					ObservableList<TablePosition> cells = tableUserList.getSelectionModel().getSelectedCells();
//					if (!GlobalCollection.isPotentialOwner()) {
//						try {
//							GlobalCollection.setUser(GlobalCollection.getUserList().get(cells.get(0).getRow()));
//							GlobalCollection.setEditable(false);
//							BorderPane updateUser;
//							updateUser = (BorderPane) FXMLLoader.load(getClass().getResource("UpdateUser-view.fxml"));
//							((BorderPane) ViewProvider.getView("mainScreen")).setCenter(updateUser);
//							
//						} catch (Exception e) {
//
//						}
//					} else {
//						GlobalCollection.emptyAddedUsersList();
//						GlobalCollection.getAddedUsers().add(GlobalCollection.getUserList().get(cells.get(0).getRow()));
//						BorderPane addBook;
//						try {
//							addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
//							((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
//							
//						} catch (IOException e) {
//							
//						}
//				    	
//					}
//					
//				}
//			}
//		});
//	}
}
