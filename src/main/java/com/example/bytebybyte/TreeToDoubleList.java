package com.example.bytebybyte;

/**
 *
 */
public class TreeToDoubleList {
  /*
   Example:
    A: <-1<->2<->3->
    B: <-4<->5<->6->
    Re: <-1<->2<->3<->4<->5<->6->
   */
  private Node concatenate(Node a, Node b) {
    // If either list is null, return the other
    if (a == null) {
      return b;
    }
    if (b == null) {
      return a;
    }

    // Join both circular lists
    Node aEnd = a.left;
    Node bEnd = b.left;
    a.left = bEnd;
    bEnd.right = a;

    return a;
  }

  public Node treeToList(Node n) {
    if (n == null) {
      return n;
    }
    Node leftList = treeToList(n.left);
    Node rightList = treeToList(n.right);

    // make sure that node N is a circular list itself with size 1
    n.left = n;
    n.right = n;

    // Concatenate left + n + right
    return concatenate(concatenate(leftList, n), rightList);
  }

  private class Node {
    int value;
    Node left;
    Node right;
  }
}
