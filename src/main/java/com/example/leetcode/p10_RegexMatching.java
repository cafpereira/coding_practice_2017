package com.example.leetcode;

public class p10_RegexMatching {
  public boolean isMatch(String s, String p) {
    if (s.isEmpty() && p.isEmpty()) {
      // Entire expression matched with success
      return true;
    } else if (p.isEmpty()) {
      // Found partial match, return false
      return false;
    }

    if (p.length() > 1 && p.charAt(1) == '*') {
      // Pattern is STAR
      // Try to match 1 to N occurrences of pattern char 'c' (or any char if its dot)
      int i = 0;
      char c = p.charAt(0);
      while (i < s.length() && (c == '.' || s.charAt(i) == c)) {
        if (isMatch(s.substring(i + 1), p.substring(2))) {
          return true;
        }
        i++;
      }
      // Match zero occurrences
      return isMatch(s, p.substring(2));
    } else if (p.charAt(0) == '.') {
      // Pattern is DOT (without star)
      return !s.isEmpty() && isMatch(s.substring(1), p.substring(1));
    } else {
      // Pattern is [a-z]
      return !s.isEmpty() && s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p.substring(1));
    }
  }

  /**
   * Leetcode editorial solution (non-DP)
   */
  class Solution {
    public boolean isMatch(String text, String pattern) {
      if (pattern.isEmpty()) return text.isEmpty();
      boolean first_match = (!text.isEmpty() &&
          (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

      if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
        return (isMatch(text, pattern.substring(2)) ||
            (first_match && isMatch(text.substring(1), pattern)));
      } else {
        return first_match && isMatch(text.substring(1), pattern.substring(1));
      }
    }
  }

  public static void main(String[] args) {
    p10_RegexMatching solution = new p10_RegexMatching();
    System.out.println("isMatch: " + solution.isMatch("mississippi", "mis*is*p*."));
    System.out.println("isMatch: " + solution.isMatch("aaa", "ab*a"));
    System.out.println("isMatch: " + solution.isMatch("a", "ab*"));
  }
}
