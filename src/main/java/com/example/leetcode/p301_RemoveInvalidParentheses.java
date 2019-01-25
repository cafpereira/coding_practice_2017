package com.example.leetcode;

import java.util.*;

public class p301_RemoveInvalidParentheses {
  public List<String> removeInvalidParentheses(String s) {
    Set<String> res = new HashSet<>();
    StringBuilder sb = new StringBuilder();

    int n = s.length();
    int missOpen = 0;
    int missClose = 0;

    // The first pass count number of unbalanced open and close brackets
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '(') {
        missOpen++;
      } else if (s.charAt(i) == ')') {
        if (missOpen == 0) {
          missClose++;
        } else {
          missOpen--;
        }
      }
    }
    removeHelper(s, 0, 0,0, missOpen, missClose, sb, res);
    return new ArrayList<>(res);
  }

  public void removeHelper(String s, int start, int openCount, int closeCount, int missOpen, int missClose, StringBuilder sb, Set<String> res) {
    int n = s.length();

    // Base case
    if (start == n) {
      if (missOpen == 0 && missClose == 0) {
        // Backtracking will produce duplicated results,
        // but the Set will store only unique strings.
        res.add(sb.toString());
      }
      return;
    }

    char cur = s.charAt(start);

    // Here we try to remove any char that will leave us one step closer to the final expression balanced
    if (cur == '(' && missOpen > 0) {
      removeHelper(s, start + 1, openCount, closeCount, missOpen - 1, missClose, sb, res);
    } else if (cur == ')' && missClose > 0) {
      removeHelper(s, start + 1, openCount, closeCount, missOpen, missClose - 1, sb, res);
    }

    // Then we move forward the characters ingestion process.
    // Also keep a running count of open and close brackets seen so far.
    sb.append(cur);

    if (Character.isLetterOrDigit(cur)) {
      removeHelper(s, start + 1, openCount, closeCount, missOpen, missClose, sb, res);
    } else if (cur == '(') {
      // recurse and increase open count
      removeHelper(s, start + 1, openCount + 1, closeCount, missOpen, missClose, sb, res);
    } else { // cur == ')'
      // recurse and increase close count if that's valid action, i.e,
      // closing bracket will not unbalance the next expression
      if (openCount > closeCount) {
        removeHelper(s, start + 1, openCount, closeCount + 1, missOpen, missClose, sb, res);
      }
    }

    // delete char for backtracking
    sb.setLength(sb.length() - 1);
  }

  // ( ( ) ) ( )
  // 0 1 2 3 4 5
  // size = 6
  public static void main(String[] args) {
    p301_RemoveInvalidParentheses solution = new p301_RemoveInvalidParentheses();
    System.out.println("removeInvalid:" + solution.removeInvalidParentheses("()())()"));
  }
}
