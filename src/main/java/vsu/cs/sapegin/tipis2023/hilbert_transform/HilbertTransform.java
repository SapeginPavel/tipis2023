package vsu.cs.sapegin.tipis2023.hilbert_transform;

import vsu.cs.sapegin.tipis2023.dft.Complex;
import vsu.cs.sapegin.tipis2023.dft.DFT;

public class HilbertTransform {
    //Физический смысл: фазовый сдвиг всех спектральных составляющих исходного сигнала на pi/2
    //множество мгновенных амплитуд - огибающая
    public static Complex[] hilbertTransform(Complex[] signal) {
        int length = signal.length;

        //Выполняем fft
        Complex[] spectrum = DFT.fft(signal);

        //Создаём фильтр Гильберта
        Complex[] hilbertFilter = new Complex[length];
        int mid = length / 2;
        hilbertFilter[0] = new Complex();
        hilbertFilter[mid] = new Complex(1, 1);
        for (int i = 1; i < mid; i++) {
            double sign = (i % 2 == 0) ? -1.0 : 1.0;
            hilbertFilter[i] = new Complex(sign * 2, sign * 2);
            hilbertFilter[length - i] = new Complex(sign * 2, sign * 2);
        }

        //Умножаем спектр на фильтр Гильберта
        Complex[] transformedSpectrum = new Complex[length];
        for (int i = 0; i < length; i++) {
            transformedSpectrum[i] = spectrum[i].multiply(hilbertFilter[i]);
        }

        //Выполняем ifft
        return DFT.ifft(transformedSpectrum);
    }
}
