package com.example.codersnacks;

import java.util.*;
import java.util.stream.Collectors;

class AlienAlphabet {

  public static List<String> getAlphabetOrder(String[] words) {
    Set<List<Character>> relations = getRelationships(words);
    GraphA graph =  GraphA.buildGraph(words, relations);
    int alphabetLen = graph.keySet().size();

    List<String> order = new ArrayList<>();
    while (order.size() < alphabetLen) {
      // Compute topological ordering by iteratively removing
      // nodes whitout outgoing edges from the graph
      List<String> letters = nodesWithoutOutgoingEdges(graph);
      if (letters.size() != 1) {
        // if size > 1, there are multiple solutions
        // and size == 0 the graph contains a loop
        return null;
      }
      String next = letters.get(0);
      graph.removeNode(next);

      order.add(next);
    }
    return order;
  }

  private static List<String> nodesWithoutOutgoingEdges(GraphA graph) {
    return graph.entrySet()
        .stream()
        .filter(e -> e.getValue().adj.isEmpty())
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  public static Set<List<Character>> getRelationships(String[] words) {
    Set<List<Character>> rel = new HashSet<>();
    for (int i = 1; i < words.length; i++) {
      int j = 0;
      String w1 = words[i-1];
      String w2 = words[i];
      while (j < w1.length() && j < w2.length()
              && w1.charAt(j) == w2.charAt(j)) {
        j++;
      }
      if (j < w1.length() && j < w2.length()) {
        rel.add(Arrays.asList(w1.charAt(j), w2.charAt(j)));
      }
    }
    return rel;
  }

  public static void main(String[] args) {
    expect(new String[]{"bcca", "babc", "dadc", "aad", "aac"}, "b, d, c, a");
    expect(new String[]{"abc"}, "None");
    expect(new String[]{"a", "ac", "aa", "b", "c"}, "None");
  }

  private static void expect(String[] words, String expected) {
    System.out.println("getAlphabetOrder(words) = " + getAlphabetOrder(words) + " # Expected: "+ expected);
  }
}

class GraphA extends HashMap<String, VertexA> {

  public static GraphA buildGraph(String[] words, Set<List<Character>> edges) {
    GraphA g = new GraphA();
    for (String w : words) {
      for (char c : w.toCharArray()){
        g.getOrCreate(String.valueOf(c));
      }
    }
    for (List<Character> e : edges){
//      String from = String.valueOf(e.get(0));
//      String to = String.valueOf(e.get(1));
      String from = String.valueOf(e.get(1));
      String to = String.valueOf(e.get(0));
      g.getOrCreate(from).append(to);
      g.getOrCreate(to);
    }
    return g;
  }

  public VertexA getOrCreate(String id) {
    if (!this.containsKey(id)) {
      VertexA v = new VertexA(id);
      this.put(id, v);
    }
    return this.get(id);
  }

  public void removeNode(String node) {
    for (Map.Entry<String, VertexA> e : entrySet()) {
      e.getValue().adj.remove(node);
    }
    this.remove(node);
  }
}

class VertexA {
  String id;
  List<String> adj = new ArrayList<>();

  public VertexA(String id) {
    this.id = id;
  }
  public void append(String vertex) {
    adj.add(vertex);
  }

  @Override
  public String toString() {
    return "{'id':'"+id+"', 'adj': " + adj + "}";
  }
}
