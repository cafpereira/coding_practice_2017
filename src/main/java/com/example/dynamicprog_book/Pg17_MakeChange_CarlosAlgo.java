package com.example.dynamicprog_book;

/**
 * Given an integer representing a given amount of change,
 * write a function to compute the total number of coins
 * required to make that amount of change.
 *
 * makeChange(1) = 1 (1)
 * makeChange(6) = 2 (5 + 1)
 * makeChange(49) = 7 (25 + 10 + 10 + 1 + 1 + 1 + 1)
 */
public class Pg17_MakeChange_CarlosAlgo {
  public static int[] coins = {10,6,1};

  public static void main(String[] args) {
    System.out.println("makeChange(12) = " + makeChange(12));
  }

  public static int makeChange(int cents) {
    return makeChange(cents, 0);
  }

  public static int makeChange(int cents, int i) {
    if (cents < 0 || i >= coins.length) {
      return 0;
    }
    int withCoin = makeChange(cents - coins[i], i) + 1;
    int withoutCoin = makeChange(cents, i + 1) + 1;
    return Math.min(withCoin, withoutCoin);
  }
}
