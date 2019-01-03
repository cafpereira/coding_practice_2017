package com.example.leetcode;

import java.util.*;

public class p575_DistributeCandies {
  public int distributeCandies(int[] candies) {
    if (candies == null || candies.length < 1) {
      return 0;
    }

    Set<Integer> unique = new HashSet<>();
    for (int c : candies) {
      unique.add(c);
    }

    int half = candies.length / 2;

    // Max candy types equals the size of unique set, however,
    // sister cannot have more than half since candies must be
    // distributed equally.
    return Math.min(half, unique.size());
  }
}
