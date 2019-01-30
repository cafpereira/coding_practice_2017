package com.example.leetcode;

import java.util.*;

public class p282_ExpressionAddOperators_V2 {

  public List<String> addOperators(String num, int target) {
    List<String> res = new ArrayList<>();
    if (num == null || num.isEmpty()) {
      return res;
    }
    backtrack(num, target, 0, "", 0, 0, res);
    return res;
  }

  public void backtrack(String num, int target, int pos, String exp, long eval, long lastOperand, List<String> res) {
    int n = num.length();
    // Base case
    if (pos == n) {
      if (eval == target) {
        res.add(exp);
      }
      return;
    }

    for (int i = pos; i < num.length(); i++) {
      if ( i > pos && num.charAt(pos) == '0') {
        // we can't have numbers with multiple digits started with zero
        break;
      }

      String operand = num.substring(pos, i + 1);
      long operandLong = Long.parseLong(operand);

      if (pos == 0) {
        // if its the first position, just add the digit(s) and backtrack
        backtrack(num, target, i + 1, operand, operandLong, operandLong, res);
      } else {
        // Add
        backtrack(num, target, i + 1, exp + '+' + operand, eval + operandLong, operandLong, res);
        // Subtract
        backtrack(num, target, i + 1, exp + '-' + operand, eval - operandLong, -operandLong, res);
        // Multiply
        // Since we read the expression always from left to right without parentheses, we can evaluate the numeric
        // result of the expression as we go without additional memory (stacks or queues).
        // The trick is to 'undo' the last operation done before we reached the multiplication and track the operand
        // was used on that operation. Then we multiply the current and last operands, add (or subtract) to the
        // previous value (the value we have after the undo).
        long previous = eval - lastOperand;
        long multiply = operandLong * lastOperand;
        long nextEval = previous + multiply;
        backtrack(num, target, i + 1, exp + '*' + operand, nextEval, multiply, res);
      }
    }
  }

  public static void main(String[] args) {
    p282_ExpressionAddOperators_V2 solution = new p282_ExpressionAddOperators_V2();
    System.out.println("addOperators: " + solution.addOperators("105", 5));
  }
}
