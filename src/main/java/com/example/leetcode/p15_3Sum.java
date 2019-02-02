package com.example.leetcode;

import java.util.*;

public class p15_3Sum {
  public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    List<List<Integer>> res = new ArrayList<>();
    if (n < 3) {
      return res;
    }
    Arrays.sort(nums);
    int target = 0;

    for (int i = 0; i < n - 2; i++) {
      if (i > 0 && nums[i - 1] == nums[i]) {
        // Skip over duplicates
        continue;
      }
      int j = i + 1;
      int k = n - 1;
      int compl = target - nums[i];
      while (j < k) {
        int sum = nums[j] + nums[k];
        if (sum  == compl) {
          res.add(Arrays.asList(nums[i], nums[j], nums[k]));
          // Skip over duplicates
          while(j < k && nums[j] == nums[j + 1]) j++;
          while(j < k && nums[k - 1] == nums[k]) k--;
          // Increase hi and lo pointers to next values
          // Q1) Why another increase after we just moved both pointers above?
          // A: Notice the pointer will pass all the duplicated ocurrences of j and k,
          // but they will still be pointing at positions that have the same initial
          // values used to calculate the intermediary sum value.

          // Q2) Why both pointers at same time?
          // Given that we just found a sum S' that is equal the complement C, we know
          // any other sum S'' that is also equal to C MUST have another pair of values
          // for j and k, otherwise we would end with a number that has the same sum for
          // two different operands, which is impossible.
          j++; k--;
        } else if (sum < compl) {
          // No need to skip duplicates since elements j (or k) are not part of the result
          j++;
        } else { // sum > compl
          k--;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    p15_3Sum solution = new p15_3Sum();
    System.out.println("threeSum: " + solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
  }
}
