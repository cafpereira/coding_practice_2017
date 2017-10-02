package com.example.hackerrank.part02_algorithms;

import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-bubble-sort
 * guide: https://www.youtube.com/watch?v=6Gv8vg0kcHc
 */
class BubbleSort {

  public static int bubbleSort(int[] a) {
    int n = a.length;
    int res = 0;

    for (int i = 0; i < n; i++) {
      // Track number of elements swapped during a single array traversal
      int numberOfSwaps = 0;

      for (int j = 0; j < n - 1; j++) {
        // Swap adjacent elements if they are in decreasing order
        if (a[j] > a[j + 1]) {
          swap(a, j, j + 1);
          numberOfSwaps++;
        }
      }

      // If no elements were swapped during a traversal, array is sorted
      if (numberOfSwaps == 0) {
        break;
      }
      res += numberOfSwaps;
    }
    return res;
  }

  public static void swap(int[] array, int src, int dest) {
    int temp = array[src];
    array[src] = array[dest];
    array[dest] = temp;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int a[] = new int[n];
    for(int a_i=0; a_i < n; a_i++){
      a[a_i] = in.nextInt();
    }
    System.out.println("Array is sorted in "+ bubbleSort(a) +" swaps.");
    System.out.println("First Element: " + a[0]);
    System.out.println("Last Element: "+ a[n-1]);
  }
}
