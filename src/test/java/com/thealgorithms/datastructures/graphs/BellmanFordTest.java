package com.thealgorithms.datastructures.graphs;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BellmanFordTest {

    static BellmanFord BF;

    @BeforeAll
    static void startUp()  {
        BF = new BellmanFord(0, 0);
    }

    @Test
    void dunnyTest()    {
        assertTrue(true);
    }

    @AfterAll
    static void printCoverage()    {
        float covered = 0;
        for (int i : BF.coverage)  {
            if (i != 0) {
                covered += 1;
            }
        }
        float coverage = covered / BF.coverage.length * 100;
        System.out.println(Arrays.toString(BF.coverage));
        System.out.println("Percentage of branches taken: " + coverage + "%");
    }
}
