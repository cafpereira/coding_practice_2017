package com.example.codersnacks;

import java.util.Arrays;

class PotsOfGold {

  public static boolean gameWinnerRec(int[] coins) {
    return bestScoreRec(coins, 0, coins.length - 1) > 0;
  }

  // Version #1 - Recursive (Exponential)
  public static int bestScoreRec(int[] coins, int start, int end) {
    if (start == end) {
      return coins[start];
    }
    // Subtract the opponent optimal score from  gold we are taking
    int firstScore = coins[start] - bestScoreRec(coins, start + 1, end);
    int secondScore = coins[end] - bestScoreRec(coins, start, end - 1);

    return Math.max(firstScore, secondScore);
  }

  // Version #2 - Recursive (Memoized)
  public static boolean gameWinnerMemo(int[] coins) {
    int n = coins.length;
    return bestScoreMemo(coins, 0, n - 1, new int[n][n]) > 0;
  }

  public static int bestScoreMemo(int[] coins, int start, int end, int[][] memo) {
    if (start == end) {
      return coins[start];
    }

    int firstScore = coins[start] - bestScoreMemo(coins, start + 1, end, memo);
    int secondScore = coins[end] - bestScoreMemo(coins, start, end - 1, memo);

    memo[start][end] = Math.max(firstScore, secondScore);
    return memo[start][end];
  }

  // Version #3 - Dynamic Programming
  public static boolean gameWinnerDP(int[] coins) {
    int n = coins.length;
    int[][] table = new int[n][n];

    for (int len = 0; len < n; len++) {
      for (int i = 0; i < n - len; i++) {
        int j = i + len;
        if (i == j) {
          table[i][j] = coins[i];
        } else {
          int first = coins[i] - table[i + 1][j];
          int second = coins[j] - table[i][j - 1];
          table[i][j] = Math.max(first, second);
        }
      }
    }
    return table[0][n - 1] > 0;
  }



  public static void main(String[] args) {
    expect(new int[]{10, 10, 100, 60}, true);
    expect(new int[]{20, 90, 90, 10}, true);
    expect(new int[]{10, 100, 10}, false);
  }

  public static void expect(int[] coins, boolean expected){
    System.out.println("gameWinnerDP("+ Arrays.toString(coins)+ ") = " + gameWinnerDP(coins) + " # Expected: " + expected);
  }
}
