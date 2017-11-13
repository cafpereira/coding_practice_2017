package com.example.coderust.part08_backtracking;

import java.util.*;

public class q2_AllPossibleParens {

  public static void allParens (int n) {
    StringBuilder builder = new StringBuilder();
    allParens(0, 0, builder, n);
  }

  public static void allParens (int open, int closed, StringBuilder builder, int n) {
    // Base-case
    if (open == n && open == closed) {
      System.out.println(builder.toString());
    }

    if (open < n) {
      builder.append("(");
      allParens(open + 1, closed, builder, n);
      builder.setLength(builder.length() - 1); // Backtrack
    }

    if (closed < open) {
      builder.append(")");
      allParens(open, closed + 1, builder, n);
      builder.setLength(builder.length() - 1); // Backtrack
    }
  }


  public static void main(String[] args) {
    allParens(3);
  }
}
