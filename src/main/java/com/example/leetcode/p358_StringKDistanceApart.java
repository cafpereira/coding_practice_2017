package com.example.leetcode;

import java.util.*;

public class p358_StringKDistanceApart {
  static class Letter {
    char c;
    int instances;
    Letter(char c, int instances) {
      this.c = c;
      this.instances = instances;
    }
  }
  public String rearrangeString(String s, int k) {
    if (s == null || s.isEmpty()) {
      return s;
    }

    // Max heap used to greedily select the char with
    // most remaining instances
    PriorityQueue<Letter> maxHeap = createMaxHeap(s);

    // Wait queue will block us to re-select any character
    // that has been used less than k positions ago.
    Deque<Letter> waitQueue = new LinkedList<>();

    // Rearrange solution
    StringBuilder sb = new StringBuilder();

    while(!maxHeap.isEmpty()) {
      Letter cur = maxHeap.poll();
      sb.append(cur.c);
      cur.instances--;

      waitQueue.add(cur);   // character with zero instances left should also
                            // go to the queue because we need the history
                            // of recently used characters.

      // initial k-1 characters queue still being filled
      if (waitQueue.size() < k) {
        continue;
      }

      // The character at the head of queue was not used in the last
      // k positions, it can return to the heap if there is still any
      // instances to be added.
      Letter head = waitQueue.remove();
      if (head.instances > 0) {
        maxHeap.add(head);
      }
    }

    // If the wait queue has still a character X with instances not
    // used, then we return no solution.
    // Why? Because we used every avaliable character from the heap
    // and still it was not possible to fill up  k positions before
    // adding X again.
    for (Letter l : waitQueue) {
      if (l.instances > 0) {
        return "";
      }
    }

    return sb.toString();
  }

  private PriorityQueue<Letter> createMaxHeap(String s) {
    Map<Character, Integer> letterCount = buildMap(s.toCharArray());

    PriorityQueue<Letter> maxHeap = new PriorityQueue<>((t1, t2) ->  {
      if (t1.instances != t2.instances) {
        return Integer.compare(t2.instances, t1.instances);
      }
      return Character.compare(t1.c, t2.c);
    });
    for (Map.Entry<Character, Integer> e : letterCount.entrySet()) {
      maxHeap.add(new Letter(e.getKey(), e.getValue()));
    }
    return maxHeap;
  }

  private Map<Character, Integer> buildMap(char[] s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }

  public static void main(String[] args) {
    p358_StringKDistanceApart solution = new p358_StringKDistanceApart();
    System.out.println("rearrangeString = " + solution.rearrangeString("aabbcc", 3));
  }
}
