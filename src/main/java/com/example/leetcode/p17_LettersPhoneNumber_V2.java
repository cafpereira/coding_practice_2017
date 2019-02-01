package com.example.leetcode;

import java.util.*;

/**
 * This implementation uses a BFS instead of DFS to build up the letter combinations
 */
public class p17_LettersPhoneNumber_V2 {
  public final List<String> keys = Arrays.asList("0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");

  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits == null || digits.isEmpty()) {
      return res;
    }

    LinkedList<String> queue = new LinkedList<>();
    queue.add("");

    while (queue.peek().length() < digits.length()) {
      String next = queue.remove();
      int i = next.length();
      String letters = keys.get(digits.charAt(i) - '0');
      for (char c : letters.toCharArray()) {
        queue.add(next + c);
      }
    }
    return queue;
  }
}
