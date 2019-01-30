package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;

public class p56_MergeIntervals {
  public List<Interval> merge(List<Interval> intervals) {
    List<Interval> res = new ArrayList<>();
    Interval cur = null;

    if (intervals.size() < 1) {
      return res;
    }
    Collections.sort(intervals, (i1, i2) -> Integer.compare(i1.start, i2.start));

    for (Interval next : intervals) {
      if (cur == null) {
        cur = next;
        continue;
      }
      if (next.start <= cur.end) {
        cur.end = Math.max(cur.end, next.end);
      } else {
        res.add(cur);
        cur = next;
      }
    }
    res.add(cur);

    return res;
  }
}
