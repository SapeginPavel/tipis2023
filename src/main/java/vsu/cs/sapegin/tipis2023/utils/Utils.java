package vsu.cs.sapegin.tipis2023.utils;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.dft.Complex;

public class Utils {

    public static int getIndexOfMax(double[] arr) {
        double max = arr[0];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

    public static double[] getModulesOfComplexes(Complex[] complexes) {
        double[] resArr = new double[complexes.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Math.sqrt(complexes[i].real * complexes[i].real + complexes[i].im * complexes[i].im);
        }
        return resArr;
    }

    public static double[] getRealPartOfComplexes(Complex[] complexes) {
        double[] res = new double[complexes.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = complexes[i].real;
        }
        return res;
    }

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

    public static int getIndexOfComplexWithMaxModule(Complex[] complex) {
        int index = 0;
        double max = -1;
        for (int i = 0; i < complex.length; i++) {
            if (complex[i].getModule() > max) {
                max = complex[i].getModule();
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
}
