package com.example.coderust.part03_math;

import static com.example.coderust.part03_math.State.*;

enum State {
  START,
  INTEGER,
  DECIMAL,
  ERR;
}

public class q10_IsNumberValid {

  public static boolean isValid (String num) {
    State cur = START;
    int i = 0;
    if (num.charAt(0) == '+' || num.charAt(0) == '-') {
      i++;
    }
    while (i < num.length()) {
      cur = next(num, cur, i++);
      if (cur == ERR) {
        return false;
      }
    }
    return true;
  }

  public static State next (String str, State state, int i) {
    char c = str.charAt(i);
    switch (state) {
      case START:
      case INTEGER:
        if (c >= '0' && c <= '9') {
          return INTEGER;
        }
        if (c == '.') {
          return DECIMAL;
        }
        break;
      case DECIMAL:
        if (c >= '0' && c <= '9') {
          return DECIMAL;
        }
        break;
    }
    return ERR;
  }
  public static void main(String[] args) {
    expect("4.325", true);
    expect("1.1.1", false);
    expect("222", true);
    expect("22.", true);
    expect("22.22.", false);
  }

  private static void expect(String num, boolean expected) {
    System.out.println("isValid("+ num +") = " + isValid(num) + " # Expected " + expected);
  }
}
