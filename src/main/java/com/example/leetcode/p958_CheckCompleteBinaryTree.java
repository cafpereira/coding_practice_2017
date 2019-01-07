package com.example.leetcode;

import com.example.leetcode.utils.*;
import java.util.*;

public class p958_CheckCompleteBinaryTree {
  public boolean isCompleteTree(TreeNode root) {
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    // Run level order traversal on the binary tree.
    // Example:
    //                 A
    //               /   \
    //              B     C
    //            /
    //           D
    // Visit order: A, B, C, D, null, null, null

    while (queue.peek() != null) {
      TreeNode cur = queue.remove();
      // Add all children (even nulls) to the queue
      queue.add(cur.left);
      queue.add(cur.right);
    }

    // Complete tree should have exclusively null elements left in the queue.
    while (!queue.isEmpty()) {
      if (queue.remove() != null) {
        return false;
      }
    }
    return true;
  }
}
