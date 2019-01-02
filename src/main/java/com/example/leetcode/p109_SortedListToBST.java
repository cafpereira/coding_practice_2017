package com.example.leetcode;

import com.example.leetcode.utils.ListNode;
import com.example.leetcode.utils.TreeNode;
import java.util.*;

public class p109_SortedListToBST {
  public TreeNode sortedListToBST(ListNode head) {
    // Corner case
    if (head == null) {
      return null;
    }
    List<Integer> array = new ArrayList<>();
    ListNode cur = head;

    while(cur != null) {
      array.add(cur.val);
      cur = cur.next;
    }
    return sortedArrayToBST(array);
  }

  public TreeNode sortedArrayToBST(List<Integer> array) {
    int n = array.size();
    return bst(0, n - 1, array);
  }

  public TreeNode bst(int start, int end, List<Integer> array) {
    if (start > end) {
      return null;
    }
    int mid = (start + end) / 2;
    TreeNode root = new TreeNode(array.get(mid));
    root.left = bst(start, mid - 1, array);
    root.right = bst(mid + 1, end, array);

    return root;
  }
}
