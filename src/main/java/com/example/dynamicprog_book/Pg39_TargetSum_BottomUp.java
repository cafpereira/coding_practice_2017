package com.example.dynamicprog_book;

import java.io.*;
import java.util.*;

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
    public int targetSum(int[] nums, int T) {
        int sum = 0;

        // Our cache has to range from -sum(nums) to
        // sum(nums), so we offset everything by sum
        for (int num : nums) sum += num;

        int[][] cache = new int[nums.length + 1][2 * sum + 1];
        if (sum == 0) return 0;

        // Initialize i=0, T=0, remember that array is offset by 'sum'
        // Therefore, cache[0][sum] is actually the starting (0,0) coordinate
        cache[0][sum] = 1;

        // Iterate over previous row and update the current row
        // This means that instead of assinging to current cache[i]
        // the sum and subtraction of num[i-1], we increment any
        // existent value on cache[i+1] with the value of cache[i].
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) { // equivalent of -sum <= j < sum
                int prev = cache[i - 1][j];
                if (prev != 0) {
                    cache[i][j - nums[i - 1]] += prev;
                    cache[i][j + nums[i - 1]] += prev;
                }
            }
        }
        return cache[nums.length][sum + T];
    }
}
