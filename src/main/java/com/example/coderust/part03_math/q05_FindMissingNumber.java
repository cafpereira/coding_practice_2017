package com.example.coderust.part03_math;

import java.util.*;

public class q05_FindMissingNumber {

  public static int missing(int[] A, int n) {
    if (n < 2) {
      return n;
    }
    int totalSum = ( n * (n + 1) ) / 2;
    int localSum = 0;
    for (int v : A) {
      localSum += v;
    }
    return totalSum - localSum;
  }
}
