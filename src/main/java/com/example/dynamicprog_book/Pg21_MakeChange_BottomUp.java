package com.example.dynamicprog_book;

/**
 * Given an integer representing a given amount of change, write a function to compute the total number of coins required to make that amount of
 * change.
 *
 * makeChange(1) = 1 (1) makeChange(6) = 2 (5 + 1) makeChange(49) = 7 (25 + 10 + 10 + 1 + 1 + 1 + 1)
 */
public class Pg21_MakeChange_BottomUp {
  // Bottom up dynamic programming solution.
  // Iteratively compute number of coins for
  // larger and larger amounts of change
  private static int[] coins = new int[]{10, 6, 1};

  public static void main(String[] args) {
    System.out.println("makeChange(12) = " + makeChange(12));
  }

  public static int makeChange(int c) {
    int[] cache = new int[c + 1];
    for (int i = 1; i <= c; i++) {
      int minCoins = Integer.MAX_VALUE;
      // Try removing each coin from the total
      // and see which requires the fewest
      // extra coins
      for (int coin : coins) {
        if (i - coin >= 0) {
          int currCoins = cache[i - coin] + 1;
          if (currCoins < minCoins) {
            minCoins = currCoins;
          }
        }
      }
      cache[i] = minCoins;
    }
    return cache[c];
  }
}
