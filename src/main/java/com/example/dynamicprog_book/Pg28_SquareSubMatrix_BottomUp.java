package com.example.dynamicprog_book;

/**
 * Given a 2D boolean array, find the largest square subarray of true values.
 * The return value should be the side length of the largest square subarray.
 */
public class Pg28_SquareSubMatrix_BottomUp {
  // Bottom up solution. Start from the
  // upper left corner and compute each larger
  // submatrix
  public int squareSubmatrix(boolean[][] arr) {
    int max = 0;
    // Initialize cache
    int[][] cache = new int[arr.length][arr[0].length];
    // Iterate over matrix to compute each value
    for (int i = 0; i < cache.length; i++) {
      for (int j = 0; j < cache[0].length; j++) {
        // If we’re in the first row/column then
        // the value is just 1 if that cell is
        // true and 0 otherwise. In other rows and
        // columns need to look up and to the left
        if (i == 0 || j == 0) {
          cache[i][j] = arr[i][j] ? 1 : 0;
        } else if (arr[i][j]) {
          cache[i][j] =
              Math.min(Math.min(cache[i][j - 1],
                  cache[i - 1][j]),
                  cache[i - 1][j - 1]) + 1;
        }
        if (cache[i][j] > max) {
          max = cache[i][j];
        }
      }
    }
    return max;
  }
}
