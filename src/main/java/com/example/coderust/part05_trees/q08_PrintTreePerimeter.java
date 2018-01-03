package com.example.coderust.part05_trees;

import java.io.*;
import java.util.*;

public class q08_PrintTreePerimeter {

  public static void printPerimeter (Node root) {
    if (root == null) {
      return;
    }
    print(root);
    printLeftPerimeter(root.left);
    if (root.left != null || root.right != null) {
      // Dont call print leaves if root is also a leaf node
      printLeaves(root);
    }
    printRightPerimeter(root.right);
  }

  public static void print(Node node) {
    System.out.print(node.val + ", ");
  }

  public static void printLeftPerimeter(Node node) {
    while (node != null) {
      Node next = node.left != null ? node.left : node.right;
      if (next != null) {
        print(node);
      }
      node = next;
    }
  }

  public static void printLeaves(Node node) {
    if (node == null) {
      return;
    }
    printLeaves(node.left);
    printLeaves(node.right);
    if (node.left == null && node.right == null) {
      print(node);
    }
  }

  public static void printRightPerimeter(Node node) {
    Stack<Node> stack = new Stack<>();
    while (node != null) {
      Node next = node.right != null ? node.right : node.left;
      if (next != null) {
        stack.push(node);
      }
      node = next;
    }

    while (!stack.isEmpty()) {
      print(stack.pop());
    }
  }

  public static void main(String[] args) {
    Node root = new TreeBuilder().root(1)
        .left(2)
          .left(4)
            .leftLeave(9)
          .parent()
        .right(5)
          .rightLeave(8)
          .parent()
        .parent()
        .right(3)
          .right(7)
            .rightLeave(10)
        .build();
    System.out.println("treeToDoublyList(root) = ");
    printPerimeter(root);
    System.out.println("# Expected: 1, 2, 4, 9, 8, 10, 7, 3" );
  }
}
