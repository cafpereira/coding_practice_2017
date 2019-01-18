package com.example.leetcode;

import java.util.*;

public class p90_CombinationSumII {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<Integer> cur = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    Arrays.sort(candidates);

    backtrack(candidates, cur, 0, target, res);
    return res;
  }

  void backtrack(int[] candidates, List<Integer> cur, int start, int target, List<List<Integer>> res) {
    int n = candidates.length;

    if (target == 0) {
      // A solution found
      res.add(new ArrayList<>(cur));
    } else if (target > 0) {
      for (int i = start; i < n; i++) {

        // Why this works?
        // Since the array is sorted and its being scanned left to right.
        // if the previous element is equal this means there MUST have been
        // another recursion branch in the past that started with candidates[i-1],
        // which already found a possible combination sum.
        // No need to start a new recursion branch that will create the same answer
        if (i > start && candidates[i] == candidates[i - 1]) {
          continue;
        }
        cur.add(candidates[i]);
        backtrack(candidates, cur, i + 1, target - candidates[i], res);
        cur.remove(cur.size() - 1);
      }
    }
  }
}
