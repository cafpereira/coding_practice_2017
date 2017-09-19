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
public class Pg38_TargetSum_TopDown {
    // Top down dynamic programming solution. Like
    // 0-1 Knapsack, we use a HashMap to save space
    public int targetSum(int[] nums, int T) {
        // Map: i -> sum -> value
        Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
        return targetSum(nums, T, 0, 0, cache);
    }

    // Overloaded recursive function
    private int targetSum(int[] nums, int T, int i, int sum,
            Map<Integer, Map<Integer, Integer>> cache) {
        if (i == nums.length) {
            return sum == T ? 1 : 0;
        }
        // Check the cache and return if we get a hit
        if (!cache.containsKey(i)) cache.put(i, new HashMap<>());
        Integer cached = cache.get(i).get(sum);
        if (cached != null) return cached;
            // If we didn't hit in the cache, compute
            // the value and store to cache
        int toReturn = targetSum(nums, T, i + 1, sum + nums[i], cache) +
                        targetSum(nums, T, i + 1, sum - nums[i], cache);
        cache.get(i).put(sum, toReturn);
        return toReturn;
    }
}
