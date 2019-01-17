package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;

public class p426_ConvertBSTtoLinkList_V2 {
  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return null;
    }

    Node left = treeToDoublyList(root.left);
    Node right = treeToDoublyList(root.right);

    root.left = root;
    root.right = root;

    return connect(connect(left, root), right);
  }

  public Node connect(Node head1, Node head2) {
    if (head1 == null) {
      return head2;
    }
    if (head2 == null) {
      return head1;
    }

    Node tail1 = head1.left;
    Node tail2 = head2.left;

    // Connect old tail with 2nd head
    tail1.right = head2;
    head2.left = tail1;

    // Connect 1st head with new tail
    head1.left = tail2;
    tail2.right = head1;

    return head1;
  }
}
