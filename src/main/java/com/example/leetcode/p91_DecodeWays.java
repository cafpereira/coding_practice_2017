package com.example.leetcode;

public class p91_DecodeWays {
  public int numDecodings(String s) {
    // Base-case
    if (s.isEmpty()) {
      // Reach end of deconding path
      return 1;
    }
    if (s.charAt(0) == '0') {
      // No decoding code mapped to zero. skip
      return 0;
    }

    // decoded [1-9]
    int numWays = numDecodings(s.substring(1));

    int code = Integer.MAX_VALUE;
    if (s.length() > 1) {
      code = Integer.parseInt(s.substring(0,2));
    }

    if (code <= 26) { // code >= 10, since first digit is not zero
      numWays += numDecodings(s.substring(2));
    }
    return numWays;
  }
}
