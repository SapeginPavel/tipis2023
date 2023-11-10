package vsu.cs.sapegin.tipis2023;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import vsu.cs.sapegin.tipis2023.dft.Complex;
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
    void onClickBuild_2_atta(ActionEvent event) throws Exception {
        Point2D[] pointsOrig = SinusGenerator.getPointsForDefaultSinus();
        Point2D[] pointsAmplMod = SinusGenerator.getPointsForSinusWithModulation(Options.getFrequencyBase(), Options.getFrequencyBase(), Options.getAmplitudeBase(), Options.getAmplitudeMod(), false, Options.getMeanderFrequency(), Options.getDefaultMaxX());
        Point2D[] pointsFreqMod = SinusGenerator.getPointsForSinusWithModulation(Options.getFrequencyBase(), Options.getFrequencyMod(), Options.getAmplitudeBase(), Options.getAmplitudeBase(), false, Options.getMeanderFrequency(), Options.getDefaultMaxX());
        Point2D[] pointsPhaseMod = SinusGenerator.getPointsForSinusWithModulation(Options.getFrequencyBase(), Options.getFrequencyBase(), Options.getAmplitudeBase(), Options.getAmplitudeBase(), true, Options.getMeanderFrequency(), Options.getDefaultMaxX());
        //самый показательный пример - когда частота равна 2

        buildGraphic(lchOrigSignal_2_atta, pointsOrig);
        buildGraphic(lchAmplitudeModulation_2_atta, pointsAmplMod);
        buildGraphic(lchFrequencyModulation_2_atta, pointsFreqMod);
        buildGraphic(lchPhaseModulation_2_atta, pointsPhaseMod);

        Point2D[] pointsOrigRange = generatePointsFromFFT(pointsOrig, 1);
        Point2D[] pointsAmplModRange = generatePointsFromFFT(pointsAmplMod, 1);
        Point2D[] pointsFreqModRange = generatePointsFromFFT(pointsFreqMod, 1);
        Point2D[] pointsPhaseModRange = generatePointsFromFFT(pointsPhaseMod, 1);

        buildGraphic(lchOrigSignalRange_2_atta, pointsOrigRange);
        buildGraphic(lchAmplitudeModulationRange_2_atta, pointsAmplModRange);
        buildGraphic(lchFrequencyModulationRange_2_atta, pointsFreqModRange);
        buildGraphic(lchPhaseModulationRange_2_atta, pointsPhaseModRange);

        //Тестирую fft
        //todo: попробовать поменять максимальный X в Options

        lchPhaseModulationRange_2_atta.getData().clear();

//        Point2D[] cutOffPoints = SecondAttaActions.cutOffTheSpectrum(pointsAmplModRange, 2);
        double[] y = new double[pointsAmplMod.length]; // 256 pointsOrig.length тут будет y[] обрезанного спектра
        for (int i = 0; i < y.length; i++) {
            y[i] = pointsAmplMod[i].getY();
        }

        System.out.println("Size of pointsAmplMod: " + pointsAmplMod.length); //todo
        Complex[] myFFT = DFT.fft(getArrayPaddedToRequiredSize(y), sampleRate);
        System.out.println("Size of myFFT = " + myFFT.length);
        System.out.println(Arrays.toString(myFFT));
        Complex[] myIFFT = DFT.ifft(myFFT, 128); //todo: does not work
        double[] modules = new double[myIFFT.length];
        for (int i = 0; i < modules.length; i++) {
            modules[i] = myIFFT[i].real;
        }
//        double[] modules = DFT.getModules(myIFFT);
        Point2D[] resPoints = Utils.generatePointsWithStepForY(modules, 0,1);
        buildGraphic(lchPhaseModulationRange_2_atta, resPoints);





        tickPeaksForRange(radioButtonTickYes_2_atta.isSelected());
    }

    private Point2D[] generatePointsFromFFT(Point2D[] points, double step) {
        Point2D[] pointsByStep = getPointsByStep(points, Options.getDefaultAmountOfPointsForUnitSegment() / sampleRate); //выбираем с каким-то шагом точки из основных точек

        double[] y = getFromPointsY(pointsByStep);

        double[] afterFFT = doFFT(y);

        return Utils.generatePointsWithStepForY(afterFFT, 0, step);
    }

    private Point2D[] getPointsByStep(Point2D[] points, int step) {
        return Utils.getPointsByStep(points, step);
    }

    private double[] getFromPointsY(Point2D[] points) {
        double[] y = new double[points.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = points[i].getY();
        }
        return y;
    }

    private double[] doFFT(double[] y) {
        double[] yRequiredSize = getArrayPaddedToRequiredSize(y);
        return DFT.getModulesAfterFFT(DFT.fft(yRequiredSize, sampleRate));
    }

    private double[] getArrayPaddedToRequiredSize(double[] y) {
        int size = getMinNecessarySizeOfArrayForFFT(y);
        if (size > y.length) {
            double[] newY = new double[size];
            for (int i = 0; i < y.length; i++) {
                newY[i] = y[i];
            }
            return newY;
        } else {
            return y;
        }
    }

    private int getMinNecessarySizeOfArrayForFFT(double[] y) {
        int n = y.length;
        int size = 1;
        while (true) {
            if (n > size) {
                size *= 2;
            } else {
                break;
            }
        }
        return size;
    }

    private void buildGraphic(LineChart lch, Point2D[] points) {
        lch.getData().add(SeriesGenerator.getSeries(points));
    }

    //отмечает пики только для целых значений
    private void tickPeaksForRange(boolean tick) {
        for (LineChart lch : lineChartsRange_2_atta) {
            if (tick) {
                lch.setCreateSymbols(true);
            } else {
                lch.setCreateSymbols(false);
                continue;
            }
            int amount = lch.getData().size();
            for (int i = 0; i < amount; i++) {
                ObservableList<XYChart.Data> dataList = ((XYChart.Series) lch.getData().get(i)).getData();
                dataList.get(0).getNode().setVisible(false);
                dataList.get(dataList.size() - 1).getNode().setVisible(false);
                for (int j = 1; j < dataList.size() - 1; j++) {
                    double current = (Double) dataList.get(j).getYValue();
                    double prev = (Double) dataList.get(j - 1).getYValue();
                    double next = (Double) dataList.get(j + 1).getYValue();
                    Node node = dataList.get(j).getNode();
                    if (current > prev && current > next && current > 0.001) {
                        Tooltip tooltip = new Tooltip("x = " + dataList.get(j).getXValue());
                        Tooltip.install(node, tooltip);
                    } else {
                        node.setVisible(false);
                    }
                }
            }
        }
    }

    @FXML
    void onClickReset_2_atta(ActionEvent event) {
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
            }
        });

        RadioButton rb = (RadioButton) toggleGroupSampleRate_2_atta.getSelectedToggle();
        sampleRate = Integer.parseInt(rb.getText());
    }
}
