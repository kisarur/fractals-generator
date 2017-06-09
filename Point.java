/**
 * Author       : Kisaru Liyanage
 * Description  : This is a class used to create points in x-y plane
 * Date         : 04/09/2016
 */

public class Point {
    protected double x, y;

    Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    //this method maps a point from one region to another region
    public void map(Region fromRegion, Region toRegion) {
        this.x = toRegion.getxMin() + x * (toRegion.getWidth()/fromRegion.getWidth());
        this.y = toRegion.getyMin() + y * (toRegion.getHeight()/fromRegion.getHeight());
    }

    //this method converts a point to a complex number using its x and y values
    public Complex toComplex() {
        return new Complex(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
