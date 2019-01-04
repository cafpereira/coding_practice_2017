package com.example.hackerrank.ctci.part02_algorithms;

import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-merge-sort
 * guide: https://www.youtube.com/watch?v=KF2j-9iSf4Q
 */
class CountInversions {

  static long countInversions(int[] a) {
    int end = a.length - 1;
    return countInversions(a, 0, end);
  }

  static long countInversions(int[] a, int start, int end) {
    if (start >= end) {
      return 0;
    }

    int mid = (start + end) / 2;
    long left = countInversions(a, start, mid);
    long right = countInversions(a, mid + 1, end);

    int cur = 0;
    int l = start;
    int r = mid + 1;
    int size = end - start + 1;
    int[] aux = new int[size];

    long inversions = 0;
    while (cur < size && l <= mid && r <= end) {
      if (a[l] <= a[r]) {
        aux[cur] = a[l++];
      } else {
        aux[cur] = a[r++];
        inversions += (mid - l) + 1;
      }
      cur++;
    }

    while (cur < size && l <= mid) {
      aux[cur++] = a[l++];
    }

    for (int i = 0; i < cur; i++) {
      a[start + i] = aux[i];
    }
    return left + right + inversions;
  }

  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for(int a0 = 0; a0 < t; a0++){
      int n = in.nextInt();
      int[] arr = new int[n];
      for(int arr_i = 0; arr_i < n; arr_i++){
        arr[arr_i] = in.nextInt();
      }
      long result = countInversions(arr);
      System.out.println(result);
    }
    in.close();
  }
}
