package com.example.leetcode;

import java.io.*;
import java.util.*;

/**
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
public class p532_findKDiffPairs {
  public int findPairs(int[] nums, int k) {
    Arrays.sort(nums);
    Set<Integer> set = new HashSet<>();
    Set<Integer> from = new HashSet<>();
    int n = nums.length;
    for (int i = 0; i < n; i++) {
      // if complement found
      if (set.contains(nums[i])) {
        from.add(nums[i] - k);
        set.remove(nums[i]);
      }
      int sum = nums[i] + k;
      set.add(sum);
    }
    return from.size();
  }
}
