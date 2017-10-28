package com.example.coderust.part05_trees;

public class Node {
  Node left;
  Node right;
  Integer val;

  Node parent;

  public Node(Integer val, Node parent) {
    this.val = val;
    this.parent = parent;
  }

  @Override
  public String toString() {
    return "[" + this.val + "]";
  }
}
