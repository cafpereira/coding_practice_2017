package com.example.hackerrank.ctci.part03_concepts;

import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-recursive-staircase
 * guide: https://www.youtube.com/watch?v=eREiwuvzaUM
 */
public class p18_Recursion_ClimbStairs {

  public static final int NUM_JUMPS = 3;

  public static int climbWays(int n) {
    int[] ways = new int[n + 1];
    ways[0] = 1;

    for (int i = 1; i <= n; i++) {
      for (int s = 1; s <= NUM_JUMPS; s++) {
        int prev = i - s;
        if (prev >= 0) {
          ways[i] += ways[prev];
        }
      }
    }
    return ways[n];
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int s = in.nextInt();
    for(int a0 = 0; a0 < s; a0++){
      int n = in.nextInt();
      System.out.println(climbWays(n));
    }
  }
}
