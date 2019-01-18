package com.example.leetcode;

import java.util.*;

public class p560_SubarraySumEqualsK {
  public int subarraySum(int[] nums, int k) {
    int n = nums.length;
    int sum = 0;

    Map<Integer, Integer> sumFreq = new HashMap<>();
    sumFreq.put(0, 1);  // Assume the first m elements sum[0:m] == k
                        // in that case we need to return one occurrence
                        // of zero as the complement since sum - k == 0

    int res = 0;
    for (int i = 0; i < n; i++) {
      sum += nums[i];
      int compl = sum - k;
      if (sumFreq.containsKey(compl)) {
        res += sumFreq.get(compl);
      }
      sumFreq.put(sum, sumFreq.getOrDefault(sum, 0) + 1);
    }
    return res;
  }
}
