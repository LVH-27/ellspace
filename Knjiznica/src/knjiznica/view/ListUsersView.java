package knjiznica.view;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import knjiznica.model.GlobalCollection;
import knjiznica.model.SelectUsers;
import knjiznica.model.User;

public class ListUsersView {
	
	@FXML
	private TableView<User> tableUserList;
	
	@FXML
	private TableColumn<User, String> firstNameCol;
	
	@FXML
	private TableColumn<User, String> middleNameCol;
	
	@FXML
	private TableColumn<User, String> lastNameCol;
	
	@FXML
	private TableColumn<User, String> countryCol;
	
	@FXML
	private TableColumn<User, Integer> postalCodeCol;
	
	@FXML
	private TableColumn<User, String> streetCol;
	
	@FXML
	private TableColumn<User, String> houseNumberCol;
	
	@FXML
	private TableColumn<User, String> phoneNumberCol;
	
	public void initialize() {
		
		ArrayList<User> users = SelectUsers.select();
		
		GlobalCollection.emptyList();
		
		for (int i = 0; i < users.size(); ++i) {
				
			GlobalCollection.getList().add(users.get(i));
			
		}
		
		tableUserList.setItems(GlobalCollection.getList());
		firstNameCol.  setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		firstNameCol.setStyle("-fx-alignment: CENTER;");
		middleNameCol. setCellValueFactory(new PropertyValueFactory<User, String>("middleName"));
		middleNameCol.setStyle("-fx-alignment: CENTER;");
		lastNameCol.   setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		lastNameCol.setStyle("-fx-alignment: CENTER;");
		countryCol.    setCellValueFactory(new PropertyValueFactory<User, String>("country"));
		countryCol.setStyle("-fx-alignment: CENTER;");
		postalCodeCol. setCellValueFactory(new PropertyValueFactory<User, Integer>("postalCode"));
		postalCodeCol.setStyle("-fx-alignment: CENTER;");
		streetCol.     setCellValueFactory(new PropertyValueFactory<User, String>("street"));
		streetCol.setStyle("-fx-alignment: CENTER;");
		houseNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("houseNumber"));
		houseNumberCol.setStyle("-fx-alignment: CENTER;");
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
		phoneNumberCol.setStyle("-fx-alignment: CENTER;");
		
		tableUserList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			//@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableUserList.getSelectionModel().getSelectedCells();
					for (TablePosition<?, ?> cell : cells) {
					   System.out.println(GlobalCollection.getList().get(cell.getRow()).getID());
					}
				}
			}
		});
	}
}
