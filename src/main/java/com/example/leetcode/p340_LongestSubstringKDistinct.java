package com.example.leetcode;

public class p340_LongestSubstringKDistinct {
  public static String longestDistinctSubstring(String str, int k) {
    int start = 0;
    int distinct = 0;
    String maxStr = null;
    int[] count = new int[256];
    for (int end = 0; end < str.length(); end++) {
      if (count[str.charAt(end)]++ == 0) {
        distinct++;
      }
      // Move start to the right and keep removing chars from
      // the current window until distinct count is back to K.
      if (distinct > k) {
        while (--count[str.charAt(start++)] > 0); // No need to test if 'start' is out of limit
        // because it will eventually meet 'end' and
        // distinct counter will converge to K
        distinct--;
      }
      int len = end - start + 1;
      if (maxStr == null || len > maxStr.length()) {
        maxStr = str.substring(start, end + 1);
      }
    }
    return maxStr;
  }


  public static void main(String[] args) {
    System.out.println("longestDistinctSubstring('karappa',1) = " + longestDistinctSubstring("karappa", 1));
    System.out.println("longestDistinctSubstring('karappa',2) = " + longestDistinctSubstring("karappa", 2));
    System.out.println("longestDistinctSubstring('karappa',3) = " + longestDistinctSubstring("karappa", 3));
  }
}
