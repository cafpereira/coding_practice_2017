package com.example.dynamicprog_book;

/**
 * Given an integer representing a given amount of change, write a function to compute the total number of coins required to make that amount of
 * change.
 *
 * makeChange(1) = 1 (1) makeChange(6) = 2 (5 + 1) makeChange(49) = 7 (25 + 10 + 10 + 1 + 1 + 1 + 1)
 */
public class Pg17_MakeChange_BruteForce {
  // Brute force solution. Go through every
  // combination of coins that sum up to c to
  // find the minimum number
  private static int[] coins = new int[]{10, 6, 1};

  public static void main(String[] args) {
    System.out.println("makeChange(12) = " + makeChange(12));
  }

  public static int makeChange(int c) {
    if (c == 0) {
      return 0;
    }
    int minCoins = Integer.MAX_VALUE;
    // Try removing each coin from the total and
    // see how many more coins are required
    for (int coin : coins) {
      // Skip a coin if it’s value is greater
      // than the amount remaining
      if (c - coin >= 0) {
        int currMinCoins = makeChange(c - coin);
        if (currMinCoins < minCoins) {
          minCoins = currMinCoins;
        }
      }
    }
    // Add back the coin removed recursively
    return minCoins + 1;
  }
}
