package com.example.coderust.part01_arrays;

import java.util.*;


/**
 * Given three integer arrays sorted in ascending order, find the smallest
 * number that is common in all three arrays.
 */

public class q03_SmallestCommomNumber {

  static int find_least_common_number(int[] arr1,
                                      int[] arr2,
                                      int[] arr3) {
    int i = 0, j = 0, k = 0;

    while (i < arr1.length &&
        j < arr2.length &&
        k < arr3.length) {

      // Found the smallest common number
      if (arr1[i] == arr2[j] &&
          arr2[j] == arr3[k]) {
        return arr1[i];
      }

      // Let's advance the iterator
      // for the smallest value.

      if (arr1[i] <= arr2[j] &&
          arr1[i] <= arr3[k]) {
        i++;
      } else if (arr2[j] <= arr1[i] &&
          arr2[j] <= arr3[k]) {
        j++;
      } else if (arr3[k] <= arr1[i] &&
          arr3[k] <= arr2[j]) {
        k++;
      }
    }

    return -1;
  }
}

