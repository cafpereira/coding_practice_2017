package com.example.leetcode;

import java.util.*;

public class p5a_LongestCommonSubstring_V2 {
  public static String LCS(String A, String B) {
    String lcs = "";

    // Corner-cases
    if (A == null || A.isEmpty() || B == null || B.isEmpty()) {
      return lcs;
    }

    int n = A.length();
    int m = B.length();
    int[][] dp = new int[n+1][m+1];

    // Extra padding to easily match rows i and col j with string chars i and j
    String s1 = "#" + A;
    String s2 = "#" + B;

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s1.charAt(i) == s2.charAt(j)) {
          dp[i][j] = dp[i-1][j-1] + 1;
          if (dp[i][j] > lcs.length()) {
            int start = i - dp[i][j];
            lcs = A.substring(start, i);
          }
        }
      }
    }
    return lcs;
  }


  public static void main(String[] args) {
    System.out.println("LCS('abab','baba') = " + LCS("abab", "baba"));
    System.out.println("LCS('abcd','cbce') = " + LCS("abcd", "cbce"));
  }
}
