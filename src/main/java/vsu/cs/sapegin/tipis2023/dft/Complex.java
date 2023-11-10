package vsu.cs.sapegin.tipis2023.dft;

public class Complex {
    public double im = 0;
    public double real = 0;

    public Complex() {
    }

    public Complex(double real) {
        this.real = real;
    }

    public Complex(double real, double im) {
        this.real = real;
        this.im = im;
    }

    public Complex multiply(Complex other) {
        double real = this.real * other.real - this.im * other.im;
        double im = this.real * other.im + this.im * other.real;
        return new Complex(real, im);
    }

    public void add(Complex other) {
        this.real += other.real;
        this.im += other.im;
    }

    public Complex plus(Complex other) {
        return new Complex(this.real + other.real, this.im + other.im);
    }

    public Complex minus(Complex other) {
        return new Complex(this.real - other.real, this.im - other.im);
    }

    @Override
    public String toString() {
        return "Complex{" +
                "im=" + im +
                ", real=" + real +
                '}';
    }

    public double getModule() {
        return Math.sqrt(this.real * this.real + this.im * this.im);
    }
}
