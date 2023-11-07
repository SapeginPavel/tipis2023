package vsu.cs.sapegin.tipis2023.deprecated;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.dft.DFT;
import vsu.cs.sapegin.tipis2023.second_atta.Options;
import vsu.cs.sapegin.tipis2023.utils.Utils;

public class Deprecated {
//    private Point2D[] generatePointsFromDFT(Point2D[] points) {
//        Point2D[] pointsByStep = Utils.getPointsByStep(points, Options.getDefaultAmountOfPointsForUnitSegment() / sampleRate);
//
//        double[] y = new double[pointsByStep.length];
//        for (int i = 0; i < y.length; i++) {
//            y[i] = pointsByStep[i].getY();
//        }
//        double[] dftY = DFT.dft(y, sampleRate);
//        Point2D[] resPoints = new Point2D[dftY.length];
//        for (int i = 0; i < resPoints.length; i++) {
//            resPoints[i] = new Point2D(i, dftY[i]);
//        }
//        return resPoints;
//    }

    //    private Point2D[] generatePointsFromDFT2(Point2D[] points, double step) {
//        Point2D[] pointsByStep = Utils.getPointsByStep(points, Options.getDefaultAmountOfPointsForUnitSegment() / sampleRate);
//
//        double[] y = new double[pointsByStep.length];
//        for (int i = 0; i < y.length; i++) {
//            y[i] = pointsByStep[i].getY();
//        }
//        double[] dft2Y = DFT.dft2(y, sampleRate, step);
//        Point2D[] resPoints = Utils.generatePointsWithStepForY(dft2Y, 0, step);
//        return resPoints;
//    }
}
