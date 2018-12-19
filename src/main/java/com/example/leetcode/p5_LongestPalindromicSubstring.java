package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class p5_LongestPalindromicSubstring {

  public static String longestPalindrome(String s) {
    List<int[]> res = new ArrayList<>();
    // Corner-cases
    if (s == null || s.length() < 2) {
      return s;
    }
    int n = s.length();
    boolean[][] dp = new boolean[n][n];
    String longestPalin = "";

    for (int len = 0; len < n; len++) {
      for (int i = 0; i < n - len; i++) {
        int j = i + len;
        if (s.charAt(i) == s.charAt(j)) {
          if (len < 2) {
            dp[i][j] = true;
          } else {
            dp[i][j] = dp[i + 1][j - 1];
          }
          if (dp[i][j] && len + 1 > longestPalin.length()) {
            longestPalin = s.substring(i, j + 1);
          }
        }
      }
    }
    return longestPalin;
  }

  /**
   * Most recent submission (2018-12-18)
   */
  public static String longestPalindromeNew(String s) {
    int n = s.length();
    boolean[][] dp = new boolean[n][n];

    int start = 0, end = 0;

    for (int step = 0; step < n; step++) {
      for (int i = 0; i < n - step; i++) {
        int j = i + step;

        boolean palin = s.charAt(i) == s.charAt(j);
        if (j > i + 1) {
          palin = palin && dp[i + 1][j - 1];
        }
        dp[i][j] = palin;

        if (palin) {
          start = i;
          end = j + 1;
        }
      }
    }
    return s.substring(start, end);
  }

  public static void main(String[] args) {
    System.out.println("longestPalindrome('babad') = " + longestPalindromeNew("babad") + " # Expected: bab");
    System.out.println("longestPalindrome('abacdfgdcaba') = " + longestPalindromeNew("abacdfgdcaba") + " # Expected: aba");
    System.out.println("longestPalindrome('bb') = " + longestPalindromeNew("bb") + " # Expected: bb");
  }
}
