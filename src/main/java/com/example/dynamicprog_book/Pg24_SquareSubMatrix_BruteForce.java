package com.example.dynamicprog_book;

/**
 * Given a 2D boolean array, find the largest square subarray of true values.
 * The return value should be the side length of the largest square subarray.
 */
public class Pg24_SquareSubMatrix_BruteForce {

  // Brute force solution. From each cell
  // find the biggest square submatrix for which
  // it is the upper left-hand corner
  public int squareSubmatrix(boolean[][] arr) {
    int max = 0;
    // Compute for each cell the biggest subarray
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j]) {
          max = Math.max(max, squareSubmatrix(arr, i, j));
        }
      }
    }
    return max;
  }

  // Overloaded recursive function
  private int squareSubmatrix(boolean[][] arr, int i, int j) {
    // Base case at bottom or right of the matrix
    if (i == arr.length || j == arr[0].length) {
      return 0;
    }
    // If the cell is false then it’s not part
    // of a valid submatrix
    if (!arr[i][j]) {
      return 0;
    }
    // Find the size of the right, bottom, and
    // bottom right submatrices and add 1 to the
    // minimum of those 3 to get the result
    return 1 + Math.min(Math.min(
        squareSubmatrix(arr, i + 1, j),
        squareSubmatrix(arr, i, j + 1)),
        squareSubmatrix(arr, i + 1, j + 1));
  }
}
