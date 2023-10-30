package vsu.cs.sapegin.tipis2023.second_atta;

public class Options {
    private static int amplitudeBase = 1;
    private static int amplitudeMod = 2;

    private static int frequencyBase = 4;
    private static int frequencyMod = 8;

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
}
