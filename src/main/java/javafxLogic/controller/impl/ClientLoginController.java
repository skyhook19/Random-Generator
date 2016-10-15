package javafxLogic.controller.impl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafxLogic.controller.AbstractController;

public class ClientLoginController extends AbstractController{

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button cancelBtn;

    @FXML
    protected void initialize() {
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent keyEvent)
            {
                if(keyEvent.getCode() == KeyCode.ENTER)
                {
                   handleLogin();
                }
            }
        });

    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) mainWindow.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleLogin() {
        String errorMessage = isInputValid();

        if(errorMessage.length() != 0){
            showValidationError(errorMessage);
            return;
        }
    }

    private String isInputValid() {
        String errorMessage = "";
        String login = loginTextField.getText();
        String password = passwordField.getText();

        if (login == null || login.length() == 0) {
            errorMessage += "Login is not valid!\n";
        }
        if (password == null || password.length() == 0) {
            errorMessage += "Password is not valid!\n";
        }

        return errorMessage;
    }

    private void showValidationError(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainWindow.getScene().getWindow());
        alert.setTitle("Invalid Fields");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
