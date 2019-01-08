package com.example.leetcode;

import java.util.*;

public class p939_MinimumAreaRectangle {
  public int minAreaRect(int[][] points) {
    int n = points.length;
    if (n < 4) {
      return 0;
    }

    int minArea = Integer.MAX_VALUE;
    Map<Integer, Set<Integer>> lookup = fromPoints(points);

    for (int i = 0; i < n - 3; i++) {
      int[] p1 = points[i];
      for (int j = i + 1; j < n; j++) {
        int[] p2 = points[j];
        if (!sharedAxis(p1, p2)) {
          int[] p3 = { p1[0], p2[1] };
          int[] p4 = { p2[0], p1[1] };
          if (found(lookup, p3, p4)) {
            minArea = Math.min(minArea, area(p1, p2));
          }
        }
      }
      remove(lookup, p1);
    }
    return minArea == Integer.MAX_VALUE ? 0 : minArea;
  }

  Map<Integer, Set<Integer>> fromPoints(int[][] points) {
    Map<Integer, Set<Integer>> res = new HashMap<>();
    for (int[] p : points) {
      Set<Integer> set = res.getOrDefault(p[0], new HashSet<Integer>());
      set.add(p[1]);
      res.put(p[0], set);
    }
    return res;
  }

  boolean sharedAxis(int[] p1, int[] p2) {
    return p1[0] == p2[0] || p1[1] == p2[1];
  }

  boolean found(Map<Integer, Set<Integer>> lookup, int[] p1, int[] p2) {
    boolean found1 = lookup.get(p1[0]).contains(p1[1]);
    boolean found2 = lookup.get(p2[0]).contains(p2[1]);
    return found1 && found2;
  }

  int area(int[] p1, int[] p2) {
    return Math.abs((p2[0] - p1[0]) * (p2[1] - p1[1]));
  }

  void remove(Map<Integer, Set<Integer>> lookup, int[] p) {
    lookup.get(p[0]).remove(p[1]);
  }

  public static void main(String[] args) {
    p939_MinimumAreaRectangle solution = new p939_MinimumAreaRectangle();

    int[][] points = {{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}};
    System.out.println("min area: " + solution.minAreaRect(points));
  }
}
