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
}
