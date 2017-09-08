package knjiznica.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindowOpen {
	
	public static void openWindow(String message) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText(message);
		
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.initOwner((Stage) ViewProvider.getView("primaryStage"));  
		
		alert.showAndWait();
		
	}
}
