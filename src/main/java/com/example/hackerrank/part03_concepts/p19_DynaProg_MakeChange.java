package com.example.hackerrank.part03_concepts;

import java.util.Arrays;
import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-coin-change
 * guide: https://www.youtube.com/watch?v=sn0DWI-JdNA
 */
public class p19_DynaProg_MakeChange {

  /*
    money = 10
    coins = [2, 3, 5, 6]
    ways:
      0 1 2 3 4 5 6 7 8 9 10
    2 1 0 1 0 1 0 1 0 1 0 1
    3 1 0 1 1 1 1 2 1 2 2 2
    5 1 0 1 1 1 2 2 2 3 3 4
    6 1 0 1 1 1 3 3 2 4 4 5
  */
  public static long makeChange(int[] coins, int money) {
    Arrays.sort(coins);
    int c = coins.length;
    long[][] ways = new long[c][money + 1];

    for (int i = 0; i < c; i++) {
      ways[i][0] = 1;
    }

    for (int i = 0; i < c; i++) {
      for (int j = 1; j <= money; j++) {
        long prev = i > 0 ? ways[i-1][j] : 0;
        long cur = j >= coins[i] ? ways[i][j - coins[i]] : 0;
        ways[i][j] = prev + cur;
      }
    }
    return ways[c-1][money];
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int coins[] = new int[m];
    for(int coins_i=0; coins_i < m; coins_i++){
      coins[coins_i] = in.nextInt();
    }
    System.out.println(makeChange(coins, n));
  }
}
