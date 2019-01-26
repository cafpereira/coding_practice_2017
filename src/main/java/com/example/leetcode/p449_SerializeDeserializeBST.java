package com.example.leetcode;

import com.example.leetcode.utils.*;
import java.util.*;
import java.util.stream.*;

/**
 * This solution will use a preorder traversal to reconstruct the tree.
 * Time Complexity: O(N^2) for degenerated trees
 */
public class p449_SerializeDeserializeBST {
  static final String DELIM = ",";

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
      // No need to use NULL to mark the leaf nodes.
      // Since this is a BST we know which the elements
      // will be on the left or right side of the tree
      // without extra information
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

    return preOrderDeserialize(preorder);
  }

  private TreeNode preOrderDeserialize(List<Integer> preorder) {
    if (preorder.isEmpty()) {
      return null;
    }

    TreeNode root = new TreeNode(preorder.remove(0));
    List<Integer> smaller = new LinkedList<>();
    while (!preorder.isEmpty() && preorder.get(0) < root.val ) {
      smaller.add(preorder.remove(0));
    }
    root.left = preOrderDeserialize(smaller);
    root.right = preOrderDeserialize(preorder);
    return root;
  }
}
