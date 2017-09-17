package com.example.hackerrank.part01_datastruct;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-balanced-brackets
 * guide: https://www.youtube.com/watch?v=IhJGJG-9Dx8
 */
class BalancedBrackets {
  public static Map<Character, Character> matches = new HashMap<>();

  static {
    matches.put('(', ')');
    matches.put('{', '}');
    matches.put('[', ']');
  }

  public static boolean isBalanced(String expression) {
    Deque<Character> stack = new LinkedList<>();

    for (char c : expression.toCharArray()) {
      if (isOpenExpr(c)) {
        stack.push(c);
      } else {
        if (stack.isEmpty() || !matchTerms(stack.pop(), c)) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  public static boolean matchTerms(Character openTerm, Character closeTerm) {
    return matches.get(openTerm).equals(closeTerm);
  }

  public static boolean isOpenExpr(Character exp) {
    return matches.keySet().contains(exp);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    for (int a0 = 0; a0 < t; a0++) {
      String expression = in.next();
      System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
    }
  }
}