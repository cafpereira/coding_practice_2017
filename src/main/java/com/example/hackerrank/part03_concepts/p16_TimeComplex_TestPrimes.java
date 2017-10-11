package com.example.hackerrank.part03_concepts;

import java.util.*;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-big-o
 * guide: https://www.youtube.com/watch?v=v4cd1O4zkGw
 */
class p16_TimeComplex_TestPrimes {

  public static boolean isPrime(int n) {
    if (n == 1) {
      return false;
    }
    int sqrt = (int) Math.sqrt(n);
    for (int i = 2; i <= sqrt; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int p = in.nextInt();

    for(int a0 = 0; a0 < p; a0++) {
      int n = in.nextInt();
      System.out.println(isPrime(n) ? "Prime" : "Not prime");
    }
  }

}
