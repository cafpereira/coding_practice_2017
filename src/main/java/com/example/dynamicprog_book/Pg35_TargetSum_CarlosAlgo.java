package com.example.dynamicprog_book;

/**
 * Given an array of integers, nums and a target value T,
 * find the number of ways that you can add and subtract
 * the values in nums to add up to T.
 *
 * nums = {1, 1, 1, 1, 1}
 * T = 3
 * targetSum(nums, T) = 5
 */
public class Pg35_TargetSum_CarlosAlgo {

  public static int[] nums = {1, 1, 1, 1, 1};

  public static int targetSum(int[] nums, int T) {
    return targetSum(nums, T, 0, 0);
  }

  public static int targetSum(int[] nums, int T, int i, int sum) {
    if (i == nums.length) {
      return sum == T ? 1 : 0;
    }
    return targetSum(nums, T, i + 1, sum + nums[i]) + // Add nums[i]
           targetSum(nums, T, i + 1, sum - nums[i]);  // Subtract nums[i]
  }

  public static void main(String[] args) {
    System.out.println("targetSum(nums, 3) = " + targetSum(nums, 3));
  }
}

