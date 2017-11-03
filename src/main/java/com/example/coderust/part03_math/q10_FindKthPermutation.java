package com.example.coderust.part03_math;

import java.util.*;

public class q10_FindKthPermutation {
  public static List findKthPermutation (List elements, int k, List perm) {
    int n = elements.size();
    if (n == 1) {
      perm.add(elements.remove(0));
      return perm;
    }
    // How many permutations after first digit?
    int blockSize = factorial(n - 1);

    // Find the first "i" index such as:
    // i * blockSize <= k < (i + 1) * blockSize
    int i = (k - 1) /  blockSize;

    // A[i] is the next element of the Kth permutation
    perm.add(elements.remove(i));

    // Recursively solve the problem for the remaining elements
    // Dont forget to deduce from K the skipped blocks.
    return findKthPermutation(elements, k - (i * blockSize), perm);
  }

  public static int factorial(int n) {
    if (n == 0 || n == 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }
}
