package com.example.leetcode;

import com.example.leetcode.utils.Interval;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class p253_MeetingRoomsII_V2 {
  static class Event implements Comparable<Event>{
    double time;
    boolean start;

    Event(double time, boolean start) {
      this.time = time;
      this.start = start;
    }

    @Override
    public int compareTo(Event other) {
      return Double.compare(this.time, other.time);
    }
  }

  public int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length < 1) {
      return 0;
    }
    List<Event> events = new ArrayList<>();
    for (Interval m : intervals) {
      events.add(new Event(m.start, true));
      events.add(new Event(m.end - 0.5, false));  // Make sure meeting end events appear first
                                                              // in the sorted array. This is necessary
                                                              // to avoid extra count case a meeting starts
                                                              // at the same time another meeting ends.
    }
    Collections.sort(events);

    int res = 0;
    int rooms = 0;

    for (Event e : events) {
      if (e.start) {
        rooms++;
      } else { // event end
        rooms--;
      }
      res = Math.max(res, rooms);
    }
    return res;
  }
}
