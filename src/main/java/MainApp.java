import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafxLogic.controller.impl.ClientLoginController;

import java.io.IOException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setLoginWindowScene(primaryStage);
        primaryStage.show();
    }

    public void setLoginWindowScene(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ClientLoginWindow.fxml"));
            Pane rootLayout = loader.load();
            ClientLoginController controller = loader.getController();
            controller.setMainWindow(primaryStage);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
