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

    @Test
    void test2() {
        ClosestPair cp = new ClosestPair(12);
        cp.array[0] = cp.buildLocation(0, 0);
        cp.array[1] = cp.buildLocation(1, 0);
        cp.array[2] = cp.buildLocation(8, 0);
        cp.array[3] = cp.buildLocation(8.49, 0);
        cp.array[4] = cp.buildLocation(12, 0);
        cp.array[5] = cp.buildLocation(13, 0);

        cp.array[6] = cp.buildLocation(20, 0);
        cp.array[7] = cp.buildLocation(21, 0);
        cp.array[8] = cp.buildLocation(28, 0);
        cp.array[9] = cp.buildLocation(28.5, 0);
        cp.array[10] = cp.buildLocation(32, 0);
        cp.array[11] = cp.buildLocation(33, 0);

        // Will first split into 0-5 and 6-11
        // 0-5 will then split into 0-2 and 3-5 which will be bruteforced and both return distance 1.
        // Merge step on 0-2 and 3-5 will register minNum as 0.49, and save points 2 and 3 as closest.
        // 6-11 will then split into 6-8 and 9-11 which will be bruteforced and both return distance 1.
        // Mege step on 6-8 and 9-11 will get a length 0.5 for points 8 with 9 which is less than 1, but
        // not less than the global saved 0.49, which will result in the innermost if will not be taken
        // resulting in full branch coverage.

        cp.xQuickSort(cp.array, 0, cp.array.length - 1); // Sorting by x value

        double result; // minimum distance

        result = cp.closestPair(cp.array, cp.array.length);
        System.out.println("Output Data");
        System.out.println("(" + cp.point1.x + ", " + cp.point1.y + ")");
        System.out.println("(" + cp.point2.x + ", " + cp.point2.y + ")");
        System.out.println("Minimum Distance : " + result);

        assertTrue(Math.abs(result-0.49) < 1e-10);
    }
}
