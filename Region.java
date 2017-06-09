/**
 * Author       : Kisaru Liyanage
 * Description  : This is a class used to create regions in x-y plane using region boundaries
 * Date         : 04/09/2016
 */

public class Region {
    private double xMin, xMax, yMin, yMax;

    Region(double xMin, double xMax, double yMin, double yMax) {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    public double getWidth () {
        return xMax - xMin;
    }

    public double getHeight () {
        return yMax - yMin;
    }

    public double getxMin() {
        return xMin;
    }

    public double getxMax() {
        return xMax;
    }

    public double getyMin() {
        return yMin;
    }

    public double getyMax() {
        return yMax;
    }
}
