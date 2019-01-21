package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;


/**
 * Serialize the tree using BST traversal. This will match
 * binary tree encoding used by leetcode OJ
 */
public class p297_SerializeDeserializeBinaryTree {
  static final String NULL = "null";
  static final String DELIM = ",";

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) {
      return NULL;
    }
    StringBuilder sb = new StringBuilder();
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while(!queue.isEmpty()) {
      TreeNode node = queue.remove();
      if (node != null) {
        sb.append(node.val + DELIM);
        queue.add(node.left);
        queue.add(node.right);
      } else {
        sb.append(NULL + DELIM);
      }
    }
    // cleanup last delimiter comma from output
    sb.setLength(sb.length() - 1);

    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data == null || data.isEmpty() || data.equals(NULL)) {
      return null;
    }

    String[] values = data.split(",");
    Deque<String> dataQueue = new LinkedList<>(Arrays.asList(values));

    TreeNode root = treeNode(dataQueue.remove());
    Deque<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode parent = queue.remove();

      parent.left = treeNode(dataQueue.remove());
      if (parent.left != null) {
        queue.add(parent.left);
      }

      parent.right = treeNode(dataQueue.remove());
      if (parent.right != null) {
        queue.add(parent.right);
      }
    }
    return root;
  }

  private TreeNode treeNode(String val) {
    if (val.equals(NULL)) {
      return null;
    }
    return new TreeNode(Integer.parseInt(val));
  }

  public static void main(String[] args) {
    p297_SerializeDeserializeBinaryTree solution = new p297_SerializeDeserializeBinaryTree();
    TreeNode root = buildTree();
    System.out.println("serialize: " + solution.serialize(root));
    System.out.println("deserialize: " + solution.deserialize("3,9,8,4,0,1,7,null,null,null,2,5,null,null,null,null,null,null,null"));
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
