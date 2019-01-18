package com.example.leetcode;

import java.util.*;

/**
 * This implementation sorts the arrays and skip numbers that are larger than target.
 * It uses for-loop that simulate a DFS search on the possible combinations.
 */
public class p39_CombinationSum_V2 {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(candidates);
    backtrack(result, new ArrayList<>(), candidates, target, 0);
    return result;
  }

  /**
   * Backtracking approach that generate all subsets, but now we also track the sum of the elements
   * each time we pick a new number for the current subset, then we compare if the current sum is
   * bellow, equal or higher than our target number.
   */
  private void backtrack(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target, int start) {
    if (target > 0) {
      for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
        cur.add(candidates[i]);
        backtrack(result, cur, candidates, target - candidates[i], i); // backtrack again to the ith position
                                                                              // b/c we can select the same value
                                                                              // multiple times
        cur.remove(cur.size() - 1);
      }
    } else if (target == 0) {
      result.add(new ArrayList<>(cur));
    }
  }

  public static void main(String[] args) {
    p39_CombinationSum_V2 s = new p39_CombinationSum_V2();
    System.out.println("combinationSum: " + s.combinationSum(new int[]{2, 3, 6, 7}, 7));
  }
}