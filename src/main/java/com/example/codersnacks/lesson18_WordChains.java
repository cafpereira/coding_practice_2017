package com.example.codersnacks;

import java.util.*;

class WordChains {
  public static Set<String> dic = new HashSet<>();

  static {
    dic.addAll(Arrays.asList("hot","dot","dog","lot","log","cog"));
    dic.addAll(Arrays.asList("food", "floods", "floor", "blood", "flood"));
  }

  public static List<String> wordLadderBFS(String start, String end, Set<String> dic) {
    Queue<String> queue = new LinkedList<>();
    queue.add(start);
    Map<String, String> backtrack = new HashMap<>();
    backtrack.put(start, null);

    while (!queue.isEmpty()) {
      String cur = queue.poll();
      for (String adj : findNeighbors(cur, dic)) {
        if (!backtrack.containsKey(adj)) {
          backtrack.put(adj, cur);
          if (adj.equals(end)) {
            break;
          } else {
            queue.add(adj);
          }
        }
      }
    }
    return backtrack.containsKey(end) ? reconstructChain(backtrack, end) : null;
  }

  public static List<String> reconstructChain(Map<String, String> backtrack, String last) {
    List<String> chain = new ArrayList<>();
    while(last != null) {
      chain.add(last);
      last = backtrack.get(last);
    }
    Collections.reverse(chain);
    return chain;
  }

  public static Set<String> findNeighbors(String word, Set<String> dic) {
    Set<String> neighbors = new HashSet<>();
    int w = word.length();
    for (int i = 0; i < w; i++) {
      for (int l = 0; l < 26; l++) {
        char c = (char) ('a' + l);
        String cand = word.substring(0,i) + c + word.substring(i + 1);
        if (dic.contains(cand)) {
          neighbors.add(cand);
        }
      }
    }
    return neighbors;
  }


  public static void main(String[] args) {
    expect("hit", "cog");
  }

  public static void expect(String start, String end){
    System.out.println("wordLadderBFS('"+ start + "', '" + end + "') = " + wordLadderBFS(start, end, dic));
  }
}
