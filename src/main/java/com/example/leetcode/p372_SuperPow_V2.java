package com.example.leetcode;

/**
 * This solution breaks the large exponent array b into smaller recursive calls like this:
 *
 * a^[5,2,3,4] = (a^[5,2,3])^10 * a^4
 * a^[5,2,3] = (a^[5,2])^10 * a^3
 * a^[5,2] = (a^[5])^10 * a^2
 * a^[5] = a^5
 *
 * And then we can reuse the method pow(a, b, d) for single digit exponents.
 */
public class p372_SuperPow_V2 {

  public int superPow(int a, int[] b) {
    int d = 1337;
    // Input 'a' can be very large (integer max value == 2^31 - 1)
    // Use mod prevent overflow when we multiply 'a' by other numbers.
    a = a % d;
    int res = 1;
    for (int dig : b) {
      int cur =  pow(a, dig, d);
      res = (pow(res, 10, d) * cur) % d;
    }
    return res;
  }

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
      // if B == 1, then res will be: (a % d)
      res = (res * a) % d; // This can also exceed d, use module again.
    }
    return res;
  }

  public static void main(String[] args) {
    p372_SuperPow_V2 solution = new p372_SuperPow_V2();
    System.out.println("res: " + solution.superPow(2, new int[]{3}));
    System.out.println("res: " + solution.superPow(2147483647, new int[]{2, 0, 0}));
  }
}
