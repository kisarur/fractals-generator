/**
 * Author       : Kisaru Liyanage
 * Description  : This is an abstract class used to create Fractals
 *                (should be extended by a sub class before using)
 * Date         : 04/09/2016
*/

import javax.swing.*;
import java.awt.*;

public abstract class Fractal extends JPanel{
    private static final int WIDTH = 800;
    private Region complexRegion;
    protected int iterations;
    protected int colorScheme[];
    private Color colorsOfPoints[][] = new Color[WIDTH][WIDTH];

    Fractal (double regionMinX, double regionMaxX, double regionMinY, double regionMaxY, int iterations) {
        complexRegion = new Region(regionMinX, regionMaxX, regionMinY, regionMaxY);
        this.iterations = iterations;
        setColorScheme();
    }

    //this method computes the colors of all the points in the drawing canvas for the relevant set
    protected void computeFractalColors() {
        int numOfThreads = 4;
        Thread threads[] = new ColorCalcThread[numOfThreads];

        //calculating colors of points using threads
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new ColorCalcThread(i, WIDTH / numOfThreads);
            threads[i].start();
        }

        //joining all the threads (this is to make sure all the threads have finished their job before
        //point colors are used by the drawing canvas to draw the fractal)
        try {
            for (int i = 0; i < numOfThreads; i++) {
                threads[i].join();
            }
        } catch (Exception e) {
            System.out.println("Error occurred when joining threads!");
        }
    }

    //this is an inner class used to calculate colors of points using threads
    private class ColorCalcThread extends Thread {
        private int threadCount;
        private int threadRange;

        ColorCalcThread(int tCount, int tRange) {
            threadCount = tCount;
            threadRange = tRange;
        }

        @Override
        public void run() {
            Region drawingCanvas = new Region(0, WIDTH - 1, 0, WIDTH - 1);
            Point point;
            for (int i = threadCount * threadRange; i < (threadCount + 1) * threadRange; i++) {
                for (int j = 0; j < WIDTH; j++) {
                    //mapping point of drawing canvas to complex plane and computing the color
                    //of the point
                    point = new Point(i, j);
                    point.map(drawingCanvas, complexRegion);
                    colorsOfPoints[i][j] = getPointColor(calculateDivergingN(point.toComplex()));
                }
            }
        }
    }

    abstract int calculateDivergingN(Complex complexPoint);

    abstract void setColorScheme();

    //this method takes the iteration count at the scheme converges for a point, and returns the relevant color
    //in the color scheme specified by setColorScheme
    private Color getPointColor(int divergingN) {
        Color color = null;
        if (divergingN == iterations) { //point is in set
            color = Color.black;
        } else { //point is not in set. so assign a color in colorScheme based on the divergingN
            for (int i = colorScheme.length; i > 0; i--) {
                if (divergingN > (i - 1) * 5) {
                    color = new Color(colorScheme[i - 1]);
                    break;
                }
            }
        }

        return color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //drawing the points on the canvas using the colors computed by computeFractalColors()
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++) {
                g2.setColor(colorsOfPoints[i][j]);

                //points have been mapped thinking that the bottom left corner of canvas is (0, 0)
                //but in JPanel, origin is at top left corner
                g2.drawLine(i, WIDTH - 1 - j, i, WIDTH - 1 - j);
            }
        }

    }

    public static void main(String[] args) {
        Fractal fractal = null;
        String set = args[0];
        if (set.equals("Mandelbrot")) {
            switch (args.length){
                case 1:
                    fractal = new Mandelbrot();
                    break;
                case 5:
                    fractal = new Mandelbrot(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
                    break;
                case 6:

                    fractal = new Mandelbrot(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Integer.parseInt(args[5]));
                    break;
                default:
                    System.out.println("Error: Inavlid input format!");
            }
        } else if (set.equals("Julia")) {
            switch (args.length){
                case 1:
                    fractal = new Julia();
                    break;
                case 3:
                    fractal = new Julia(Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                    break;
                default:
                    System.out.println("Error: Inavlid input format!");
            }
        }

        if (fractal != null) {
            JFrame frame = new JFrame("Fractal");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(fractal);
            frame.setPreferredSize(new Dimension(WIDTH - 1, WIDTH - 1));
            frame.setSize(WIDTH - 1, WIDTH - 1);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } 
    }

}

