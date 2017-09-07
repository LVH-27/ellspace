package knjiznica.view;

import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import knjiznica.model.ViewProvider;

public class MainView {
	
	@FXML 
	private Group root;
	
	private Scene scene = null;
	
	@FXML 
	private ImageView background;
	
	private double imageWidth = 0;
	private double imageHeight = 0;
	
	
	//@FXML
	//private MenuBar menuBar;
	
	public void initialize() {
		ViewProvider.setView("main", this);
	}
	
	public Group getRoot() {
		return root;
	}
	
	public void start() throws IOException {
		this.scene = root.getScene();
		root.setId("booklist");		
		Image backgroundImage = background.getImage();
		//this.img = backgroundImage;
		this.imageWidth = backgroundImage.getWidth();
		this.imageHeight = backgroundImage.getHeight();
		addListeners();
		centerBackground();
		BorderPane main = (BorderPane) FXMLLoader.load(getClass().getResource("Login-view.fxml"));
		setBorderPane(main);
	}
	
	
	@SuppressWarnings("unlikely-arg-type")
	public void setBorderPane(BorderPane borderPane) {
		Integer indexOfLast = root.getChildren().size() - 1;
		if (root.getChildren().get(indexOfLast) instanceof BorderPane) {
			root.getChildren().remove(indexOfLast);
		}		
		root.getChildren().add(borderPane);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
	}
	
	public void addListeners() {
		scene.heightProperty().addListener(
	            (ObservableValue<? extends Number> ov, Number t, Number t1) -> {
	            	centerBackground();
	            });
		scene.widthProperty().addListener(
	            (ObservableValue<? extends Number> ov, Number t, Number t1) -> {
	            	centerBackground();
	        	});
	}
	
	private void centerBackground() {
    	if (scene.getWidth()/imageWidth > scene.getHeight()/imageHeight) {
    		background.setFitWidth(scene.getWidth());
    		background.setFitHeight(scene.getWidth()/imageWidth*imageHeight); 		
    		background.setTranslateX(0);
    		background.setTranslateY(-(background.getFitHeight()-scene.getHeight())/2);
    	} else {
    		background.setFitWidth(scene.getHeight()/imageHeight*imageWidth);
    		background.setFitHeight(scene.getHeight());
    		background.setTranslateX(-(background.getFitWidth()-scene.getWidth())/2);
    		background.setTranslateY(0);
    	}
	}
}
