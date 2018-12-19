package com.example.leetcode;

import java.io.*;
import java.util.*;

/**
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
public class p532_findKDiffPairs_V2 {
  public int findPairs(int[] nums, int k) {
    // Corner cases
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // k is the absolute difference of two elements
    // since absolute value is always non-negative: |x − y| ≥ 0
    // therefore k should always be >= 0
    if (k < 0) { return 0; }

    // Build a frequency map of the values in the array
    Map<Integer, Integer> freq = new HashMap<>();
    for (int n : nums) {
      freq.put(n, freq.getOrDefault(n, 0) + 1);
    }

    // Frequency map now has one unique entry per value, this
    // will allow to count duplicate ocurrences as just one
    int count = 0;
    for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
      if (k == 0) {
        // if diff is zero then looks for repeated numbers
        if (entry.getValue() >= 2) {
          count++;
        }
      } else {
        int complement = k + entry.getKey();
        // If complementary sum already been seen, then we found a pair
        if (freq.containsKey(complement)) {
          count++;
        }
      }
    }
    return count;
  }
}
