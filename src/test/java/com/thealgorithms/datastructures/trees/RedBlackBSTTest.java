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
        int[] numbers = new int[100];
        for (int i = 0; i < 100 ; i++) {
            numbers[i] = i;
        }
        for (int num: numbers){
            tree.insert(num);
        }
        //tree.printTree(tree.root);
    }

    @Test
    public void testDummy() {
        assertTrue(true);
    }

    @Test
    public  void TestingCoverage(){

        System.out.println("Before:");
        tree.printTree(tree.root);
        for(int i = 0; i < 100 ; i++) {
           tree.delete((i*7)%99);
        }

        System.out.println("After:");
        tree.printTree(tree.root);
    }
    @AfterAll
    public static void calculateCoverage() {
        double BranchesTaken = 0;
        for (int i = 1; i < RedBlackBST.branchCoverage.length; i++) {
            if (RedBlackBST.branchCoverage[i]!= 0) {
                BranchesTaken++;
                System.out.println("index: " + i);
            }
        }
        double percentage = BranchesTaken/(RedBlackBST.branchCoverage.length-1);
        System.out.println("Branches Taken = " + BranchesTaken);
        System.out.format("Proportion of branches taken: %.2f%%\n", percentage*100);
    }

}