package com.example.leetcode;

import java.util.*;

/**
 * Find out the longest length of subarrays with at most 2 different type of fruits
 *
 * Solution: use a sliding widows and keep track of counts for each fruit.
 * If window holds 3 types, slide start and remove fruits from count until
 * there only 2 types again in the window.
 */
public class p904_FruitIntoBaskets {

  public int totalFruit(int[] tree) {
    if (tree == null) {
      return 0;
    }

    int start = 0, maxLen = 0;
    Map<Integer, Integer> freq = new HashMap<>();

    for (int i = 0; i < tree.length; i++) {
      freq.put(tree[i], freq.getOrDefault(tree[i], 0) + 1);

      while (freq.size() > 2) {
        int t = tree[start];
        freq.put(t, freq.get(t) - 1);
        if (freq.get(t) == 0) {
          freq.remove(t);
        }
        start++;
      }
      int len = i - start + 1;
      maxLen = Math.max(maxLen, len);
    }
    return maxLen;
  }

  public static void main(String[] args) {
    expect(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}, 5);
  }

  private static void expect(int[] input, int expected) {
    p904_FruitIntoBaskets solution = new p904_FruitIntoBaskets();
    System.out.println("totalFruit(" + Arrays.toString(input) + ") = " + solution.totalFruit(input) + " Expected: " + expected);
  }
}
