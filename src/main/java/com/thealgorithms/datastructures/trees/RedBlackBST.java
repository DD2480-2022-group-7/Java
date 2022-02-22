package com.thealgorithms.datastructures.trees;

import java.util.Scanner;

/**
 * @author jack870131
 */
public class RedBlackBST {

    private final int R = 0;
    private final int B = 1;

    private class Node {

        int key = -1, color = B;
        Node left = nil, right = nil, p = nil;

        Node(int key) {
            this.key = key;
        }
    }

    private final Node nil = new Node(-1);
    public Node root = nil;

    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.print(
                ((node.color == R) ? " R " : " B ") + "Key: " + node.key + " Parent: " + node.p.key + "\n");
        printTree(node.right);
    }

    public void printTreepre(Node node) {
        if (node == nil) {
            return;
        }
        System.out.print(
                ((node.color == R) ? " R " : " B ") + "Key: " + node.key + " Parent: " + node.p.key + "\n");
        printTree(node.left);
        printTree(node.right);
    }

    public Node findNode(int key, Node findNode) {
        if (root == nil) {
            return null;
        }
        if (key < findNode.key) {
            if (findNode.left != nil) {
                return findNode( key, findNode.left);
            }
        } else if (key > findNode.key) {
            if (findNode.right != nil) {
                return findNode(key, findNode.right);
            }
        } else if (findNode.key == key) {
            return findNode;
        }
        return null;
    }

    public void insert(int key) {
        Node node = new Node(key);
        Node temp = root;
        if (root == nil) {
            root = node;
            node.color = B;
            node.p = nil;
        } else {
            node.color = R;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.p = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }

    private void fixTree(Node node) {
        while (node.p.color == R) {
            Node y = nil;
            if (node.p == node.p.p.left) {
                y = node.p.p.right;

                if (y != nil && y.color == R) {
                    node.p.color = B;
                    y.color = B;
                    node.p.p.color = R;
                    node = node.p.p;
                    continue;
                }
                if (node == node.p.right) {
                    node = node.p;
                    rotateLeft(node);
                }
                node.p.color = B;
                node.p.p.color = R;
                rotateRight(node.p.p);
            } else {
                y = node.p.p.left;
                if (y != nil && y.color == R) {
                    node.p.color = B;
                    y.color = B;
                    node.p.p.color = R;
                    node = node.p.p;
                    continue;
                }
                if (node == node.p.left) {
                    node = node.p;
                    rotateRight(node);
                }
                node.p.color = B;
                node.p.p.color = R;
                rotateLeft(node.p.p);
            }
        }
        root.color = B;
    }

    void rotateLeft(Node node) {
        if (node.p != nil) {
            if (node == node.p.left) {
                node.p.left = node.right;
            } else {
                node.p.right = node.right;
            }
            node.right.p = node.p;
            node.p = node.right;
            if (node.right.left != nil) {
                node.right.left.p = node;
            }
            node.right = node.right.left;
            node.p.left = node;
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.p = root;
            root.p = right;
            right.left = root;
            right.p = nil;
            root = right;
        }
    }

    void rotateRight(Node node) {
        if (node.p != nil) {
            if (node == node.p.left) {
                node.p.left = node.left;
            } else {
                node.p.right = node.left;
            }

            node.left.p = node.p;
            node.p = node.left;
            if (node.left.right != nil) {
                node.left.right.p = node;
            }
            node.left = node.left.right;
            node.p.right = node;
        } else {
            Node left = root.left;
            root.left = root.left.right;
            left.right.p = root;
            root.p = left;
            left.right = root;
            left.p = nil;
            root = left;
        }
    }

    void transplant(Node target, Node with) {
        if (target.p == nil) {
            root = with;
        } else if (target == target.p.left) {
            target.p.left = with;
        } else {
            target.p.right = with;
        }
        with.p = target.p;
    }

    Node treeMinimum(Node subTreeRoot) {
        while (subTreeRoot.left != nil) {
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }

    boolean delete(int key) {
        Node z = new Node(key);
        if ((z = findNode(key, root)) == null) {
            return false;
        }
        Node x;
        Node y = z;
        int yorigcolor = y.color;

        if (z.left == nil) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == nil) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = treeMinimum(z.right);
            yorigcolor = y.color;
            x = y.right;
            if (y.p == z) {
                x.p = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.p = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.p = y;
            y.color = z.color;
        }
        if (yorigcolor == B) {
            deleteFixup(x);
        }
        return true;
    }

    public static int[] branchCoverage = new int[19];
    void deleteFixup(Node x) {
        while (x != root && x.color == B) {
            branchCoverage[1]++;
            if (x == x.p.left) {
                branchCoverage[2]++;
                Node w = x.p.right;
                if (w.color == R) {
                    branchCoverage[3]++;
                    w.color = B;
                    x.p.color = R;
                    rotateLeft(x.p);
                    w = x.p.right;
                } else {
                    branchCoverage[4]++;
                }
                if (w.left.color == B && w.right.color == B) {
                    branchCoverage[5]++;
                    w.color = R;
                    x = x.p;
                    continue;
                } else if (w.right.color == B) {
                    branchCoverage[6]++;
                    w.left.color = B;
                    w.color = R;
                    rotateRight(w);
                    w = x.p.right;
                } else {
                    branchCoverage[7]++;
                }
                if (w.right.color == R) {
                    branchCoverage[8]++;
                    w.color = x.p.color;
                    x.p.color = B;
                    w.right.color = B;
                    rotateLeft(x.p);
                    x = root;
                } else {
                    branchCoverage[9]++;
                }
            } else {
                branchCoverage[10]++;
                Node w = x.p.left;
                if (w.color == R) {
                    branchCoverage[11]++;
                    w.color = B;
                    x.p.color = R;
                    rotateRight(x.p);
                    w = x.p.left;
                } else {
                    branchCoverage[12]++;
                }
                if (w.right.color == B && w.left.color == B) {
                    branchCoverage[13]++;
                    w.color = R;
                    x = x.p;
                    continue;
                } else if (w.left.color == B) {
                    branchCoverage[14]++;
                    w.right.color = B;
                    w.color = R;
                    rotateLeft(w);
                    w = x.p.left;
                } else {
                    branchCoverage[15]++;
                }
                if (w.left.color == R) {
                    branchCoverage[16]++;
                    w.color = x.p.color;
                    x.p.color = B;
                    w.left.color = B;
                    rotateRight(x.p);
                    x = root;
                } else {
                    branchCoverage[17]++;
                }
            }
        }
        x.color = B;
        branchCoverage[18]++;
    }

    public void insertDemo() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Add items");

            int item;

            item = scan.nextInt();
            while (item != -999) {
                insert(item);
                item = scan.nextInt();
            }
            printTree(root);
            System.out.println("Pre order");
            printTreepre(root);
            break;
        }
        scan.close();
    }

    public void deleteDemo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Delete items");
        int item;
        Node node;
        item = scan.nextInt();
        node = new Node(item);
        System.out.print("Deleting item " + item);
        if (delete(item)) {
            System.out.print(": deleted!");
        } else {
            System.out.print(": does not exist!");
        }

        System.out.println();
        printTree(root);
        System.out.println("Pre order");
        printTreepre(root);
        scan.close();
    }
}
