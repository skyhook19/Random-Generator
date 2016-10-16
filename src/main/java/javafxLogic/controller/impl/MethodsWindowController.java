package javafxLogic.controller.impl;

import algorithmLogic.DefaultRandom;
import algorithmLogic.RandomAlgorithm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafxLogic.controller.AbstractController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javafxLogic.constants.Regions.*;
import static javafxLogic.constants.Errors.*;

public class MethodsWindowController extends AbstractController{

    /*
    @FXML
    private ComboBox methodCheckBox;
    @FXML
    private ComboBox intervalCheckBox;
    private String chosenMethod = "";
    */

    @FXML
    private TextField selectionSize;
    private String chosenSelectionSize = "";

    @FXML
    private BarChart barChart;

    private HashMap<String, RandomAlgorithm> algorithms;

    protected void initialize() {
        algorithms = new HashMap<String, RandomAlgorithm>(){{
            put("Стандартный рандом",new DefaultRandom());
            put("Метод Фон-Неймана",new DefaultRandom());
            put("Метод 3",new DefaultRandom());
            put("Метод 4",new DefaultRandom());
        }};

        selectionSize.textProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        selectionSize.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
    }

    @FXML
    private void handleGenerateBtn(){
        String error = isInputValid();

        if(!isInputValid().isEmpty()){
            showValidationError(error);
            return;
        }
        if(!doesSelectionSizeChanged()) return;

        chosenSelectionSize = selectionSize.getText();
        barChart.getData().clear();

        for (Map.Entry<String, RandomAlgorithm> entry: algorithms.entrySet()){
            RandomAlgorithm algorithm = entry.getValue();
            drawBars(algorithm.run(Integer.parseInt(chosenSelectionSize)), entry.getKey());
        }

    }

    private void drawBars(int[] countOfEntrance, String name){
        barChart.getXAxis().setAnimated(false);
        barChart.getYAxis().setAnimated(false);
        barChart.getXAxis().setLabel("Интервалы");
        barChart.getYAxis().setLabel("Число вхождений");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(name);
        series1.getData().add(new XYChart.Data(ONE, countOfEntrance[0]));
        series1.getData().add(new XYChart.Data(TWO, countOfEntrance[1]));
        series1.getData().add(new XYChart.Data(THREE, countOfEntrance[2]));
        series1.getData().add(new XYChart.Data(FOUR, countOfEntrance[3]));
        series1.getData().add(new XYChart.Data(FIVE, countOfEntrance[4]));
        series1.getData().add(new XYChart.Data(SIX, countOfEntrance[5]));
        series1.getData().add(new XYChart.Data(SEVEN, countOfEntrance[6]));
        series1.getData().add(new XYChart.Data(EIGHT, countOfEntrance[7]));
        series1.getData().add(new XYChart.Data(NINE, countOfEntrance[8]));
        series1.getData().add(new XYChart.Data(TEN, countOfEntrance[9]));
        series1.getData().add(new XYChart.Data(ELEVEN, countOfEntrance[10]));
        series1.getData().add(new XYChart.Data(TWELVE, countOfEntrance[11]));
        series1.getData().add(new XYChart.Data(THIRTEEN, countOfEntrance[12]));
        series1.getData().add(new XYChart.Data(FOURTEEN, countOfEntrance[13]));
        series1.getData().add(new XYChart.Data(FIFTEEN, countOfEntrance[14]));
        series1.getData().add(new XYChart.Data(SIXTEEN, countOfEntrance[15]));
        series1.getData().add(new XYChart.Data(SEVENTEEN, countOfEntrance[16]));
        series1.getData().add(new XYChart.Data(EIGHTEEN, countOfEntrance[17]));
        series1.getData().add(new XYChart.Data(NINETEEN, countOfEntrance[18]));
        series1.getData().add(new XYChart.Data(TWENTY, countOfEntrance[19]));

        barChart.getData().add(series1);
    }

    @Override
    protected String isInputValid() {
        String error = "";
        String input = selectionSize.getText();

        if(input.isEmpty()) error += SELECTION_SIZE_IS_EMPTY + "\n";
        else if(input.startsWith("0")) error += NUMBER_STARTS_WITH_ZERO + "\n";
        else if(Integer.parseInt(input) > 50000) error += SELECTION_SIZE_IS_TOO_BIG + "\n";

        return error;
    }

    private boolean doesSelectionSizeChanged(){
       if(selectionSize.getText().equals(chosenSelectionSize)) return false;
       else return true;
    }
}
