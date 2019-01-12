package com.example.leetcode;

/**
 * Decode ways DP solution
 */
public class p91_DecodeWays_V2 {
  public int numDecodings(String s) {
    int n = s.length();
    int[] dp = new int[n];

    dp[0] = s.charAt(0) != '0' ? 1 : 0;

    for (int i = 1; i < n; i++) {
      int ways1 = dp[i - 1];
      int ways2 = i - 2 >= 0 ? dp[i - 2] : 1;

      int oneDigit = s.charAt(i) - '0';
      if (oneDigit > 0) {
        dp[i] += ways1;
      }

      int twoDigit = Integer.parseInt(s.substring(i-1, i+1));
      if (twoDigit >= 10 && twoDigit <= 26) {
        dp[i] += ways2;
      }
    }
    return dp[n-1];
  }
}
