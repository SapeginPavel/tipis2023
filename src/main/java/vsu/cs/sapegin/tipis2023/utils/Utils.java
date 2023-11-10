package vsu.cs.sapegin.tipis2023.utils;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.dft.Complex;

import java.util.Arrays;

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

//    public static int getIndexOfMaxRealPart(Complex[] complex) {
//        System.out.println(Arrays.toString(complex));
//        int index = 0;
//        double max = -1;
//        for (int i = 0; i < complex.length; i++) {
//            if (complex[i].real > max) {
//                max = complex[i].real;
//                index = i;
//            }
//        }
//        return index;
//    }
//
//    public static int getIndexOfNextMinRealPartValue(Complex[] complex, int startIndex) {
//        double min = complex[startIndex].real;
//        int indexOfMin = startIndex;
//        for (int i = startIndex; i < complex.length; i++) {
//            if (complex[i].real < min) {
//                min = complex[i].real;
//                indexOfMin = i;
//            } else {
//                break;
//            }
//        }
//        return indexOfMin;
//    }
//
//    public static int getIndexOfNextMaxRealPartValue(Complex[] complex, int startIndex) {
//        double max = complex[startIndex].real;
//        int indexOfMax = startIndex;
//        for (int i = startIndex; i < complex.length; i++) {
//            if (complex[i].real > max) {
//                max = complex[i].real;
//                indexOfMax = i;
//            } else {
//                break;
//            }
//        }
//        return indexOfMax;
//    }

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

    public static int getIndexOfNextComplexWithMinModule(Complex[] complex, int startIndex) {
        double min = complex[startIndex].getModule();
        int indexOfMin = startIndex;
        for (int i = startIndex; i < complex.length; i++) {
            if (complex[i].getModule() < min) {
                min = complex[i].getModule();
                indexOfMin = i;
            }
        }
        System.out.println();
        System.out.println();
        return indexOfMin;
    }

    public static int getIndexOfNextComplexWithMaxModule(Complex[] complex, int startIndex) {
        double max = complex[startIndex].getModule();
        int indexOfMax = startIndex;
        for (int i = startIndex; i < complex.length; i++) {
            if (complex[i].getModule() > max) {
                max = complex[i].getModule();
                indexOfMax = i;
            }
        }
        return indexOfMax;
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
