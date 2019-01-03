package com.example.leetcode;

import java.util.HashMap;
import java.util.Map;

public class p299_BullsAndCows {
  public String getHint(String secret, String guess) {
    int n = secret.length();
    char[] s = secret.toCharArray();
    char[] g = guess.toCharArray();

    Map<Character, Integer> secretCount = new HashMap<>();
    Map<Character, Integer> guessCount = new HashMap<>();

    int hits = 0;
    for (int i = 0; i < n; i++) {
      if (g[i] == s[i]) {
        hits++;
      } else {
        secretCount.put(s[i], secretCount.getOrDefault(s[i], 0) + 1);
        guessCount.put(g[i], guessCount.getOrDefault(g[i], 0) + 1);
      }
    }

    int near = 0;
    for (Character color : guessCount.keySet()) {
      int c1 = guessCount.get(color);
      int c2 = secretCount.getOrDefault(color, 0);
      near += Math.min(c1, c2);
    }

    return hits + "A" + near + "B";
  }
}
