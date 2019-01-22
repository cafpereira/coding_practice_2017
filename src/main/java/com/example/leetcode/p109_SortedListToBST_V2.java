package com.example.leetcode;

import com.example.leetcode.utils.*;
import java.util.*;

/**
 * This optmization we dont have to build an array to construct the BST. We traverse the
 * linked list simulating the inorder traversal and create the tree nodes as we go.
 * Time: O(N)
 * Space: O(logN), the BST is always balanced.
 */
public class p109_SortedListToBST_V2 {
  ListNode current;

  public TreeNode sortedListToBST(ListNode head) {
    // Corner case
    if (head == null) {
      return null;
    }

    int n = traverseList(head);
    this.current = head;

    // This solution we simulate an inorder walk using the range
    // of the list (1 to n), then we track the head of the list
    // after the left half is traversed.
    return bst(1, n);
  }

  public TreeNode bst(int start, int end) {
    // Base case
    if (start > end) {
      return null;
    }

    int mid = (start + end) / 2;

    TreeNode left = bst(start, mid - 1);

    // whenever we are done building the left half of the BST,
    // the cur pointer in the linked list will point to the root
    TreeNode root = new TreeNode(current.val);

    // Move current ahead to keep the invariant
    this.current = this.current.next;

    root.left = left;
    root.right = bst(mid + 1, end);

    return root;
  }

  public int traverseList(ListNode head) {
    int size = 0;
    while(head != null) {
      size++;
      head = head.next;
    }
    return size;
  }

  public static void main(String[] args) {
    p109_SortedListToBST_V2 solution = new p109_SortedListToBST_V2();
    solution.sortedListToBST(listNode(Arrays.asList(-10,-3,0,5,9)));
  }

  private static ListNode listNode(List<Integer> list) {
    ListNode head = new ListNode(0);
    ListNode prev = head;

    for (int v : list) {
      ListNode node = new ListNode(v);
      prev.next = node;
      prev = node;
    }
    return head.next;
  }
}
