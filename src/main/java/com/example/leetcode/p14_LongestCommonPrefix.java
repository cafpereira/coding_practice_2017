package com.example.leetcode;

public class p14_LongestCommonPrefix {
  public String longestCommonPrefix(String[] strs) {
    if (strs.length < 1) {
      return "";
    }
    int p = 0;
    boolean done = false;
    while (!done) {
      Character cur = null;
      for (String str : strs) {
        if (p >= str.length()) {
          done = true;
          break;
        }
        if (cur == null) {
          cur = str.charAt(p);
        } else {
          if (cur != str.charAt(p)) {
            done = true;
            break;
          }
        }
      }
      if (!done) {
        p++;
      }
    }
    return strs[0].substring(0, p);
  }
}
