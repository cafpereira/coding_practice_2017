package com.example.leetcode;

import com.example.leetcode.utils.TreeNode;

public class p270_ClosestValueBST_V2 {
  public int closestValue(TreeNode root, double target) {
    TreeNode cur = root;
    int closest = root.val;
    while (cur != null) {
      double diff = Math.abs(target - cur.val);
      if (diff < Math.abs(target - closest)) {
        // update if current value is closer to target
        closest = cur.val;
      }
      if (cur.val > target) {
        cur = cur.left;
      } else { // val <= target
        cur = cur.right;
      }
    }
    return closest;
  }
}
