package knjiznica.view;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Executor;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import knjiznica.model.GlobalCollection;
import knjiznica.model.Library;
import knjiznica.model.SelectLibraries;
import knjiznica.model.ViewProvider;


public class ListLibrariesView {
	
	@FXML
	private TableView<Library> tableLibraryList;
	
	@FXML
	private TableColumn<Library, String> firstNameCol;
	
	@FXML
	private TableColumn<Library, String> countryCol;
	
	@FXML
	private TableColumn<Library, String> cityCol;
	
	@FXML
	private TableColumn<Library, String> postalCodeCol;
	
	@FXML
	private TableColumn<Library, String> streetCol;
	
	@FXML
	private TableColumn<Library, String> houseNumberCol;
	
	@FXML
	private TableColumn<Library, String> phoneNumberCol;
	
	@FXML
	private TableColumn<Library, String> emailCol;
	
	@FXML
	private TableColumn<Library, String> informationCol;
	
	@FXML
	private TableColumn<Library, String> opensCol;
	
	@FXML
	private TableColumn<Library, String> closesCol;
	
	private static StackPane sp = (StackPane) ViewProvider.getView("stackPane");
	private static Executor exec;
	
	public void initialize() {	
		
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
		
		GlobalCollection.emptyList();
		
		sp.getChildren().add((MaskerPane) ViewProvider.getView("mask"));
		
		ArrayList<Library> libraries = new ArrayList<Library>();
		
		Task<ArrayList<Library>> getLibrariesTableTask = new Task<ArrayList<Library>>() {
            @Override
            public ArrayList<Library> call() throws Exception {
            	
    			Thread.sleep(600);
    			
    			return SelectLibraries.select();  
            }
		};
		
		getLibrariesTableTask.setOnSucceeded(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			libraries.addAll(getLibrariesTableTask.getValue());
			populateTable(libraries);
		});
		
		//TODO after thread fails populate libraries table
		
		/*getAuthorsTableTask.setOnFailed(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			afterThreadFails();
	    });*/
		exec.execute(getLibrariesTableTask);		
		
	}
	public void populateTable(ArrayList<Library> libraries) {
		
		LocalDate localDate = LocalDate.now();
		DayOfWeek weekDay = localDate.getDayOfWeek();
		int weekDayInt = weekDay.getValue();
		
		ArrayList<String> weekCheck = new ArrayList<String>(); 
		ArrayList<String> weekOpens = new ArrayList<String>(); 
		ArrayList<String> weekCloses = new ArrayList<String>(); 
		
		for (int i = 0; i < libraries.size(); ++i) {
			weekCheck = GlobalCollection.getBusinessHoursList().get(i).getCheck();
			weekOpens = GlobalCollection.getBusinessHoursList().get(i).getBeginTime();
			weekCloses = GlobalCollection.getBusinessHoursList().get(i).getEndTime();
			if (weekCheck.get(weekDayInt - 1).equals("Opened")) {
				libraries.get(i).setOpens(weekOpens.get(weekDayInt - 1));
				libraries.get(i).setCloses(weekCloses.get(weekDayInt - 1));
				
			} else {
				libraries.get(i).setOpens("-");
				libraries.get(i).setCloses("-");
			}
			
			GlobalCollection.getLibraryList().add(libraries.get(i));
		} 
		
		tableLibraryList. setItems(GlobalCollection.getLibraryList());
		firstNameCol.     setCellValueFactory(new PropertyValueFactory<Library, String>("firstName"));
		firstNameCol.     setStyle("-fx-alignment: CENTER;");
		countryCol.       setCellValueFactory(new PropertyValueFactory<Library, String>("country"));
		countryCol.       setStyle("-fx-alignment: CENTER;");
		cityCol.          setCellValueFactory(new PropertyValueFactory<Library, String>("city"));
		cityCol.          setStyle("-fx-alignment: CENTER;");
		postalCodeCol.    setCellValueFactory(new PropertyValueFactory<Library, String>("postalCode"));
		postalCodeCol.    setStyle("-fx-alignment: CENTER;");
		streetCol.        setCellValueFactory(new PropertyValueFactory<Library, String>("street"));
		streetCol.        setStyle("-fx-alignment: CENTER;");
		houseNumberCol.   setCellValueFactory(new PropertyValueFactory<Library, String>("houseNumber"));
		houseNumberCol.   setStyle("-fx-alignment: CENTER;");
		phoneNumberCol.   setCellValueFactory(new PropertyValueFactory<Library, String>("phoneNumber")); 
		phoneNumberCol.   setStyle("-fx-alignment: CENTER;");
		emailCol.         setCellValueFactory(new PropertyValueFactory<Library, String>("email"));
		emailCol.         setStyle("-fx-alignment: CENTER;");
		informationCol.   setCellValueFactory(new PropertyValueFactory<Library, String>("information"));
		informationCol.   setStyle("-fx-alignment: CENTER;");
		opensCol.         setCellValueFactory(new PropertyValueFactory<Library, String>("opens"));
		opensCol.         setStyle("-fx-alignment: CENTER;");
		closesCol.        setCellValueFactory(new PropertyValueFactory<Library, String>("closes"));
		closesCol.        setStyle("-fx-alignment: CENTER;");
		
		tableLibraryList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableLibraryList.getSelectionModel().getSelectedCells();
					
					if (!GlobalCollection.isPotentialOwner()) {
						try {
							GlobalCollection.setBusinessHours(GlobalCollection.getBusinessHoursList().get(cells.get(0).getRow()));
							GlobalCollection.setLibrary(GlobalCollection.getLibraryList().get(cells.get(0).getRow()));
							GlobalCollection.setEditable(false);
							BorderPane updateLibrary;
							updateLibrary = (BorderPane) FXMLLoader.load(getClass().getResource("UpdateLibrary-view.fxml"));
							((BorderPane) ViewProvider.getView("mainScreen")).setCenter(updateLibrary);
							
						} catch (Exception e) {
							
						}
					} else {
						GlobalCollection.emptyAddedLibrariesList();
						GlobalCollection.getAddedLibraries().add(GlobalCollection.getLibraryList().get(cells.get(0).getRow()));
						BorderPane addBook;
						try {
							addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
							((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
							
						} catch (IOException e) {
							
						}
					}
					
				}
			}
		});
	}
	
}
