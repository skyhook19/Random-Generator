package javafxLogic.controller.impl;

import algorithmLogic.DefaultRandom;
import algorithmLogic.AbstractRandomAlgorithm;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafxLogic.controller.AbstractController;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import static javafxLogic.constants.Errors.*;
import static javafxLogic.constants.Regions.*;

public class MethodsWindowController extends AbstractController {

    @FXML
    private TextField randomValuesQuantityTextField;
    private String randomValuesQuantity = "";

    @FXML
    private BarChart defaultMethodBar;
    @FXML
    private BarChart fonNeumannBar;
    @FXML
    private BarChart bar3;
    @FXML
    private BarChart bar4;

    private List<BarChart> charts;
    private List<AbstractRandomAlgorithm> algorithms;
    private List<String> descriptions;

    protected void initialize() {

        charts = new ArrayList<BarChart>() {{
            add(defaultMethodBar);
            add(fonNeumannBar);
            add(bar3);
            add(bar4);
        }};

        descriptions = new ArrayList<String>() {{
            add("Стандартный рандом");
            add("Фон-Неймана");
            add("Линейный конгруэнтный");
            add("Метод половинных квадратов");
        }};

        randomValuesQuantityTextField.textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        randomValuesQuantityTextField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
    }

    @FXML
    private void handleGenerateBtn() {
        String error = isInputValid();

        if (!isInputValid().isEmpty()) {
            showValidationError(error);
            return;
        }
        if (!doesSelectionSizeChanged()) {
            return;
        }

        initializeAlgorithms(Integer.parseInt(randomValuesQuantityTextField.getText()));
        String pattern = "##0.0000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        for (int i = 0; i < charts.size(); i++) {
            charts.get(i).getData().clear();
            drawBars(algorithms.get(i).generateArrayOfIndices(), charts.get(i),
                    descriptions.get(i) + ", Дисперсия = " + decimalFormat.format(algorithms.get(i).computeDispersion()));
        }
    }

    @FXML
    private void handleExitBtn() {
        setClientLoginWindowScene(mainWindow);
    }

    private void drawBars(int[] countOfEntrance, BarChart bar, String name) {
        bar.getXAxis().setAnimated(false);
        bar.getYAxis().setAnimated(false);
        bar.getXAxis().setLabel("Интервалы");
        bar.getYAxis().setLabel("Число вхождений");
        bar.setBarGap(1);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName(name);

        for (int i = 0; i < 20; i++) {
            String title = REGIONS.get(i);
            series1.getData().add(new XYChart.Data(title, countOfEntrance[i]));
        }
        bar.getData().add(series1);
    }

    @Override
    protected String isInputValid() {
        String error = "";
        String input = randomValuesQuantityTextField.getText();

        if (input.isEmpty()) error += SELECTION_SIZE_IS_EMPTY + "\n";
        else if (input.startsWith("0")) error += NUMBER_STARTS_WITH_ZERO + "\n";
        else if (Integer.parseInt(input) > 50000) error += SELECTION_SIZE_IS_TOO_BIG + "\n";

        return error;
    }

    private boolean doesSelectionSizeChanged() {
        return !randomValuesQuantityTextField.getText().equals(randomValuesQuantity);
    }

    private void initializeAlgorithms(int randomValuesQuantity){
        algorithms = new ArrayList<AbstractRandomAlgorithm>() {{
            add(new DefaultRandom(randomValuesQuantity));
            add(new DefaultRandom(randomValuesQuantity));
            add(new DefaultRandom(randomValuesQuantity));
            add(new DefaultRandom(randomValuesQuantity));
        }};
    }

    public void setClientLoginWindowScene(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MethodsWindowController.class.getResource("/fxml/ClientLoginWindow.fxml"));
            Pane rootLayout = loader.load();
            ClientLoginController controller = loader.getController();
            controller.setMainWindow(primaryStage);
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Вход");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
