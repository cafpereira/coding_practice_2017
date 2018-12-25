package com.example.leetcode;

import java.util.*;

public class p127_WordLadder_V2 {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> dic = new HashSet<>(wordList);
    // Corner case
    if (!dic.contains(endWord)) {
      return 0;
    }

    // Run a two-end BFS search. When we look both ends (beginSet, endSet)
    // we reduce the size of the search space by half of the exponent factor.
    // For instance, if a normal BFS would take O(b^2d), this will be:
    // O(b^d) + O(b^d) == O(b^d)
    Set<String> beginSet = new HashSet<>();
    Set<String> endSet = new HashSet<>();
    Set<String> visited = new HashSet<>();

    beginSet.add(beginWord);
    endSet.add(endWord);

    visited.add(beginWord);
    visited.add(endWord);
    int distance = 2;

    // If either search set becomes empty, this means there is not direct
    // transformation path from beginWord to endWord
    while (!beginSet.isEmpty() && !endSet.isEmpty()) {
      if (beginSet.size() > endSet.size()) {
        // Memory optmization that will search the smaller end first.
        // This will take the path that generate less words in between.
        Set<String> tmp = beginSet;
        beginSet = endSet;
        endSet = tmp;
      }

      Set<String> nextLevel = new HashSet<>();

      for (String word : beginSet) {
        for (int i = 0; i < word.length(); i++) {
          char[] array = word.toCharArray();
          for (char c = 'a'; c <= 'z'; c++) {
            array[i] = c;
            String cand = new String(array);
            if (endSet.contains(cand)) {
              return distance;
            }
            if (visited.contains(cand) || !dic.contains(cand)) {
              continue;
            }
            visited.add(cand);
            nextLevel.add(cand);
          }
        }
      }
      beginSet = nextLevel;
      distance++;
    }

    // Transformation path not found
    return 0;
  }

  public static void main(String[] args) {
    p127_WordLadder_V2 solution = new p127_WordLadder_V2();
    List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
    System.out.println("result: " + solution.ladderLength("hit", "cog", wordList));
  }
}