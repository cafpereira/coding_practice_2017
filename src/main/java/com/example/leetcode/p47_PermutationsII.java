package com.example.leetcode;

import java.util.*;

public class p47_PermutationsII {
  public List<List<Integer>> permuteUnique(int[] nums) {
    int n = nums.length;
    boolean[] used = new boolean[n];
    List<Integer> perm = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    Arrays.sort(nums);

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
      // used[i] is trivial, no need to explain.
      if (used[i] ||
          // Why this works?
          // Since the array is sorted and its being scanned left to right.
          // if the previous element is equal AND not flagged as used in this
          // recursion branch, this means there MUST have been another previous
          // recursion branch (left->right scan) that started with num[i-1], and
          // generated a list of permutations for that letter.
          // Therefore, we dont need to start a 2nd recursion branch that will
          // end with the same permutation.
          (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false)) {
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
