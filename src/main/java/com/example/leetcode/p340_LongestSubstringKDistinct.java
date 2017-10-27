package com.example.leetcode;

public class p340_LongestSubstringKDistinct {
  public static String longestDistinctSubstring(String str, int k) {
    int start = 0;
    int distinct = 0;
    String res = null;
    int[] count = new int[256];

    for (int end = 0; end < str.length(); end++) {
      char c = str.charAt(end);
      if (count[c] == 0) {
        distinct++;
      }
      count[c]++;
      if (distinct == k) {
        int len = end - start + 1;
        if (res == null || len > res.length()) {
          res = str.substring(start, end + 1);
        }
      }
      // Move start to the right and keep removing chars from
      // the current window until distinct count is back to K.
      while (distinct > k) {
        // No need to check with start goes out-of-boundary, because distinct counter
        // MUST reach zero before that happens.
        char s = str.charAt(start++);
        count[s]--;
        if (count[s] == 0) {
          distinct--;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println("longestDistinctSubstring('karappa',1) = " + longestDistinctSubstring("karappa", 1));
    System.out.println("longestDistinctSubstring('karappa',2) = " + longestDistinctSubstring("karappa", 2));
    System.out.println("longestDistinctSubstring('karappa',3) = " + longestDistinctSubstring("karappa", 3));
  }
}
