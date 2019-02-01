package com.example.leetcode;

import java.util.*;

/**
 * WARNING: This is not an AC solution (no memory optimization, will quick overflow)
 * But it can be used to demo how to easily produce and store all the palindromic subsequences.
 */
public class p730_CountDifferentPalindromicSubsequences {
  public int countPalindromicSubsequences(String S) {
    int n = S.length();
    Set<String>[][] dp = new Set[n][n];

    for (int step = 0; step < n; step++) {
      for (int i = 0; i < n - step; i++) {
        int j = i + step;
        if (i == j) {
          dp[i][j] = new HashSet<>();
          dp[i][j].add(S.substring(i, j + 1));
          continue;
        }
        Set<String> palins = new HashSet<>();
        palins.addAll(dp[i][j - 1]);
        palins.addAll(dp[i + 1][j]);

        if (S.charAt(i) == S.charAt(j)) {
          palins.add(S.charAt(i) + "" +  S.charAt(j));
          if (dp[i + 1][j - 1] != null) {
            for (String p : dp[i + 1][j - 1]) {
              palins.add(S.charAt(i) + p + S.charAt(j));
            }
          }
        }
        dp[i][j] = palins;
      }
    }
    return dp[0][n -1].size();
  }

  public static void main(String[] args) {
    p730_CountDifferentPalindromicSubsequences solution = new p730_CountDifferentPalindromicSubsequences();
    System.out.println("countPalinSeq: " + solution.countPalindromicSubsequences("bccb")); // expected: ['b', 'c', 'bb', 'cc', 'bcb', 'bccb']
  }
}
