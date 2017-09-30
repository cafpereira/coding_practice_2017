package com.example.pramp_questions;

/**
 * Given a 2D matrix M, filled with either 0s or 1s, count the number of islands of 1s in M.
 * An island is a group of adjacent values that are all 1s.
 *
 * Every cell in M can be adjacent to the 4 cells that are next to it on the same row or column.
 *
 * Example: the matrix below has 6 islands:
 * 0  1  0  1  0
 * 0  0  1  1  1
 * 1  0  0  1  0
 * 0  1  1  0  0
 * 1  0  1  0  1
 */
public class IslandCount {

  public static int[][] small = {
      {0,0,0},
      {1,1,0},
      {1,1,0},
      {0,0,1},
      {0,0,1},
      {1,1,0}
  };

  public static int countIslands(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    boolean[][] visited = new boolean[m][n];

    int count = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 1 && !visited[i][j]) {
          count++;
          traverse(matrix, visited, i, j);
        }
      }
    }
    return count;
  }

  public static void traverse(int[][] matrix, boolean[][] visited, int i, int j) {
    int m = matrix.length;
    int n = matrix[0].length;

    if (i < 0 || i >= m || j < 0 || j >= n) {
      return;
    }

    if (matrix[i][j] == 0 || visited[i][j]) {
      return;
    }

    visited[i][j] = true;

    // directions:    down,  right,   up,    left
    int[][] shifts = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    for (int[] shift : shifts) {
      traverse(matrix, visited, i + shift[0], j + shift[1]);
    }
  }

  public static void main(String[] args) {
    System.out.println("countIslands() = " + countIslands(small));
  }
}
