package com.example.leetcode;

/**
 * WARNING: this solution is not AC because array b can have a very large number that will
 * overflow the 'arrayToInt(b)' function. The next version V2 will fix this issue.
 */
public class p372_SuperPow {

  public int superPow(int a, int[] b) {
    int d = 1337;
    // Input 'a' can be very large (integer max value == 2^31 - 1)
    // Use mod prevent overflow when we multiply 'a' by other numbers.
    a = a % d;
    return pow(a, arrayToInt(b), d);
  }

  /**
   * Returns a^b % d
   */
  public int pow(int a, int b, int d) {
    // Base case
    if (b == 0) {
      return 1 % d;
    }
    // If B == 1, half will be (1 % d) and res will be (1 * 1) % d
    int half = pow(a, b / 2, d);
    int res = (half * half) % d; // this product can be higher than d
                                 // take the module of the result

    if (b % 2 == 1) {
      // B == 1, res is (a % d)
      res = (res * a) % d; // This can also exceed d, use the module here too.
    }
    return res;
  }

  public int arrayToInt(int[] b) {
    int res = 0;
    for (int v : b) {
      res = res * 10 + v;
    }
    return res;
  }

  public static void main(String[] args) {
    p372_SuperPow solution = new p372_SuperPow();
    System.out.println("res: " + solution.superPow(2, new int[]{3}));
    System.out.println("res: " + solution.superPow(2147483647, new int[]{2, 0, 0}));
  }
}
