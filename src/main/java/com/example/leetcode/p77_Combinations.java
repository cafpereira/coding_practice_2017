package com.example.leetcode;

import java.util.*;

public class p77_Combinations {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    if (n < 1 || k < 1) {
      return res;
    }
    List<Integer> comb = new LinkedList<>();
    backtrack(n, 1, k, comb, res);
    return res;
  }

  void backtrack(int n, int start, int k, List<Integer> comb, List<List<Integer>> res) {
    // Base case
    if (comb.size() == k) {
      res.add(new ArrayList<>(comb));
      return;
    }
    for (int i = start; i <= n; i++) {
      comb.add(i);
      backtrack(n, i + 1, k, comb, res);
      comb.remove(comb.size() - 1);
    }
  }
}
