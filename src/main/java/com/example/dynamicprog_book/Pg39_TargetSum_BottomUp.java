package com.example.dynamicprog_book;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, nums and a target value T,
 * find the number of ways that you can add and subtract
 * the values in nums to add up to T.
 *
 * nums = {1, 1, 1, 1, 1}
 * T = 3
 * targetSum(nums, T) = 5
 */
public class Pg39_TargetSum_BottomUp {
    // Top down dynamic programming solution. Like
    // 0-1 Knapsack, we use a HashMap to save space
    public int targetSum(int[] nums, int T) {
        // Map: i -> sum -> value
        int RANGE = 99;
        if (T < RANGE || T > RANGE) {
            return 0;
        }
        int[][] cache = new int[nums.length][2 * RANGE];
        for (int i = 0; i < nums.length; i++) {
            for (int run = -RANGE; run <= RANGE; run++) {
                if (i == 0) {
                    if (nums[i] == run) {
                        cache[i][run] = 1;
                    }
                } else {
                    int add = cache[i - 1][run - nums[i]];
                    int sub = cache[i - 1][run + nums[i]];
                    cache[i][run] = add + sub;
                }
            }
        }
        return cache[nums.length][T];
    }
}
