package com.example.codersnacks;

import java.util.*;

public class lesson20_ClosestPoints {

  public static List<Point> closestPoints(Iterator<Point> points, int k) {
    PriorityQueue<Point> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
    maxHeap.add(points.next());
    while (points.hasNext()) {
      Point p = points.next();
      if (p.distance() < maxHeap.peek().distance()) {
        maxHeap.add(p);
        if (maxHeap.size() > k) {
          maxHeap.remove();
        }
      }
    }
    return new ArrayList<>(maxHeap);
  }
}

class Point implements Comparable<Point> {
  double x; double y;
  public double distance() {
    return Math.sqrt(x*x + y*y);
  }
  public int compareTo(Point other) {
    return Double.compare(this.distance(), other.distance());
  }
}
