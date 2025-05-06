import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main screen of client GUI
 */
public class Main extends Application {
	private static Stage primaryStage;
	public static ClientConsole clientConsole;
	public static String serverIP = "localhost";

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		clientConsole = new ClientConsole(serverIP, 5555);
		switchScene("MainPage.fxml");
	}

	/*
	 * public static String serverIP = "localhost"; // default value public static
	 * ClientConsole clientConsole;
	 * 
	 * public static void main(String[] args) { if (args.length > 0) { serverIP =
	 * args[0]; // use the provided IP } launch(args); }
	 * 
	 * @Override public void start(Stage stage) throws Exception { clientConsole =
	 * new ClientConsole(serverIP, 5555); switchScene("MainPage.fxml"); }
	 */

	/**
	 * Static method for changing the displayed screen and applying the correct CSS
	 * styling.
	 * 
	 * @param fxmlFile The FXML file to load
	 * @throws Exception if FXML file is not found or has issues
	 */
	public static void switchScene(String fxmlFile) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		// Apply specific CSS per scene
		if (fxmlFile.equals("ShowOrder.fxml")) {
			scene.getStylesheets().add(Main.class.getResource("showorders.css").toExternalForm());
		} else if (fxmlFile.equals("UpdateOrder.fxml")) {
			scene.getStylesheets().add(Main.class.getResource("updateorder.css").toExternalForm());
		}

		primaryStage.setScene(scene);
		primaryStage.setTitle("AutoParking reservation");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		if (clientConsole != null && clientConsole.client != null) {
			System.out.println("Client is shutting down");
			clientConsole.client.closeConnection();
		}
		super.stop();
	}

}
