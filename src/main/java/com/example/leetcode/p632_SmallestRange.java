package com.example.leetcode;

import java.util.*;

/**
 * Use a BST tree to keep track of the first (min) and last (max) elements of the range. Can also
 * use priority queue (heap), it will require extra coding to update the max value as we pop and
 * add new values to the heap.
 */
public class p632_SmallestRange {
  static class Entry {
    int value;
    int sourceId;
    int index;
    Entry(int value, int sourceId, int index) {
      this.value = value;
      this.sourceId = sourceId;
      this.index = index;
    }
  }

  public int[] smallestRange(List<List<Integer>> nums) {
    TreeSet<Entry> bst = new TreeSet<>((e1, e2) -> {
      if (e1.value == e2.value) {
        // Compare sourceId if value is the same otherwise
        // treeset will automatically remove duplicated
        // values from the tree.
        return Integer.compare(e1.sourceId, e2.sourceId);
      }
      return Integer.compare(e1.value, e2.value);
    });

    for (int i = 0; i < nums.size(); i++) {
      List<Integer> l = nums.get(i);
      bst.add(new Entry(l.get(0), i, 0));
    }

    Entry minStart = null;
    Entry minEnd = null;
    int minRange = Integer.MAX_VALUE;

    while (true) {
      Entry start = bst.first();
      Entry end = bst.last();

      int curRange = end.value - start.value;

      if (curRange < minRange) {
        minStart = start;
        minEnd = end;
        minRange = curRange;
      }
      // As soon as reach the end of any of the lists, return the range
      if (!advance(bst, nums)) {
        return new int[]{minStart.value, minEnd.value};
      }
    }
  }

  /**
   * Remove the minimum value from the BST and add the next element of the list
   * that originally had that value (if possible)
   */
  private boolean advance(TreeSet<Entry> bst, List<List<Integer>> nums) {
    Entry cur = bst.first();
    List<Integer> source = nums.get(cur.sourceId);
    int size = source.size();

    int nextIdx = cur.index + 1;
    if (nextIdx >= size) {
      // No more new elements to add, return false
      return false;
    }
    Entry next = new Entry(source.get(nextIdx), cur.sourceId, nextIdx);
    bst.remove(cur);
    bst.add(next);

    return true;
  }

  public static void main(String[] args) {
    Integer[][] inputs = {
        {-89,1,69,89,90,98},
        {-43,-36,-24,-14,49,61,66,69},
        {73,94,94,96},
        {11,13,76,79,90},
        {-40,-20,1,9,12,12,14},
        {-91,-31,0,21,25,26,28,29,29,30},
        {23,88,89},
        {31,42,42,57},
        {-2,6,11,12,12,13,15},
        {-3,25,34,36,39},
        {-7,3,29,29,31,32,33},
        {4,11,14,15,15,18,19},
        {-34,9,12,19,19,19,19,20},
        {-26,4,47,53,64,64,64,64,64,65},
        {-51,-25,36,38,50,54},
        {17,25,38,38,38,38,40},
        {-30,12,15,19,19,20,22},
        {-14,-13,-10,68,69,69,72,74,75},
        {-39,42,70,70,70,71,72,72,73},
        {-67,-34,6,26,28,28,28,28,29,30,31}
    };
    List<List<Integer>> nums = new ArrayList<>();
    for (Integer[] row : inputs) {
      nums.add(Arrays.asList(row));
    }

    p632_SmallestRange solution = new p632_SmallestRange();
    System.out.println("smallestRange: " + Arrays.toString(solution.smallestRange(nums))); // expected [13.73]
  }
}
