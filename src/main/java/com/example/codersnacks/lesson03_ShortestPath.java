package com.example.codersnacks;

import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLBoundPortType;
import java.io.*;
import java.util.*;

class ShortestPath {

  public static int dijkstra(Object[][] edges, String start, String end) {
    Graph graph = Graph.buildGraph(edges);
    Map<String, Integer> bestCosts = new HashMap<>();
    PriorityQueue<Edge> queue = new PriorityQueue<>();
    queue.add(new Edge(start, 0));

    while (!queue.isEmpty() && !bestCosts.containsKey(end)) {
      // Find the next cheapest vertex to add
      Edge lowest = queue.poll();
      if (bestCosts.containsKey(lowest.vertex)) {
        continue;
      }
      bestCosts.put(lowest.vertex, lowest.cost);
      for (Edge e : graph.get(lowest.vertex).adj) {
        queue.add(new Edge(e.vertex, lowest.cost + e.cost));
      }
    }
    if (!bestCosts.containsKey(end)) {
      // Graph is not strongly connected, and there
      // is no path between start and end vertices
      return -1;
    }
    return bestCosts.get(end);
  }
  public static void main(String[] args) {
    Object[][] edges = {{"A", "B", 5},{"A", "C", 3},{"C", "D", 2},{"B", "D", 8},
                        {"D", "E", 4},{"D", "F", 4},{"E", "G", 6},{"F", "G", 5}};
    System.out.println("shortestDist('A','G'): " + dijkstra(edges, "A", "G") + " # Expected: 14");
  }
}

class Graph extends HashMap<String, Vertex> {

  public static Graph buildGraph(Object[][] edges) {
    Graph g = new Graph();
    for (Object[] e : edges){
      String from = (String) e[0];
      String to = (String) e[1];
      Integer cost = (Integer) e[2];
      g.getOrCreate(from).append(to, cost);
      g.getOrCreate(to).append(from, cost);
    }
    return g;
  }

  public Vertex getOrCreate(String id) {
    if (!this.containsKey(id)) {
      Vertex v = new Vertex(id);
      this.put(id, v);
    }
    return this.get(id);
  }
}

class Vertex {
  String id;
  List<Edge> adj = new ArrayList<>();

  public Vertex(String id) {
    this.id = id;
  }
  public void append(String vertex, Integer cost) {
    adj.add(new Edge(vertex, cost));
  }

  @Override
  public String toString() {
    return "{'id':'"+id+"', 'adj': " + adj + "}";
  }
}

class Edge implements Comparable<Edge>{
  String vertex;
  Integer cost;

  public Edge(String vertex, Integer cost) {
    this.vertex = vertex;
    this.cost = cost;
  }
  @Override
  public String toString() {
    return "('"+ vertex +"', " + cost + ")";
  }

  @Override
  public int compareTo(Edge other) {
    return this.cost.compareTo(other.cost);
  }
}
