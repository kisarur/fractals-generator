/**
 * Author       : Kisaru Liyanage
 * Description  : This is a sub class of Fractal class used to create Fractals using Julia set
 * Date         : 04/09/2016
 */

public class Julia extends Fractal{
    private Complex K; //complex number given by the user


    Julia () {
        super(-1, 1, -1, 1, 1000);
        K = new Complex(-0.4, 0.6);
        computeFractalColors();
    }

    Julia (double realC, double complexC){
        super(-1, 1, -1, 1, 1000);
        K = new Complex(realC, complexC);
        computeFractalColors();
    }

    //this method sets the color scheme used to draw the points
    public void setColorScheme () {
        //array contains colors in hex, outermost color to innermost color
        colorScheme = new int[]{0x0f3d3d, 0x145252, 0x196666, 0x1f7a7a, 0x29a3a3, 0x33cccc, 0x5cd6d6, 0x99e6e6, 0xc2f0f0, 0xebfafa, 0xffffff};
    }

    //this method calculates the number of iterations at which the scheme starts diverging, for a point
    //in complex region
    public int calculateDivergingN(Complex complexPoint) {
        int n;
        Complex z = complexPoint;
        for (n = 0; n < iterations; n++) {
            z = z.squared().add(K);
            if (z.absSquared() > 4) {
                break;
            }
        }
        return n;
    }

}
