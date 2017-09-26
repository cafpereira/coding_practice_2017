package com.example.hackerrank.part01_datastruct;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-find-the-running-median guide: https://www.youtube.com/watch?v=VmogG01IjYc
 */
class RunningMedian {

  public static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(64, Collections.reverseOrder());

  public static float getMedian(int i) {
    // Add next number either to lower half (maxHeap)
    // or upper half (minHeap)
    if (maxHeap.isEmpty()) {
      maxHeap.add(i);
    } else if (i <= maxHeap.peek()) {
      maxHeap.add(i);
    } else {
      minHeap.add(i);
    }

    // Re-balance both halves if necessary
    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.add(maxHeap.poll());
    } else if (minHeap.size() > maxHeap.size()) {
      maxHeap.add(minHeap.poll());
    }

    // If both side have the same qtty, the median
    // is the average of each the max and min
    if (maxHeap.size() == minHeap.size()) {
      return ((float) maxHeap.peek() + minHeap.peek()) / 2;
    } else {
      // Otherwise return the max element of the lower half
      return (float) maxHeap.peek();
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] a = new int[n];
    for (int a_i = 0; a_i < n; a_i++) {
      a[a_i] = in.nextInt();
      System.out.println(getMedian(a[a_i]));
    }
  }
}
