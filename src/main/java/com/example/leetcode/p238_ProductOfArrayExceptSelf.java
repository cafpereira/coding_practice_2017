package com.example.leetcode;

public class p238_ProductOfArrayExceptSelf {
  /**
   * Time: O(n)
   * Space: O(n)
   */
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] left = new int[n];
    int[] right = new int[n];

    left[0] = 1;
    for (int i = 1; i < n; i++) {
      left[i] = nums[i - 1] * left[i - 1];
    }

    right[n - 1] = 1;
    for (int i = n - 2; i >= 0; i--) {
      right[i] = nums[i + 1] * right[i + 1];
    }

    for (int i = 0; i < n; i++) {
      left[i] = left[i] * right[i];
    }
    return left;
  }

  /**
   * Time: O(n)
   * Space: O(1)
   */
  public int[] productExceptSelfV2(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];

    result[0] = 1;
    for (int i = 1; i < n; i++) {
      result[i] = nums[i - 1] * result[i - 1];
    }

    int acc = nums[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      result[i] = result[i] * acc;
      acc = acc * nums[i];
    }
    return result;
  }
}
