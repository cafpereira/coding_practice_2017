package com.example.leetcode;

import java.util.*;

public class p16_3SumClosest {
  public int threeSumClosest(int[] nums, int target) {
    int n = nums.length;
    if (n < 3) {
      return 0;
    }
    Arrays.sort(nums);
    int minDist = Integer.MAX_VALUE;
    int closest = 0;

    for (int i = 0; i < n - 2; i++) {
      int j = i + 1;
      int k = n - 1;

      while (j < k) {
        int sum = nums[i] + nums[j] + nums[k];
        if (Math.abs(target - sum) < minDist) {
          minDist = Math.abs(target - sum);
          closest = sum;
        }
        if (sum  == target) {
          // Exact sum found, will not find better solution
          // return the closest sum
          return closest;
        } else if (sum < target) {
          j++;
        } else { // sum > target
          k--;
        }
      }
    }
    return closest;
  }

  public static void main(String[] args) {
    p16_3SumClosest solution = new p16_3SumClosest();
    System.out.println("threeSumClosest: " + solution.threeSumClosest(new int[]{0, 2, 1, -3}, 1));
  }
}
