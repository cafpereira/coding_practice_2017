package com.example.leetcode;

/**
 * Placeholder for Expand Around Center
 */
public class p5_LongestPalindromicSubstring_V2 {
  public String longestPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }

    int n = s.length();
    int start = 0, end = 0;

    for (int i = 0; i < n; i++) {
      int len1 = expandFromCenter(s, i, i);

      // No need to check if i+1 is out of bound because
      // expandFromCenter(..) will not access the invalid
      // index and will return zero.
      int len2 = expandFromCenter(s, i, i + 1);

      int len = Math.max(len1, len2);

      // (end - start) is less than real palindrome length,
      // but this will only replace start with another
      // start of the same length
      if (len > end - start) {

        // Note: For odd numbers, e.g: 5, the result of
        // len / 2 (int division) is equivalent to:
        // (len - 1) divided by 2.

        // The start bellow works for both odd and even strings.
        // For odd strings, i is the exact center of the string,
        // if we subtract half of the even size (len-1), it will
        // go to the start of the palindrome

        // For even strings, i is the last element of the first
        // half of size len/2, so if we subtract half of the odd
        // size (len-1), this is will be the start.
        start = i - (len - 1) / 2;

        // Same idea applies to the end position, for odd and
        // even strings, len/2 increment will go to the last
        // index of the palindrome.
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  public int expandFromCenter(String s, int c1, int c2) {
    int n = s.length();
    while (c1 >= 0 && c2 < n && s.charAt(c1) == s.charAt(c2)) {
      c1--;
      c2++;
    }

    // c1 and c2 will be always before/after the true start/end
    // of the palin string. Return how many elements there are
    // in between:
    return c2 - c1 - 1;
  }
}
