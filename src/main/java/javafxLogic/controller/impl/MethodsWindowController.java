package javafxLogic.controller.impl;

import algorithmLogic.DefaultRandom;
import algorithmLogic.RandomAlgorithm;
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
    private TextField selectionSize;
    private String chosenSelectionSize = "";

    @FXML
    private BarChart defaultMethodBar;
    @FXML
    private BarChart fonNeumannBar;
    @FXML
    private BarChart bar3;
    @FXML
    private BarChart bar4;

    private List<BarChart> charts;
    private List<RandomAlgorithm> algorithms;
    private List<String> descriptions;

    protected void initialize() {

        charts = new ArrayList<BarChart>() {{
            add(defaultMethodBar);
            add(fonNeumannBar);
            add(bar3);
            add(bar4);
        }};

        algorithms = new ArrayList<RandomAlgorithm>() {{
            add(new DefaultRandom());
            add(new VonNeumannAlgorithm());
            add(new DefaultRandom());
            add(new DefaultRandom());
        }};

        descriptions = new ArrayList<String>() {{
            add("Стандартный рандом");
            add("Фон-Неймана");
            add("Линейный конгруэнтный");
            add("Метод половинных квадратов");
        }};

        selectionSize.textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        selectionSize.setText(newValue.replaceAll("[^\\d]", ""));
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

        chosenSelectionSize = selectionSize.getText();

        for (int i = 0; i < charts.size(); i++) {
            charts.get(i).getData().clear();
            drawBars(algorithms.get(i).run(Integer.parseInt(chosenSelectionSize)), charts.get(i), descriptions.get(i));
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
        String input = selectionSize.getText();

        if (input.isEmpty()) error += SELECTION_SIZE_IS_EMPTY + "\n";
        else if (input.startsWith("0")) error += NUMBER_STARTS_WITH_ZERO + "\n";
        else if (Integer.parseInt(input) > 50000) error += SELECTION_SIZE_IS_TOO_BIG + "\n";

        return error;
    }

    private boolean doesSelectionSizeChanged() {
        return !selectionSize.getText().equals(chosenSelectionSize);
    }

    private double computeDispersion(int[] countOfEntrance) {
        return 0;
    }
}
