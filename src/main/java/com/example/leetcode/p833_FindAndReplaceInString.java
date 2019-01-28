package com.example.leetcode;

import java.io.*;
import java.util.*;

/**
 * Sort indexes and replace from right to left.
 * Time: O(NlogN) + O(NS) => O(NS),
 * N = # of replacements
 * S = size of string S
 *
 * Can improve time to  O(NlogN) + O(S) if we use
 * String builder to perform the replaces. E.g:
 *    StringBuilder sb =  new StringBuilder(S);
 *    sb.replace(start, end, replaceStr);
 */
public class p833_FindAndReplaceInString {
  static class Replace {
    int index;
    String source;
    String target;
    Replace(int index, String source, String target) {
      this.index = index;
      this.source = source;
      this.target = target;
    }
  }

  public String findReplaceString(String s, int[] indexes, String[] sources, String[] targets) {
    int n = indexes.length;

    List<Replace> replaces = sortedList(indexes, sources, targets);
    for (int i = n - 1; i >= 0; i--) {
      s = replaceHelper(s, replaces.get(i).index, replaces.get(i).source, replaces.get(i).target);
    }
    return s;
  }

  public List<Replace> sortedList(int[] indexes, String[] sources, String[] targets) {
    int n = indexes.length;

    List<Replace> sorted = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      sorted.add(new Replace(indexes[i], sources[i], targets[i]));
    }
    Collections.sort(sorted, (r1, r2) -> Integer.compare(r1.index, r2.index));

    return sorted;
  }

  public String replaceHelper(String s, int index, String source, String target) {
    int start = index;
    int end = index + source.length();
    if (s.substring(start, end).equals(source)) {
      return s.substring(0, start) + target + s.substring(end);
    } else {
      return s;
    }
  }
}
