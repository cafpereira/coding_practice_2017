package com.example.hackerrank.practice;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

/**
 * problem: https://www.hackerrank.com/challenges/find-the-running-median/problem
 */
public class RunningMedian {

  private static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  private static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

  /*
   * Complete the runningMedian function below.
   */
  static double[] runningMedian(int[] a) {
    List<Double> res = new ArrayList<>();
    for (int v : a) {
      res.add(median(v));
    }
    return res.stream().mapToDouble(v -> v).toArray();
  }

  static double median(int v) {
    // Put v either on the left or right side
    if (!minHeap.isEmpty() && v >= minHeap.peek()) {
      minHeap.add(v);
    } else {
      maxHeap.add(v);
    }

    if (maxHeap.size() > minHeap.size() + 1) {
      // Even size, but left and right sides are unbalanced
      minHeap.add(maxHeap.poll());
    }

    if (minHeap.size() > maxHeap.size()) {
      // If odd size, then move median to left side
      maxHeap.add(minHeap.poll());
    }

    if (minHeap.size() == maxHeap.size()) {
      return (maxHeap.peek() + minHeap.peek()) / 2.0;
    } else {
      return maxHeap.peek();
    }
  }

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int aCount = Integer.parseInt(scanner.nextLine().trim());

    int[] a = new int[aCount];

    for (int aItr = 0; aItr < aCount; aItr++) {
      int aItem = Integer.parseInt(scanner.nextLine().trim());
      a[aItr] = aItem;
    }

    double[] result = runningMedian(a);

    for (int resultItr = 0; resultItr < result.length; resultItr++) {
      bufferedWriter.write(String.valueOf(result[resultItr]));

      if (resultItr != result.length - 1) {
        bufferedWriter.write("\n");
      }
    }

    bufferedWriter.newLine();

    bufferedWriter.close();
  }
}
