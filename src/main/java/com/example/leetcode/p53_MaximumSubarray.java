package com.example.leetcode;

public class p53_MaximumSubarray {
  public int maxSubArray(int[] nums) {
    int maxSum = nums[0];
    int curSum = 0;

    for (int v : nums) {
      curSum += v;
      if (curSum > maxSum) {
        maxSum = curSum;
      }
      if (curSum < 0) {
        curSum = 0;
      }
    }
    return maxSum;
  }

  public static void main(String[] args) {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    p53_MaximumSubarray s = new p53_MaximumSubarray();
    System.out.println("Max sum: " + s.maxSubArray(nums));
  }
}
