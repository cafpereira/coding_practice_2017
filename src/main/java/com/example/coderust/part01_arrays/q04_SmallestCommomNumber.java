package com.example.coderust.part01_arrays;


/**
 * Given three integer arrays sorted in ascending order, find the smallest
 * number that is common in all three arrays.
 */

public class q04_SmallestCommomNumber {

  public static int searchRotated(int[] A, int v) {
    int start = 0;
    int end = A.length - 1;
    while (start <= end) {
      int m = (start + end) / 2;
      if (A[m] == v) {
        return m;
      }
      else if (A[start] < A[end]) { // start to end is strictly increasing
        if (A[m] < v) {  // go right
          start = m + 1;
        } else { // go left
          end = m - 1;
        }
      }
      else { // rotation cliff between start and end
        if (A[m] < v) {
          if (A[end] < v) { // target element is before the cliff, go left
            end = m - 1;
          }
          else {  // else, keep going right
            start = m + 1;
          }
        }
        else { // A[m] > v
          if (A[start] > v) { // target element is after the cliff, go right
            start = m + 1;
          }
          else {  // else, keep going left
            end = m - 1;
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] A = {176, 188, 199, 200, 210, 222, 1, 10, 20, 47, 59, 63, 75, 88, 99, 107, 120, 133, 155, 162};
    System.out.println("searchRotated(A, 200) = " + searchRotated(A, 200) + " # Expected: 3");
  }
}

