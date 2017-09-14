package com.example.hackerrank.part01_datastruct;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * problem:
 * https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem
 * guide:
 * https://www.youtube.com/watch?v=NLAzwv4D5iI
 */
class LeftRotation {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    int a[] = new int[n];
    for(int a_i=0; a_i < n; a_i++){
      a[a_i] = in.nextInt();
    }
    rotate(a, k, n);
    print(a);
  }

  public static void rotate(int[] a, int k, int n){
    int rotation = k % n;
    // Reverse all elements
    reverse(a, 0, n);

    // Reverse subarrays
    int split = n - rotation;
    reverse(a, 0, split);
    reverse(a, split, n);
  }

  public static void reverse(int[] A, int start, int last) {
    int end = last - 1;
    while (start < end) {
      int tmp = A[start];
      A[start++] = A[end];
      A[end--] = tmp;
    }
  }

  public static void print(int[] A) {
    StringBuilder sb = new StringBuilder();
    for (int a : A){
      sb.append(a + " ");
    }
    System.out.println(sb.toString());
  }
}
