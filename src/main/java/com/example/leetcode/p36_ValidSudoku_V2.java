package com.example.leetcode;

/**
 * This optmization we check duplicates for rows, columns and sub-grids
 * in one single pass.
 */
public class p36_ValidSudoku_V2 {
  public boolean isValidSudoku(char[][] board) {
    int N = 9;
    int M = 3;
    int L = 3;

    // Store frequency counts for rows, cols and boxes
    // in the L dimension (0, 1 and 2 respectively)
    int[][][] freq = new int[L][N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board[i][j] == '.') {
          continue;
        }
        // Convert char to int
        int d = board[i][j] - '0' - 1; // assume value is between 1 and 9

        // Increment and retrieve frequency count for the digit d
        int rowCount = ++freq[0][i][d];
        int colCount = ++freq[1][j][d];

        // This is a variation of the standard conversion that compilers already
        // make to access data in 2D arrays. Internally, a 2D array (NxM) is a
        // linear array of size N * M. A[i][j] == element i * M + j.

        // In this problem we have a total of 9 inner 3x3 boxes inside a outer 9x9 grid:
        // A B C
        // D E F
        // G H I

        // If we divide the coordinates i,j by 3 we can map each "box group"
        // to the correct position in the linear frequency view.
        int k = (i/3) * M + (j/3);
        int boxCount = ++freq[2][k][d];

        if (rowCount > 1 || colCount > 1 || boxCount > 1) {
          return false;
        }
      }
    }
    return true;
  }
}
