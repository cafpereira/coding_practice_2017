package com.example.leetcode;

import com.example.leetcode.utils.*;
import java.util.*;

/**
 * Serialize using DFS traversal, not so readable as
 * BFS option but uses less memory and implementation
 * is simple.
 */
public class p297_SerializeDeserializeBinaryTree_V2 {
  static final String NULL = "null";
  static final String DELIM = ",";

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();

    preOrderSerializer(root, sb);
    // cleanup last delimiter comma from output
    sb.setLength(sb.length() - 1);

    return sb.toString();
  }

  private void preOrderSerializer(TreeNode node, StringBuilder sb) {
    if (node == null) {
      sb.append(NULL).append(DELIM);
      return;
    }
    sb.append(node.val).append(DELIM);
    preOrderSerializer(node.left, sb);
    preOrderSerializer(node.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data == null || data.isEmpty() || data.equals(NULL)) {
      return null;
    }
    String[] values = data.split(",");
    Deque<String> dataQueue = new LinkedList<>(Arrays.asList(values));

    return preOrderDeserializer(dataQueue);
  }

  private TreeNode preOrderDeserializer(Deque<String> dataQueue) {
    if (dataQueue.isEmpty()) {
      return null;
    }
    TreeNode node = treeNode(dataQueue.remove());
    if (node != null) {
      node.left = preOrderDeserializer(dataQueue);
      node.right = preOrderDeserializer(dataQueue);
    }
    return node;
  }

  private TreeNode treeNode(String val) {
    if (val.equals(NULL)) {
      return null;
    }
    return new TreeNode(Integer.parseInt(val));
  }

  public static void main(String[] args) {
    p297_SerializeDeserializeBinaryTree_V2 solution = new p297_SerializeDeserializeBinaryTree_V2();
    TreeNode root = buildTree();
    System.out.println("serialize: " + solution.serialize(root));
    System.out.println("deserialize: " + solution.deserialize("3,9,4,null,null,0,null,2,null,null,8,1,5,null,null,null,7,null,null"));
  }

  static TreeNode buildTree() {
    TreeNode a = new TreeNode(3);

    TreeNode b = new TreeNode(9);
    TreeNode c = new TreeNode(8);

    TreeNode d = new TreeNode(4);
    TreeNode e = new TreeNode(0);
    TreeNode f = new TreeNode(1);
    TreeNode g = new TreeNode(7);

    TreeNode h = new TreeNode(2);
    TreeNode i = new TreeNode(5);

    a.left = b;
    a.right = c;

    b.left = d;
    b.right = e;
    c.left = f;
    c.right = g;

    e.right = h;
    f.left = i;

    return a;
  }
}
