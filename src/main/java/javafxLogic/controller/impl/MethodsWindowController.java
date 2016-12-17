package javafxLogic.controller.impl;

import algorithmLogic.DefaultRandom;
import algorithmLogic.AbstractRandomAlgorithm;
import algorithmLogic.VonNeumannAlgorithm;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafxLogic.controller.AbstractController;

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

        randomValuesQuantity = randomValuesQuantityTextField.getText();

        for (int i = 0; i < charts.size(); i++) {
            charts.get(i).getData().clear();
            drawBars(algorithms.get(i).generateArrayOfIndices(), charts.get(i), descriptions.get(i));
        }
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
            add(new VonNeumannAlgorithm(randomValuesQuantity));
            add(new DefaultRandom(randomValuesQuantity));
            add(new DefaultRandom(randomValuesQuantity));
        }};
    }
}
