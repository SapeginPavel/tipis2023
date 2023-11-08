package vsu.cs.sapegin.tipis2023.dft;

import java.util.Arrays;

public class DFT {

    public static Complex[] fft(double[] y, int sampleRate) {
        Complex[] complexes = new Complex[y.length];
        for (int i = 0; i < complexes.length; i++) {
            complexes[i] = new Complex(y[i], 0);
        }
        return Arrays.copyOfRange(fft(complexes, sampleRate), 0, sampleRate / 2);
    }

    private static Complex[] fft(Complex[] x, int sampleRate) {
        return generalFourierTransform(x, sampleRate, false);
    }

    public static double[] getModulesAfterFFT(Complex[] complexesAfterFFT) {
        return getModules(complexesAfterFFT);
    }

    public static double[] getModules(Complex[] yArrOutput) {
        double[] resArr = new double[yArrOutput.length];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = Math.sqrt(yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im);
        }
        return resArr;
    }

    public static Complex[] ifft(double[] y, int sampleRate) {
        Complex[] complexes = new Complex[y.length];
        for (int i = 0; i < complexes.length; i++) {
            complexes[i] = new Complex(y[i], 0);
        }
        return ifft(complexes, sampleRate);
    }

    public static Complex[] ifft(Complex[] x, int sampleRate) {
        return generalFourierTransform(x, sampleRate, true);
    }

    private static Complex[] generalFourierTransform(Complex[] x, int sampleRate, boolean inverse) {
        int N = sampleRate; //x.length
        if (N == 1) {
            return x;
        }

        Complex[] xEven = new Complex[N / 2];
        Complex[] xOdd = new Complex[N / 2];
        for (int k = 0; k < N / 2; k++) {
            xEven[k] = x[2 * k];
            xOdd[k] = x[2 * k + 1];
        }

        Complex[] XEven = generalFourierTransform(xEven, N / 2, inverse);
        Complex[] XOdd = generalFourierTransform(xOdd, N / 2, inverse);

        Complex[] X = new Complex[N];
        for (int k = 0; k < N / 2; k++) {
            int sign = inverse ? 1 : -1;
            double cof = sign * 2 * Math.PI * k / N;
            Complex complex = new Complex(Math.cos(cof), Math.sin(cof));
            X[k] = XEven[k].plus(XOdd[k].multiply(complex));
            X[k + N / 2] = XEven[k].minus(XOdd[k].multiply(complex));
        }
        return X;
    }
}
