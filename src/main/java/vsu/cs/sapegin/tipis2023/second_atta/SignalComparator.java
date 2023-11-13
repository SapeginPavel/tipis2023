package vsu.cs.sapegin.tipis2023.second_atta;

import java.util.Arrays;

public class SignalComparator {
    public static double[] generateSignalByCompareInputSignalWithBaseSignal(double[] inputSignal, double baseSignal) {
        double[] resSignal = new double[inputSignal.length];
        baseSignal = resSignal[0];
        for (int i = 0; i < resSignal.length; i++) {
            if (inputSignal[i] > baseSignal) {
                resSignal[i] = 1;
            } else {
                resSignal[i] = 0;
            }
        }
        return resSignal;
    }
}
