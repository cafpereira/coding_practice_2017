package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class p312_BurstBalloons {
  public static int maxBurst_dp(int[] balloons) {
    int n = balloons.length;
    int[] values = new int[n + 2];
    values[0] = 1;
    values[n + 1] = 1;
    for (int i = 0; i < n; i++) {
      values[i+1] = balloons[i];
    }
    int[][] dp = new int[n + 2][n + 1];
    for (int len = 0; len < n;  len++) {
      for (int start = 1; start < n - len + 1; start++) {
        int end = start + len;
        for (int i = start; i <= end; i++) {
          int coins = dp[start][i - 1] + values[start - 1] * values[i] * values[end + 1] + dp[i + 1][end];
          dp[start][end] = Math.max(dp[start][end], coins);
        }
      }
    }
    return dp[1][n];
  }

  public static void main(String[] args) {
    System.out.println("maxBurst(balloons) = " + maxBurst_dp(new int[]{3,1,5,8}) + " # Expected: 167");
  }
}
