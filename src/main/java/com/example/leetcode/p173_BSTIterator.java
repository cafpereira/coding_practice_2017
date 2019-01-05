package com.example.leetcode;

import com.example.leetcode.utils.*;
import java.util.*;

public class p173_BSTIterator {
  Deque<TreeNode> stack = new LinkedList<>();

  public p173_BSTIterator(TreeNode root) {
    traverseLeft(root);
  }

  /** @return the next smallest number */
  public int next() {
    TreeNode res = stack.pop();
    traverseLeft(res.right);
    return res.val;
  }

  /** @return whether we have a next smallest number */
  public boolean hasNext() {
    return !stack.isEmpty();
  }

  public void traverseLeft(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }
}
