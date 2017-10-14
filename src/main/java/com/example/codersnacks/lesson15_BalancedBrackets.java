package com.example.codersnacks;

import java.util.*;

class BalancedBrackets {

  public static Map<Character,Character> closeParens = new HashMap<>();

  static {
    closeParens.put(')','(');
    closeParens.put(']','[');
    closeParens.put('}','{');
  }

  public static boolean isBalanced(String text) {
    Stack<Character> stack = new Stack<Character>();
    for (Character c : text.toCharArray()) {
      if (closeParens.values().contains(c)) { // c is an open parenthesis
        stack.push(c);
      }
      else if (stack.isEmpty() || stack.pop() != closeParens.get(c)) {
        // Input text is not balanced
        return false;
      }
    }
    // Check unmatched parens in the stack
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    expect("([]){()}", true);
    expect("[{]}", false);
    expect("[()[]{()()", false);
  }

  public static void expect(String text, boolean expected){
    System.out.println("isBalanced('"+ text + "') = " + isBalanced(text) + " # Expected: " + expected);
  }
}
