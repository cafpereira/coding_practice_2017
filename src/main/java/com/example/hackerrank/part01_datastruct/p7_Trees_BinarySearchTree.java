package com.example.hackerrank.part01_datastruct;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-is-binary-search-tree
 * guide: https://www.youtube.com/watch?v=i_Q0v_Ct5lY
 */
class BinarySearchTree {

  /*
  * Hidden stub code will pass a root argument to the function below.
  * Complete the function to solve the challenge.
  * Hint: you may want to write one or more helper functions.
  */
  boolean checkBST(NodeBST root) {
    return checkBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  boolean checkBST(NodeBST node, int left, int right) {
    if (node == null) {
      return true;
    }
    if (node.data > left && node.data < right) {
      return checkBST(node.left, left, node.data) && checkBST(node.right, node.data, right);
    } else {
      return false;
    }
  }
}

class NodeBST {
  int data;
  NodeBST left;
  NodeBST right;
}