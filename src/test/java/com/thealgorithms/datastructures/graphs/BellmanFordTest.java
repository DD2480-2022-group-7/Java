package com.thealgorithms.datastructures.graphs;

import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class BellmanFordTest {

    static BellmanFord BF;

    @BeforeAll
    static void startUp()  {
        BF = new BellmanFord(0, 0);
    }

    @Test
    // The smallest graph
    void testOneVertex()  {
        // First line is the number of vertices and number of edges. The other lines are directed edges with
        // source vertex, end vertex, and weight.
        String inputString = "1 0";

        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        int[] result = BF.go(inputStream, false);

        assertEquals(result[0], 0);
    }

    @Test
    // Only two vertices and only a single directed edge between them
    void testTwoVertices()  {
        String inputString = "2 1 " +
                "0 1 3";

        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        int[] result = BF.go(inputStream, false);

        assertEquals(result[0], 0);
        assertEquals(result[1], 3);
    }

    @Test
    // Onyl two vertices but the edge between them is directed from 1 to 0.
    void testTwoVerticesEdgeWrongWay()  {
        String inputString = "2 1 " +
                "1 0 3";

        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        int[] result = BF.go(inputStream, false);

        assertEquals(result[0], 0);
        assertEquals(result[1], Integer.MAX_VALUE);
    }

    @Test
    // A test where routes traversing several vertices may be shorter than
    // the direct route
    void testShorterPathsThroughMoreVertices()   {
        String inputString = "5 6 " +
                "0 1 3 " +
                "1 2 2 " +
                "2 3 1 " +
                "0 2 6 " +
                "0 3 9 " +
                "1 3 1";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        int[] result = BF.go(inputStream, false);

        assertEquals(result[0], 0);
        assertEquals(result[1], 3);
        assertEquals(result[2], 5);
        assertEquals(result[3], 4);
        assertEquals(result[4], Integer.MAX_VALUE);

        // Make sure that printing doesn't change outcome
        InputStream inputStreamWithPrint = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        int[] resultWithPrint = BF.go(inputStreamWithPrint, true);

        assertEquals(resultWithPrint[0], 0);
        assertEquals(resultWithPrint[1], 3);
        assertEquals(resultWithPrint[2], 5);
        assertEquals(resultWithPrint[3], 4);
        assertEquals(resultWithPrint[4], Integer.MAX_VALUE);
    }

    @Test
    // A test which has a negative cycle, in which the result returned should be null
    void testNegativeCycle()    {
        String inputString = "5 7 " +
                "0 1 3 " +
                "1 2 1 " +
                "2 3 1 " +
                "0 2 6 " +
                "0 3 9 " +
                "2 4 -2 " +
                "4 1 0";
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes(StandardCharsets.UTF_8));
        int[] result = BF.go(inputStream, false);

        assertNull(result);
    }
}
