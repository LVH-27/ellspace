package knjiznica.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import knjiznica.model.AddPublisherToDatabase;
import knjiznica.model.AlertWindowOpen;
import knjiznica.model.ErrorLabelMessage;
import knjiznica.model.FormattingName;
import knjiznica.model.GlobalCollection;
import knjiznica.model.PostalCodeComboThread;
import knjiznica.model.Publisher;
import knjiznica.model.SelectPublishers;
import knjiznica.model.ViewProvider;


public class AddPublisherView { 
	
	@FXML
	private TextField nameField;
	
	@FXML
	private TextField countryField;
	
	@FXML
	private TextField streetField;
	
	@FXML
	private TextField houseNumberField;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private TableView<Publisher> tablePublisherList;
	
	@FXML
	private TableColumn<Publisher, String> nameCol;
	
	@FXML
	private TableColumn<Publisher, String> countryCol;
	
	@FXML
	private TableColumn<Publisher, String> streetCol;
	
	@FXML
	private TableColumn<Publisher, String> houseNumberCol;
	
	@FXML
	private TableColumn<Publisher, String> postalCodeCol;
	
	@FXML
	private TableColumn<Publisher, String> cityCol;
	
	@FXML
	private TextField searchField;
	
	@FXML
	private GridPane addedPublishersGrid;
	
	@FXML
	private ComboBox<String> postalCodeCombo;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private CheckBox addressCheck;
	
	public static boolean isInterrupted = false;
	public static boolean isReached = true;
	
	private String nameCombo = "postalCodeComboAddPublisher";
	private String publisherName;
	private String country;
	private String street;
	private String houseNumber;
	private String postalCode;
	private SingleSelectionModel<String> postalCodeSingle;
	private boolean check;
	
	public void initialize() {
		
		for(int i = 0; i < GlobalCollection.getAddedPublishers().size(); ++i) {
			HBox container = new HBox(8);
			Label l1 = new Label();
			l1.setText(GlobalCollection.getAddedPublishers().get(i).getName());
			Button b1 = new Button();
			b1.setText("-");
			final Pos CENTER = Pos.CENTER;
			container.getChildren().addAll(l1, b1);
			container.setAlignment(CENTER);
			addedPublishersGrid.addRow(i + 1, container);
			b1.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	GlobalCollection.getAddedPublishers().remove(GridPane.getRowIndex(container) - 1);
					addedPublishersGrid.getChildren().remove(container);
			   
			        ObservableList<Node> childrens = addedPublishersGrid.getChildren();
			        int i = 1;
			        for (Node node : childrens) {
			        	if(GridPane.getRowIndex(node) == null) {
			        		continue;
			        	}
			            GridPane.setRowIndex(node, i);
			            i++;
			        }
					
			    }
			});
		}
		Image imageAddButton = new Image(getClass().getResourceAsStream("/resources/add-button.png"));
		addButton.setGraphic(new ImageView(imageAddButton));
		addButton.setId("transparentButton");
		
		Image imageBackButton = new Image(getClass().getResourceAsStream("/resources/back-button.png"));
		backButton.setGraphic(new ImageView(imageBackButton));
		backButton.setId("transparentButton");
		
		ViewProvider.setView(nameCombo, postalCodeCombo);
		PostalCodeComboThread.setComboData(nameCombo);

		ArrayList<Publisher> publishers = SelectPublishers.select(); 
		
		GlobalCollection.emptyList();
		
		for (int i = 0; i < publishers.size(); ++i) {
			GlobalCollection.getPublisherList().add(publishers.get(i));
		} 
		if(GlobalCollection.isAdd()) {
			HBox container = new HBox(8);
			Label l1 = new Label();
			l1.setText(GlobalCollection.getPublisherList().get(GlobalCollection.getPublisherList().size() - 1).getName());
			Button b1 = new Button();
			b1.setText("-");
			final Pos CENTER = Pos.CENTER;
			container.getChildren().addAll(l1, b1);
			container.setAlignment(CENTER);
			addedPublishersGrid.addRow(GlobalCollection.getAddedPublishers().size() + 1, container);
			b1.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	GlobalCollection.getAddedPublishers().remove(GridPane.getRowIndex(container) - 1);
					addedPublishersGrid.getChildren().remove(container);
			   
			        ObservableList<Node> childrens = addedPublishersGrid.getChildren();
			        int i = 1;
			        for (Node node : childrens) {
			        	if(GridPane.getRowIndex(node) == null) {
			        		continue;
			        	}
			            GridPane.setRowIndex(node, i);
			            i++;
			        }
					
			    }
			});
		}
		tablePublisherList.setItems(GlobalCollection.getPublisherList());
		FilteredList<Publisher> filteredData = new FilteredList<Publisher>(GlobalCollection.getPublisherList(), e -> true);
		searchField.setOnKeyReleased(e -> {
			searchField.textProperty().addListener((observableValue, oldValue, newValue) ->{
				filteredData.setPredicate((Predicate<? super Publisher>) publisher ->{
					if(newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					String[] splitStr = lowerCaseFilter.split(" ");
					ArrayList<String> splittedFilter = new ArrayList<String>();
					ArrayList<String> splittedAuthorData = new ArrayList<String>();
					
					for(int i = 0; i < splitStr.length; ++i) {
						splittedFilter.add(splitStr[i]);
					}
					splittedAuthorData.add(publisher.getName().toLowerCase());
					splittedAuthorData.add(publisher.getCountry().toLowerCase());
					splittedAuthorData.add(publisher.getPostalCode().toLowerCase());
					splittedAuthorData.add(publisher.getStreet().toLowerCase());
					splittedAuthorData.add(publisher.getHouseNumber().toLowerCase());
					splittedAuthorData.add(publisher.getCityName().toLowerCase());
					int i;
					for(i = 0; i < splittedFilter.size(); ++i) {
						int j;
						for(j = 0; j < splittedAuthorData.size(); ++j) {
							if(splittedAuthorData.get(j).contains(splittedFilter.get(i))) {
								break;
							}
						}
						if(j == splittedAuthorData.size()) {
							break;
						}
					}
					if(i == splittedFilter.size()) {
						return true;
					}
					
					return false;
				});
			});
			SortedList<Publisher> sortedData = new SortedList<Publisher>(filteredData);
			sortedData.comparatorProperty().bind(tablePublisherList.comparatorProperty());
			tablePublisherList.setItems(sortedData);
		});
		nameCol.   setCellValueFactory(new PropertyValueFactory<Publisher, String>("name"));
		nameCol.   setStyle("-fx-alignment: CENTER;");
		countryCol.   setCellValueFactory(new PropertyValueFactory<Publisher, String>("country"));
		countryCol.   setStyle("-fx-alignment: CENTER;");
		streetCol.   setCellValueFactory(new PropertyValueFactory<Publisher, String>("street"));
		streetCol.   setStyle("-fx-alignment: CENTER;");
		houseNumberCol.   setCellValueFactory(new PropertyValueFactory<Publisher, String>("houseNumber"));
		houseNumberCol.   setStyle("-fx-alignment: CENTER;");
		postalCodeCol.   setCellValueFactory(new PropertyValueFactory<Publisher, String>("postalCode"));
		postalCodeCol.   setStyle("-fx-alignment: CENTER;");
		cityCol.   setCellValueFactory(new PropertyValueFactory<Publisher, String>("cityName"));
		cityCol.   setStyle("-fx-alignment: CENTER;");
		
		tablePublisherList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tablePublisherList.getSelectionModel().getSelectedCells();
					try {
						if(!GlobalCollection.getAddedPublishers().contains(GlobalCollection.getPublisherList().get(cells.get(0).getRow()))) {
							HBox container = new HBox(8);
							Label l1 = new Label();
							l1.setText(GlobalCollection.getPublisherList().get(cells.get(0).getRow()).getName());
							Button b1 = new Button();
							b1.setText("-");
							final Pos CENTER = Pos.CENTER;
							container.getChildren().addAll(l1, b1);
							container.setAlignment(CENTER);
							GlobalCollection.getAddedPublishers().add(GlobalCollection.getPublisherList().get(cells.get(0).getRow()));
							addedPublishersGrid.addRow(GlobalCollection.getAddedPublishers().size(), container);
							b1.setOnAction(new EventHandler<ActionEvent>() {
							    @Override public void handle(ActionEvent e) {
							    	GlobalCollection.getAddedPublishers().remove(GridPane.getRowIndex(container) - 1);
									addedPublishersGrid.getChildren().remove(container);
							   
							        ObservableList<Node> childrens = addedPublishersGrid.getChildren();
							        int i = 1;
							        for (Node node : childrens) {
							        	if(GridPane.getRowIndex(node) == null) {
							        		continue;
							        	}
							            GridPane.setRowIndex(node, i);
							            i++;
							        }
									
							    }
							});
						}	
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
	}
	
	@FXML
	private void toggleCheck() {
		if (addressCheck.isSelected()) {
			countryField.setVisible(true);
			streetField.setVisible(true);
			houseNumberField.setVisible(true);
			postalCodeCombo.setVisible(true);

		} else {
			countryField.setVisible(false);
			streetField.setVisible(false);
			houseNumberField.setVisible(false);
			postalCodeCombo.setVisible(false);
		}
	}
	
	@FXML
	private void activateBack() throws IOException {
		//TODO We need to return to AddBook but without resetting TextFields
//		BorderPane clientsMenu = (BorderPane) ViewProvider.getView("clientsMenu");
//		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(clientsMenu);
	}
	
	@FXML
	private void activateAdd() throws IOException {
		
		isInterrupted = false;
		isReached = true;
		
		errorLabel.setVisible(false);
		postalCodeSingle = postalCodeCombo.getSelectionModel();
		publisherName = nameField.getText();
		country = countryField.getText();
		street = streetField.getText();
		houseNumber = houseNumberField.getText();
		
		publisherName = publisherName.trim();
		country = country.trim();
		street = street.trim();
		houseNumber = houseNumber.trim();
		
		nameField.setStyle("");
		postalCodeCombo.setStyle("");
		countryField.setStyle("");
		streetField.setStyle("");
		houseNumberField.setStyle("");
		
		final String redBorder = "-fx-border-color: #ff0000;\n";
		
		check = true;
		
		
		if (publisherName.isEmpty()) {
			check = false;
			nameField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);	
		}
		
		if (postalCodeSingle.isEmpty() && addressCheck.isSelected()) {
			check = false;
			postalCodeCombo.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
		} 
		
		if (country.isEmpty() && addressCheck.isSelected()) {
			check = false;
			countryField.setStyle(redBorder);
			errorLabel.setText(ErrorLabelMessage.getInfoMiss());
			errorLabel.setVisible(true);
		} 
		
		if (houseNumber.length() > 6) {
			check = false;
			houseNumberField.setStyle(redBorder);
			if (!errorLabel.isVisible()) {
				errorLabel.setText(ErrorLabelMessage.getWrongFormat());
				errorLabel.setVisible(true);
			}
		}
		
		if (check) {
			
			if (street.isEmpty()) {
				street = null;
			} 
			
			if (houseNumber.isEmpty()) {
				houseNumber = null;
			}
			
			postalCode = postalCodeSingle.getSelectedItem();
			publisherName = FormattingName.format(publisherName);
			
			errorLabel.setVisible(false);
			
			int postalCodeInt = -1;
			
			if(addressCheck.isSelected()) {
				postalCodeInt = Integer.parseInt((postalCode.split(" - "))[0]);
			}
			
			
			isInterrupted = false;
			isReached = true;
			
			AddPublisherToDatabase.addPublisher(publisherName, country, postalCodeInt, street, houseNumber, addressCheck.isSelected());
			
			if (!isInterrupted && isReached) {
				AlertWindowOpen.openWindow("Publisher successfully added!");
				GlobalCollection.setAdd(true);
	    		//TODO We need to return to AddBook but without resetting TextFields, inserting this publisher
				BorderPane addPublisherTable = (BorderPane) FXMLLoader.load(getClass().getResource("AddPublisherTable-view.fxml"));
		    	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addPublisherTable);
		    	
			} else if (isInterrupted) {
				errorLabel.setText(ErrorLabelMessage.getSomething());
				errorLabel.setVisible(true);
				
			} else {
				errorLabel.setText(ErrorLabelMessage.getFailReach());
				errorLabel.setVisible(true); 	
			}	
		}
	}
}
