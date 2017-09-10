package com.example.codersnacks;

import java.text.*;
import java.util.*;

/**
 * Write methods that calculate the mean, median and mode of an
 * array of integer values
 */
class MeanMedianMode {

  public static double mean(int[] A) {
    if (A == null || A.length == 0) {
      return 0;
    }
    int sum = 0;
    for (int val : A) {
      sum += val;
    }
    return ((double)sum / A.length);
  }

  public static int median(int[] A) {
    // TODO..
    return 0;
  }

  public static int mode(int[] A) {
    // TODO..
    return 0;
  }

  public static void main(String[] args) {
//    expect("mean", new int[]{2,5,7,7,9}, 6);
//    expect("mean", new int[]{3,4}, 3.5);
//    expect("mean", new int[]{}, 0);

    expect("median", new int[]{2,5,7,7,9}, 6);
    expect("median", new int[]{3,4}, 3);
  }

  private static void expect(String stat, int[] input, double expected) {
    Object out = null;
    if (stat.equals("mean")) {
      out = mean(input);
    } else if (stat.equals("median")) {
      out = median(input);
    }
    MessageFormat mf = new MessageFormat("{0}({1}) == {2} ## Expected: {3}");
    System.out.println(mf.format(new Object[] {stat, toS(input), out, expected}));
  }

  private static String toS(int[] input) {
    return Arrays.toString(input);
  }
}
