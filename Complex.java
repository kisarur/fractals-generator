/**
 * Author       : Kisaru Liyanage
 * Description  : This is a sub class of Point class used to create complex numbers and do
 *                some complex arithmetic(addition, square, square of absolute) on them
 * Date         : 04/09/2016
 */

public class Complex extends Point{
    Complex(double real, double imaginary) {
        super(real, imaginary);
    }

    public Complex squared() {
        return new Complex((x * x - y * y), (2 * x * y));
    }

    public Complex add(Complex number) {
        return new Complex((x + number.getReal()), (y + number.getImaginary()));
    }

    public double absSquared() {
        return x * x + y * y;
    }

    public double getReal() {
        return x;
    }

    public double getImaginary() {
        return y;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
