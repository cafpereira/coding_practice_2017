package com.example.leetcode;

import com.example.leetcode.utils.*;
import java.util.*;
import java.util.stream.*;

/**
 * This is a improved version that runs in linear time O(N)
 * but it needs a global variable to track the sequence of
 * recursion calls.
 */
public class p449_SerializeDeserializeBST_V2 {
  static final String DELIM = ",";

  int curIndex;

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) {
      return "";
    }
    StringBuilder sb = new StringBuilder();

    preOrderSerialize(root, sb);

    // remove last delimiter from output
    sb.setLength(sb.length() - 1);
    return sb.toString();
  }

  private void preOrderSerialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
      return;
    }
    sb.append(root.val).append(DELIM);
    preOrderSerialize(root.left, sb);
    preOrderSerialize(root.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data == null || data.isEmpty()) {
      return null;
    }

    String[] values = data.split(",");
    List<Integer> preorder = Arrays.stream(values)
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    curIndex = 0;
    return preOrderDeserialize(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private TreeNode preOrderDeserialize(List<Integer> preorder, int low, int hi) {
    if (curIndex == preorder.size()) {
      return null;
    }

    int value = preorder.get(curIndex);
    if (value < low || value > hi) {
      return null;
    }

    // If value is within the bound limits, then we create the next node of the tree
    TreeNode root = new TreeNode(value);

    // Important we increase the index counter and call the left and right
    // recursions in the correct order
    curIndex++;
    root.left = preOrderDeserialize(preorder, low, value);
    root.right = preOrderDeserialize(preorder, value, hi);

    return root;
  }
}
