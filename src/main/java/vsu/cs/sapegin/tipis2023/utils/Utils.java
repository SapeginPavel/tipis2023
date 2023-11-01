package vsu.cs.sapegin.tipis2023.utils;

import javafx.geometry.Point2D;

public class Utils {

    public static Point2D[] getReverseArray(Point2D[] arr) {
        Point2D[] resArr = new Point2D[arr.length];
        for (int i = 0; i < arr.length; i++) {
            resArr[i] = new Point2D(arr[i].getX(), arr[arr.length - 1 - i].getY());
        }
        return resArr;
    }

    public static Point2D[] getPointsByStep(Point2D[] points, int step) {
        Point2D[] p = new Point2D[points.length / step];
        for (int i = 0; i < p.length; i++) {
            p[i] = points[i * step];
        }
        return p;
    }

    public static int getIndexOfMaxValueY(Point2D[] points) {
        int index = 0;
        double max = 0;
        for (int i = 0; i < points.length; i++) {
            if (points[i].getY() > max) {
                max = points[i].getY();
                index = i;
            }
        }
        return index;
    }
}
