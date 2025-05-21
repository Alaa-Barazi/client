import data.Login;
import data.ResponseWrapper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController {

	@FXML
	private TextField usernameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField visiblePasswordField;

	@FXML
	private ImageView eyeIcon;

	@FXML
	private Label errorLabel;

	private static Label errorLabelStatic;

	private Image eyeOpen;
	private Image eyeClosed;
	private boolean showingPassword = false;

	@FXML
	public void initialize() {
		errorLabelStatic = errorLabel;

		eyeOpen = new Image(getClass().getResourceAsStream("/images/eye_open.png"));
		eyeClosed = new Image(getClass().getResourceAsStream("/images/eye_closed.png"));
		eyeIcon.setImage(eyeClosed);

		// Attach a display handler only once (in Main or init app logic)
		// Do NOT create a new clientConsole here

		// Override ClientConsole to capture server response
		Main.clientConsole = new ClientConsole(Main.serverIP, 5555) {
			@Override
			public void display(Object role) {
				Platform.runLater(() -> {
					try {
						ResponseWrapper rsp = (ResponseWrapper) role;
						System.out.println("role20" + role.toString() + " rsp type " + rsp.getType());
						switch (rsp.getData().toString()) {
						case "subscriber":
							System.out.println("subscriberHomePage.fxml");
							Main.switchScene("subscriberHomePage.fxml");
							break;
						case "worker":
							System.out.println("workerHomePage.fxml");
							Main.switchScene("workerHomePage.fxml");
							break;
						case "manager":
							System.out.println("managerHomePage.fxml");
							Main.switchScene("managerHomePage.fxml");
							break;
						default:
							if (errorLabelStatic != null) {
								errorLabelStatic.setText("Invalid username or password.");
								errorLabelStatic.setStyle("-fx-text-fill: red;");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
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
			String username = usernameField.getText().trim();
			String password = passwordField.isVisible() ? passwordField.getText().trim()
					: visiblePasswordField.getText().trim();
			System.out.println(username + "->" + password);
			if (username.isEmpty() || password.isEmpty()) {
				errorLabel.setText("Username and password are required.");
				return;
			}
			if (username.equals("admin") && password.equals("admin")) {

			}
			Login login = new Login(username, password);
			ResponseWrapper rsp = new ResponseWrapper("LOGIN", login);
			Main.clientConsole.accept(rsp);
		} catch (Exception e) {
			errorLabel.setText("Login failed due to error.");
			e.printStackTrace();
		}
	}

}
