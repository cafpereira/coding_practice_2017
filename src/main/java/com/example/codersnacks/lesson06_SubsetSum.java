package com.example.codersnacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubsetSumDP {

  public static boolean subsetSumDP(List<Integer> numbers, int target) {
    int n = numbers.size();
    boolean[][] table = new boolean[n][target + 1];

    // Set TRUE on the two possible columns of the first number 'N1'
    // [0][0] = you can always make zero by not selecting any number
    // [0][N1] = only if N1 is less or equal target.
    table[0][0] = true;
    if (numbers.get(0) <= target) {
      table[0][numbers.get(0)] = true;
    }

    // Now we iterate on the first row, but we look ahead the next
    // number (e.g. N2). We need to stop iterating on the row before
    // the last one, i.e, i == n - 2
    for (int i = 0; i < n - 1; i++) {
      int next = numbers.get(i + 1);
      for (int j = 0; j <= target; j++) {
        if (table[i][j]) {
          // Set TRUE on the collum below
          table[i+1][j] = true;
          // Also TRUE on the column [j + next],
          // only if its within target range
          if ((j + next) <= target) {
            table[i+1][j + next] = true;
          }
        }
      }
    }
    return table[n - 1][target];
  }
}

class SubsetSumMemo {

  public static boolean subsetSumRecur(List<Integer> numbers, int target) {
    return subsetSumRecur(numbers, target, 0, new HashMap<>());
  }

  public static boolean subsetSumRecur(List<Integer> numbers, int target, int i, Map<String, Boolean> cache) {
    String key = "i:" + i + ",target:" + target;

    if (cache.containsKey(key)) {
      return cache.get(key);
    }
    if (target == 0) {
      return true;
    }
    if (i == numbers.size()) {
      return false;
    }

    int num = numbers.get(i);
    boolean res = subsetSumRecur(numbers, target - num, i + 1, cache) ||
        subsetSumRecur(numbers, target, i + 1, cache);
    cache.put(key, res);

    return res;
  }
}

class SubsetSumSolution {

  public static void main(String[] args) {
    expect(new Integer[]{19, 3, 7, 10, 11}, 18, true);
    expect(new Integer[]{16, 8, 10, 4, 24}, 18, true);
    expect(new Integer[]{16, 8, 10, 4, 24}, 19, false);
//    expect(new Integer[]{17, 4, 19, -6, 1}, -2, true);
    expect(new Integer[]{1, 1, 1, 1, 9}, 9, true);
    expect(new Integer[]{19}, 19, true);
  }

  public static void expect(Integer[] input, Integer target, boolean expected) {
    List<Integer> numbers = new ArrayList<>();
    numbers.addAll(Arrays.asList(input));
    System.out.println("subsetSumDPV1("+numbers+", "+target+") = " + SubsetSumDP.subsetSumDP(numbers, target) + " # Expected: "+ expected);
  }
}