package com.example.leetcode;

import java.util.*;

public class p5a_LongestCommonSubstring {
  public static String LCS(String A, String B) {
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
        }
        else {
          dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
        }
      }
    }
    List<Character> match = buildMatch(dp, s1, s2);
    StringBuilder builder = new StringBuilder();
    for (Character c : match) {
      builder.append(c);
    }
    return builder.toString();
  }

  public static List<Character> buildMatch(int[][] dp, String s1, String s2) {
    List<Character> match = new ArrayList<>();
    int i = s1.length() - 1;
    int j = s2.length() - 1;

    while (i > 0 && j > 0) {
      int cur = dp[i][j];
      int up = dp[i-1][j];
      int left = dp[i][j - 1];
      if (cur > Math.max(left, up)) {
        // s1[i] == s2[j], add to match list
        match.add(s1.charAt(i)); // or s2.charAt(j)
        // Move to left-upper diagonal
        i--; j--;
      } else if (cur == left) {
        // go the the left cell
        j--;
      } else { // cur == up
        i--;
      }
    }
    Collections.reverse(match);
    return match;
  }

  public static void main(String[] args) {
    System.out.println("LCS('abab','baba') = " + LCS("abab", "baba"));
    System.out.println("LCS('abcd','cbce') = " + LCS("abcd", "cbce"));
  }
}
