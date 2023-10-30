package vsu.cs.sapegin.tipis2023;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.second_atta.Options;

public class SinusGenerator {

    private static int defaultMaxX = Options.getDefaultMaxX();
    private static int defaultAmountOfPointsForUnitSegment = Options.getDefaultAmountOfPointsForUnitSegment();

    //Возможно, в качестве параметра ещё надо будет передавать фазу

    public static Point2D[] getPointsForSinus(int frequency) {
        return getPointsForSinusForSegment(frequency, 1, 0, defaultMaxX);
    }

    public static Point2D[] getPointsForSinus(int frequency, int amplitude) {
//        return getPointsForSinus(frequency, amplitude, 5, 200);
        return getPointsForSinusForSegment(frequency, amplitude, 0, defaultMaxX);
    }

    public static Point2D[] getPointsForSinus(int frequency, int amplitude, int maxX) {
//        return getPointsForSinus(frequency, amplitude, max_x, 200);
        return getPointsForSinusForSegment(frequency, amplitude, 0, maxX);

    }

    public static Point2D[] getPointsForSinusWithModulation(int bFrequency, int mFrequency, int bAmplitude, int mAmplitude, int meanderFrequency, int maxX, int amountOfPointsForSegment) {
        return null;
    }

    private static Point2D[] getPointsForSinusForSegment(int frequency, int amplitude, double startX, double endX) {
        int amountOfPointsForSegment = Options.getDefaultAmountOfPointsForUnitSegment();
        double deltaX = 1.0 / amountOfPointsForSegment; //(endX - startX)
        int sizeOfSegment = (int) (amountOfPointsForSegment * (endX - startX));
        Point2D[] points = new Point2D[sizeOfSegment];
        double currentX = 0;
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(currentX, amplitude * Math.sin((2 * Math.PI) * currentX * frequency));
            currentX += deltaX;
        }
        return points;
    }
}
