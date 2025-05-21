import data.Login;
import data.ResponseWrapper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SubscriberLoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;
	@FXML
	private Label errorLabel;
	@FXML
	private TextField visiblePasswordField;
	@FXML
	private ImageView eyeIcon;

	private Image eyeOpen;
	private Image eyeClosed;

	private boolean showingPassword = false;

	@FXML
	public void initialize() {
		eyeOpen = new Image(getClass().getResourceAsStream("/images/eye_open.png"));
		eyeClosed = new Image(getClass().getResourceAsStream("/images/eye_closed.png"));
		eyeIcon.setImage(eyeClosed);

		// Override ClientConsole to capture server response
		Main.clientConsole = new ClientConsole(Main.serverIP, 5555) {
			@Override
			public void display(Object message) {
				Platform.runLater(() -> {
					if (message.equals(true)) {
						errorLabel.setText("success");
						errorLabel.setStyle("-fx-text-fill: green;");
					} else {
						errorLabel.setText("wrong details");
						errorLabel.setStyle("-fx-text-fill: red;");
					}
				});
			}
		};
	}

	@FXML
	private void togglePasswordVisibility() {
		showingPassword = !showingPassword;

		if (showingPassword) {
			visiblePasswordField.setText(passwordField.getText());
			visiblePasswordField.setVisible(true);
			visiblePasswordField.setManaged(true);
			passwordField.setVisible(false);
			passwordField.setManaged(false);
			eyeIcon.setImage(eyeOpen);
		} else {
			passwordField.setText(visiblePasswordField.getText());
			passwordField.setVisible(true);
			passwordField.setManaged(true);
			visiblePasswordField.setVisible(false);
			visiblePasswordField.setManaged(false);
			eyeIcon.setImage(eyeClosed);
		}
	}

	@FXML
	private void handleLogin() {
		try {
			System.out.println("hableLogin");
			String username = usernameField.getText();
			String password = passwordField.isVisible() ? passwordField.getText() : visiblePasswordField.getText();
			System.out.println(username + " " + password);
			Login login = new Login(username, password);
			ResponseWrapper rsp = new ResponseWrapper("SUBSCRIBER_LOGIN", login);
			Main.clientConsole.accept(rsp);
		} catch (Exception e) {
			errorLabel.setText("Login failed due to error.");
			e.printStackTrace();
		}

	}

}
