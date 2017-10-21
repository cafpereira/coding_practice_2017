package com.example.coderust.part01_arrays;

import java.util.*;;

/**
 * Given a large array of integers and a window of size 'w',
 * find the current maximum in the window as the window slides
 * through the entire array.
 */

class MaxQueue {
  private Queue<Integer> queue = new LinkedList<>();
  private Deque<Integer> localMax = new LinkedList<>();

  public void enqueue(Integer val) {
    queue.add(val);
    while (!localMax.isEmpty() && localMax.getLast() < val) {
      localMax.removeLast();
    }
    localMax.addLast(val);
  }

  public Integer dequeue() {
    Integer head = queue.remove();
    if (localMax.getFirst() == head) {
      localMax.removeFirst();
    }
    return head;
  }

  public Integer max() {
    return localMax.getFirst();
  }

  public Integer size() {
    return queue.size();
  }
}

public class q02_MaxSlideWindow {

  public static int[] getMaximumSliding(int[] A, int w) {
    int n = A.length;
    int[] maxSlide = new int[n];
    MaxQueue window = new MaxQueue();

    for (int i = 0; i < n; i++) {
      int val = A[i];
      window.enqueue(val);
      if (window.size() > w) {
        window.dequeue();
      }
      maxSlide[i] = window.max();
    }
    return maxSlide;
  }

  public static void main(String[] args) {
    System.out.println("output: " + Arrays.toString(getMaximumSliding(new int[]{-4, 2, -5, 1, -1, 6}, 3)));
  }
}

