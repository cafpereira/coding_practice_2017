package com.example.leetcode;

import java.util.*;

public class p76_MinimumWindowSubstring {

  public String minWindow(String s, String t) {
    // Valid inputs
    if (s.isEmpty() || t.isEmpty()) {
      return "";
    }
    Map<Character, Integer> freq = buildMap(t);
    int minStart = 0;
    int minLen = Integer.MAX_VALUE;
    int start = 0;

    // We need a data structure that tells us if all elements of the pattern
    // have been matched. We can iterate over the frequency map and verify
    // each frequency count is zero or less. Lets do an optimization so we can
    // calculate the same information in constant time.
    // Every time we match a new letter from the pattern we increase totalMatches
    // counter, if counter gets equal to the size of the pattern, then the entire
    // pattern is matched.
    int totalMatches = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!freq.containsKey(c)) {
        // nothing to do, keep searching for pattern matches
        continue;
      }

      // Decrease frequency count
      freq.put(c, freq.get(c) - 1);

      // Increase match count unless its a surplus match, i.e,
      // the pattern letter already has the necessary number of
      // matches inside the sliding window.
      if (freq.get(c) >= 0) {
        totalMatches++;
      }

      // current window contains all letters. Notice there is no need
      // to verify start is before end because totalMatches WILL be
      // less than T when that happens
      while(totalMatches ==  t.length()) {
        int len = i - start + 1;
        if (len < minLen) {
          // update min window
          minStart = start;
          minLen = len;
        }

        // Remove first element from window
        char b = s.charAt(start++);

        if (!freq.containsKey(b)) {
          // Unnecessary character, keep going
          continue;
        }

        // Update frequency map after removal
        freq.put(b, freq.get(b) + 1);

        if (freq.get(b) > 0) {
          // The current window is now behind one match, need to
          // update totalMatches counter
          totalMatches--;
        }
      } // end while()

      // Notice that at this point start is currently at a position that
      // dont have all the necessary letters of T inside the window.
      // for loop will increase end pointer until we find the missing letters
      // or reach end of S.
    }
    return minLen > s.length() ? "" : s.substring(minStart, minStart + minLen);
  }

  private Map<Character, Integer> buildMap(String t) {
    Map<Character, Integer> res = new HashMap<>();
    for (char c : t.toCharArray()) {
      res.put(c, res.getOrDefault(c, 0) + 1);
    }
    return res;
  }

  public static void main(String[] args) {
    p76_MinimumWindowSubstring solution = new p76_MinimumWindowSubstring();
    System.out.println("minWindow: " + solution.minWindow("ADOBECODEBANC", "ABC")); // expect "BANC"
  }
}
