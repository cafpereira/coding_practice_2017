package com.example.coderust.part05_trees;

import java.util.*;

public class q03_IterativeInorder {

  public static void iterativeInOrder (Node root) {
    Stack<Node> stack = new Stack<>();
    Node cur = root;

    while (!stack.isEmpty() || cur != null) {
      if (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      else {
        Node node = stack.pop();
        System.out.print(node.val + ", ");
        cur = node.right;
      }
    }
  }
}
