package com.example.leetcode;

import java.util.*;

public class p282_ExpressionAddOperators {

  public List<String> addOperators(String num, int target) {
    StringBuilder sb = new StringBuilder();
    List<String> res = new ArrayList<>();

    if (num.isEmpty()) {
      return res;
    }
    backtrack(num, target, null, sb, res);
    return res;
  }

  public void backtrack(String num, int target, Value lastVal,
                        StringBuilder sb, List<String> res) {
    // Base case
    if (num.isEmpty()) {
      String exp = sb.toString();
      if (lastVal.value == target) {
        res.add(exp);
      }
      return;
    }

    for (int end = 1; end <= num.length(); end++) {
      String operand = num.substring(0, end);
      long operandLong = Long.parseLong(operand);
      if (operand.length() > 1 && operand.startsWith("0")) {
        continue;
      }
      if (lastVal == null) {
        sb.append(operand);
        Value firstVal = new Value(0, '+', operandLong);
        backtrack(num.substring(end), target, firstVal, sb, res);
        sb.setLength(sb.length() - end);
      } else {
        for (char op : new char[]{'+','-','*'}) {
          sb.append(op);
          sb.append(operand);
          Value nextVal = update(lastVal, op, operandLong);
          backtrack(num.substring(end), target, nextVal, sb, res);
          sb.setLength(sb.length() - end);
          sb.setLength(sb.length() - 1);
        }
      }
    }
  }

  Value update(Value lastVal, char op, long operand) {
    if (op == '*') {
      long multiplier = lastVal.operand * operand;
      return new Value(lastVal.undo(), lastVal.op, multiplier);
    } else {
      return new Value(lastVal.value, op, operand);
    }
  }

  static class Value {
    long value;
    char op;
    long operand;

    Value(long prev, char op, long operand) {
      this.value = op == '+' ? prev + operand : prev - operand;
      this.op = op;
      this.operand = operand;
    }

    long undo() {
      return op == '+' ? value - operand : value + operand;
    }
  }

  public static void main(String[] args) {
    p282_ExpressionAddOperators solution = new p282_ExpressionAddOperators();
    System.out.println("addOperators: " + solution.addOperators("2147483647", 2147483647));
  }
}
