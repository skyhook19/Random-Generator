package javafxLogic.controller.impl;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafxLogic.controller.AbstractController;

import java.util.ArrayList;
import java.util.List;

import static javafxLogic.controller.Regions.*;

public class MethodsWindowController extends AbstractController{

    @FXML
    private ComboBox methodCheckBox;
    @FXML
    private ComboBox intervalCheckBox;
    @FXML
    private BarChart barChart;

    private String chosenMethod = "";
    private String chosenInterval = "";

    protected void initialize() {
        methodCheckBox.getItems().addAll("Рандом", "Фон-Неймана", "Метод 3", "Метод 4");
        intervalCheckBox.getItems().addAll("50", "500", "1000", "5000", "25000", "50000");
    }

    @FXML
    private void handleGenerateBtn(){
        String error = isInputValid();

        if(!isInputValid().isEmpty()){
            showValidationError(error);
            return;
        }
        if(!comboBoxesChanged()) return;

        chosenMethod = methodCheckBox.getSelectionModel().getSelectedItem().toString();
        chosenInterval = intervalCheckBox.getSelectionModel().getSelectedItem().toString();
        drawBars(new ArrayList<>());
    }

    private void drawBars(List<Integer> countOfEntrance){
        barChart.getXAxis().setAnimated(false);
        barChart.getYAxis().setAnimated(false);
        barChart.getXAxis().setLabel("Интервалы");
        barChart.getYAxis().setLabel("Число вхождений");

        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(one, 25601.34));
        series1.getData().add(new XYChart.Data(two, 20148.82));
        series1.getData().add(new XYChart.Data(three, 10000));
        series1.getData().add(new XYChart.Data(four, 35407.15));
        series1.getData().add(new XYChart.Data(five, 12000));
        series1.getData().add(new XYChart.Data(six, 25601.34));
        series1.getData().add(new XYChart.Data(seven, 20148.82));
        series1.getData().add(new XYChart.Data(eight, 10000));
        series1.getData().add(new XYChart.Data(nine, 35407.15));
        series1.getData().add(new XYChart.Data(ten, 12000));
        series1.getData().add(new XYChart.Data(eleven, 25601.34));
        series1.getData().add(new XYChart.Data(twelve, 20148.82));
        series1.getData().add(new XYChart.Data(thirteen, 10000));
        series1.getData().add(new XYChart.Data(fourteen, 35407.15));
        series1.getData().add(new XYChart.Data(fifteen, 12000));
        series1.getData().add(new XYChart.Data(sixteen, 25601.34));
        series1.getData().add(new XYChart.Data(seventeen, 20148.82));
        series1.getData().add(new XYChart.Data(eighteen, 10000));
        series1.getData().add(new XYChart.Data(nineteen, 35407.15));
        series1.getData().add(new XYChart.Data(twenty, 12000));


        barChart.getData().clear();
        barChart.getData().add(series1);
        barChart.lookupAll(".default-color0.chart-bar").forEach(n -> n.setStyle("-fx-bar-fill: #08E8DE;"));
    }

    @Override
    protected String isInputValid() {
        String error = "";

        if(methodCheckBox.getSelectionModel().getSelectedItem() == null){
            error += "You should choose method!\n";
        }
        if(intervalCheckBox.getSelectionModel().getSelectedItem() == null){
            error += "You should choose interval!\n";
        }

        return error;
    }

    private boolean comboBoxesChanged(){
       if(methodCheckBox.getSelectionModel().getSelectedItem().toString().equals(chosenMethod)
               && intervalCheckBox.getSelectionModel().getSelectedItem().toString().equals(chosenInterval)){
           return false;
       }

       return true;
    }
}
