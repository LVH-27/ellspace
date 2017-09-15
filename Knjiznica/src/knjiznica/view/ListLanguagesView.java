package knjiznica.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.controlsfx.control.MaskerPane;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import knjiznica.model.GlobalCollection;
import knjiznica.model.Language;
import knjiznica.model.SelectLanguages;
import knjiznica.model.ViewProvider;

public class ListLanguagesView {
	
	@FXML
	private Button acceptButton;
	
	@FXML
	private GridPane addedLanguagesGrid;
	
	@FXML
	private BorderPane addedLanguagesBorder;
	
	@FXML
	private TableView<Language> tableLanguageList;
	
	@FXML
	private TableColumn<Language, String> nameCol;
	
	@FXML
	private TableColumn<Language, String> nameHrCol;
	
	@FXML
	private TableColumn<Language, String> nameDeCol;
	
	private static StackPane sp = (StackPane) ViewProvider.getView("stackPane");
	private static Executor exec;
	private final static int buttonSize = 20;
	
	public void initialize() {
		
		
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
		
		Image imageAcceptButton = new Image(getClass().getResourceAsStream("/resources/accept-button.png"));
		acceptButton.setGraphic(new ImageView(imageAcceptButton));
		acceptButton.setId("transparentButton");
		
		sp.getChildren().add((MaskerPane) ViewProvider.getView("mask"));
		
		for (int i = 0; i < GlobalCollection.getAddedLanguages().size(); ++i) {
			Label l = new Label();
			Button b = new Button();
			
			l.setText(GlobalCollection.getAddedLanguages().get(i).getName());
			
			b.setMaxWidth(buttonSize); b.setPrefWidth(buttonSize); b.setMinWidth(buttonSize); b.setMaxHeight(buttonSize); b.setPrefHeight(buttonSize); b.setMinHeight(buttonSize);
			b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/remove-button.png"))));
			b.setId("smallButton");
			
			addedLanguagesGrid.addRow(i + 1, l, b);
			
			b.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	GlobalCollection.getAddedLanguages().remove(GridPane.getRowIndex(l) - 1);
					addedLanguagesGrid.getChildren().removeAll(l, b);
			        ObservableList<Node> childrens = addedLanguagesGrid.getChildren();
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
		
		ArrayList<Language> languages = new ArrayList<Language>();
		
		Task<ArrayList<Language>> getLanguagesTable = new Task<ArrayList<Language>>() {
            @Override
            public ArrayList<Language> call() throws Exception {
            	
    			Thread.sleep(600);
    			
    			return SelectLanguages.select();  
            }
		};
		//TODO after thread fails populate languages table
		getLanguagesTable.setOnSucceeded(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			languages.addAll(getLanguagesTable.getValue());
			populateTable(languages);
		});
		exec.execute(getLanguagesTable);
		
	}
	
	private void populateTable(ArrayList<Language> languages) {
		GlobalCollection.emptyList();
		
		for (int i = 0; i < languages.size(); ++i) {
			GlobalCollection.getLanguageList().add(languages.get(i));
		} 
		
		tableLanguageList. setItems(GlobalCollection.getLanguageList());
		nameCol.  setCellValueFactory(new PropertyValueFactory<Language, String>("name"));
		nameCol.  setStyle("-fx-alignment: CENTER;");
		nameHrCol.  setCellValueFactory(new PropertyValueFactory<Language, String>("nameHr"));
		nameHrCol.  setStyle("-fx-alignment: CENTER;");
		nameDeCol.  setCellValueFactory(new PropertyValueFactory<Language, String>("nameDe"));
		nameDeCol.  setStyle("-fx-alignment: CENTER;");
		tableLanguageList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableLanguageList.getSelectionModel().getSelectedCells();
					try {
						if (!GlobalCollection.getAddedLanguages().contains(GlobalCollection.getLanguageList().get(cells.get(0).getRow()))) {
							
							Label l = new Label();
							Button b = new Button();						
							addedLanguagesBorder.setManaged(true);
							addedLanguagesBorder.setVisible(true);

							l.setText(GlobalCollection.getLanguageList().get(cells.get(0).getRow()).getName());
							
							b.setMaxWidth(buttonSize); b.setPrefWidth(buttonSize); b.setMinWidth(buttonSize); b.setMaxHeight(buttonSize); b.setPrefHeight(buttonSize); b.setMinHeight(buttonSize);
							b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/remove-button.png"))));
							b.setId("smallButton");
							
							GlobalCollection.getAddedLanguages().add(GlobalCollection.getLanguageList().get(cells.get(0).getRow()));
							addedLanguagesGrid.addRow(GlobalCollection.getAddedLanguages().size(), l, b);
							
							b.setOnAction(new EventHandler<ActionEvent>() {
							    @Override
							    public void handle(ActionEvent e) {
							    	GlobalCollection.getAddedLanguages().remove(GridPane.getRowIndex(l) - 1);
									addedLanguagesGrid.getChildren().removeAll(l, b);
							        ObservableList<Node> childrens = addedLanguagesGrid.getChildren();
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
						
					} catch(Exception e) {
						
					}
					
				}
			}
		});
	}
	@FXML
	public void activateAccept() throws IOException {
		BorderPane addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
		((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
	}
}
