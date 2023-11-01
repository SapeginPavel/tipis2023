package vsu.cs.sapegin.tipis2023.second_atta;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.SinusGenerator;
import vsu.cs.sapegin.tipis2023.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SecondAttaActions {

    public static Point2D[] cutOffTheSpectrum(Point2D[] points, int range) throws Exception {
        int indexOfPeak = Utils.getIndexOfMaxValueY(points);
        if ((indexOfPeak - range) < 0 || (indexOfPeak + range) >= points.length) {
            throw new Exception("Invalid range for cutting of the spectrum");
        }
        Point2D[] cutPoints = new Point2D[points.length];
        for (int i = 0; i < cutPoints.length; i++) {
            if (i >= (indexOfPeak - range) && i <= (indexOfPeak + range)) {
                cutPoints[i] = points[i];
            } else {
                cutPoints[i] = new Point2D(i, 0);
            }
        }
        return cutPoints;
    }

    public static int[] getCarrierFrequencies(Point2D[] points) {
        List<Integer> frequenciesList = new ArrayList<>();
        for (Point2D p : points) {
            if (p.getY() != 0) {
                frequenciesList.add((int) p.getX());
            }
        }
        int[] frequenciesArr = new int[frequenciesList.size()];
        for (int i = 0; i < frequenciesList.size(); i++) {
            frequenciesArr[i] = frequenciesList.get(i);
        }
        return frequenciesArr;
    }

    public static Point2D[] generateSignalFromFrequenciesArr(int[] frequencies,int amountOfPointsForUnitSegment, double maxX) {
        if (frequencies.length == 0) {
            return null;
        }
        double[] y = new double[(int) (amountOfPointsForUnitSegment * maxX)];
        for (int i = 0; i < frequencies.length; i++) {
            Point2D[] tempPoints = SinusGenerator.getPointsForSinus(frequencies[i]);
            for (int j = 0; j < y.length; j++) {
                y[j] += tempPoints[j].getY();
            }
        }
        double deltaX = 1.0 / amountOfPointsForUnitSegment;
        double currentX = 0;
        Point2D[] points = new Point2D[(int) (amountOfPointsForUnitSegment * maxX)];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(currentX, y[i]);
            currentX += deltaX;
        }
        return points;
    }
}
