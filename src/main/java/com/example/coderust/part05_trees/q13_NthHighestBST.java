package com.example.coderust.part05_trees;

import java.util.*;

public class q13_NthHighestBST {

  public static int visited = 0;

  public static Node nthHighest (Node node, int n) {
    if (node == null) {
      return null;
    }
    Node res = nthHighest(node.right, n);
    if (visited == n) {
      return res;
    }
    visited++;
    if (visited == n) {
      return node;
    }
    return nthHighest(node.left, n);
  }

  public static void main(String[] args) {
    Node root = new TreeBuilder().root(100)
        .left(50)
          .leftLeave(25)
          .rightLeave(75)
        .parent()
        .right(200)
          .leftLeave(125)
          .rightLeave(350)
        .build();
    System.out.println("nthHighest(root, 3rd) = " + nthHighest(root, 3) + " # Expected: [125]" );
  }
}
