package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;

/**
 * Use DFS search (right -> left) to traverse the tree and add new nodes
 * to the view every time a level (non-null) is visited for the 1st time.
 */
class p199_BinaryTreeRightSideView {
  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    dfs(root, 1, res);
    return res;
  }

  void dfs(TreeNode node, int level, List<Integer> res) {
    // Base case
    if (node == null) {
      return;
    }

    // If level is higher than stored view elements,
    // then this level is being seen for the first time.
    if (res.size() < level) {
      res.add(node.val);
    }

    // Pre-order traversal will ensure that top nodes
    // will be added before lower nodes
    dfs(node.right, level + 1, res);
    dfs(node.left, level + 1, res);
  }
}
