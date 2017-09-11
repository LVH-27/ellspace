package knjiznica.view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
	
	public void initialize() {
		
		GlobalCollection.emptyList();
		
		ArrayList<Library> libraries = SelectLibraries.select(); 
		
		for (int i = 0; i < libraries.size(); ++i) {
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
		
		tableLibraryList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableLibraryList.getSelectionModel().getSelectedCells();
					GlobalCollection.setBusinessHours(GlobalCollection.getBusinessHoursList().get(cells.get(0).getRow()));
					GlobalCollection.setLibrary(GlobalCollection.getLibraryList().get(cells.get(0).getRow()));
					GlobalCollection.setEditable(false);
					BorderPane updateLibrary;
					
					try {
						updateLibrary = (BorderPane) FXMLLoader.load(getClass().getResource("UpdateLibrary-view.fxml"));
						((BorderPane) ViewProvider.getView("mainScreen")).setCenter(updateLibrary);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
