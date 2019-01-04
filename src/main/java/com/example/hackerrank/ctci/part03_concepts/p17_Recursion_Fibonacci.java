package com.example.hackerrank.ctci.part03_concepts;

import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-fibonacci-numbers
 * guide: https://www.youtube.com/watch?v=KEEKn7Me-ms
 */
public class p17_Recursion_Fibonacci {

  public static int fibonacci(int n) {
    // Complete the function.
    int n1 = 1;
    int n2 = 0;
    if (n < 2) {
      return n;
    }

    for (int i = 2; i <= n; i++) {
      int fib = n1 + n2;
      n2 = n1;
      n1 = fib;
    }
    return n1;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    scanner.close();
    System.out.println(fibonacci(n));
  }
}
