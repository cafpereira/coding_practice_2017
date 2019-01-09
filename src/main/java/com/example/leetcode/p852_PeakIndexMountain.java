package com.example.leetcode;

public class p852_PeakIndexMountain {
  public int peakIndexInMountainArray(int[] A) {
    boolean ascending = A[0] <= A[1];
    int i = 1;
    int n = A.length;
    while (true) {
      if (ascending) {
        while (i < n - 1 && A[i] <= A[i+1]) {
          i++;
        }
        return i;
      } else {
        while (i < n - 1 && A[i] >= A[i+1]) {
          i++;
        }
        if (i < n -1) {
          ascending = true;
        } else {
          return 0;
        }
      }
    }
  }
}
