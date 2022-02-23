package com.thealgorithms.divideandconquer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * For a set of points in a coordinates system (10000 maximum), ClosestPair
 * class calculates the two closest points.
 */
public final class ClosestPair {

    /**
     * Number of points
     */
    int numberPoints;
    /**
     * Input data, maximum 10000.
     */
    private Location[] array;
    /**
     * Minimum point coordinate.
     */
    Location point1 = null;
    /**
     * Minimum point coordinate.
     */
    Location point2 = null;
    /**
     * Minimum point length.
     */
    private static double minNum = Double.MAX_VALUE;

    public static void setMinNum(double minNum) {
        ClosestPair.minNum = minNum;
    }

    public static void setSecondCount(int secondCount) {
        ClosestPair.secondCount = secondCount;
    }

    /**
     * secondCount
     */
    private static int secondCount = 0;

    /**
     * Constructor.
     */
    ClosestPair(int points) {
        numberPoints = points;
        array = new Location[numberPoints];
    }

    /**
     * Location class is an auxiliary type to keep points coordinates.
     */
    public static class Location {

        double x;
        double y;

        /**
         * @param xpar (IN Parameter) x coordinate <br>
         * @param ypar (IN Parameter) y coordinate <br>
         */
        Location(final double xpar, final double ypar) { // Save x, y coordinates
            this.x = xpar;
            this.y = ypar;
        }
    }

    public Location[] createLocation(int numberValues) {
        return new Location[numberValues];
    }

    public Location buildLocation(double x, double y) {
        return new Location(x, y);
    }

    /**
     * xPartition function: arrange x-axis.
     *
     * @param a (IN Parameter) array of points <br>
     * @param first (IN Parameter) first point <br>
     * @param last (IN Parameter) last point <br>
     * @return pivot index
     */
    public int xPartition(final Location[] a, final int first, final int last) {

        Location pivot = a[last]; // pivot
        int i = first - 1;
        Location temp; // Temporarily store value for position transformation
        for (int j = first; j <= last - 1; j++) {
            if (a[j].x <= pivot.x) { // Less than or less than pivot
                i++;
                temp = a[i]; // array[i] <-> array[j]
                a[i] = a[j];
                a[j] = temp;
            }
        }
        i++;
        temp = a[i]; // array[pivot] <-> array[i]
        a[i] = a[last];
        a[last] = temp;
        return i; // pivot index
    }

    /**
     * yPartition function: arrange y-axis.
     *
     * @param a (IN Parameter) array of points <br>
     * @param first (IN Parameter) first point <br>
     * @param last (IN Parameter) last point <br>
     * @return pivot index
     */
    public int yPartition(final Location[] a, final int first, final int last) {

        Location pivot = a[last]; // pivot
        int i = first - 1;
        Location temp; // Temporarily store value for position transformation
        for (int j = first; j <= last - 1; j++) {
            if (a[j].y <= pivot.y) { // Less than or less than pivot
                i++;
                temp = a[i]; // array[i] <-> array[j]
                a[i] = a[j];
                a[j] = temp;
            }
        }
        i++;
        temp = a[i]; // array[pivot] <-> array[i]
        a[i] = a[last];
        a[last] = temp;
        return i; // pivot index
    }

    /**
     * xQuickSort function: //x-axis Quick Sorting.
     *
     * @param a (IN Parameter) array of points <br>
     * @param first (IN Parameter) first point <br>
     * @param last (IN Parameter) last point <br>
     */
    public void xQuickSort(final Location[] a, final int first, final int last) {

        if (first < last) {
            int q = xPartition(a, first, last); // pivot
            xQuickSort(a, first, q - 1); // Left
            xQuickSort(a, q + 1, last); // Right
        }
    }

    /**
     * yQuickSort function: //y-axis Quick Sorting.
     *
     * @param a (IN Parameter) array of points <br>
     * @param first (IN Parameter) first point <br>
     * @param last (IN Parameter) last point <br>
     */
    public void yQuickSort(final Location[] a, final int first, final int last) {

        if (first < last) {
            int q = yPartition(a, first, last); // pivot
            yQuickSort(a, first, q - 1); // Left
            yQuickSort(a, q + 1, last); // Right
        }
    }

    /**
     * closestPair function: find closest pair.
     *
     * @param a (IN Parameter) array stored before divide <br>
     * @param indexNum (IN Parameter) number coordinates a <br>
     * @return minimum distance <br>
     */
    public double closestPair(final Location[] a, final int indexNum) {
        if (indexNum <= 3) { // If the number of coordinates is 3 or less
            return bruteForce(a);
        }
        int divideXIndex = indexNum / 2; // Intermediate value for divide
        Location[] aLeft = Arrays.copyOfRange(a, 0, divideXIndex);
        Location[] aRight = Arrays.copyOfRange(a, 0, indexNum - divideXIndex);

        // called area since the left array contains all locations left of the X midpoint
        double minLeftArea = closestPair(aLeft, divideXIndex);
        double minRightArea = closestPair(aRight, indexNum - divideXIndex);

        // window size (= minimum length)
        double minValue = Math.min(minLeftArea, minRightArea); // Minimum length

        // Create window.  Set the size for creating a window
        // and creating a new array for the coordinates in the window
        ArrayList<Location> insideWindow = new ArrayList<>();
        for (Location loc : a) {
            double xGap = Math.abs(a[divideXIndex].x - loc.x);
            if (xGap < minValue) {
                insideWindow.add(loc);
            } else {
                if (loc.x > a[divideXIndex].x) {
                    break;
                }
            }
        }
        ClosestPair.setSecondCount(secondCount + insideWindow.size());
        // new array for coordinates in window
        Location[] firstWindow = new Location[secondCount];
        firstWindow = insideWindow.toArray(firstWindow);
        yQuickSort(firstWindow, 0, secondCount - 1); // Sort by y coordinates
        /* Coordinates in Window */
        minValue = checkPointsInWindow(firstWindow, minValue);
        ClosestPair.setSecondCount(0);
        return minValue;
    }

    private double checkPointsInWindow(Location[] window, double minValue) {
        double length;
        // size comparison within window
        for (int i = 0; i < secondCount - 1; i++) {
            for (int j = (i + 1); j < secondCount; j++) {
                double xGap = Math.abs(window[i].x - window[j].x);
                double yGap = Math.abs(window[i].y - window[j].y);
                if (yGap < minValue) {
                    length = Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
                    // If measured distance is less than current min distance
                    if (length < minValue) {
                        // Change minimum distance to current distance
                        minValue = length;
                        // Conditional for registering final coordinate
                        if (length < minNum) {
                            ClosestPair.setMinNum(length);
                            point1 = window[i];
                            point2 = window[j];
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return minValue;
    }

    /**
     * bruteForce function: When the number of coordinates is less than 3.
     *
     * @param arrayParam (IN Parameter) array stored before divide <br>
     * @return <br>
     */
    public double bruteForce(final Location[] arrayParam) {

        double minValue = Double.MAX_VALUE; // minimum distance
        double length;
        double xGap; // Difference between x coordinates
        double yGap; // Difference between y coordinates
        double result = 0;

        if (arrayParam.length == 2) {
            // Difference between x coordinates
            xGap = (arrayParam[0].x - arrayParam[1].x);
            // Difference between y coordinates
            yGap = (arrayParam[0].y - arrayParam[1].y);
            // distance between coordinates
            length = Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
            // Conditional statement for registering final coordinate
            if (length < minNum) {
                ClosestPair.setMinNum(length);
            }
            point1 = arrayParam[0];
            point2 = arrayParam[1];
            result = length;
        }
        if (arrayParam.length == 3) {
            for (int i = 0; i < arrayParam.length - 1; i++) {
                for (int j = (i + 1); j < arrayParam.length; j++) {
                    // Difference between x coordinates
                    xGap = (arrayParam[i].x - arrayParam[j].x);
                    // Difference between y coordinates
                    yGap = (arrayParam[i].y - arrayParam[j].y);
                    // distance between coordinates
                    length = Math.sqrt(Math.pow(xGap, 2) + Math.pow(yGap, 2));
                    // If measured distance is less than current min distance
                    if (length < minValue) {
                        // Change minimum distance to current distance
                        minValue = length;
                        if (length < minNum) {
                            // Registering final coordinate
                            ClosestPair.setMinNum(length);
                            point1 = arrayParam[i];
                            point2 = arrayParam[j];
                        }
                    }
                }
            }
            result = minValue;
        }
        return result; // If only one point returns 0.
    }

    /**
     * main function: execute class.
     *
     * @param args (IN Parameter) <br>
     */
    public static void main(final String[] args) {

        // Input data consists of one x-coordinate and one y-coordinate
        ClosestPair cp = new ClosestPair(12);
        cp.array[0] = cp.buildLocation(2, 3);
        cp.array[1] = cp.buildLocation(2, 16);
        cp.array[2] = cp.buildLocation(3, 9);
        cp.array[3] = cp.buildLocation(6, 3);
        cp.array[4] = cp.buildLocation(7, 7);
        cp.array[5] = cp.buildLocation(19, 4);
        cp.array[6] = cp.buildLocation(10, 11);
        cp.array[7] = cp.buildLocation(15, 2);
        cp.array[8] = cp.buildLocation(15, 19);
        cp.array[9] = cp.buildLocation(16, 11);
        cp.array[10] = cp.buildLocation(17, 13);
        cp.array[11] = cp.buildLocation(9, 12);

        System.out.println("Input data");
        System.out.println("Number of points: " + cp.array.length);
        for (int i = 0; i < cp.array.length; i++) {
            System.out.println("x: " + cp.array[i].x + ", y: " + cp.array[i].y);
        }

        cp.xQuickSort(cp.array, 0, cp.array.length - 1); // Sorting by x value

        double result; // minimum distance

        result = cp.closestPair(cp.array, cp.array.length);
        // ClosestPair start
        // minimum distance coordinates and distance output
        System.out.println("Output Data");
        System.out.println("(" + cp.point1.x + ", " + cp.point1.y + ")");
        System.out.println("(" + cp.point2.x + ", " + cp.point2.y + ")");
        System.out.println("Minimum Distance : " + result);
    }
}
