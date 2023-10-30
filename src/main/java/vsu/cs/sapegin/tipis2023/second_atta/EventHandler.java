package vsu.cs.sapegin.tipis2023.second_atta;

import javafx.scene.chart.LineChart;

import java.util.List;

public class EventHandler {

    public static void resetLineCharts(List<LineChart> lchs) {
        for (LineChart l : lchs) {
            l.getData().clear();
        }
    }

}
