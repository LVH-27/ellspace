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
import knjiznica.model.Genre;
import knjiznica.model.GlobalCollection;
import knjiznica.model.SelectGenres;
import knjiznica.model.ViewProvider;

public class ListGenresView {
	
	@FXML
	private Button acceptButton;
	
	@FXML
	private GridPane addedGenresGrid;
	
	@FXML
	private BorderPane addedGenresBorder;
	
	@FXML
	private TableView<Genre> tableGenreList;
	
	@FXML
	private TableColumn<Genre, String> nameCol;
	
	@FXML
	private TableColumn<Genre, String> nameHrCol;
	
	@FXML
	private TableColumn<Genre, String> nameDeCol;
	
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
		
		for (int i = 0; i < GlobalCollection.getAddedGenres().size(); ++i) {
			Label l = new Label();
			Button b = new Button();
			
			l.setText(GlobalCollection.getAddedGenres().get(i).getName());
			
			b.setMaxWidth(buttonSize); b.setPrefWidth(buttonSize); b.setMinWidth(buttonSize); b.setMaxHeight(buttonSize); b.setPrefHeight(buttonSize); b.setMinHeight(buttonSize);
			b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/remove-button.png"))));
			b.setId("smallButton");
			
			addedGenresGrid.addRow(i + 1, l, b);
			
			b.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	GlobalCollection.getAddedGenres().remove(GridPane.getRowIndex(l) - 1);
					addedGenresGrid.getChildren().removeAll(l, b);
					if (GlobalCollection.getAddedGenres().size() == 0) {
						addedGenresBorder.setManaged(false);
						addedGenresBorder.setVisible(false);
					}
			        ObservableList<Node> childrens = addedGenresGrid.getChildren();
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
		
		ArrayList<Genre> genres = new ArrayList<Genre>();
		
		Task<ArrayList<Genre>> getGenresTable = new Task<ArrayList<Genre>>() {
            @Override
            public ArrayList<Genre> call() throws Exception {
            	
    			Thread.sleep(600);
    			
    			return SelectGenres.select();  
            }
		};
		//TODO after thread fails populate genress table
		getGenresTable.setOnSucceeded(e -> {
			sp.getChildren().remove((MaskerPane) ViewProvider.getView("mask"));
			genres.addAll(getGenresTable.getValue());
			populateTable(genres);
		});
		exec.execute(getGenresTable);
		
	}
	
	private void populateTable(ArrayList<Genre> genres) {
		GlobalCollection.emptyList();
		
		for (int i = 0; i < genres.size(); ++i) {
			GlobalCollection.getGenresList().add(genres.get(i));
		} 
		
		tableGenreList. setItems(GlobalCollection.getGenresList());
		nameCol.  setCellValueFactory(new PropertyValueFactory<Genre, String>("name"));
		nameCol.  setStyle("-fx-alignment: CENTER;");
		nameHrCol.  setCellValueFactory(new PropertyValueFactory<Genre, String>("nameHr"));
		nameHrCol.  setStyle("-fx-alignment: CENTER;");
		nameDeCol.  setCellValueFactory(new PropertyValueFactory<Genre, String>("nameDe"));
		nameDeCol.  setStyle("-fx-alignment: CENTER;");
		tableGenreList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableGenreList.getSelectionModel().getSelectedCells();
					try {
						if (!GlobalCollection.getAddedGenres().contains(GlobalCollection.getGenresList().get(cells.get(0).getRow()))) {
							
							Label l = new Label();
							Button b = new Button();						
							addedGenresBorder.setManaged(true);
							addedGenresBorder.setVisible(true);

							l.setText(GlobalCollection.getGenresList().get(cells.get(0).getRow()).getName());
							
							b.setMaxWidth(buttonSize); b.setPrefWidth(buttonSize); b.setMinWidth(buttonSize); b.setMaxHeight(buttonSize); b.setPrefHeight(buttonSize); b.setMinHeight(buttonSize);
							b.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/resources/remove-button.png"))));
							b.setId("smallButton");
							
							GlobalCollection.getAddedGenres().add(GlobalCollection.getGenresList().get(cells.get(0).getRow()));
							addedGenresGrid.addRow(GlobalCollection.getAddedGenres().size(), l, b);
							
							b.setOnAction(new EventHandler<ActionEvent>() {
							    @Override
							    public void handle(ActionEvent e) {
							    	GlobalCollection.getAddedGenres().remove(GridPane.getRowIndex(l) - 1);
									addedGenresGrid.getChildren().removeAll(l, b);
							        ObservableList<Node> childrens = addedGenresGrid.getChildren();
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
