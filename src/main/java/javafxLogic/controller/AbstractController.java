package javafxLogic.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public abstract class AbstractController {

    @FXML protected Pane mainWindow;

    @FXML
    protected abstract void initialize();
}
