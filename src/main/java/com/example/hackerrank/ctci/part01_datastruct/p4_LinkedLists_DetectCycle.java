package com.example.hackerrank.ctci.part01_datastruct;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-linked-list-cycle
 * guide: https://www.youtube.com/watch?v=MFOAbpfrJ8g
 */
class DetectCycle {
  boolean hasCycle(Node head) {
    if (head == null || head.next == null) {
      return false;
    }

    Node slow = head;
    Node fast = head;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }
}

class Node {
  int data;
  Node next;
}
