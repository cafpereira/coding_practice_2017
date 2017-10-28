package com.example.coderust.part05_trees;

public class TreeBuilder {
  Node root;
  Node current;

  public TreeBuilder root(Integer val) {
    root = new Node(val, null);
    current = root;
    return this;
  }

  public TreeBuilder left(Integer val) {
    current.left = new Node(val, current);
    current = current.left;
    return this;
  }

  public TreeBuilder right(Integer val) {
    current.right = new Node(val, current);
    current = current.right;
    return this;
  }

  public TreeBuilder parent() {
    current = current.parent;
    return this;
  }

  public Node build() {
    Node tree = root;
    root = null;
    current = null;
    return tree;
  }
}
