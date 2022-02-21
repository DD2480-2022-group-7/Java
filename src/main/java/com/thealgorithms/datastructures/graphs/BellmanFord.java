package com.thealgorithms.datastructures.graphs;

import java.io.InputStream;
import java.util.*;

class BellmanFord /*Implementation of Bellman ford to detect negative cycles. Graph accepts inputs in form of edges which have
start vertex, end vertex and weights. Vertices should be labelled with a number between 0 and total number of vertices-1,both inclusive*/ {

    // For manual code coverage checks
    public int[] coverage = new int[13];

    int vertex, edge;
    private Edge edges[];
    private int index = 0;

    BellmanFord(int v, int e) {
        vertex = v;
        edge = e;
        edges = new Edge[e];
    }

    class Edge {

        int u, v;
        int w;

        /**
         * @param u Source Vertex
         * @param v End vertex
         * @param c Weight
         */
        public Edge(int a, int b, int c) {
            u = a;
            v = b;
            w = c;
        }
    }

    /**
     * @param p[] Parent array which shows updates in edges
     * @param i Current vertex under consideration
     */
    void printPath(int p[], int i) {
        if (p[i] == -1) // Found the path back to parent
        {
            return;
        }
        printPath(p, p[i]);
        System.out.print(i + " ");
    }

    public static void main(String args[]) {
        BellmanFord obj = new BellmanFord(0, 0); // Dummy object to call nonstatic variables
        obj.go(System.in);
    }

    public int[]
            go(InputStream input) // Interactive run for understanding the class first time. Assumes source vertex is 0 and
    // shows distance to all vertices
    {
        Scanner sc = new Scanner(input); // Grab scanner object for user input
        int i, v, e, u, ve, w, j, neg = 0;
        System.out.println("Enter no. of vertices and edges please");
        v = sc.nextInt();
        e = sc.nextInt();
        Edge arr[] = new Edge[e]; // Array of edges
        System.out.println("Input edges");
        for (i = 0; i < e; i++) {
            coverage[0] += 1;
            u = sc.nextInt();
            ve = sc.nextInt();
            w = sc.nextInt();
            arr[i] = new Edge(u, ve, w);
        }
        int dist[]
                = new int[v]; // Distance array for holding the finalized shortest path distance between source
        // and all vertices
        int p[] = new int[v]; // Parent array for holding the paths
        for (i = 0; i < v; i++) {
            coverage[1] += 1;
            dist[i] = Integer.MAX_VALUE; // Initializing distance values
        }
        dist[0] = 0;
        p[0] = -1;
        for (i = 0; i < v - 1; i++) {
            coverage[2] += 1;
            for (j = 0; j < e; j++) {
                coverage[3] += 1;
                if ((int) dist[arr[j].u] != Integer.MAX_VALUE
                        && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                    coverage[4] += 1;
                    dist[arr[j].v] = dist[arr[j].u] + arr[j].w; // Update
                    p[arr[j].v] = arr[j].u;
                }   else    {
                    coverage[5] += 1;
                }
            }
        }
        // Final cycle for negative checking
        for (j = 0; j < e; j++) {
            coverage[6] += 1;
            if ((int) dist[arr[j].u] != Integer.MAX_VALUE && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                coverage[7] += 1;
                neg = 1;
                System.out.println("Negative cycle");
                break;
            }   else    {
                coverage[8] += 1;
            }
        }
        if (neg == 0) // Go ahead and show results of computation
        {
            coverage[9] += 1;
            System.out.println("Distances are: ");
            for (i = 0; i < v; i++) {
                coverage[10] += 1;
                System.out.println(i + " " + dist[i]);
            }
            System.out.println("Path followed:");
            for (i = 0; i < v; i++) {
                coverage[11] += 1;
                System.out.print("0 ");
                printPath(p, i);
                System.out.println();
            }
        }   else    {
            coverage[12] += 1;
        }
        sc.close();
        return dist;
    }

    /**
     * @param source Starting vertex
     * @param end Ending vertex
     * @param Edge Array of edges
     */
    public void show(
            int source,
            int end,
            Edge arr[]) // Just shows results of computation, if graph is passed to it. The graph should
    // be created by using addEdge() method and passed by calling getEdgeArray() method
    {
        int i, j, v = vertex, e = edge, neg = 0;
        double dist[]
                = new double[v]; // Distance array for holding the finalized shortest path distance between source
        // and all vertices
        int p[] = new int[v]; // Parent array for holding the paths
        for (i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE; // Initializing distance values
        }
        dist[source] = 0;
        p[source] = -1;
        for (i = 0; i < v - 1; i++) {
            for (j = 0; j < e; j++) {
                if ((int) dist[arr[j].u] != Integer.MAX_VALUE
                        && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                    dist[arr[j].v] = dist[arr[j].u] + arr[j].w; // Update
                    p[arr[j].v] = arr[j].u;
                }
            }
        }
        // Final cycle for negative checking
        for (j = 0; j < e; j++) {
            if ((int) dist[arr[j].u] != Integer.MAX_VALUE && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                neg = 1;
                System.out.println("Negative cycle");
                break;
            }
        }
        if (neg == 0) // Go ahead and show results of computaion
        {
            System.out.println("Distance is: " + dist[end]);
            System.out.println("Path followed:");
            System.out.print(source + " ");
            printPath(p, end);
            System.out.println();
        }
    }

    /**
     * @param x Source Vertex
     * @param y End vertex
     * @param z Weight
     */
    public void addEdge(int x, int y, int z) // Adds unidirectional edge
    {
        edges[index++] = new Edge(x, y, z);
    }

    public Edge[] getEdgeArray() {
        return edges;
    }
}
