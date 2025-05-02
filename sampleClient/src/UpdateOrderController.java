
import data.UpdateOrderDetails;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;

public class UpdateOrderController {

	@FXML
	private TextField orderNumberField;
	@FXML
	private TextField parkingSpaceField;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Label statusLabel;

	@FXML
	public void initialize() {
		// Disable past dates
		datePicker.setDayCellFactory(picker -> new DateCell() {
			@Override
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date.isBefore(LocalDate.now())) {
					setDisable(true);
					setStyle("-fx-background-color: #eeeeee;");
				}
			}
		});

		// Set default date
		datePicker.setValue(LocalDate.now());

		// Override ClientConsole to capture server response
		Main.clientConsole = new ClientConsole("localhost", 5555) {
			@Override
			public void display(Object message) {
				Platform.runLater(() -> {
					if (message instanceof String str) {
						statusLabel.setText(str);
						if (str.toLowerCase().contains("invalid")) {
							statusLabel.setStyle("-fx-text-fill: red;");
						} else if (str.toLowerCase().contains("success")) {
							statusLabel.setStyle("-fx-text-fill: green;");
						} else {
							statusLabel.setStyle("-fx-text-fill: black;");
						}
					}
				});
			}
		};
	}

	public void handleUpdate() {
		try {
			int orderNumber = Integer.parseInt(orderNumberField.getText().trim());
			int parkingSpace = Integer.parseInt(parkingSpaceField.getText().trim());
			LocalDate localDate = datePicker.getValue();

			if (localDate == null) {
				statusLabel.setText("Please select a date.");
				statusLabel.setStyle("-fx-text-fill: red;");
				return;
			}

			Date sqlDate = Date.valueOf(localDate);
			UpdateOrderDetails update = new UpdateOrderDetails(orderNumber, parkingSpace, sqlDate);
			Main.clientConsole.accept(update);
		} catch (NumberFormatException e) {
			statusLabel.setText("Invalid number format.");
			statusLabel.setStyle("-fx-text-fill: red;");
		} catch (Exception e) {
			statusLabel.setText("Error: " + e.getMessage());
			statusLabel.setStyle("-fx-text-fill: red;");
		}
	}

	public void goBack() {
		try {
			Main.switchScene("MainPage.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
