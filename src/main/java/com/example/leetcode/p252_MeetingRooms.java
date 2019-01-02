package com.example.leetcode;

import com.example.leetcode.utils.Interval;
import java.util.*;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class p252_MeetingRooms {
  public boolean canAttendMeetings(Interval[] intervals) {
    if (intervals == null || intervals.length < 1) {
      return true;
    }

    Arrays.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval i1, Interval i2) {
        return Integer.compare(i1.start, i2.start);
      }
    });

    int prevEnd = -1;
    for (Interval m : intervals) {
      if (m.start < prevEnd) {
        return false;
      }
      prevEnd = m.end;
    }
    return true;
  }
}
