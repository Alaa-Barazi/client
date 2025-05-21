import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainPageController {

	@FXML
	private TextArea infoArea;

	@FXML
	public void initialize() {
		// Override display so messages from server go into infoArea
		Main.clientConsole = new ClientConsole(Main.serverIP, 5555) {
			@Override
			public void display(Object message) {
				Platform.runLater(() -> {
					if (message instanceof String str) {
						infoArea.setText(str);
					}
				});
			}
		};
	}

	public void handleConnectionInfo() {
		Main.clientConsole.accept("clientDetails");
	}

	public void handleOrders() {
		try {
			Main.switchScene("SubscriberLogin.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleUpdate() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderIDPage.fxml"));
			Parent root = loader.load();

			Stage newWindow = new Stage();
			newWindow.setTitle("Enter Order ID");
			newWindow.setScene(new Scene(root));
			newWindow.initOwner(((Stage) infoArea.getScene().getWindow()));
			newWindow.initModality(Modality.APPLICATION_MODAL); // blocks main window
			newWindow.showAndWait(); // waits until this window closes

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
