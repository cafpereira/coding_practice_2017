package com.example.leetcode.utils;
import java.util.*;

public class TreeHelper {

  private static final String NULL = "null";
  private static final String DELIM = ",";

  // Decodes your encoded data to tree.
  public static TreeNode deserialize(String input) {
    if (input == null || input.isEmpty()) {
      return null;
    }

    // clean begin and ending brackets [...]
    int len = input.length();
    String data = input.substring(1, len - 1);

    String[] values = data.split(DELIM);
    Deque<String> dataQueue = new LinkedList<>(Arrays.asList(values));

    TreeNode root = treeNode(dataQueue.remove());
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty() && !dataQueue.isEmpty()) {
      TreeNode parent = queue.remove();

      if (!dataQueue.isEmpty()) {
        parent.left = treeNode(dataQueue.remove());
        if (parent.left != null) {
          queue.add(parent.left);
        }
      }

      if (!dataQueue.isEmpty()) {
        parent.right = treeNode(dataQueue.remove());
        if (parent.right != null) {
          queue.add(parent.right);
        }
      }
    }
    return root;
  }

  private static TreeNode treeNode(String val) {
    if (val.equals(NULL)) {
      return null;
    }
    return new TreeNode(Integer.parseInt(val));
  }
}
