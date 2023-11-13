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
import vsu.cs.sapegin.tipis2023.hilbert_transform.HilbertTransform;
import vsu.cs.sapegin.tipis2023.second_atta.Options;
import vsu.cs.sapegin.tipis2023.second_atta.SignalComparator;
import vsu.cs.sapegin.tipis2023.utils.Utils;

public class Controller {

    int sampleRate;

    List<LineChart> lineCharts_2_atta = null;
    List<LineChart> lineChartsRange_2_atta = null;
    List<LineChart> lineChartsTask_2_atta = null;

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
    private LineChart<?, ?> lchHilbertTransform_2_atta;

    @FXML
    private LineChart<?, ?> lchCutOffAmplMod_2_atta;

    @FXML
    private LineChart<?, ?> lchReconstructedSignal_2_atta;

    @FXML
    private LineChart<?, ?> lchModulatingSignal_2_atta;

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

//        int maxFrequencyForRange = Options.getMaxFrequencyForDFT();
        int maxFrequencyForRange = sampleRate / 2;
        Point2D[] pointsOrigRange = generatePointsFromFFT(pointsOrig, maxFrequencyForRange);
        Point2D[] pointsAmplModRange = generatePointsFromFFT(pointsAmplMod, maxFrequencyForRange);
        Point2D[] pointsFreqModRange = generatePointsFromFFT(pointsFreqMod, maxFrequencyForRange);
        Point2D[] pointsPhaseModRange = generatePointsFromFFT(pointsPhaseMod, maxFrequencyForRange);

        buildGraphic(lchOrigSignalRange_2_atta, pointsOrigRange);
        buildGraphic(lchAmplitudeModulationRange_2_atta, pointsAmplModRange);
        buildGraphic(lchFrequencyModulationRange_2_atta, pointsFreqModRange);
        buildGraphic(lchPhaseModulationRange_2_atta, pointsPhaseModRange);

        tickPeaksForRange(radioButtonTickYes_2_atta.isSelected());
    }

    @FXML
    void onExecuteTask_2_atta(ActionEvent event) throws Exception {
//        Point2D[] pointsAmplMod = getPointsFromLineChartWithStep(lchAmplitudeModulation_2_atta, Options.getDefaultAmountOfPointsForUnitSegment() / sampleRate);
//        double[] y = getFromPointsY(pointsAmplMod);
//
//        Complex[] afterFFT = DFT.fft(getArrayPaddedToRequiredSize(y));
////        Complex[] afterFFTRrr = DFT.fft(getArrayPaddedToRequiredSize(yRrr, "afterFFTRrr: changes?"));
//        Complex[] afterFFTPositiveFrequencies = Arrays.copyOfRange(afterFFT, 0, afterFFT.length / 2);
////        Complex[] afterFFTPositiveFrequenciesRrr = Arrays.copyOfRange(afterFFT, 0, afterFFTRrr.length / 2);
//
//        Point2D[] pointsAmplModRange = getPointsFromLineChartWithStep(lchAmplitudeModulationRange_2_atta, 1); // т. е. забираем все точки
//        double[] yRrr = getFromPointsY(pointsAmplModRange);
//        double maxXValue = pointsAmplModRange[pointsAmplModRange.length - 1].getX();
//        int amountOfPointsForUnitSegmentRrr = (int) (yRrr.length / maxXValue); //по идее, тут и без округления должно быть ровно
//        int indexOfPeakRrr = Utils.getIndexOfMax(yRrr);
//        int widthRrr = Options.getWidthOfCutOffSignal(); //сколько берём целых точек амплитудного спектра
//        int generalAmountOfPointsRrr = widthRrr * amountOfPointsForUnitSegmentRrr;
//        Point2D[] cutOffAmplRange = Arrays.copyOfRange(pointsAmplModRange, indexOfPeakRrr - generalAmountOfPointsRrr, indexOfPeakRrr + generalAmountOfPointsRrr + 1);
//        buildGraphic(lchCutOffAmplMod_2_atta, cutOffAmplRange);
//        double[] yForIFFTRrr = getFromPointsY(cutOffAmplRange);
//        System.out.println("yForIFFTRrr size = " + yForIFFTRrr.length);
//        double[] yForIFFTRrrPadded = getArrayPaddedToRequiredSize(yForIFFTRrr, "yForIFFTRrrPadded changed?");
//        System.out.println("yForIFFTRrrPadded size = " + yForIFFTRrrPadded.length);
//        Complex[] afterIFFTRrr = DFT.ifft(yForIFFTRrrPadded);
//        double[] amplitudesFromIFFFT = Utils.getModulesOfComplexes(afterIFFTRrr);
//        Point2D[] pointsIFFT = Utils.generatePointsWithStepForY(amplitudesFromIFFFT, 0, 1); //todo: исправить шаг
//        buildGraphic(lchReconstructedSignal_2_atta, pointsIFFT);
//
//
//
//        int indexOfPeak = Utils.getIndexOfComplexWithMaxModule(afterFFTPositiveFrequencies);
//        int amountOfPointsForUnitSegment = afterFFT.length / Options.getDefaultAmountOfPointsForUnitSegment();
//        int width = Options.getWidthOfCutOffSignal(); //сколько берём целых точек амплитудного спектра
//        int generalAmountOfPoints = width * amountOfPointsForUnitSegment;
//
//        Complex[] cutOffAmplitudeRange = Arrays.copyOfRange(afterFFTPositiveFrequencies, indexOfPeak - generalAmountOfPoints, indexOfPeak + generalAmountOfPoints + 1);
//
//
//        double[] amplitudes = Utils.getModulesOfComplexes(cutOffAmplitudeRange);
//        double step = 1.0 / amountOfPointsForUnitSegment;
//        Point2D[] pointsAfterCutOff = Utils.generatePointsWithStepForY(amplitudes, (indexOfPeak - generalAmountOfPoints) * step, step);
////        buildGraphic(lchCutOffAmplMod_2_atta, pointsAfterCutOff);
//
//        Complex[] cutOffAmplitudeRangeForFFT = new Complex[afterFFTPositiveFrequencies.length];
//
//        for (int i = 0; i < cutOffAmplitudeRangeForFFT.length; i++) {
//            if (i >= (indexOfPeak - generalAmountOfPoints) && i <= (indexOfPeak + generalAmountOfPoints)) {
//                cutOffAmplitudeRangeForFFT[i] = afterFFTPositiveFrequencies[i];
//            } else {
//                cutOffAmplitudeRangeForFFT[i] = new Complex();
//            }
//        }
//
//        Complex[] cutOffFFTRequiredSize = getArrayPaddedToRequiredSize(cutOffAmplitudeRangeForFFT);
//        Complex[] ifftCutOffComplex = DFT.ifft(cutOffFFTRequiredSize);
//
//        double[] amplitudesFromRealPart = Utils.getRealPartOfComplexes(ifftCutOffComplex);
//        double stepAfterIFFT = 1 / (sampleRate / 2.0); //так как IFFT уже меньше в 2 раза
//        Point2D[] ifftCutOffPoints = Utils.generatePointsWithStepForY(amplitudesFromRealPart, 0, stepAfterIFFT);
////        buildGraphic(lchReconstructedSignal_2_atta, ifftCutOffPoints);
//
//        Complex[] hilbert = HilbertTransform.hilbertTransform(ifftCutOffComplex);
//        double[] hilbertAmplitudes = Utils.getModulesOfComplexes(hilbert);
//        Point2D[] pointsHilbert = Utils.generatePointsWithStepForY(hilbertAmplitudes, 0, stepAfterIFFT);
//        buildGraphic(lchHilbertTransform_2_atta, pointsHilbert);
//
//        double minHilbertAmplitude = hilbertAmplitudes[0];
//        double maxHilbertAmplitude = hilbertAmplitudes[0];
//        for (int i = 1; i < hilbertAmplitudes.length; i++) {
//            maxHilbertAmplitude = Math.max(maxHilbertAmplitude, hilbertAmplitudes[i]);
//            minHilbertAmplitude = Math.min(minHilbertAmplitude, hilbertAmplitudes[i]);
//        }
//        double[] fromComparator = SignalComparator.generateSignalByCompareInputSignalWithBaseSignal(hilbertAmplitudes, (maxHilbertAmplitude - minHilbertAmplitude) / 2 + minHilbertAmplitude);
//
//        System.out.println(Arrays.toString(fromComparator));
//
//        Point2D[] pointsComparator = Utils.generatePointsWithStepForY(fromComparator, 0, stepAfterIFFT);
//        buildGraphic(lchModulatingSignal_2_atta, pointsComparator);
    }

    private Point2D[] getPointsFromLineChartWithStep(LineChart lch, int step) {
        ObservableList<XYChart.Data> dataList = ((XYChart.Series) lch.getData().get(lch.getData().size() - 1)).getData();
        Point2D[] allPoints = new Point2D[dataList.size()];
        for (int i = 0; i < allPoints.length; i++) {
            allPoints[i] = new Point2D((Double) dataList.get(i).getXValue(), (Double) dataList.get(i).getYValue());
        }
        return Utils.getPointsByStep(allPoints, step);
    }

    private Point2D[] generatePointsFromFFT(Point2D[] points, int maxFrequencyForRange) {
        Point2D[] pointsByStep = Utils.getPointsByStep(points, Options.getDefaultAmountOfPointsForUnitSegment() / sampleRate); //выбираем с каким-то шагом точки из основных точек

        double[] y = getFromPointsY(pointsByStep);

        //небольшое дублирование кода, так как нам надо передавать сразу нужное количество элементов для fft, чтобы учесть это количество в построении графиков
        double[] yRequiredSize = getArrayPaddedToRequiredSize(y);
        double[] afterFFT = doFFTAndGetModules(yRequiredSize);

        double step = (sampleRate + 0.0) / afterFFT.length;

        int length = (int) (1 / step * maxFrequencyForRange);

        maxFrequencyForRange = Math.min(length, afterFFT.length / 2);

        double[] positiveFrequencies = Arrays.copyOfRange(afterFFT, 0, maxFrequencyForRange);
        return Utils.generatePointsWithStepForY(positiveFrequencies, 0, step);
    }

    private double[] getFromPointsY(Point2D[] points) {
        double[] y = new double[points.length];
        for (int i = 0; i < y.length; i++) {
            y[i] = points[i].getY();
        }
        return y;
    }

    private double[] doFFTAndGetModules(double[] y) {
        return Utils.getModulesOfComplexes(doFFT(y));
    }

    private Complex[] doFFT(double[] y) {
        double[] yRequiredSize = getArrayPaddedToRequiredSize(y);
        return DFT.fft(yRequiredSize);
    }

    private double[] getArrayPaddedToRequiredSize(double[] y, String s) {
        int size = getMinNecessarySizeOfArrayForFFT(y);
        System.out.println(s);
        if (size > y.length) {
            double[] newY = new double[size];
            for (int i = 0; i < y.length; i++) {
                newY[i] = y[i];
            }
            return newY;
        } else {
            System.out.println("without changes");
            return y;
        }
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

    private Complex[] getArrayPaddedToRequiredSize(Complex[] complex) {
        int size = getMinNecessarySizeOfArrayForFFT(complex);
        if (size > complex.length) {
            Complex[] newComplex = new Complex[size];
            for (int i = 0; i < complex.length; i++) {
                newComplex[i] = complex[i];
            }
            for (int i = complex.length; i < size; i++) {
                newComplex[i] = new Complex();
            }
            return newComplex;
        } else {
            return complex;
        }
    }

    private int getMinNecessarySizeOfArrayForFFT(Complex[] complex) {
        int n = complex.length;
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
        for (LineChart l : lineChartsTask_2_atta) {
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
        lineChartsTask_2_atta = List.of(lchCutOffAmplMod_2_atta, lchReconstructedSignal_2_atta, lchModulatingSignal_2_atta, lchHilbertTransform_2_atta);

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
