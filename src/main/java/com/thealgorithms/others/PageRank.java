package com.thealgorithms.others;

import java.util.*;

class PageRank {

    public static void main(String args[]) {
        int nodes, i, j;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the Number of WebPages: ");
        nodes = in.nextInt();
        PageRank p = new PageRank();
        System.out.println("Enter the Adjacency Matrix with 1->PATH & 0->NO PATH Between two WebPages: ");
        for (i = 1; i <= nodes; i++) {
            for (j = 1; j <= nodes; j++) {
                p.path[i][j] = in.nextInt();
                if (j == i) {
                    p.path[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(p.path));
        p.calc(nodes);
        for (int k = 1; k < PageRank.branchCoverage.length; k++) {
            if(PageRank.branchCoverage[k] == 0){
                System.out.println("Branch " + k + " was never taken");
            }
        }
    }

    public int path[][] = new int[10][10];
    public double pagerank[] = new double[10];
    public static int[] branchCoverage = new int[15];

    public void calc(double totalNodes) {

        double InitialPageRank;
        double OutgoingLinks = 0;
        double DampingFactor = 0.85;
        double TempPageRank[] = new double[10];
        int ExternalNodeNumber;
        int InternalNodeNumber;
        int k = 1; // For Traversing
        int ITERATION_STEP = 1;
        InitialPageRank = 1 / totalNodes;
        System.out.printf(
                " Total Number of Nodes :" + totalNodes + "\t Initial PageRank  of All Nodes :" + InitialPageRank + "\n");

        // 0th ITERATION _ OR _ INITIALIZATION PHASE //
        for (k = 1; k <= totalNodes; k++) {
            this.pagerank[k] = InitialPageRank;
            branchCoverage[1]++;
        }
        System.out.printf("\n Initial PageRank Values , 0th Step \n");

        for (k = 1; k <= totalNodes; k++) {
            System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
            branchCoverage[2]++;
        }

        while (ITERATION_STEP <= 2) // Iterations
        {
            branchCoverage[3]++;
            // Store the PageRank for All Nodes in Temporary Array
            for (k = 1; k <= totalNodes; k++) {
                TempPageRank[k] = this.pagerank[k];
                this.pagerank[k] = 0;
                branchCoverage[4]++;
            }

            for (InternalNodeNumber = 1; InternalNodeNumber <= totalNodes; InternalNodeNumber++) {
                branchCoverage[5]++;
                for (ExternalNodeNumber = 1; ExternalNodeNumber <= totalNodes; ExternalNodeNumber++) {
                    branchCoverage[6]++;
                    if (this.path[ExternalNodeNumber][InternalNodeNumber] == 1) {
                        branchCoverage[7]++;
                        k = 1;
                        OutgoingLinks = 0; // Count the Number of Outgoing Links for each ExternalNodeNumber
                        while (k <= totalNodes) {
                            branchCoverage[8]++;
                            if (this.path[ExternalNodeNumber][k] == 1) {
                                branchCoverage[9]++;
                                OutgoingLinks = OutgoingLinks + 1; // Counter for Outgoing Links
                            } else {
                                branchCoverage[10]++;
                            }
                            k = k + 1;
                        }
                        // Calculate PageRank
                        this.pagerank[InternalNodeNumber] += TempPageRank[ExternalNodeNumber] * (1 / OutgoingLinks);
                    } else {
                        branchCoverage[11]++;
                    }
                }
                System.out.printf("\n After " + ITERATION_STEP + "th Step \n");

                for (k = 1; k <= totalNodes; k++) {
                    System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
                    branchCoverage[12]++;
                }

                ITERATION_STEP = ITERATION_STEP + 1;
            }

            // Add the Damping Factor to PageRank
            for (k = 1; k <= totalNodes; k++) {
                branchCoverage[13]++;
                this.pagerank[k] = (1 - DampingFactor) + DampingFactor * this.pagerank[k];
            }

            // Display PageRank
            System.out.printf("\n Final Page Rank : \n");
            for (k = 1; k <= totalNodes; k++) {
                System.out.printf(" Page Rank of " + k + " is :\t" + this.pagerank[k] + "\n");
                branchCoverage[14]++;
            }

        }
    }
}
