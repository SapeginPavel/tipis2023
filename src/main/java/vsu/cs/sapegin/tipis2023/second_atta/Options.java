package vsu.cs.sapegin.tipis2023.second_atta;

public class Options {

    //ощущение, что моё обратное преобразование фурье не работает
    private static int amplitudeBase = 1;
    private static int amplitudeMod = 2;

    private static int frequencyBase = 8; //8
    private static int frequencyMod = 14;

    private static int meanderFrequency = 1;

    private static double defaultMinX = 0;
    private static double defaultMaxX = 3; //3
    private static int defaultAmountOfPointsForUnitSegment = 256; //256

    private static int maxFrequencyForDFT = 64;

    //фаза


    public static int getAmplitudeBase() {
        return amplitudeBase;
    }

    public static void setAmplitudeBase(int amplitudeBase) {
        Options.amplitudeBase = amplitudeBase;
    }

    public static int getAmplitudeMod() {
        return amplitudeMod;
    }

    public static void setAmplitudeMod(int amplitudeMod) {
        Options.amplitudeMod = amplitudeMod;
    }

    public static int getFrequencyBase() {
        return frequencyBase;
    }

    public static void setFrequencyBase(int frequencyBase) {
        Options.frequencyBase = frequencyBase;
    }

    public static int getFrequencyMod() {
        return frequencyMod;
    }

    public static void setFrequencyMod(int frequencyMod) {
        Options.frequencyMod = frequencyMod;
    }

    public static double getDefaultMinX() {
        return defaultMinX;
    }

    public static void setDefaultMinX(double defaultMinX) {
        Options.defaultMinX = defaultMinX;
    }

    public static double getDefaultMaxX() {
        return defaultMaxX;
    }

    public static void setDefaultMaxX(double defaultMaxX) {
        Options.defaultMaxX = defaultMaxX;
    }

    public static int getDefaultAmountOfPointsForUnitSegment() {
        return defaultAmountOfPointsForUnitSegment;
    }

    public static void setDefaultAmountOfPointsForUnitSegment(int defaultAmountOfPointsForUnitSegment) {
        Options.defaultAmountOfPointsForUnitSegment = defaultAmountOfPointsForUnitSegment;
    }

    public static int getMeanderFrequency() {
        return meanderFrequency;
    }

    public static void setMeanderFrequency(int meanderFrequency) {
        Options.meanderFrequency = meanderFrequency;
    }

    public static int getMaxFrequencyForDFT() {
        return maxFrequencyForDFT;
    }

    public static void setMaxFrequencyForDFT(int maxFrequencyForDFT) {
        Options.maxFrequencyForDFT = maxFrequencyForDFT;
    }
}
