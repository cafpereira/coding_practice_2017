package com.example.coderust.part07_graphs;

import java.util.*;

class Vertex implements Comparable<Vertex> {
  int id;
  int minWeight = Integer.MAX_VALUE;
  List<Edge> edges;
  Edge spanningEdge;

  @Override
  public int compareTo(Vertex other) {
    return Integer.compare(this.minWeight, other.minWeight);
  }

  // @Override
  // equals and hashCode...
}

class Edge {
  Vertex v1; Vertex v2;
  int weight;

  public Vertex neighborOf(Vertex in) {
    return in == this.v1 ? this.v2 : this.v1;
  }
}


public class q02_MinimumSpanningTree {

  public static List<Edge> primAlgoMST (List<Vertex> graph) {
    TreeSet<Vertex> bst = new TreeSet<Vertex>();
    List<Edge> minSpanTree = new ArrayList<>();

    // Pick an arbitrary node and start with minWeight = 0
    graph.get(0).minWeight = 0;
    for (Vertex v : graph) {
      bst.add(v);
    }

    while(!bst.isEmpty()) {
      Vertex cur = bst.first(); bst.remove(cur);
      if (cur.spanningEdge != null) {
        minSpanTree.add(cur.spanningEdge);
      }
      for (Edge edge : cur.edges) {
        Vertex v = edge.neighborOf(cur);
        // Returning edge? Make sure vertex is not already visited
        // by checking if it still exists in the BST
        if (!bst.contains(v)) {
          continue;
        }
        // Verify if smaller spanning tree is possible
        if (v.minWeight > edge.weight) {
          v.minWeight = edge.weight;
          v.spanningEdge = edge;
          bst.remove(v); bst.add(v);
        }
      }
    }
    return minSpanTree;
  }

}
