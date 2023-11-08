package vsu.cs.sapegin.tipis2023.deprecated;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.dft.Complex;
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


//    public static double[] idft22(Complex[] yArrInput, int sampleRate, double maxX, double step) {
//        Complex[] yArrOutput = new Complex[(int) (maxX / step)];
//        double nn = 0;
//        for (int n = 0; n < yArrOutput.length; n++) {
//            yArrOutput[n] = new Complex();
//            for (int k = 0; k < yArrInput.length; k++) {
//                double cof = 2 * Math.PI * k * nn / sampleRate;
//                yArrOutput[n].add(yArrInput[k].multiply(new Complex(Math.cos(cof), Math.sin(cof))));
////                yArrOutput[n].real += yArrInput[k] * Math.cos(cof);
////                yArrOutput[n].im += yArrInput[k] * Math.sin(cof);
//            }
//            yArrOutput[n].real /= sampleRate; //?
//            yArrOutput[n].im /= sampleRate;
//            nn += step;
//        }
//
//        double[] resArr = new double[yArrOutput.length];
//        for (int i = 0; i < resArr.length; i++) {
//            resArr[i] = Math.sqrt(yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im);
//        }
////        System.out.println("resArr idft2 : ");
////        System.out.println(Arrays.toString(resArr));
//        return resArr;
//    }

    public static double[] dft(double[] yArrInput, int sampleRate) {

        Complex[] yArrOutput = new Complex[(sampleRate + 1) / 2];
        for (int k = 0; k < yArrOutput.length; k++) {
            yArrOutput[k] = new Complex();
            for (int n = 0; n < yArrInput.length; n++) {
                double cof = 2 * Math.PI * k * n / sampleRate;
                yArrOutput[k].real += yArrInput[n] * Math.cos(cof);
                yArrOutput[k].im -= yArrInput[n] * Math.sin(cof);
            }
        }

        double[] resArr = new double[yArrOutput.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Math.sqrt(yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im) / 1000;
        }
        return resArr;
    }

    public static double[] dft2(double[] yArrInput, int sampleRate, double step) {
        //ОГРАНИЧЕНИЕ на максимальную частоту (задаётся в Options):
        int sizeCof = Math.min(sampleRate / 2, Options.getMaxFrequencyForDFT());
        Complex[] yArrOutput = new Complex[(int) (sizeCof / step)]; //(sampleRate + 1) / 2
        double kk = 0;
        for (int k = 0; k < yArrOutput.length; k++) {
            yArrOutput[k] = new Complex();
            for (int n = 0; n < yArrInput.length; n++) {
                double cof = 2 * Math.PI * kk * n / sampleRate;
                yArrOutput[k].real += yArrInput[n] * Math.cos(cof);
                yArrOutput[k].im -= yArrInput[n] * Math.sin(cof);
            }
            kk += step;
        }

        double[] resArr = new double[yArrOutput.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Math.sqrt(yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im);
        }
        return resArr;
    }

//    public static double[] idft2(double[] yArrInput, int sampleRate, double maxX, double step) {
//        Complex[] yArrOutput = new Complex[(int) (maxX / step)];
//        double nn = 0;
//        for (int n = 0; n < yArrOutput.length; n++) {
//            yArrOutput[n] = new Complex();
//            for (int k = 0; k < yArrInput.length; k++) {
//                double cof = 2 * Math.PI * k * nn / sampleRate;
//                yArrOutput[n].real += yArrInput[k] * Math.cos(cof);
//                yArrOutput[n].im += yArrInput[k] * Math.sin(cof);
//            }
//            yArrOutput[n].real /= sampleRate; //?
//            yArrOutput[n].im /= sampleRate;
//            nn += step;
//        }
//
//        double[] resArr = new double[yArrOutput.length];
//        for (int i = 0; i < resArr.length; i++) {
//            resArr[i] = Math.sqrt(yArrOutput[i].im * yArrOutput[i].im); //yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im
//        }
//        return resArr;
//    }
//
//    public static Complex[] dft22(double[] yArrInput, int sampleRate, double step) {
//        //ОГРАНИЧЕНИЕ на максимальную частоту (задаётся в Options):
//        int sizeCof = Math.min(sampleRate / 2, Options.getMaxFrequencyForDFT());
//        Complex[] yArrOutput = new Complex[(int) (sizeCof / step)];
//        double kk = 0;
//        for (int k = 0; k < yArrOutput.length; k++) {
//            yArrOutput[k] = new Complex();
//            for (int n = 0; n < yArrInput.length; n++) {
//                double cof = 2 * Math.PI * kk * n / sampleRate;
//                yArrOutput[k].real += yArrInput[n] * Math.cos(cof);
//                yArrOutput[k].im -= yArrInput[n] * Math.sin(cof);
//            }
//            kk += step;
//        }
//
//        return yArrOutput;
//    }

//    private static Complex[] fft(Complex[] x, int sampleRate) {
//        int N = x.length;
//        if (N == 1) {
//            return x;
//        }
//
//        Complex[] xEven = new Complex[N / 2];
//        Complex[] xOdd = new Complex[N / 2];
//        for (int k = 0; k < N / 2; k++) {
//            xEven[k] = x[2 * k];
//            xOdd[k] = x[2 * k + 1];
//        }
//
//        Complex[] XEven = fft(xEven, sampleRate);
//        Complex[] XOdd = fft(xOdd, sampleRate);
//
//        Complex[] X = new Complex[N];
//
//        for (int k = 0; k < N / 2; k++) {
//            double cof = -2 * Math.PI * k / N;
//            Complex complex = new Complex(Math.cos(cof), Math.sin(cof));
//            X[k] = XEven[k].plus(XOdd[k].multiply(complex));
//            X[k + N / 2] = XEven[k].minus(XOdd[k].multiply(complex));
//        }
//        return X;
//    }

//    public static Complex[] ifft(Complex[] x, int sampleRate) {
//        int N = x.length;
//        if (N == 1) {
//            return x;
//        }
//
//        Complex[] xEven = new Complex[N / 2];
//        Complex[] xOdd = new Complex[N / 2];
//        for (int k = 0; k < N / 2; k++) {
//            xEven[k] = x[2 * k];
//            xOdd[k] = x[2 * k + 1];
//        }
//
//        Complex[] XEven = ifft(xEven, sampleRate);
//        Complex[] XOdd = ifft(xOdd, sampleRate);
//
//        Complex[] X = new Complex[N];
//        for (int k = 0; k < N / 2; k++) {
//            double cof = 2 * Math.PI * k / N;
//            Complex complex = new Complex(Math.cos(cof), Math.sin(cof));
//            X[k] = XEven[k].plus(XOdd[k].multiply(complex));
//            X[k + N / 2] = XEven[k].minus(XOdd[k].multiply(complex));
//        }
//        return X;
//    }

}
