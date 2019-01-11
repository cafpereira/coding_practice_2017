package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;

public class p270_ClosestValueBST {
  public int closestValue(TreeNode root, double target) {
    TreeNode cur = root;
    int lo = Integer.MIN_VALUE;
    int hi = Integer.MAX_VALUE;

    while (cur != null) {
      double val = (double) cur.val;
      if (val >= target) {
        hi = cur.val;
        cur = cur.left;
      } else { // val < target
        lo = cur.val;
        cur = cur.right;
      }
    }
    return closestBound(lo, hi, target);
  }

  int closestBound(int lo, int hi, double target) {
    if (lo == Integer.MIN_VALUE) {
      return hi;
    }
    if (hi == Integer.MAX_VALUE) {
      return lo;
    }
    double l1 = target - lo;
    double l2 = hi - target;
    return l1 < l2 ? lo : hi;
  }
}
