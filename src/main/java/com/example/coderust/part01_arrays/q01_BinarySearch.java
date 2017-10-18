package com.example.coderust.part01_arrays;

/**
 * Given a sorted array of integers, return the index of the given key. Return -1 if not found.
 */
public class q01_BinarySearch {
  public static int binarySearch(int[] A, int key) {
    int low = 0;
    int high = A.length - 1;

    while (low <= high) {
      int mid = low + ((high - low) / 2);
      if (A[mid] == key) {
        return mid;
      }
      if (key < A[mid]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return -1;
  }
}
