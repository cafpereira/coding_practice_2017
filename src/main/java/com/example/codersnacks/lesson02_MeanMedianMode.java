package com.example.codersnacks;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Write methods that calculate the mean, median and mode of an array of integer values
 */
class MeanMedianMode {

  public static double mean(int[] A) {
    if (A == null || A.length == 0) {
      // Be careful with sentinel values, since they could be a
      // valid answers themselves
      return 0;
    }
    // Long type to prevent sum overflow
    long sum = 0;
    for (int val : A) {
      sum += val;
    }
    return ((double) sum / A.length);
  }

  /**
   * Assume median is is the middle element of odd length arrays, or the smaller middle element for even arrays.
   */
  public static int median(int[] A) {
    int rank = A.length / 2;
    if (A.length % 2 == 0) {
      rank--;
    }
    return quickSelect(A, rank);
  }

  private static int quickSelect(int[] A, int rank) {
    int m = A.length / 2;
    int pivot = A[m];
    List<Integer> smaller = new ArrayList<>();
    List<Integer> larger = new ArrayList<>();
    int equalCount = 0;
    for (int elem : A) {
      if (elem < pivot) {
        smaller.add(elem);
      } else if (elem == pivot) {
        equalCount++;
      } else { // elem > pivot
        larger.add(elem);
      }
    }

    if (smaller.size() > rank) { // rank element is in the smaller subarray
      return quickSelect(toA(smaller), rank);
    } else if (smaller.size() + equalCount > rank) { // Pivot is the rank(th) element
      return pivot;
    } else { // rank element is in the larger subarray
      int newRank = rank - (smaller.size() + equalCount);
      return quickSelect(toA(larger), newRank);
    }
  }

  public static int mode(int[] A) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int elem : A) {
      if (!map.containsKey(elem)) {
        map.put(elem, 0);
      }
      map.put(elem, map.get(elem) + 1);
    }

    int mode = -1;
    int freq = -1;
    for (Map.Entry<Integer, Integer> e : map.entrySet()) {
      if (e.getValue() > freq) {
        mode = e.getKey();
        freq = e.getValue();
      }
    }
    return mode;
  }

  public static void main(String[] args) {
    expect("mean", new int[]{2,5,7,7,9}, 6);
    expect("mean", new int[]{3,4}, 3.5);
    expect("mean", new int[]{}, 0);
    expect("mean", new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}, Integer.MAX_VALUE);

    expect("median", new int[]{2,5,7,7,9}, 7);
    expect("median", new int[]{2,5,7,9}, 5);
    expect("median", new int[]{9,1,8,2,7,3,6,4,5}, 5);
    expect("median", new int[]{5,5,5,5,5,5,5}, 5);

    expect("mode", new int[]{2,5,7,7,9}, 7);
    expect("mode", new int[]{1,1,1,0,0,2,2,4,4,4,4}, 4);
    expect("mode", new int[]{5,6,7,8,9,10,11}, 5);
    expect("mode", new int[]{1}, 1);
    expect("mode", new int[]{1,1,2,2,2}, 2);
  }

  private static void expect(String stat, int[] input, double expected) {
    Object out = null;
    if (stat.equals("mean")) {
      out = mean(input);
    } else if (stat.equals("median")) {
      out = median(input);
    } else if (stat.equals("mode")) {
    out = mode(input);
    }
    MessageFormat mf = new MessageFormat("{0}({1}) == {2} # Expected: {3}");
    System.out.println(mf.format(new Object[]{stat, toS(input), out, expected}));
  }

  private static String toS(int[] input) {
    return Arrays.toString(input);
  }

  private static int[] toA(List<Integer> list) {
    return list.stream().mapToInt(Integer::intValue).toArray();
  }
}
