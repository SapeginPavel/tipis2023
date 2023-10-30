package vsu.cs.sapegin.tipis2023;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;

public class Controller {

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
    private RadioButton radioButtonTickNo_2_atta;

    @FXML
    private RadioButton radioButtonTickYes_2_atta;

    @FXML
    private Slider sliderRate_2_atta;

    @FXML
    private ToggleGroup tickPeaks_2_atta;

    @FXML
    void onClickBuild_2_atta(ActionEvent event) {

    }

    @FXML
    void onClickReset_2_atta(ActionEvent event) {

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
        assert sliderRate_2_atta != null : "fx:id=\"sliderRate_2_atta\" was not injected: check your FXML file 'view.fxml'.";
        assert tickPeaks_2_atta != null : "fx:id=\"tickPeaks_2_atta\" was not injected: check your FXML file 'view.fxml'.";

    }

}
