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
	
	public <T> void initialize() {
		
		ArrayList<User> users = SelectUsers.select();
		for (int i = 0; i < users.size(); ++i) {
			GlobalCollection.getList().add(users.get(i));
		}
		
		tableUserList.setItems(GlobalCollection.getList());
		
		firstNameCol.  setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		middleNameCol. setCellValueFactory(new PropertyValueFactory<User, String>("middleName"));
		lastNameCol.   setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		countryCol.    setCellValueFactory(new PropertyValueFactory<User, String>("country"));
		postalCodeCol. setCellValueFactory(new PropertyValueFactory<User, Integer>("postalCode"));
		streetCol.     setCellValueFactory(new PropertyValueFactory<User, String>("street"));
		houseNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("houseNumber"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
		
		tableUserList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableUserList.getSelectionModel().getSelectedCells();
					for (TablePosition<T, ?> cell : cells) {
					   System.out.println(GlobalCollection.getList().get(cell.getRow()).getID());
					}
				}
			}
		});
	}
}
