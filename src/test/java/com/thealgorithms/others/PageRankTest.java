package com.thealgorithms.others;

import com.thealgorithms.datastructures.trees.RedBlackBST;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PageRankTest {

    PageRank pr = new PageRank();

    @BeforeEach
    public void initPageRank() {
             /* Adjacency Matrix
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1} */
        pr.path = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    }

    @Test
    public void testDummy() {
        assertTrue(true);
    }

    @Test
    public  void TestingCoverage() {
        pr.calc(5);
        assertEquals(pr.pagerank[1], 0.15000000000000002);
        assertEquals(pr.pagerank[2], 0.32000000000000006);
        assertEquals(pr.pagerank[3], 0.15000000000000002);
        assertEquals(pr.pagerank[4], 0.66);
        assertEquals(pr.pagerank[5], 0.32000000000000006);
    }

    @AfterAll
    public static void calculateCoverage() {
        double BranchesTaken = 0;
        for (int i = 1; i < PageRank.branchCoverage.length; i++) {
            if (PageRank.branchCoverage[i]!= 0) {
                BranchesTaken++;
                System.out.println("index: " + i);
            }
        }
        double percentage = BranchesTaken/(PageRank.branchCoverage.length-1);
        System.out.println("Branches Taken = " + BranchesTaken);
        System.out.format("Proportion of branches taken: %.2f%%\n", percentage*100);
    }
}