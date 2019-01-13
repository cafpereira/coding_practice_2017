package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class p285_InorderSuccessorBST {
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (root == null) {
      return null;
    }
    int key = p.val;

    TreeNode cur = root;
    TreeNode suc = null;

    while(cur != null) {
      if (cur.val > key) {
        if (suc == null || cur.val < suc.val) {
          suc = cur;
        }
        cur = cur.left;
      }
      else {
        cur = cur.right;
      }
    }
    return suc;
  }
}