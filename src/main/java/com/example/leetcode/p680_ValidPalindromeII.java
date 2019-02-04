package com.example.leetcode;

import java.util.*;

public class p680_ValidPalindromeII {
  public boolean validPalindrome(String s) {
    if (s == null || s.isEmpty()) {
      return false;
    }
    int n = s.length();
    int lo = 0;
    int hi = n - 1;
    while(lo < hi) {
      if (s.charAt(lo) != s.charAt(hi)) {
        return matchPalin(s, lo + 1, hi) || matchPalin(s, lo, hi - 1);
      }
      lo++;
      hi--;
    }
    return true;
  }

  public boolean matchPalin(String s, int lo, int hi) {
    while(lo < hi) {
      if (s.charAt(lo++) != s.charAt(hi--)) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    p680_ValidPalindromeII solution = new p680_ValidPalindromeII();
    System.out.println("validPalindrome: " + solution.validPalindrome("abc"));
  }
}
