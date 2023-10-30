package vsu.cs.sapegin.tipis2023;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.chart.XYChart;

public class SeriesGenerator {
    public static XYChart.Series getSeries(Point2D[] points) {
        ObservableList<XYChart.Data> data = FXCollections.observableArrayList();
        for (int i = 0; i < points.length; i++) {
            data.add(new XYChart.Data(points[i].getX(), points[i].getY()));
        }
        XYChart.Series series = new XYChart.Series();
        series.setData(data);
        return series;
    }
}
