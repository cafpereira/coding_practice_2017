package com.example.dynamicprog_book;

/**
 * Given an integer representing a given amount of change, write a function to compute the total number of coins required to make that amount of
 * change.
 *
 * makeChange(1) = 1 (1) makeChange(6) = 2 (5 + 1) makeChange(49) = 7 (25 + 10 + 10 + 1 + 1 + 1 + 1)
 */
public class Pg20_MakeChange_TopDown {
  // Top down dynamic solution. Cache the values
  // as we compute them
  private static int[] coins = new int[]{10, 6, 1};

  public static void main(String[] args) {
    System.out.println("makeChange(12) = " + makeChange(12));
  }

  public static int makeChange(int c) {
    // Initialize cache with values as -1
    int[] cache = new int[c + 1];
    for (int i = 1; i < c + 1; i++) {
      cache[i] = -1;
    }
    return makeChange(c, cache);
  }

  // Overloaded recursive function
  private static int makeChange(int c, int[] cache) {
    // Return the value if it’s in the cache
    if (cache[c] >= 0) {
      return cache[c];
    }
    int minCoins = Integer.MAX_VALUE;
    // Find the best coin
    for (int coin : coins) {
      if (c - coin >= 0) {
        int currMinCoins =
            makeChange(c - coin, cache);
        if (currMinCoins < minCoins) {
          minCoins = currMinCoins;
        }
      }
    }
    // Save the value into the cache
    cache[c] = minCoins + 1;
    return cache[c];
  }
}
