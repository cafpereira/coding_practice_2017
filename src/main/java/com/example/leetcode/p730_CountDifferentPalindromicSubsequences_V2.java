package com.example.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class p730_CountDifferentPalindromicSubsequences_V2 {

  int MOD = 1_000_000_007;
  Map<Character, TreeSet<Integer>> occur = new HashMap<>();
  int[][] memo;

  public int countPalindromicSubsequences(String S) {
    int n = S.length();
    memo = new int[n][n];

    // Initialize occurrences map
    for (char c : new char[]{'a', 'b', 'c', 'd'}) {
      occur.put(c, new TreeSet<>());
    }
    for (int i = 0; i < n; i++) {
      occur.get(S.charAt(i)).add(i);
    }

    return dp(0, n - 1) - 1; // -1 to remove blank palindrome from final count
  }

  public int dp(int i, int j) {
    if (memo[i][j] > 0) {
      // return memoized solution
      return memo[i][j];
    }

    int ans = 1; // Count blank as valid palindrome
    if (i <= j) {
      // Search for palindromes between S[i:j] in the form:
      // a...a, b...b, etc
      for (char c : new char[]{'a', 'b', 'c', 'd'}) {

        // Get the first and last ocurrences of c in S[i:j]
        Integer start = occur.get(c).ceiling(i);
        Integer end = occur.get(c).floor(j);

        // If c is betwen (i,j) then count this char as
        // as single letter palindrome
        if (start != null && i <= start && start <= j) {
          ans++;
        }

        // Count palindromes in S[start+1:end-1] and given the way
        // we build the search of start and end (first and last
        // occurrences of c) we know the resulting count will be
        // from a disjoint set, so there will be no duplicates.
        if (start != null && end != null && start < end) {
          ans += dp(start + 1, end - 1);
        }
        // Prevent integer overflow
        ans %= MOD;
      }
    }
    memo[i][j] = ans;
    return ans;
  }

  public static void main(String[] args) {
    p730_CountDifferentPalindromicSubsequences_V2 solution = new p730_CountDifferentPalindromicSubsequences_V2();
//    System.out.println("countPalinSeq: " + solution.countPalindromicSubsequences("bccb"));
    System.out.println("countPalinSeq: " + solution.countPalindromicSubsequences("aaab"));
  }
}
