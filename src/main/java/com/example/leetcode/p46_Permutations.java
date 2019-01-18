package com.example.leetcode;

import java.util.*;

public class p46_Permutations {
  public List<List<Integer>> permute(int[] nums) {
    int n = nums.length;
    boolean[] used = new boolean[n];
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> perm = new ArrayList<>();

    backtrack(nums, used, perm, res);
    return res;
  }

  void backtrack(int[] nums, boolean[] used, List<Integer> perm, List<List<Integer>> res) {
    int n = nums.length;

    // Base case
    if (perm.size() == n) {
      res.add(new ArrayList<>(perm));
      return;
    }

    for (int i = 0; i < n; i++) {
      if (used[i]) {
        continue;
      }
      // Add ith element and recurse
      used[i] = true;
      perm.add(nums[i]);

      backtrack(nums, used, perm, res);

      // backtrack
      perm.remove(perm.size() - 1);
      used[i] = false;
    }
  }
}
