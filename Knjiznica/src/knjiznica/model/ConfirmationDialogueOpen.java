package knjiznica.model;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ConfirmationDialogueOpen {
	
	public static void open() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("ISBN already in use!");
		alert.setContentText("Using this ISBN will apply changes to title, summary, authors, publishers, genres and languages?");

		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.OK){
		    GlobalCollection.setOk(true);
		} else {
		    GlobalCollection.setOk(false);
		}
	}
}
