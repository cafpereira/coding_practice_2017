package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class p131_PalindromePartitioning {

  public List<List<String>> partition(String s) {
    List<String> cur = new ArrayList<>();
    List<List<String>> res = new ArrayList<>();

    backtrack(s, cur, 0, res);

    return res;
  }

  void backtrack(String s, List<String> cur, int start, List<List<String>> res) {
    int n = s.length();
    // Base case:
    // if start pointer reaches end of the string we have
    // found all palindrome partitions of s.
    if (start == n) {
      res.add(new ArrayList<>(cur));
    }

    for (int p = start; p < n; p++) {
      if (palindrome(s, start, p)) {
        String palin = s.substring(start, p + 1);
        // Add palindrome to cur and recurse and find next partition
        cur.add(palin);
        backtrack(s, cur, p + 1, res);
        cur.remove(cur.size() - 1);
      }
    }
  }

  boolean palindrome(String s, int low, int high) {
    while(low < high) {
      if (s.charAt(low++) != s.charAt(high--)) {
        return false;
      }
    }
    return true;
  }
}
