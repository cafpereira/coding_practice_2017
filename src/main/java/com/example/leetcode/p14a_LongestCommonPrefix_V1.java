package com.example.leetcode;

public class p14a_LongestCommonPrefix_V1 {
  public String longestCommonPrefix(String[] strs) {
    if (strs.length < 1) {
      return "";
    }
    for (int prefix = 0; prefix < strs[0].length(); prefix++) {
      char c = strs[0].charAt(prefix);
      for (int s = 1; s < strs.length; s++) {
        if (prefix == strs[s].length() || c != strs[s].charAt(prefix)) {
          return strs[0].substring(0, prefix);
        }
      }
    }
    // Edge case: first string matches completely all the others
    return strs[0];
  }
}
