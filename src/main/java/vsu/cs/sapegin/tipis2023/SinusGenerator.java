package vsu.cs.sapegin.tipis2023;

import javafx.geometry.Point2D;

public class SinusGenerator {

    //Возможно, в качестве параметра ещё надо будет передавать фазу

    public static Point2D[] getPointsForSinus(int frequency) {
        return getPointsForSinus(frequency, 1, 5, 200);
    }

    public static Point2D[] getPointsForSinus(int frequency, int amplitude) {
        return getPointsForSinus(frequency, amplitude, 5, 200);
    }

    public static Point2D[] getPointsForSinus(int frequency, int amplitude, int max_x) {
        return getPointsForSinus(frequency, amplitude, max_x, 200);
    }

    public static Point2D[] getPointsForSinus(int frequency, int amplitude, int maxX, int amountOfPointsForSegment) {
        double deltaX = (maxX + 0.0) / amountOfPointsForSegment;
        Point2D[] points = new Point2D[amountOfPointsForSegment * maxX];
        double currentX = 0;
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(currentX, amplitude * Math.sin((2 * Math.PI) * currentX * frequency));
            currentX += deltaX;
        }
        return points;
    }
}
