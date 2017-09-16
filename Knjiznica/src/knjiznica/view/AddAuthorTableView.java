package knjiznica.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

import org.controlsfx.control.MaskerPane;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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
import javafx.scene.layout.StackPane;
import knjiznica.model.AddAuthorToDatabase;
import knjiznica.model.AlertWindowOpen;
import knjiznica.model.Author;
import knjiznica.model.CheckInputLetters;
import knjiznica.model.ErrorLabelMessage;
import knjiznica.model.GlobalCollection;
import knjiznica.model.SelectAuthors;
import knjiznica.model.ViewProvider;

public class AddAuthorTableView {
	
	@FXML
	private TextField searchField;
	
	@FXML
	private TextField firstNameField;
	
	@FXML
	private TextField middleNameField;

	@FXML
	private TextField lastNameField;
	
	@FXML
	private CheckBox isAliveCheck;
	
	@FXML
	private TextField yearOfBirthField;
	
	@FXML
	private TextField yearOfDeathField;
	
	@FXML
	private Button addButton;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button acceptButton;
	
	@FXML
	private Label errorLabelMiss;
	
	@FXML
	private Label errorLabelTooMuch;
	
	@FXML
	private GridPane addedAuthorsGrid;
	
	@FXML
	private BorderPane addedAuthorsBorder;
	
	@FXML
	private TableView<Author> tableAuthorList;
	
	@FXML
	private TableColumn<Author, String> firstNameCol;
	
	@FXML
	private TableColumn<Author, String> middleNameCol;
	
	@FXML
	private TableColumn<Author, String> lastNameCol;
	
	@FXML
	private TableColumn<Author, String> yearOfBirthCol;
	
	@FXML
	private TableColumn<Author, String> yearOfDeathCol;
	
	public static boolean checkIndeterminate;
	
	public static boolean isInterrupted = false;
	public static boolean isReached = true; 
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String yearOfBirth;
	private String yearOfDeath;
	private boolean isAlive;
	private final static int buttonSize = 20;
	private static StackPane sp = (StackPane) ViewProvider.getView("stackPane");
	private static Executor exec;
	
	public void initialize() {
		
		//FIXME implement error when year has more than 4 digits and it should work when year is < 0
		//XXX Comment: Should be 5 digits for e.g. "-1649" as in "1649 B.C." - requires changes in database
		
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
		
		addedAuthorsBorder.setManaged(true);
		addedAuthorsBorder.setVisible(true);
		
		if (GlobalCollection.getAddedAuthors().size() == 0) {
			addedAuthorsBorder.setManaged(false);
			addedAuthorsBorder.setVisible(false);
		}
		
		for (int i = 0; i < GlobalCollection.getAddedAuthors().size(); ++i) {
			Label l = new Label();
			Button b = new Button();
			
			String firstName = GlobalCollection.getAddedAuthors().get(i).getFirstName();
			String middleNameFormat = " " + GlobalCollection.getAddedAuthors().get(i).getMiddleName() + " ";
			String lastName = GlobalCollection.getAddedAuthors().get(i).getLastName();
			
			if (middleNameFormat.equals(" - ")) {
				middleNameFormat = " ";
			}
			
			l.setText(firstName + middleNameFormat + lastName);
			
			b.setMaxWidth(buttonSize); b.setPrefWidth(buttonSize); b.setMinWidth(buttonSize); b.setMaxHeight(buttonSize); b.setPrefHeight(buttonSize); b.setMinHeight(buttonSize);
			b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/remove-button.png"))));
			b.setId("smallButton");
			
			addedAuthorsGrid.addRow(i + 1, l, b);
			
			b.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	GlobalCollection.getAddedAuthors().remove(GridPane.getRowIndex(l) - 1);
					addedAuthorsGrid.getChildren().removeAll(l, b);
					if (GlobalCollection.getAddedAuthors().size() == 0) {
						addedAuthorsBorder.setManaged(false);
						addedAuthorsBorder.setVisible(false);
					}
			        ObservableList<Node> childrens = addedAuthorsGrid.getChildren();
			        int i = 0;
			        for (Node node : childrens) {
			        	if (GridPane.getRowIndex(node) == null) {
			        		continue;
			        	}
			            GridPane.setRowIndex(node, i/2 + 1);
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
		
		Image imageAcceptButton = new Image(getClass().getResourceAsStream("/resources/accept-button.png"));
		acceptButton.setGraphic(new ImageView(imageAcceptButton));
		acceptButton.setId("transparentButton");
		
		sp.getChildren().add((MaskerPane) ViewProvider.getView("mask"));
				
		ArrayList<Author> authors = new ArrayList<Author>();
		
		Task<ArrayList<Author>> getAuthorsTableTask = new Task<ArrayList<Author>>() {
            @Override
            public ArrayList<Author> call() throws Exception {
            	
    			Thread.sleep(600);
    			
    			return SelectAuthors.select();  
            }
		};
		
		getAuthorsTableTask.setOnSucceeded(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			authors.addAll(getAuthorsTableTask.getValue());
			populateTable(authors);
		});
		getAuthorsTableTask.setOnFailed(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			afterThreadFails();
	    });
		exec.execute(getAuthorsTableTask);
		
	}
	
	@FXML
	private void activateBack() throws IOException {
		BorderPane addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
	}
	
	@FXML
	private void activateAdd() throws IOException {
		
		checkIndeterminate = false;
		
		isInterrupted = false;
		isReached = true;
		
		errorLabelMiss.   setVisible(false);
		errorLabelTooMuch.setVisible(false);
		
		firstName  = firstNameField. getText();
		middleName = middleNameField.getText();
		lastName   = lastNameField.  getText();
		isAlive    = isAliveCheck.   isSelected();
		
		if (!isAlive) {
			isAlive = isAliveCheck.isIndeterminate();
			if (isAlive) {
				checkIndeterminate = true;
			}
		}
		
		yearOfBirth = yearOfBirthField.getText();
		yearOfDeath = yearOfDeathField.getText();
		
		firstName   = firstName.  trim();
		middleName  = middleName. trim();
		lastName    = lastName.   trim();
		yearOfBirth = yearOfBirth.trim();
		yearOfDeath = yearOfDeath.trim();
		
		firstNameField.  setStyle("");
		middleNameField. setStyle("");
		lastNameField.   setStyle("");
		isAliveCheck.    setStyle("");
		yearOfBirthField.setStyle("");
		yearOfDeathField.setStyle("");
		
		boolean birth = true;
		boolean death = true;
		
		try {
			Integer.parseInt(yearOfBirth);
			
		} catch(Exception e) {
			birth = false;
			
		} finally {
			if (yearOfBirth.isEmpty()) {
				birth = true;
			}
		}
		
		try {
			Integer.parseInt(yearOfDeath);
			
		} catch(Exception e) {
			death = false;
			
		} finally {
			if (yearOfDeath.isEmpty()) {
				death = true;
			}
		}
		
		boolean showMiss = false;
		boolean showTooMuch = false;
		
		final String redBorder = "-fx-border-color: #ff0000;\n";
		
		if (firstName.isEmpty()) {
			firstNameField.setStyle(redBorder);
			showMiss = true;
			errorLabelMiss.setText(ErrorLabelMessage.getInfoMiss());
			errorLabelMiss.setVisible(true);
		}
		
		if (lastName.isEmpty()) {
			lastNameField.setStyle(redBorder);
			showMiss = true;
			errorLabelMiss.setText(ErrorLabelMessage.getInfoMiss());
			errorLabelMiss.setVisible(true);
		}
		
		if (!firstName.isEmpty() && !CheckInputLetters.check(firstName)) {
			firstNameField.setStyle(redBorder);
			showMiss = true;
			errorLabelMiss.setText(ErrorLabelMessage.getWrongFormat());
			errorLabelMiss.setVisible(true);
		}
		
		if (!middleName.isEmpty() && !CheckInputLetters.check(middleName)) {
			middleNameField.setStyle(redBorder);
			
			if (!showMiss) {
				showMiss = true;
				errorLabelMiss.setText(ErrorLabelMessage.getWrongFormat());
				errorLabelMiss.setVisible(true);
			}
		}
		
		if (!lastName.isEmpty() && !CheckInputLetters.check(lastName)) {
			lastNameField.setStyle(redBorder);
			
			if (!showMiss) {
				showMiss = true;
				errorLabelMiss.setText(ErrorLabelMessage.getWrongFormat());
				errorLabelMiss.setVisible(true);
			}
		}
		
		if (!birth) {
			yearOfBirthField.setStyle(redBorder);
			
			showTooMuch = true;
			errorLabelTooMuch.setText("Please enter birth year\n"
					+ "in 4 digits (e.g. 1973).");
			errorLabelTooMuch.setVisible(true);
		}
		
		if (!death && !isAliveCheck.isSelected() && !isAliveCheck.isIndeterminate()) {
			yearOfDeathField.setStyle(redBorder);
			
			if (!showTooMuch) {
				showTooMuch = true;
				errorLabelTooMuch.setText("Please enter death year\n"
						+ "in 4 digits (e.g. 1973).");
				errorLabelTooMuch.setVisible(true);
			}
		}			
		
		if (!yearOfBirth.isEmpty() && !yearOfDeath.isEmpty() && birth && death && Integer.parseInt(yearOfBirth) > Integer.parseInt(yearOfDeath)) {
			yearOfBirthField.setStyle(redBorder);
			yearOfDeathField.setStyle(redBorder);
			
			if (!showTooMuch) {
				showTooMuch = true;
				errorLabelTooMuch.setText("Year of birth cannot be\n"
						+ "larger than year of death.");
				errorLabelTooMuch.setVisible(true);
			}
		}
		
		if (!showMiss && !showTooMuch) {
			
			if (isAliveCheck.isSelected() || isAliveCheck.isIndeterminate() || yearOfDeath.isEmpty()) {
    			yearOfDeath = null;
    		}
			
			if (yearOfBirth.isEmpty()) {
				yearOfBirth = null;
			}
			sp.getChildren().add((MaskerPane) ViewProvider.getView("mask"));
			Task<Void> addAuthorToDatabaseTask = new Task<Void>() {
	            @Override
	            public Void call() throws Exception {
	            	
	    			Thread.sleep(600);
	    			
	    			AddAuthorToDatabase.addAuthor(firstName, middleName, lastName, isAlive, yearOfBirth, yearOfDeath);					
	    			return null;  
	            }
			};
			addAuthorToDatabaseTask.setOnSucceeded(e -> {
				try {
					afterThreadFinishes();
					
				} catch (IOException e1) {
						e1.printStackTrace();
				}
			});
			addAuthorToDatabaseTask.setOnFailed(e -> {
				afterThreadFails();
		    });
			errorLabelMiss.setVisible(false);
			errorLabelTooMuch.setVisible(false);
			exec.execute(addAuthorToDatabaseTask);	
		}
	}
	
	private void populateTable(ArrayList<Author>authors) {
		GlobalCollection.emptyList();
			
		for (int i = 0; i < authors.size(); ++i) {
			GlobalCollection.getAuthorList().add(authors.get(i));
		} 
		for (int i = 0; i < GlobalCollection.getAddedAuthors().size(); ++i) {
			for (int j = 0; j < GlobalCollection.getAuthorList().size(); ++j) {
				if (GlobalCollection.getAddedAuthors().get(i).getID() == GlobalCollection.getAuthorList().get(j).getID()) {
					GlobalCollection.getAddedAuthors().set(i, GlobalCollection.getAuthorList().get(j));
					break;
				}
			}
		}
		if (GlobalCollection.isAdd()) {
			Label l = new Label();
			Button b = new Button();
			
			String firstName = GlobalCollection.getAuthorList().get(GlobalCollection.getAuthorList().size() - 1).getFirstName();
			String middleNameFormat = " " + GlobalCollection.getAuthorList().get(GlobalCollection.getAuthorList().size() - 1).getMiddleName() + " ";
			String lastName = GlobalCollection.getAuthorList().get(GlobalCollection.getAuthorList().size() - 1).getLastName();
			
			if (middleNameFormat.equals(" - ")) {
				middleNameFormat = " ";
			}
			
			addedAuthorsBorder.setManaged(true);
			addedAuthorsBorder.setVisible(true);
			
			l.setText(firstName + middleNameFormat + lastName);
			
			b.setMaxWidth(buttonSize); b.setPrefWidth(buttonSize); b.setMinWidth(buttonSize); b.setMaxHeight(buttonSize); b.setPrefHeight(buttonSize); b.setMinHeight(buttonSize);
			b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/remove-button.png"))));
			b.setId("smallButton");
			
			addedAuthorsGrid.addRow(GlobalCollection.getAddedAuthors().size() + 1, l, b);
			GlobalCollection.getAddedAuthors().add(GlobalCollection.getAuthorList().get(GlobalCollection.getAuthorList().size() - 1));
	
			b.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	GlobalCollection.getAddedAuthors().remove(GridPane.getRowIndex(l) - 1);
					addedAuthorsGrid.getChildren().removeAll(l, b);
					if (GlobalCollection.getAddedAuthors().size() == 0) {
						addedAuthorsBorder.setManaged(false);
						addedAuthorsBorder.setVisible(false);
					}
			        ObservableList<Node> childrens = addedAuthorsGrid.getChildren();
			        int i = 0;
			        for (Node node : childrens) {
			        	if (GridPane.getRowIndex(node) == null) {
			        		continue;
			        	}
			            GridPane.setRowIndex(node, i/2 + 1);
			            i++;
			        }
			    }
			});
		}
		
		GlobalCollection.setAdd(false);
		
		tableAuthorList.setItems(GlobalCollection.getAuthorList());
		FilteredList<Author> filteredData = new FilteredList<Author>(GlobalCollection.getAuthorList(), e -> true);
		searchField.setOnKeyReleased(e -> {
			searchField.textProperty().addListener((observableValue, oldValue, newValue) ->{
				filteredData.setPredicate((Predicate<? super Author>) author ->{
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}
					
					String lowerCaseFilter = newValue.toLowerCase();
					String[] splitStr = lowerCaseFilter.split(" ");
					ArrayList<String> splittedFilter = new ArrayList<String>();
					ArrayList<String> splittedAuthorData = new ArrayList<String>();
					
					for (int i = 0; i < splitStr.length; ++i) {
						splittedFilter.add(splitStr[i]);
					}
					
					splittedAuthorData.add(author.getFirstName().  toLowerCase());
					splittedAuthorData.add(author.getMiddleName(). toLowerCase());
					splittedAuthorData.add(author.getLastName().   toLowerCase());
					splittedAuthorData.add(author.getYearOfBirth().toLowerCase());
					splittedAuthorData.add(author.getYearOfDeath().toLowerCase());
					
					int i;
					for (i = 0; i < splittedFilter.size(); ++i) {
						int j;
						for (j = 0; j < splittedAuthorData.size(); ++j) {
							if (splittedAuthorData.get(j).contains(splittedFilter.get(i))) {
								break;
							}
						}
						
						if (j == splittedAuthorData.size()) {
							break;
						}
					}
					
					if (i == splittedFilter.size()) {
						return true;
					}
					
					return false;
				});
			});
			
			SortedList<Author> sortedData = new SortedList<Author>(filteredData);
			sortedData.comparatorProperty().bind(tableAuthorList.comparatorProperty());
			tableAuthorList.setItems(sortedData);
		});
		
		firstNameCol.  setCellValueFactory(new PropertyValueFactory<Author, String>("firstName"));
		firstNameCol.  setStyle("-fx-alignment: CENTER;");
		middleNameCol. setCellValueFactory(new PropertyValueFactory<Author, String>("middleName"));
		middleNameCol. setStyle("-fx-alignment: CENTER;");
		lastNameCol.   setCellValueFactory(new PropertyValueFactory<Author, String>("lastName"));
		lastNameCol.   setStyle("-fx-alignment: CENTER;");
		yearOfBirthCol.setCellValueFactory(new PropertyValueFactory<Author, String>("yearOfBirth"));
		yearOfBirthCol.setStyle("-fx-alignment: CENTER;");
		yearOfDeathCol.setCellValueFactory(new PropertyValueFactory<Author, String>("yearOfDeath"));
		yearOfDeathCol.setStyle("-fx-alignment: CENTER;");
		
		tableAuthorList.setOnMouseClicked(new EventHandler<MouseEvent>() {
	
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableAuthorList.getSelectionModel().getSelectedCells();
					
					try {
						if (!GlobalCollection.getAddedAuthors().contains(GlobalCollection.getAuthorList().get(cells.get(0).getRow()))) {
							Label l = new Label();
							Button b = new Button();
							
							addedAuthorsBorder.setManaged(true);
							addedAuthorsBorder.setVisible(true);
							String firstName = GlobalCollection.getAuthorList().get(cells.get(0).getRow()).getFirstName();
							String middleNameFormat = " " + GlobalCollection.getAuthorList().get(cells.get(0).getRow()).getMiddleName() + " ";
							String lastName = GlobalCollection.getAuthorList().get(cells.get(0).getRow()).getLastName();
							
							if (middleNameFormat.equals(" - ")) {
								middleNameFormat = " ";
							}
							
							l.setText(firstName + middleNameFormat + lastName);
							
							b.setMaxWidth(buttonSize); b.setPrefWidth(buttonSize); b.setMinWidth(buttonSize); b.setMaxHeight(buttonSize); b.setPrefHeight(buttonSize); b.setMinHeight(buttonSize);
							b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/remove-button.png"))));
							b.setId("smallButton");
							
							GlobalCollection.getAddedAuthors().add(GlobalCollection.getAuthorList().get(cells.get(0).getRow()));
							addedAuthorsGrid.addRow(GlobalCollection.getAddedAuthors().size(), l, b);
							
							b.setOnAction(new EventHandler<ActionEvent>() {
							    @Override
							    public void handle(ActionEvent e) {
							    	GlobalCollection.getAddedAuthors().remove(GridPane.getRowIndex(l) - 1);
									addedAuthorsGrid.getChildren().removeAll(l, b);
									if (GlobalCollection.getAddedAuthors().size() == 0) {
										addedAuthorsBorder.setManaged(false);
										addedAuthorsBorder.setVisible(false);
									}
							        ObservableList<Node> childrens = addedAuthorsGrid.getChildren();
							        int i = 0;
							        for (Node node : childrens) {
							        	if (GridPane.getRowIndex(node) == null) {
							        		continue;
							        	}
							            GridPane.setRowIndex(node, i/2 + 1);
							            i++;
							        }
							    }
							});
						}
					} catch (Exception e) {
						
					}
				}
			}
		});
	}
	private void afterThreadFinishes() throws IOException {
		sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
		if (!isInterrupted && isReached) {
			AlertWindowOpen.openWindow("Author successfully added!");
    		GlobalCollection.setAdd(true);
    		BorderPane addAuthor = (BorderPane) FXMLLoader.load(getClass().getResource("AddAuthorTable-view.fxml"));
        	((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addAuthor);
    		
    	} else if (isInterrupted) {
    		errorLabelMiss.setText(ErrorLabelMessage.getFailReach());
    		errorLabelMiss.setVisible(true);
    		
    	} else {
        	errorLabelMiss.setText(ErrorLabelMessage.getSomething());
    		errorLabelMiss.setVisible(true);
    	}
		
	}
	
	private void afterThreadFails() {
		sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
		errorLabelMiss.setText(ErrorLabelMessage.getSomething());
		errorLabelMiss.setVisible(true);
	}
	
	@FXML
	public void disableYearOfDeath() {
		if (isAliveCheck.isSelected() || isAliveCheck.isIndeterminate()) {
			yearOfDeathField.setStyle("");
			yearOfDeathField.setDisable(true);
		} else {
			yearOfDeathField.setDisable(false);
		}
	}
	
	@FXML
	public void activateAccept() throws IOException {
		BorderPane addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
	}
}
