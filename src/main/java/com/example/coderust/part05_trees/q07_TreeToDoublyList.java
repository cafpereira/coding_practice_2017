package com.example.coderust.part05_trees;

public class q07_TreeToDoublyList {

  public static Node treeToDoublyList (Node root) {
    // Base-case
    if (root == null) {
      return null;
    }
    Node left = treeToDoublyList(root.left);
    Node right = treeToDoublyList(root.right);

    Node head = new Node(root.val, null);
    head.left = head.right = head;

    Node leftHead = mergeLists(left, head);
    return mergeLists(leftHead, right);
  }

  public static Node mergeLists(Node l1, Node l2) {
    if (l1 == null) {
      return l2;
    }

    if (l2 == null) {
      return l1;
    }

    Node tail1  = l1.left;
    Node tail2  = l2.right;

    tail1.right = l2;
    l2.left = tail1;

    tail2.right = l1;
    l1.left = tail2;

    return l1;
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
    System.out.println("treeToDoublyList(root) = " + treeToDoublyList(root) + " # Expected: 25 <-> 30 <-> 50 <-> 60 <-> 75 <-> 100 <-> 125 <-> 200 <-> 350" );
  }
}
