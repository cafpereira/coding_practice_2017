package com.example.leetcode;

import java.util.*;

public class p269_AlienDictionary {
  public static List<String> alienOrder(String[] words) {
    int n = words.length;
    Graph graph = new Graph();

    for (int i = 0; i < n - 1; i++) {
      // Only one edge per pair of words? A: Yes
      Edge e = findEdge(words[i], words[i+1]);
      if (e != null) {
        graph.get(e.to).link(e.from); // we reverse the edge direction to make easier
                                     // to implement the inDegree tracking bellow*
      }
    }
    // Validate topology by checking the inDegree count of all nodes.
    // There must be one and only one node with inDegree count equal to zero.
    List<String> order = new ArrayList<>();
    while(!graph.isEmpty()){
      Node min = graph.removeMin(); // Remove node with min inDegree from internal BST
                                  // Also decrement the degree on each* adjacent node.
      if (min.inDegree > 0) {
        // Graph has a cycle, cant determine the order of the letters
        // inside the cycle. No solution.
        return null;
      }
      if (graph.peekMin().inDegree == 0) {
        // Graph contains multiple leaf nodes, also ambiguous
        // because there are multiple solutions.
        return null;
      }
      order.add(min.value.toString());
    }
    return order;
  }

  static Edge findEdge(String w1, String w2) {
    // Match prefixes of w1 and w2, the first L1 and L2 that appear
    // immediately after the matching prefix of w1 and w2 respectively
    // will make an edge L1 -> L2
    return null;
  }

  private static class Graph {
    public Node get(Character val) {
      return null;
    }
    public boolean isEmpty() {
      return false;
    }
    public Node removeMin() {
      return null;
    }
    public Node peekMin() {
      return null;
    }
  }
  private static class Edge {
    public Character to;
    public Character from;
  }
  private static class Node {
    public Character value;
    public int inDegree;

    public Node link(Character from) {
      return null;
    }
  }
}
