package com.example.coderust.part05_trees;

import java.util.*;

public class q05_LevelOrderTraversal {

  public static void levelOrder(Node root) {
    Queue<Node> level = new ArrayDeque<>();
    level.add(root);

    while (!level.isEmpty()) {
      Queue<Node> nextLevel = new ArrayDeque<>();
      Node n;
      while ((n = level.poll()) != null) {
        System.out.print(n.val + ", ");
        if (n.left != null) {
          nextLevel.add(n.left);
        }
        if (n.right != null) {
          nextLevel.add(n.right);
        }
      }
      System.out.print("\n");
      level = nextLevel;
    }
  }

  public static void main(String[] args) {
    Node root = new TreeBuilder().root(100)
        .left(50)
          .leftLeave(25)
          .rightLeave(75)
        .parent()
        .right(200)
          .rightLeave(350)
        .build();
    System.out.println("levelOrder(root):");
    levelOrder(root);
  }
}
