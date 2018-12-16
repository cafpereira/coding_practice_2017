package com.example.leetcode;

public class p92_ReverseLinkedListII {
  public ListNode reverseBetween(ListNode head, int m, int n) {
    // corner cases
    if (head == null || m == n) { return head; }

    ListNode prev, cur;
    int i = 1;

    // Move cur to starting position
    prev = null;
    cur = head;
    while (cur !=null && i < m) {
      prev = cur;
      cur = cur.next;
      i++;
    }

    // Return if no nodes left to reverse
    if (cur == null) {
      return head;
    }

    // Lets partition the input in 3 segments: lo, mid and hi
    ListNode lastLow = prev;
    ListNode firstMid = cur;

    // Reverse the mid segment
    ListNode lastMid = reverse(firstMid, n - m);

    // if low segment is empty, then the new head is the
    // last node from the mid segment (before reverse)
    if (lastLow == null) {
      return lastMid;
    }

    // otherwise connect new mid segment with low and return
    lastLow.next = lastMid;
    return head;
  }

  public ListNode reverse(ListNode head, int count) {
    ListNode prev = head;
    ListNode ahead = head.next;
    int j = 0;

    // Move ahead and reverse list
    while (ahead != null && j < count) {
      ListNode tmp = ahead.next;
      ahead.next = prev;
      prev = ahead;
      ahead = tmp;
      j++;
    }

    // Connect mid segment with remainder hi segment
    head.next = ahead;

    // Return first node from reversed segment
    return prev;
  }
}
