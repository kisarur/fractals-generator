/**
 * Author       : Kisaru Liyanage
 * Description  : This is a sub class of Fractal class used to create Fractals using Mandelbrot set
 * Date         : 04/09/2016
 */

public class Mandelbrot  extends Fractal {

    Mandelbrot () {
        super(-1, 1, -1, 1, 1000);
        computeFractalColors();
    }

    Mandelbrot (double regionMinX, double regionMaxX, double regionMinY, double regionMaxY){
        super(regionMinX,regionMaxX, regionMinY, regionMaxY, 1000);
        computeFractalColors();
    }

    Mandelbrot (double regionMinX, double regionMaxX, double regionMinY, double regionMaxY, int iterations){
        super(regionMinX,regionMaxX, regionMinY, regionMaxY, iterations);
        computeFractalColors();
    }

    //this method sets the color scheme used to draw the points
    public void setColorScheme () {
        //array contains colors in hex, outermost color to innermost color
        colorScheme = new int[]{0x304000, 0x3F5500, 0x394d00, 0x4d6600, 0x608000, 0x86b300, 0xace600, 0xc6ff1a, 0xd9ff66, 0xecffb3};
    }

    //this method calculates the number of iterations at which the scheme starts diverging
    public int calculateDivergingN(Complex complexPoint) {
        int n;
        Complex z = new Complex(0, 0);
        for (n = 0; n < iterations; n++) {
            z = z.squared().add(complexPoint);
            if (z.absSquared() > 4) {
                break;
            }
        }
        return n;
    }

}
