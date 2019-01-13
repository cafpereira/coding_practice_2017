package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Normal solution uses a frequency map to count occurrences of values in nums1.
 * For each matching key on nums2, we add to the result and decrease counter.
 * when the count goes to zero we can remove that key from the frequency map.
 *
 * This is the solution is faster and can be used for the follow up question #1:
 * - What if the given arrays are already sorted?
 *
 * Other follow up questions
 * 2) What if nums1's size is small compared to nums2's size?
 * A: Sort the small array and do a binary search for each value in the large array.
 * 3) What if cannot load all elements into the memory?
 * A: If we can load one array in memory, we can use the frequency map solution and
 *    load chunks of the large from disk.
 *    If both arrays are on disk, then use the sorting solution. External sort can
 *    sort larges files by sorting small blocks and merging then together.
 */
class p350_IntersectionOfTwoArraysII {
  public int[] intersect(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);

    int i = 0, j = 0;
    int n = nums1.length, m = nums2.length;
    List<Integer> intersec = new ArrayList<>();

    while (i < n && j < m) {
      if (nums1[i] == nums2[j]) {
        intersec.add(nums1[i]);
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else { // nums1[i] > nums2[j]
        j++;
      }
    }

    // Convert ArrayList to array
    int[] arr = new int[intersec.size()];
    for (int k= 0; k < intersec.size(); k++) arr[k] = intersec.get(k);
    return arr;
  }
}