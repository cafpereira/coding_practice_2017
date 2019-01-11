package com.example.leetcode;

public class p304_RangeSum2D {
  int[][] rangeSum;

  /**
   * Create a range look up table, which will hold the sum of all
   * elements between (0,0) and (row, col)
   */
  public p304_RangeSum2D(int[][] matrix) {
    int n = matrix.length;
    int m = 0;

    if (n > 0) {
      m = matrix[0].length;
    }

    // Add one extra line for both column and row so we can easily
    // access the area sum without validating if previous row (or column)
    // is smaller than 0
    this.rangeSum = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
      int sum = 0;
      for (int j = 1; j <= m; j++) {
        rangeSum[i][j] = sum + matrix[i - 1][j - 1];
        sum = rangeSum[i][j];
      }
    }

    for (int j = 1; j <= m; j++) {
      int sum = 0;
      for (int i = 1; i <= n; i++) {
        rangeSum[i][j] = sum + rangeSum[i][j];
        sum = rangeSum[i][j];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {

    // Because the matrix sum values for (row, col) are stored in the
    // position (row + 1, col + 1) of the rangeSum table, we need to
    // adjust the indexes accordingly
    int A = rangeSum[row1][col2 + 1];
    int B = rangeSum[row2 + 1][col1];
    int C = rangeSum[row1][col1];

    // Calcute the sum of the areas above, left and diagonal
    int R = A + B - C;

    // response is the total area sum minus the corners
    return rangeSum[row2 + 1][col2 + 1] - R;
  }

  public static void main(String[] args) {
    int [][] matrix  = {
        {3, 0, 1, 4, 2},
        {5, 6, 3, 2, 1},
        {1, 2, 0, 1, 5},
        {4, 1, 0, 1, 7},
        {1, 0, 3, 0, 5}
    };
    p304_RangeSum2D s = new p304_RangeSum2D(matrix);
    System.out.println("sumRegion:" + s.sumRegion(2, 1, 4, 3));
  }
}
