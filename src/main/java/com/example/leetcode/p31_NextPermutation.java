package com.example.leetcode;

import java.util.Arrays;

public class p31_NextPermutation {
  public void nextPermutation(int[] nums) {
    int n = nums.length;

    // Find index j that splits the array in: P[0..j] + S[j+1..n-1]
    // S[j+1..n-1] is the longest non-increasing sequence (NIS)
    // Why? NIS is the 'last' possible permutation of the numbers in that sequence
    // This means the next permutation has to start with the element right before the S(j+1)
    int j = n - 2;
    while(j >= 0 && nums[j] >= nums[j + 1]) {
      j--;
    }

    // There is a possibility the complete array is NIS (P is empty), if yes, then skip the next step
    if (j >= 0) {
      // find smaller element in S(j+1) that is greater than nums[j]
      // Why? From all the possible permutations, this number is the best choice to start the next permutation
      // Remember we cannot pick any number from P because already belong to the prefix, and we cannot pick any
      // number smaller that nums[j] from S(j+1) since that will not preserve the NIS order, which is necessary
      // for the rest of the algorithm
      int k = n - 1;
      while (nums[j] >= nums[k]) {
        k--;
      }
      // Swap j and k
      // Why? Given the previous explanation, we know now that nums[k] is the first element that belong to the
      // next permutation.
      int temp = nums[j];
      nums[j] = nums[k];
      nums[k] = temp;
    }

    // i == first position (head) of S(j+1)
    int i = j + 1;

    // Reverse S(j+1)
    // Why: Similarly to the fact the NIS is the 'last' possible permutation of the numbers in S(j+1)
    // the reversed non decreasing sequence (NDS) is  the 'first' permutation of the same numbers.
    // Therefore, the next permutation with prefix P[0..j] will have the reversed sufix.
    int start = i;
    int end = n - 1;
    while (start < end) {
      int temp = nums[start];
      nums[start++] = nums[end];
      nums[end--] = temp;
    }
  }

  public static void main(String[] args) {
    p31_NextPermutation solution = new p31_NextPermutation();
    int[] nums = {1,5,1};
    solution.nextPermutation(nums);
    System.out.println("nextPermutation: " + Arrays.toString(nums)); // expected {5, 1, 1}
  }
}
