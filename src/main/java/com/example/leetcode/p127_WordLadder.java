package com.example.leetcode;

import java.util.*;

public class p127_WordLadder {
  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> visit = new HashSet<>(wordList);
    // Corner cases
    if (!visit.contains(endWord)) {
      return 0;
    }

    Deque<String> queue = new LinkedList<>();
    Map<String, Integer> dist = new HashMap<>();

    visit.remove(beginWord);
    queue.add(beginWord);
    dist.put(beginWord, 1);

    // Run BFS search
    while (!queue.isEmpty()) {
      String cur = queue.remove();
      if (cur.equals(endWord)) {
        return dist.get(cur);
      }

      // Calculate nextWords in a separated loop because
      // we dont want to remove words from visit set while
      // iterating through the set
      List<String> nextWords = new ArrayList<>();
      for (String word : visit) {
        if (editDistance(cur, word) == 1) {
          nextWords.add(word);
        }
      }

      for (String next : nextWords) {
        visit.remove(next);
        queue.add(next);
        dist.put(next, dist.get(cur) + 1);
      }
    }

    // No transformation path found
    return 0;
  }

  public int editDistance(String s1, String s2) {
    // Assume both strings have same length
    int n = s1.length();
    int diff = 0;
    for (int i = 0; i < n; i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        diff++;
      }
    }
    return diff;
  }
}