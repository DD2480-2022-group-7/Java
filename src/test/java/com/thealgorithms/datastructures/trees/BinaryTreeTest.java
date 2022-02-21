package com.thealgorithms.datastructures.trees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    public BinaryTree tree;

    /**
     * Constructs the following tree:
     *          5
     *        /   \
     *       3     7
     *      / \   / \
     *     1   4 6   8
     *    / \         \
     *   0   2         9
     */
    @BeforeEach
    public void initBinTree() {
        tree = new BinaryTree();
        int[] elementsInTree = { 5, 3, 1, 0, 2, 4, 7, 6, 8, 9 };
        for(int elem : elementsInTree) {
            tree.put(elem);
        }
    }

    @Test
    public void removeReturnsFalseWhenCalledOnNodeThatDoesNotExist() {
        assertFalse(tree.remove(11));
    }

    @Test
    public void removeReturnsTrueWhenCalledOnNodeInTree() {
        assertTrue(tree.remove(3));
    }

    @Test
    public void removingLeftLeafNodeSetsParentsLeftChildToNull() {
        tree.remove(0);
        assertEquals(null, tree.find(1).left);
    }

    @Test
    public void removingRightLeafNodeSetsParentsRightChildToNull() {
        tree.remove(9);
        assertEquals(null, tree.find(8).right);
    }

    @Test
    public void removingRootSetsRootToSuccessor() {
        tree.remove(5);
        assertEquals(6, tree.getRoot().data);
    }

    @Test
    public void singleChildNodeReplacesParentIfParentIsRemoved() {
        tree.remove(8);
        assertEquals(9, tree.find(7).right.data);
    }

    @Test
    public void findReturnsParentWhenCalledOnRemovedNode() {
        tree.remove(6);
        assertEquals(7, tree.find(6).data);
    }

}