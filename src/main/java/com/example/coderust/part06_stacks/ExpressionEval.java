package com.example.coderust.part06_stacks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

interface Token {
  boolean isOperator();
  Double eval(Double op1, Double op2);
  Double value();
  Operator operator();
}

abstract class Operator implements Token {
  private final Character sym;
  public static final Operator ADD = new Operator('+') {
    @Override
    public Double eval(Double op1, Double op2) {
      return op1 + op2;
    }
  };
  public static final Operator SUBTRACT = new Operator('-') {
    @Override
    public Double eval(Double op1, Double op2) {
      return op2 - op1;
    }
  };
  public static final Operator MULTIPLY = new Operator('*') {
    @Override
    public Double eval(Double op1, Double op2) {
      return op1 * op2;
    }
  };
  public static final Operator DIVIDE = new Operator('/') {
    @Override
    public Double eval(Double op1, Double op2) {
      return op2 / op1;
    }
  };
  public static Map<Character, Operator> operators = new HashMap<Character, Operator>() {{
    put('+', ADD);
    put('-', SUBTRACT);
    put('*', MULTIPLY);
    put('/', DIVIDE);
  }};

  private Operator(char sym) {
    this.sym = sym;
  }

  public static Operator of(char ch) {
    return operators.get(ch);
  }

  @Override
  public boolean isOperator() {
    return true;
  }

  @Override
  public Double value() {
    return null;
  }

  @Override
  public Operator operator() {
    return this;
  }
}

class Operand implements Token {

  final Double val;

  public Operand(String valueStr) {
    this.val = Double.parseDouble(valueStr);
  }

  @Override
  public boolean isOperator() {
    return false;
  }

  @Override
  public Double eval(Double op1, Double op2) {
    return null;
  }

  @Override
  public Double value() {
    return val;
  }

  @Override
  public Operator operator() {
    return null;
  }
}

public class ExpressionEval {

  public static double evaluate(String expr) {
    List<Token> postfix = toPostfix(expr);
    Stack<Double> ops = new Stack<>();

    for (Token token : postfix) {
      if (token.isOperator()) {
        ops.push(token.eval(ops.pop(), ops.pop()));
      } else {
        ops.push(token.value());
      }
    }
    return ops.pop();
  }

  public static List<Token> toPostfix(String expr) {
    List<Token> tokens = tokenize(expr);
    Stack<Token> opers = new Stack<>();
    List<Token> postfix = new ArrayList<>();

    for (Token t : tokens) {
      if (t.isOperator()) {
        while (!opers.isEmpty() && precede(opers.peek().operator(), t.operator())) {
          postfix.add(opers.pop());
        }
        opers.push(t);
      } else {
        postfix.add(t);
      }
    }
    while (!opers.isEmpty()) {
      postfix.add(opers.pop());
    }
    return postfix;
  }


  public static boolean precede(Operator op1, Operator op2) {
    if (op1 == Operator.MULTIPLY || op1 == Operator.DIVIDE) {
      return true;
    }

    if (op1 == Operator.ADD || op1 == Operator.SUBTRACT) {
      if (op2 == Operator.ADD || op2 == Operator.SUBTRACT) {
        return true;
      }
    }
    return false;
  }

  public static List<Token> tokenize(String expr) {
    List<Token> tokens = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (char ch : expr.toCharArray()) {
      if (!isOperator(ch)) {
        sb.append(ch);
      } else {
        tokens.add(new Operand(sb.toString()));
        tokens.add(Operator.of(ch));
        sb.setLength(0);
      }
    }
    tokens.add(new Operand(sb.toString()));

    return tokens;
  }

  public static boolean isOperator(char ch) {
    return Operator.of(ch) != null;
  }

  public static void main(String[] args) {
    expect("2+3", 5.0);
    expect("6+4/2*2", 10.0);
    expect("3+6*5-1/2.5", 32.6);
  }

  private static void expect(String expr, double expected) {
    System.out.println("evaluate(" + expr + ") = " + evaluate(expr) + " # Expected: " + expected);
  }
}
