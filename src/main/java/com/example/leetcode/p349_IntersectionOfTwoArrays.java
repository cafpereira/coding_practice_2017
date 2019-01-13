package com.example.leetcode;

import java.util.*;

class p349_IntersectionOfTwoArrays {
  public int[] intersection(int[] nums1, int[] nums2) {

    if (nums1 == null || nums2 == null || nums1.length < 1 || nums2.length < 1) {
      return new int[0];
    }

    Set<Integer> dedup1 = new HashSet<>();
    for (int v1 : nums1) {
      dedup1.add(v1);
    }

    List<Integer> intersec = new ArrayList<>();
    for (int v2 : nums2) {
      if (dedup1.contains(v2)) {
        intersec.add(v2);
        // This allow us to add only unique elements
        // to the intersec list.
        dedup1.remove(v2);
      }
    }

    // Convert ArrayList to array
    int[] arr = new int[intersec.size()];
    for (int i= 0; i < intersec.size(); i++) arr[i] = intersec.get(i);
    return arr;

    // Another option is to use Java 8 streams API, but its much slower
    // than creating the array manually (40ms vs 5ms)
    // return intersec.stream().mapToInt(i -> i).toArray();
  }
}