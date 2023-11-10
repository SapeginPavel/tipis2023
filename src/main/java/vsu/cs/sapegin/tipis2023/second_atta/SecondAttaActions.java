package vsu.cs.sapegin.tipis2023.second_atta;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.SinusGenerator;
import vsu.cs.sapegin.tipis2023.dft.Complex;
import vsu.cs.sapegin.tipis2023.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondAttaActions {

    public static Point2D[] cutOffTheSpectrum(Point2D[] points, int range) throws Exception {
        int indexOfPeak = Utils.getIndexOfMaxValueY(points);
        if ((points[indexOfPeak].getX() - range) < 0 || (points[indexOfPeak].getX() + range) >= points.length) {
            throw new Exception("Invalid range for cutting of the spectrum");
        }
        List<Point2D> cutPoints = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (points[i].getX() >= (points[indexOfPeak].getX() - range) && points[i].getX() <= (points[indexOfPeak].getX() + range)) {
                cutPoints.add(points[i]);
            }
        }
        return cutPoints.toArray(new Point2D[0]);
    }

    //todo: проверить работу. Находим пик, потом минимум, потом максимум, потом снова минимум и возвращаем симметричный кусок. По-хорошему надо бы Exception тут кидать после проверок, но ладно
    public static Complex[] cutOffTheSpectrum(Complex[] complex) {
        int indexOfPeak = Utils.getIndexOfMaxRealPart(complex);
        int nextMinMaxMin = Utils.getIndexOfNextMinRealPartValue(complex, Utils.getIndexOfNextMaxRealPartValue(complex, Utils.getIndexOfNextMinRealPartValue(complex, indexOfPeak)));
        int prevMinMaxMin = indexOfPeak - nextMinMaxMin;

        return Arrays.copyOfRange(complex, prevMinMaxMin, nextMinMaxMin);
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
