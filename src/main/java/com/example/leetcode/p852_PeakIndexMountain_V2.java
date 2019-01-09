package com.example.leetcode;

/**
 * Use Binary search to find the mountain peak
 */
public class p852_PeakIndexMountain_V2 {
  public int peakIndexInMountainArray(int[] A) {
    int lo = 0;
    int hi = A.length - 1;

    while (lo < hi) {
      int mid = (hi + lo) / 2;
      if (A[mid] > A[mid + 1]) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return A[lo];
  }
}
