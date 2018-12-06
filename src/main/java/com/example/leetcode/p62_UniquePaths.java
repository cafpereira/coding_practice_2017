package com.example.leetcode;

public class p62_UniquePaths {
  public int uniquePaths(int m, int n) {
    int[][] grid = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        // Base case
        if (i == 0 || j == 0) {
          grid[i][j] = 1;
        } else {
          int up = grid[i - 1][j];
          int left = grid[i][j - 1];
          grid[i][j] = up + left;
        }
      }
    }
    return grid[n - 1][m - 1];
  }
}
