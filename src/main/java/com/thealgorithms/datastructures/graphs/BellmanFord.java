package com.thealgorithms.datastructures.graphs;

import java.io.InputStream;
import java.util.*;

class BellmanFord /*Implementation of Bellman ford to detect negative cycles. Graph accepts inputs in form of edges which have
start vertex, end vertex and weights. Vertices should be labelled with a number between 0 and total number of vertices-1,both inclusive*/ {

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
        obj.go(System.in, true);
    }

    public int[] go(InputStream input, boolean printStuff) // Interactive run for understanding the class first time. Assumes source vertex is 0 and
                                       // shows distance to all vertices
    {
        Scanner sc = new Scanner(input); // Grab scanner object for user input or other input stream

        // Reads from InputStream, could be user input.
        int nbVertices = sc.nextInt();
        int nbEdges = sc.nextInt();

        Edge[] arr = new Edge[nbEdges]; // Array of edges
        int srcVertex, endVertex, weight;

        // Adds all given edges to the array of edges
        for (int i = 0; i < nbEdges; i++) {

            // Reads from InputStream, could be user input.
            srcVertex = sc.nextInt();
            endVertex = sc.nextInt();
            weight = sc.nextInt();
            
            arr[i] = new Edge(srcVertex, endVertex, weight);
        }
        sc.close();

        // Distance array for holding the finalized shortest path distance between source
        // and all vertices
        int[] dist = new int[nbVertices];
        // Parent array for holding the paths
        int[] paths = new int[nbVertices];
        for (int i = 0; i < nbVertices; i++) {
            dist[i] = Integer.MAX_VALUE; // Initializing distance values
        }

        // Get all distances
        calcDistances(dist, arr, paths, nbVertices, nbEdges);

        // Negative cycle checking
        int negCycle = checkNegativeCycles(dist, arr, nbEdges);
        if (negCycle == 1)  {
            if (printStuff)    {   System.out.println("Graph contains negative cycles...");    }
            return null;
        }

        // Prints result if printStuff is set to true
        if (printStuff)    {   printResult(dist, paths, nbVertices);   }

        // Returns the list of distances
        return dist;
    }

    /**
     * Calculates the shortest distance to all vertices in the given graph
     *
     * @param dist          array of calculated distances to all other vertices
     * @param arr           array with all edges in graph
     * @param paths             array for determining path
     * @param nbVertices    number of vertices
     * @param nbEdges       number of edges
     */
    private void calcDistances(int[] dist, Edge[] arr, int[] paths, int nbVertices, int nbEdges)    {
        dist[0] = 0;
        paths[0] = -1;

        for (int i = 0; i < nbVertices - 1; i++) {
            for (int j = 0; j < nbEdges; j++) {
                if ((int) dist[arr[j].u] != Integer.MAX_VALUE
                        && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                    dist[arr[j].v] = dist[arr[j].u] + arr[j].w; // Update
                    paths[arr[j].v] = arr[j].u;
                }
            }
        }
    }

    /**
     * Determines if there exists a negative cycle in the given graph.
     *
     * @param dist      array of calculated distances to all other vertices
     * @param arr       array with all edges in graph
     * @param nbEdges   number of edges
     *
     * @return      Returns 1 if there is, 0 otherwise.
     */
    private int checkNegativeCycles(int[] dist, Edge[] arr, int nbEdges)   {

        for (int j = 0; j < nbEdges; j++) {
            if ((int) dist[arr[j].u] != Integer.MAX_VALUE && dist[arr[j].v] > dist[arr[j].u] + arr[j].w) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Prints the result to standard out
     *
     * @param dist          array of calculated distances to all other vertices
     * @param paths         parent array for holding the paths
     * @param nbVertices    number of vertices
     */
    private void printResult(int[] dist, int[] paths, int nbVertices)    {

        System.out.println("Distances are: ");
        for (int i = 0; i < nbVertices; i++) {
            System.out.println(i + " " + dist[i]);
        }

        System.out.println("Path followed:");
        for (int i = 0; i < nbVertices; i++) {
            System.out.print("0 ");
            printPath(paths, i);
            System.out.println();
        }
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
