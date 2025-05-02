import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ClientAppGUI extends Application {

    private ClientConsole clientConsole;
    private TextArea outputArea;

    @Override
    public void start(Stage primaryStage) {
        // Initialize ClientConsole and override display for GUI
        clientConsole = new ClientConsole("localhost", 5555) {
            @Override
            public void display(Object message) {
                outputArea.appendText(message.toString() + "\n");
            }
        };

        // Input fields
        TextField orderNumberField = new TextField();
        orderNumberField.setPromptText("Order Number");

        TextField parkingSpaceField = new TextField();
        parkingSpaceField.setPromptText("Parking Space");

        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setPromptText("Select Date");

        // Disable past dates
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #eee;");
                }
            }
        });

        // Buttons
        Button updateButton = new Button("Update Order");
        Button showOrdersButton = new Button("Show All Orders");
        Button showClientInfoButton = new Button("Client Info");

        // Output area
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);

        // Button actions
        updateButton.setOnAction(e -> {
            clearOutput();
            try {
                int orderNumber = Integer.parseInt(orderNumberField.getText());
                int parkingSpace = Integer.parseInt(parkingSpaceField.getText());
                LocalDate localDate = datePicker.getValue();

                if (localDate == null) {
                    outputArea.appendText("Please select a date.\n");
                    return;
                }

                String date = localDate.toString();
                clientConsole.updateOrderDetails(orderNumber, parkingSpace, date);
            } catch (NumberFormatException ex) {
                outputArea.appendText("Invalid number format.\n");
            } catch (Exception ex) {
                outputArea.appendText("Error: " + ex.getMessage() + "\n");
            }
        });

        showOrdersButton.setOnAction(e -> {
            clearOutput();
            clientConsole.diplayAllOrders();
        });

        showClientInfoButton.setOnAction(e -> {
            clearOutput();
            clientConsole.showConnectedClientInfo();
        });

        // Layout
        VBox inputBox = new VBox(10,
                orderNumberField,
                parkingSpaceField,
                datePicker,
                updateButton,
                showOrdersButton,
                showClientInfoButton
        );
        inputBox.setPadding(new Insets(10));

        BorderPane layout = new BorderPane();
        layout.setLeft(inputBox);
        layout.setCenter(outputArea);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setTitle("Client Console GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void clearOutput() {
        outputArea.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
