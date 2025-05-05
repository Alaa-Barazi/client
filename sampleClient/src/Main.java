

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

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        clientConsole = new ClientConsole("localhost", 5555);
        switchScene("MainPage.fxml");
    }

    /**
     * Method for changing the displayed screen according to button pressed and apply the correct CSS styling for that page.
     * @param fxmlFile fxml file name to  load
     * @throws Exception it might ne thrown if the file doesn't exist of wrong name...
     */
    public static void switchScene(String fxmlFile) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        if (fxmlFile.equals("ShowOrders.fxml")) {
            scene.getStylesheets().add(Main.class.getResource("showorders.css").toExternalForm());
        } else if (fxmlFile.equals("UpdateOrder.fxml")) {
            scene.getStylesheets().add(Main.class.getResource("updateorder.css").toExternalForm());
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("AutoParkingReservation");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}