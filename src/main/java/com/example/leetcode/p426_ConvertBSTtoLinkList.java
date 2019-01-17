package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;


public class p426_ConvertBSTtoLinkList {
  public Node treeToDoublyList(Node root) {
    if (root == null) {
      return null;
    }

    Node l = root.left;
    Node r = root.right;

    Node leftHead = null;
    Node leftTail = null;
    if (l != null) {
      leftHead = treeToDoublyList(l);
      leftTail = leftHead.left;

      // connect middle (root)
      leftTail.right = root;
      root.left = leftTail;
    } else {
      leftHead = root;
    }

    Node rightHead = null;
    Node rightTail = null;
    if (r != null) {
      rightHead = treeToDoublyList(r);
      rightTail = rightHead.left;

      // connect middle (root)
      root.right = rightHead;
      rightHead.left = root;
    } else {
      rightTail = root;
    }

    // connect left head and right tail
    leftHead.left = rightTail;
    rightTail.right = leftHead;

    // return left head
    return leftHead;
  }

  public static void main(String[] args) {
    p426_ConvertBSTtoLinkList solution = new p426_ConvertBSTtoLinkList();

    //       2
    //     /  \
    //    1     null
    Node a = new Node(1);
    Node b = new Node(2);
    b.left = a;

    List<Node> res = new ArrayList<>();
    Node cur = solution.treeToDoublyList(b);
    for (int i = 0; i < 2; i++) {
      res.add(cur);
      cur = cur.right;
    }

    System.out.println("treeToDoublyList: " + res);
  }
}
