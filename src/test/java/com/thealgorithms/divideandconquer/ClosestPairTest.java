package com.thealgorithms.divideandconquer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    void test() {
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

        assertTrue(Math.abs(result-Math.sqrt(1+1)) < 1e-10);
    }
}
