package com.example.hackerrank.part02_algorithms;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach
 * guide: https://www.youtube.com/watch?v=0XgVhsMOcQM
 */
class BFSDistance {
  public static class Graph {

    private static final Integer EDGE_LENGTH = 6;
    public Map<Integer, Node> table;

    public Graph(int size) {
      table = new HashMap<>();
      for (int i = 0; i < size; i++) {
        table.put(i, new Node(i));
      }
    }

    public void addEdge(int first, int second) {
      table.get(first).adj.add(second);
      table.get(second).adj.add(first);
    }

    public int[] shortestReach(int startId) { // 0 indexed
      runBFS(table.get(startId));
      int[] dist = new int[table.size()];
      for (Node n : table.values()) {
        dist[n.val] = n.dist;
      }
      return dist;
    }

    public void runBFS(Node start) {
      Queue<Node> queue = new LinkedList<>();

      start.dist = 0;
      start.visited = true;
      queue.add(start);

      while (!queue.isEmpty()) {
        Node cur = queue.poll();
        for (Integer i : cur.adj) {
          Node adj = table.get(i);
          if (!adj.visited) {
            // every edge has lenght == 6
            adj.dist = cur.dist + EDGE_LENGTH;
            adj.visited = true;
            queue.add(adj);
          }
        }
      }
    }
  }

  public static class Node {
    Integer val;
    Integer dist;
    Boolean visited;
    Set<Integer> adj = new HashSet<>();

    public Node(Integer val) {
      this.val = val;
      this.dist = -1;
      this.visited = false;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int queries = scanner.nextInt();

    for (int t = 0; t < queries; t++) {

      // Create a graph of size n where each edge weight is 6:
      Graph graph = new Graph(scanner.nextInt());
      int m = scanner.nextInt();

      // read and set edges
      for (int i = 0; i < m; i++) {
        int u = scanner.nextInt() - 1;
        int v = scanner.nextInt() - 1;

        // add each edge to the graph
        graph.addEdge(u, v);
      }

      // Find shortest reach from node s
      int startId = scanner.nextInt() - 1;
      int[] distances = graph.shortestReach(startId);

      for (int i = 0; i < distances.length; i++) {
        if (i != startId) {
          System.out.print(distances[i]);
          System.out.print(" ");
        }
      }
      System.out.println();
    }
    scanner.close();
  }
}
