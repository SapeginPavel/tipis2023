package vsu.cs.sapegin.tipis2023;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.second_atta.Options;
import vsu.cs.sapegin.tipis2023.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SinusGenerator {

    private static int defaultFrequency = Options.getFrequencyBase();
    private static int defaultAmplitude = Options.getAmplitudeBase();
    private static double defaultMinX = Options.getDefaultMinX();
    private static double defaultMaxX = Options.getDefaultMaxX();
    private static int defaultAmountOfPointsForUnitSegment = Options.getDefaultAmountOfPointsForUnitSegment();

    //Возможно, в качестве параметра ещё надо будет передавать фазу

    public static Point2D[] getPointsForDefaultSinus() {
        return getPointsForSinusForSegment(defaultFrequency, defaultAmplitude, defaultMinX, defaultMaxX);
    }

    public static Point2D[] getPointsForSinus(int frequency) {
        return getPointsForSinusForSegment(frequency, defaultAmplitude, defaultMinX, defaultMaxX);
    }

    public static Point2D[] getPointsForSinus(int frequency, int amplitude) {
        return getPointsForSinusForSegment(frequency, amplitude, defaultMinX, defaultMaxX);
    }

    public static Point2D[] getPointsForSinus(int frequency, int amplitude, int maxX) {
        return getPointsForSinusForSegment(frequency, amplitude, defaultMinX, maxX);

    }

    public static Point2D[] getPointsForSinusWithModulation(int bFrequency, int mFrequency, int bAmplitude, int mAmplitude, boolean isPhaseModulation, int meanderFrequency, double maxX) {
        int size = (int) (Options.getDefaultAmountOfPointsForUnitSegment() * maxX);
        List<Point2D> points = new ArrayList<>(size);
        double meanderConstLength = 1.0 / meanderFrequency / 2; //длина участка, когда меандр постоянен по значению
//        System.out.println("meanderConstLength = " + meanderConstLength);
        int amountOfSegments = (int) (maxX / meanderConstLength);
//        System.out.println("amountOfSegments = " + amountOfSegments);
        for (int i = 0; i < amountOfSegments; i++) {
            Point2D[] temp;
            if (i % 2 == 0) {
                temp = getPointsForSinusForSegment(mFrequency, mAmplitude, meanderConstLength * i, (meanderConstLength) * (i + 1));
            } else {
                temp = getPointsForSinusForSegment(bFrequency, bAmplitude, meanderConstLength * i, (meanderConstLength) * (i + 1));
                if (isPhaseModulation) {
                    temp = Utils.getReverseArray(temp);
                }
            }
            points.addAll(Arrays.asList(temp));
        }
//        System.out.println("amount of points = " + points.size());
        return points.toArray(new Point2D[0]);
    }

    public static Point2D[] getPointsForSinusForSegment(int frequency, int amplitude, double startX, double endX) {
        int amountOfPointsForSegment = Options.getDefaultAmountOfPointsForUnitSegment();
        double deltaX = 1.0 / amountOfPointsForSegment; //(endX - startX)
        int sizeOfSegment = (int) (amountOfPointsForSegment * (endX - startX));
        Point2D[] points = new Point2D[sizeOfSegment];
        double currentX = startX;
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point2D(currentX, amplitude * Math.sin((2 * Math.PI) * currentX * frequency));
            currentX += deltaX;
        }
        return points;
    }
}
