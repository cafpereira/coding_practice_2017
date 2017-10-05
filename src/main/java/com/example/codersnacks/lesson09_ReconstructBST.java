package com.example.codersnacks;

import java.util.*;

class ReconstructBST {

  public static void main(String[] args) {
    Node root = new Node("A",
        new Node("B",
            new Node("D"), null),
        new Node("C",
            new Node("E"),
            new Node("F")));
    List<String> inOrder = inOrder(root, new ArrayList<>());
    List<String> preOrder = preOrder(root, new ArrayList<>());
    Node tree = reconstructBST(inOrder, preOrder);
    System.out.println(tree);
  }

  private static Node reconstructBST(List<String> inOrder, List<String> preOrder) {
    if (inOrder.isEmpty()) {
      return null;
    }
    Node root = new Node(preOrder.get(0));
    int leftSize = inOrder.indexOf(root.value);

    if (inOrder.size() == 1) {
      return root;
    }

    root.left = reconstructBST(copyRange(inOrder, 0, leftSize),
        copyRange(preOrder, 1, leftSize + 1));
    root.right = reconstructBST(copyRange(inOrder, leftSize + 1, inOrder.size()),
        copyRange(preOrder, leftSize + 1, preOrder.size()));

    return root;
  }

  private static List<String> copyRange(List<String> source, int start, int end) {
    List<String> copy = new ArrayList<>();
    for (int i = start; i < end; i++) {
      copy.add(source.get(i));
    }
    return copy;
  }

  private static List<String> preOrder(Node root, List<String> visited) {
    if (root == null) {
      return visited;
    }
    visited.add(root.value);
    preOrder(root.left, visited);
    preOrder(root.right, visited);

    return visited;
  }

  private static List<String> inOrder(Node root, List<String> visited) {
    if (root == null) {
      return visited;
    }
    inOrder(root.left, visited);
    visited.add(root.value);
    inOrder(root.right, visited);

    return visited;
  }
}

class Node {
  String value;
  Node left; Node right;

  public Node(String value) {
    this.value = value;
  }

  public Node(String value, Node left, Node right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }
}
