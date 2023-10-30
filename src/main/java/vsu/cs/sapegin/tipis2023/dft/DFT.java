package vsu.cs.sapegin.tipis2023.dft;

public class DFT {

    public static double[] dft(double[] yArrInput, int sampleRate) {

        Complex[] yArrOutput = new Complex[sampleRate / 2];
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
            resArr[i] = Math.sqrt(yArrOutput[i].real * yArrOutput[i].real + yArrOutput[i].im * yArrOutput[i].im);
        }
        return resArr;
    }

}