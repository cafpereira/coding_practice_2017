package com.example.leetcode;

import java.io.*;
import java.util.*;

public class p32_LongestValidParenSubstring {

  public static int longestValidParens(String s) {
    Stack<Integer> stack = new Stack<>();
    int max = 0;
    stack.push(-1); // add guardian index case we match the
    // first char of the string at position 0
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(i);
      } else { // s.charAt(i) == ‘)’
        // Pop any index we have in the top of the stack
        stack.pop();
        if (stack.isEmpty()) {
          stack.push(i); // make sure there is always at least one
                         // index in the stack so we can measure
                         // the length of future matches
        }
        // Top of the stack will have either the last position before
        // the first matching ‘(’, or it will be the same index i if
        // current char is an unbalanced right paren
        int len = i - stack.peek();
        max = Math.max(max, len);
      }
    }
    return max;
  }


  public static void main(String[] args) {
    System.out.println("longestValidParens('(()') = " + longestValidParens("(()") + " # Expected: 2");
    System.out.println("longestValidParens(')()())') = " + longestValidParens(")()())") + " # Expected: 4");
    System.out.println("longestValidParens('))))') = " + longestValidParens("))))") + " # Expected: 0");
  }
}
