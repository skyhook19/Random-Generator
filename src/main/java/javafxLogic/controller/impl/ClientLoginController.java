package javafxLogic.controller.impl;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafxLogic.controller.AbstractController;
import java.io.IOException;
import static javafxLogic.constants.Errors.*;

public class ClientLoginController extends AbstractController{

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordField;

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
    private void handleCancel(){
        mainWindow.close();
    }

    @FXML
    private void handleLogin() {
        String errorMessage = isInputValid();

        if(errorMessage.length() != 0){
            showValidationError(errorMessage);
            return;
        }

        setMethodsWindowScene(mainWindow);
    }

    protected String isInputValid() {
        String errorMessage = "";
        String login = loginTextField.getText();
        String password = passwordField.getText();

        if (login == null || login.length() == 0) {
            errorMessage +=  WRONG_LOGIN + "\n";
        }
        if (password == null || password.length() == 0) {
            errorMessage += WRONG_PASSWORD + "\n";
        }

        return errorMessage;
    }

    public void setMethodsWindowScene(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClientLoginController.class.getResource("/fxml/MethodsWindow.fxml"));
            Pane rootLayout = loader.load();
            MethodsWindowController controller = loader.getController();
            controller.setMainWindow(primaryStage);
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Генератор случайных чисел");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
