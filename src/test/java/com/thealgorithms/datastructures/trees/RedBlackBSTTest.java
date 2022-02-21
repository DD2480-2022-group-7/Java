package com.thealgorithms.datastructures.trees;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;


public class RedBlackBSTTest {

    public RedBlackBST tree;

    @BeforeEach
    public void initRBTree() {
        tree = new RedBlackBST();
        int[] numbers = {8,18,5,15,17,25,40,80};
        for (int num: numbers){
            tree.insert(num);
        }
        //tree.printTree(tree.root);
    }

    @Test
    public void testDummy() {
        assertTrue(true);
    }

    @AfterAll
    public static void calculateCoverage() {
        double BranchesTaken = 0;
        for (int b : RedBlackBST.branchCoverage) {
            if (b != 0) {
                BranchesTaken++;
            }
        }
        double percentage = BranchesTaken/RedBlackBST.branchCoverage.length;
        System.out.format("Proportion of branches taken: %.2f%%\n", percentage*100);
    }

}