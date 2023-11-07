package vsu.cs.sapegin.tipis2023.dft;

import javafx.geometry.Point2D;
import vsu.cs.sapegin.tipis2023.second_atta.Options;
import vsu.cs.sapegin.tipis2023.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DFT {

    public static double[] dft(double[] yArrInput, int sampleRate) {

        Complex[] yArrOutput = new Complex[(sampleRate + 1) / 2];
//        System.out.println("dft length arr = " + yArrOutput.length);
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
//        System.out.println("resArr: ");
//        System.out.println(Arrays.toString(resArr));
        return resArr;
    }

    public static double[] dft2(double[] yArrInput, int sampleRate, double step) {
        //ОГРАНИЧЕНИЕ на максимальную частоту (задаётся в Options):
        int sizeCof = Math.min(sampleRate / 2, Options.getMaxFrequencyForDFT());
        Complex[] yArrOutput = new Complex[(int) (sizeCof / step)]; //(sampleRate + 1) / 2
//        System.out.println("dft2 length arr = " + yArrOutput.length);
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
//        System.out.println("resArr dft2 : ");
//        System.out.println(Arrays.toString(resArr));
        return resArr;
    }

    public static double[] idft2(double[] yArrInput, int sampleRate, double maxX, double step) {
        Complex[] yArrOutput = new Complex[(int) (maxX / step)];
        double nn = 0;
        for (int n = 0; n < yArrOutput.length; n++) {
            yArrOutput[n] = new Complex();
            for (int k = 0; k < yArrInput.length; k++) {
                double cof = 2 * Math.PI * k * nn / sampleRate;
                yArrOutput[n].real += yArrInput[k] * Math.cos(cof);
                yArrOutput[n].im += yArrInput[k] * Math.sin(cof);
            }
            yArrOutput[n].real /= sampleRate; //?
            yArrOutput[n].im /= sampleRate;
            nn += step;
        }

        double[] resArr = new double[yArrOutput.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Math.sqrt(yArrOutput[i].im * yArrOutput[i].im); //yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im
        }
//        System.out.println("resArr idft2 : ");
//        System.out.println(Arrays.toString(resArr));
        return resArr;
    }

    public static Complex[] dft22(double[] yArrInput, int sampleRate, double step) {
        //ОГРАНИЧЕНИЕ на максимальную частоту (задаётся в Options):
        int sizeCof = Math.min(sampleRate / 2, Options.getMaxFrequencyForDFT());
        Complex[] yArrOutput = new Complex[(int) (sizeCof / step)];
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

        return yArrOutput;
    }

//    public static Complex[] myDFT(double[] y, int sampleRate) { //frequencies
//
//    }

    public static Complex[] myFFT(double[] y, int sampleRate) {
        Complex[] complexes = new Complex[y.length];
        for (int i = 0; i < complexes.length; i++) {
            complexes[i] = new Complex(y[i], 0);
        }
        return myFFT(complexes, sampleRate);
    }

        public static Complex[] myFFT(Complex[] x, int sampleRate) {
        int N = x.length;
        if (N == 1) {
            return x;
        }

        Complex[] xEven = new Complex[N / 2];
        Complex[] xOdd = new Complex[N / 2];
        for (int k = 0; k < N / 2; k++) {
            xEven[k] = x[2 * k];
            xOdd[k] = x[2 * k + 1];
        }

        Complex[] XEven = myFFT(xEven, sampleRate);
        Complex[] XOdd = myFFT(xOdd, sampleRate);

        Complex[] X = new Complex[N];

        for (int k = 0; k < N / 2; k++) {
            double cof = -2 * Math.PI * k / N;
            Complex complex = new Complex(Math.cos(cof), Math.sin(cof));
//            System.out.println("Size of x: " + X.length);
            X[k] = XEven[k].plus(XOdd[k].multiply(complex));
            X[k + N / 2] = XEven[k].minus(XOdd[k].multiply(complex));
        }
        return X;
    }


    public static double[] getModules(Complex[] yArrOutput) {
        double[] resArr = new double[yArrOutput.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Math.sqrt(yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im);
        }
//        System.out.println("resArr dft2 : ");
//        System.out.println(Arrays.toString(resArr));
        return resArr;
    }

    public static double[] idft22(Complex[] yArrInput, int sampleRate, double maxX, double step) {
        Complex[] yArrOutput = new Complex[(int) (maxX / step)];
        double nn = 0;
        for (int n = 0; n < yArrOutput.length; n++) {
            yArrOutput[n] = new Complex();
            for (int k = 0; k < yArrInput.length; k++) {
                double cof = 2 * Math.PI * k * nn / sampleRate;
                yArrOutput[n].add(yArrInput[k].multiply(new Complex(Math.cos(cof), Math.sin(cof))));
//                yArrOutput[n].real += yArrInput[k] * Math.cos(cof);
//                yArrOutput[n].im += yArrInput[k] * Math.sin(cof);
            }
            yArrOutput[n].real /= sampleRate; //?
            yArrOutput[n].im /= sampleRate;
            nn += step;
        }

        double[] resArr = new double[yArrOutput.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Math.sqrt(yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im);
        }
//        System.out.println("resArr idft2 : ");
//        System.out.println(Arrays.toString(resArr));
        return resArr;
    }

}
