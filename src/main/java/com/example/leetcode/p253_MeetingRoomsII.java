package com.example.leetcode;

import com.example.leetcode.utils.*;
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
public class p253_MeetingRoomsII {

  public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length < 1) {
      return 0;
    }

    Comparator<Interval> startAsc = new Comparator<Interval>() {
      @Override
      public int compare(Interval i1, Interval i2) {
        return Integer.compare(i1.start, i2.start);
      }
    };
    Arrays.sort(intervals, startAsc);

    Comparator<Interval> endAsc = new Comparator<Interval>() {
      @Override
      public int compare(Interval i1, Interval i2) {
        return Integer.compare(i1.end, i2.end);
      }
    };
    PriorityQueue<Interval> minHeap = new PriorityQueue<Interval>(endAsc);

    int maxRooms = 0;
    for (Interval m : intervals) {
      while (!minHeap.isEmpty() && minHeap.peek().end <= m.start) {
        minHeap.poll();
      }
      minHeap.add(m);
      maxRooms = Math.max(maxRooms, minHeap.size());
    }

    return maxRooms;
  }
}
