package com.example.leetcode;

import java.util.*;

public class p22_GenerateParentheses {
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    StringBuilder cur = new StringBuilder();
    return parens(n, n, cur, res);
  }

  public List<String> parens(int open, int closed, StringBuilder cur, List<String> res) {
    // Base case
    if (open == 0 && closed == 0) {
      res.add(cur.toString());
      return res;
    }
    if (open > 0) {
      cur.append("(");
      parens(open - 1, closed, cur, res);
      cur.setLength(cur.length() - 1);
    }
    if (closed > open) {
      cur.append(")");
      parens(open, closed - 1, cur, res);
      cur.setLength(cur.length() - 1);
    }
    return res;
  }
}
