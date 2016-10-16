package javafxLogic.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public abstract class AbstractController {

    @FXML protected Stage mainWindow;

    @FXML
    protected abstract void initialize();

    public Stage getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    protected abstract String isInputValid();

    protected void showValidationError(String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainWindow);
        alert.setTitle("Invalid input");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }
}
