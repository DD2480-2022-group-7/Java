package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    public BinaryTree tree;

    @BeforeEach
    public void initBinTree() {
        tree = new BinaryTree();
        tree.put(5);
        tree.put(3);
        tree.put(7);
        tree.put(2);
        tree.put(4);
        tree.put(0);
        tree.put(1);
        tree.put(6);
        tree.put(8);
        tree.put(9);
        //tree.inOrder(tree.getRoot());
    }

    @Test
    public void testDummy() {
        assertTrue(true);
    }

    @AfterAll
    public static void calculateCoverage() {
        double numBranchesTaken = 0;
        for (int i : BinaryTree.branchCount) {
            if (i != 0)  { numBranchesTaken++; }
        }
        double percentage = numBranchesTaken/BinaryTree.branchCount.length;
        System.out.format("Proportion of branches taken: %.2f%%\n", percentage*100);
    }

}