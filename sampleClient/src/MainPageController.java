import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

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
            Main.switchScene("ShowOrder.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleUpdate() {
        try {
            Main.switchScene("UpdateOrder.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
