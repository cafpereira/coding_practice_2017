package com.example.leetcode;

import java.util.*;

public class p36_ValidSudoku {
  public boolean isValidSudoku(char[][] board) {
    int N = 9;

    // Search duplicate by row and by column
    for (int k = 0; k < N; k++) {
      // Scan rows
      if (hasDuplicate(k, 0, k, N - 1, board)) {
        return false;
      }

      // Scan columns
      if (hasDuplicate(0, k, N - 1, k, board)) {
        return false;
      }
    }

    // Search sub-grid
    int GRID = 3;

    for (int y = 0; y < N; y += GRID) {
      for (int x = 0; x < N; x += GRID) {
        if (hasDuplicate(y, x, y + GRID - 1, x + GRID - 1, board)) {
          return false;
        }
      }
    }

    return true;
  }

  public boolean hasDuplicate(int y1, int x1, int y2, int x2, char[][] board) {
    Set<Character> set = new HashSet<>();
    for (int i = y1; i <= y2; i++) {
      for (int j = x1; j <= x2; j++) {
        char c = board[i][j];
        if (c != '.' && set.contains(c)) {
          return true;
        }
        set.add(c);
      }
    }
    return false;
  }
}
