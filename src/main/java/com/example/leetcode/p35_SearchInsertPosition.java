package com.example.leetcode;

public class p35_SearchInsertPosition {
  public int searchInsert(int[] nums, int target) {
    int start = 0;
    int end = nums.length - 1;
    while (start <= end) {
      int mid = (start + end) / 2;
      if (nums[mid] >= target)
        // No need to premature break the loop if target is found,
        // In a few iterations start will catch up and converge to
        // the target position
        end = mid - 1;
      else // nums[mid] < target
        start = mid + 1;
    }
    // start is either the target index or the insert position
    return start;
  }
}
