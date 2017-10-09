package com.example.hackerrank.part02_algorithms;

import java.util.*;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid
 * guide: https://www.youtube.com/watch?v=R4Nh-EgWjyQ
 */
class RegionHelper {

  public static int getBiggestRegion(int[][] matrix) {
    int n = matrix.length;
    int m = matrix[0].length;

    int max = 0;
    boolean[][] visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (!visited[i][j]) {
          int area = searchArea(matrix, i, j, visited);
          max = Math.max(max, area);
        }
      }
    }
    return max;
  }

  public static int searchArea(int[][] matrix, int i, int j, boolean[][] visited) {
    if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length
        || visited[i][j]) {
      return 0;
    }

    int totalArea = 0;
    visited[i][j] = true;

    if (matrix[i][j] == 0) {
      return totalArea;
    }

    // Sum the total area on all directions
    for (int row = i - 1; row <= i + 1; row++) {
      for (int col = j - 1; col <= j + 1; col++) {
        if (row == i && col == j) { // Dont search itself
          continue;
        }
        totalArea += searchArea(matrix, row, col, visited);
      }
    }
    return totalArea + 1;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int grid[][] = new int[n][m];
    for(int grid_i=0; grid_i < n; grid_i++){
      for(int grid_j=0; grid_j < m; grid_j++){
        grid[grid_i][grid_j] = in.nextInt();
      }
    }
    System.out.println(getBiggestRegion(grid));
  }
}
