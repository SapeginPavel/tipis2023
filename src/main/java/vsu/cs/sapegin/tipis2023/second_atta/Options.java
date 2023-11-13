package vsu.cs.sapegin.tipis2023.second_atta;

public class Options {

    /*
        Подобраны подходящие значения всех параметров. Например,
        если поставить частоту меандра meanderFrequency больше, то, чтобы
        на её фоне выделялась базовая частота frequencyBase, её также желательно будет увеличить.
        А если её увеличить, то нужно регулировать параметр widthOfCutOffSignal, которые отвечает
        за ширину срезаемого участка амплитудного спектра. Её нужно увеличивать, если происходит
        увеличение meanderFrequency и frequencyBase.
     */

    private static int widthOfCutOffSignal = 2; //2
    private static int amplitudeBase = 1; //1
    private static int amplitudeMod = 2; //2

    private static int frequencyBase = 8; //8
    private static int frequencyMod = 14; //14

    private static int meanderFrequency = 1; //1

    private static double defaultMinX = 0; //0

    //Кратные двум величины:
    private static double defaultMaxX = 2; //2
    private static int defaultAmountOfPointsForUnitSegment = 256; //256

    private static int maxFrequencyForDFT = 64; //64


    public static int getAmplitudeBase() {
        return amplitudeBase;
    }

    public static int getAmplitudeMod() {
        return amplitudeMod;
    }

    public static int getFrequencyBase() {
        return frequencyBase;
    }

    public static int getFrequencyMod() {
        return frequencyMod;
    }

    public static double getDefaultMinX() {
        return defaultMinX;
    }

    public static double getDefaultMaxX() {
        return defaultMaxX;
    }

    public static int getDefaultAmountOfPointsForUnitSegment() {
        return defaultAmountOfPointsForUnitSegment;
    }


    public static int getMeanderFrequency() {
        return meanderFrequency;
    }

    public static int getMaxFrequencyForDFT() {
        return maxFrequencyForDFT;
    }


    public static int getWidthOfCutOffSignal() {
        return widthOfCutOffSignal;
    }

}
