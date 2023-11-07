package vsu.cs.sapegin.tipis2023.utils;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.dft.Complex;

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

    public static Point2D[] generatePointsWithStepForY(double[] y, double from, double step) {
        Point2D[] points = new Point2D[y.length];
        double x = from;
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(x, y[i]);
            x += step;
        }
        return points;
    }

    public static Complex[] getEvenElements(Complex[] array) {
        return getElements(array, 0);
    }

    public static Complex[] getOddElements(Complex[] array) {
        return getElements(array, 1);
    }

    public static Complex[] getElements(Complex[] array, int positions) {
        Complex[] newArray = new Complex[array.length / 2];

        for (int i = 0; i < array.length; i++) {
            if (i % 2 == positions) {
                if (i / 2 >= newArray.length) {
                    break;
                }
                newArray[i / 2] = array[i];
            }
        }
        return newArray;
    }

//    public static double[] getEvenElements(double[] array) {
//        return getElements(array, 0);
//    }
//
//    public static double[] getOddElements(double[] array) {
//        return getElements(array, 1);
//    }
//
//    public static double[] getElements(double[] array, int positions) {
//        double[] newArray = new double[array.length / 2];
//
//        for (int i = 0; i < array.length; i++) {
//            if (i % 2 == positions) {
//                newArray[i / 2] = array[i];
//            }
//        }
//        return newArray;
//    }
}
