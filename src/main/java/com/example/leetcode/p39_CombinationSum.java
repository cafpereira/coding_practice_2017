package com.example.leetcode;

import java.util.*;

public class p39_CombinationSum {
  List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    combinationSumRec(candidates, target, 0, new LinkedList<>());
    return res;
  }

  private void combinationSumRec(int[] candidates, int target, int i, Deque<Integer> items) {
    int n = candidates.length;
    if (target == 0) {
      res.add(new ArrayList<>(items));
      return;
    }
    if (i == n) {
      return;
    }
    if (candidates[i] <= target) {
      items.add(candidates[i]);
      combinationSumRec(candidates, target - candidates[i], i, items);
      items.removeLast();
    }
    combinationSumRec(candidates, target, i + 1, items);
  }

  public static void main(String[] args) {
    p39_CombinationSum s = new p39_CombinationSum();
    System.out.println("combinationSum: " + s.combinationSum(new int[]{2,3,6,7}, 7));
  }
}