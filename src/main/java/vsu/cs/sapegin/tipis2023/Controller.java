package vsu.cs.sapegin.tipis2023;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.chart.LineChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import vsu.cs.sapegin.tipis2023.dft.DFT;
import vsu.cs.sapegin.tipis2023.second_atta.Options;
import vsu.cs.sapegin.tipis2023.utils.Utils;

public class Controller {

    int sampleRate;

    List<LineChart> lineCharts_2_atta = null;
    List<LineChart> lineChartsRange_2_atta = null;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private LineChart<?, ?> lchAmplitudeModulationRange_2_atta;

    @FXML
    private LineChart<?, ?> lchAmplitudeModulation_2_atta;

    @FXML
    private LineChart<?, ?> lchFrequencyModulationRange_2_atta;

    @FXML
    private LineChart<?, ?> lchFrequencyModulation_2_atta;

    @FXML
    private LineChart<?, ?> lchOrigSignalRange_2_atta;

    @FXML
    private LineChart<?, ?> lchOrigSignal_2_atta;

    @FXML
    private LineChart<?, ?> lchPhaseModulationRange_2_atta;

    @FXML
    private LineChart<?, ?> lchPhaseModulation_2_atta;

    @FXML
    private RadioButton radioButtonSampleRate128_2_atta;

    @FXML
    private RadioButton radioButtonSampleRate256_2_atta;

    @FXML
    private RadioButton radioButtonSampleRate32_2_atta;

    @FXML
    private RadioButton radioButtonSampleRate64_2_atta;

    @FXML
    private RadioButton radioButtonTickNo_2_atta;

    @FXML
    private RadioButton radioButtonTickYes_2_atta;

    @FXML
    private ToggleGroup toggleGroupSampleRate_2_atta;

    @FXML
    private ToggleGroup tickPeaks_2_atta;

    @FXML
    void onClickBuild_2_atta(ActionEvent event) {
        Point2D[] pointsOrig = SinusGenerator.getPointsForDefaultSinus();
        Point2D[] pointsAmplMod = SinusGenerator.getPointsForSinusWithModulation(Options.getFrequencyBase(), Options.getFrequencyBase(), Options.getAmplitudeBase(), Options.getAmplitudeMod(), false, Options.getMeanderFrequency(), Options.getDefaultMaxX());
        Point2D[] pointsFreqMod = SinusGenerator.getPointsForSinusWithModulation(Options.getFrequencyBase(), Options.getFrequencyMod(), Options.getAmplitudeBase(), Options.getAmplitudeBase(), false, Options.getMeanderFrequency(), Options.getDefaultMaxX());
        Point2D[] pointsPhaseMod = SinusGenerator.getPointsForSinusWithModulation(Options.getFrequencyBase(), Options.getFrequencyBase(), Options.getAmplitudeBase(), Options.getAmplitudeBase(), true, Options.getMeanderFrequency(), Options.getDefaultMaxX());
        //самый показательный пример - когда частота равна 2

        buildGraphic(lchOrigSignal_2_atta, pointsOrig);
        buildGraphic(lchAmplitudeModulation_2_atta, pointsAmplMod);
        buildGraphic(lchFrequencyModulation_2_atta, pointsFreqMod);
        buildGraphic(lchPhaseModulation_2_atta, pointsPhaseMod);

//        lchOrigSignal_2_atta.getData().add(SeriesGenerator.getSeries(pointsOrig));
//        lchAmplitudeModulation_2_atta.getData().add(SeriesGenerator.getSeries(pointsAmplMod));
//        lchFrequencyModulation_2_atta.getData().add(SeriesGenerator.getSeries(pointsFreqMod));
//        lchPhaseModulation_2_atta.getData().add(SeriesGenerator.getSeries(pointsPhaseMod));


        Point2D[] pointsOrigRange = generatePointsFromDFT(pointsOrig);
        Point2D[] pointsAmplModRange = generatePointsFromDFT(pointsAmplMod);
        Point2D[] pointsFreqModRange = generatePointsFromDFT(pointsFreqMod);
        Point2D[] pointsPhaseModRange = generatePointsFromDFT(pointsPhaseMod);

        buildGraphic(lchOrigSignalRange_2_atta, pointsOrigRange);
        buildGraphic(lchAmplitudeModulationRange_2_atta, pointsAmplModRange);
        buildGraphic(lchFrequencyModulationRange_2_atta, pointsFreqModRange);
        buildGraphic(lchPhaseModulationRange_2_atta, pointsPhaseModRange);

    }

    private Point2D[] generatePointsFromDFT(Point2D[] points) {
//        System.out.println(Arrays.toString(points));
        Point2D[] pointsByStep = Utils.getPointsByStep(points, Options.getDefaultAmountOfPointsForUnitSegment() / sampleRate);
//        System.out.println(Arrays.toString(pointsByStep));

        double[] y = new double[pointsByStep.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = pointsByStep[i].getY();
        }
        double[] dftY = DFT.dft(y, sampleRate);
        Point2D[] resPoints = new Point2D[dftY.length];
        for (int i = 0; i < resPoints.length; i++) {
            resPoints[i] = new Point2D(i, dftY[i]);
        }
        return resPoints;
    }

    private void buildGraphic(LineChart lch, Point2D[] points) {
        lch.getData().add(SeriesGenerator.getSeries(points));
    }

    @FXML
    void onClickReset_2_atta(ActionEvent event) {
//        EventHandler.resetLineCharts(lineCharts_2_atta);
//        EventHandler.resetLineCharts(lineChartsRange_2_atta);
        for (LineChart l : lineCharts_2_atta) {
            l.getData().clear();
        }
        for (LineChart l : lineChartsRange_2_atta) {
            l.getData().clear();
        }
    }

    @FXML
    void onClickMenuClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert lchAmplitudeModulationRange_2_atta != null : "fx:id=\"lchAmplitudeModulationRange_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert lchAmplitudeModulation_2_atta != null : "fx:id=\"lchAmplitudeModulation_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert lchFrequencyModulationRange_2_atta != null : "fx:id=\"lchFrequencyModulationRange_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert lchFrequencyModulation_2_atta != null : "fx:id=\"lchFrequencyModulation_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert lchOrigSignalRange_2_atta != null : "fx:id=\"lchOrigSignalRange_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert lchOrigSignal_2_atta != null : "fx:id=\"lchOrigSignal_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert lchPhaseModulationRange_2_atta != null : "fx:id=\"lchPhaseModulationRange_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert lchPhaseModulation_2_atta != null : "fx:id=\"lchPhaseModulation_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert radioButtonTickNo_2_atta != null : "fx:id=\"radioButtonTickNo_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert radioButtonTickYes_2_atta != null : "fx:id=\"radioButtonTickYes_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert tickPeaks_2_atta != null : "fx:id=\"tickPeaks_2_atta\" was not injected: check your FXML file 'view.fxml'.";

        lineCharts_2_atta =  List.of(lchOrigSignal_2_atta, lchAmplitudeModulation_2_atta, lchFrequencyModulation_2_atta, lchPhaseModulation_2_atta);
        lineChartsRange_2_atta = List.of(lchOrigSignalRange_2_atta, lchAmplitudeModulationRange_2_atta, lchFrequencyModulationRange_2_atta, lchPhaseModulationRange_2_atta);

        toggleGroupSampleRate_2_atta.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) {
                RadioButton rb = (RadioButton) newValue;
                sampleRate = Integer.parseInt(rb.getText());
                System.out.println("sampleRate = " + sampleRate);
            }
        });

        RadioButton rb = (RadioButton) toggleGroupSampleRate_2_atta.getSelectedToggle();
        sampleRate = Integer.parseInt(rb.getText());
        System.out.println("sampleRate = " + sampleRate);
    }
}
