package com.example.codersnacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class lesson21_ValidateBST {

  public static boolean isBST(Node root) {
    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public static boolean isBST(Node node, int low, int high) {
    if (node == null) {
      return true;
    }

    if (node.value < low || node.value > high) {
      return false;
    }

    return isBST(node.left, low, node.value) &&
        isBST(node.right, node.value, high);
  }

  class Node {
    int value;
    Node left;
    Node right;
  }
}
