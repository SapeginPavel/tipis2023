package vsu.cs.sapegin.tipis2023.utils;

import javafx.geometry.Point2D;

public class Utils {

    public static Point2D[] getReverseArray(Point2D[] arr) {
        Point2D[] resArr = new Point2D[arr.length];
        for (int i = 0; i < arr.length; i++) {
            resArr[i] = new Point2D(arr[i].getX(), arr[arr.length - 1 - i].getY());
//            resArr[i] = arr[arr.length - 1 - i];
        }
        return resArr;
    }
    public static void reverseArray(double[] arr) {
        for (int i = 0; i < (int) Math.ceil(arr.length / 2.0); i++) {
            double temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
