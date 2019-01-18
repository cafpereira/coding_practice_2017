package com.example.leetcode;

import java.util.*;

public class p90_SubsetsII {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    List<Integer> subset = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    Arrays.sort(nums);
    backtrack(nums, 0, subset, res);

    return res;
  }

  void backtrack(int[] nums, int start, List<Integer> subset, List<List<Integer>> res) {
    res.add(new ArrayList<>(subset));

    int n = nums.length;
    for (int i = start; i < n; i++) {

      // Why this works?
      // Since the array is sorted and its being scanned left to right.
      // if the previous element is equal this means there MUST have been
      // another recursion branch in the past that started with num[i-1],
      // already created subsets with the same value.
      // No need to start a new recursion branch that will create a duplicate
      // subset.
      if (i > start && nums[i] == nums[i - 1]) {
        continue;
      }
      subset.add(nums[i]);
      backtrack(nums, i + 1, subset, res);
      subset.remove(subset.size() - 1);
    }
  }
}
