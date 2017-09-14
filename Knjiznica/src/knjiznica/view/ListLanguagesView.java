package knjiznica.view;

import java.io.IOException;
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
import knjiznica.model.Language;
import knjiznica.model.SelectLanguages;
import knjiznica.model.SelectUsers;
import knjiznica.model.User;
import knjiznica.model.ViewProvider;

public class ListLanguagesView {
	@FXML
	private TableView<Language> tableLanguageList;
	
	@FXML
	private TableColumn<Language, String> nameCol;
	
	private static StackPane sp = (StackPane) ViewProvider.getView("stackPane");
	private static Executor exec;
	
	public void initialize() {
		
		exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
		
		sp.getChildren().add((MaskerPane) ViewProvider.getView("mask"));
		
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
		
	}
	
	private void populateTable(ArrayList<Language> languages) {
		GlobalCollection.emptyList();
		
		for (int i = 0; i < languages.size(); ++i) {
			GlobalCollection.getLanguageList().add(languages.get(i));
		} 
		for (int i = 0; i < GlobalCollection.getAddedLanguages().size(); ++i) {
			for (int j = 0; j < GlobalCollection.getLanguageList().size(); ++j) {
				if (GlobalCollection.getAddedLanguages().get(i).getID() == GlobalCollection.getLanguageList().get(j).getID()) {
					GlobalCollection.getAddedLanguages().set(i, GlobalCollection.getLanguageList().get(j));
					break;
				}
			}
		}
		tableLanguageList. setItems(GlobalCollection.getLanguageList());
		nameCol.  setCellValueFactory(new PropertyValueFactory<Language, String>("name"));
		nameCol.  setStyle("-fx-alignment: CENTER;");
		tableLanguageList.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("rawtypes")
					ObservableList<TablePosition> cells = tableLanguageList.getSelectionModel().getSelectedCells();
					GlobalCollection.getAddedLanguages().add(GlobalCollection.getLanguageList().get(cells.get(0).getRow()));
					BorderPane addBook;
					try {
						addBook = (BorderPane) FXMLLoader.load(getClass().getResource("AddBook-view.fxml"));
						((BorderPane) ViewProvider.getView("mainScreen")).setCenter(addBook);
						
					} catch (IOException e) {
			    	
					}
					
				}
			}
		});
	}
}
