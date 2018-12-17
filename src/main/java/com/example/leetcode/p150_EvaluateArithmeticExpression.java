package com.example.leetcode;

import java.util.*;
import java.util.regex.*;

public class p150_EvaluateArithmeticExpression {
  public int evalRPN(String[] tokens) {
    Deque<Integer> operands = new LinkedList<>();
    Pattern digit = Pattern.compile("-?\\d+");

    for (String token : tokens) {
      if (digit.matcher(token).matches()) {
        operands.push(Integer.parseInt(token));
      }
      else {
        Integer op2 = operands.pop();
        Integer op1 = operands.pop();
        Integer res = eval(op1, op2, token);
        operands.push(res);
      }
    }
    return operands.pop();
  }

  public int eval(int op1, int op2, String operator) {
    int res = 0;
    switch (operator) {
      case "+":
        res = op1 + op2;
        break;
      case "-":
        res = op1 - op2;
        break;
      case "*":
        res = op1 * op2;
        break;
      case "/":
        res = op1 / op2;
        break;
    }
    return res;
  }
}
