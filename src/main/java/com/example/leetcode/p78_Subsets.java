package com.example.leetcode;

import java.util.*;

public class p78_Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<Integer> subset = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    backtrack(nums, 0, subset, res);

    return res;
  }

  void backtrack(int[] nums, int start, List<Integer> subset, List<List<Integer>> res) {
    res.add(new ArrayList<>(subset));

    int n = nums.length;
    for (int i = start; i < n; i++) {
      subset.add(nums[i]);
      backtrack(nums, i + 1, subset, res);
      subset.remove(subset.size() - 1);
    }
  }
}
