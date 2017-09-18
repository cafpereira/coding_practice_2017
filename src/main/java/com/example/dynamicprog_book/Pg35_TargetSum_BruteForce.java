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
public class Pg35_TargetSum_BruteForce {

  // Naive brute force solution. Find every // combo
  public int targetSum(int[] nums, int T) {
    return targetSum(nums, T, 0, 0);
  }

  // Overloaded recursive function
  private int targetSum(int[] nums, int T, int i, int sum) {
    // When we've gone through every item, see // if we've reached our target sum
    if (i == nums.length) {
      return sum == T ? 1 : 0;
    }
    // Combine the possibilites by adding and
    // subtracting the current value
    return targetSum(nums, T, i + 1, sum + nums[i])
        + targetSum(nums, T, i + 1, sum - nums[i]);
  }
}

