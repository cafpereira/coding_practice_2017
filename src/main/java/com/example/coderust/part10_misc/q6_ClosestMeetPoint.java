package com.example.coderust.part10_misc;

import java.util.*;

class point {
  private int x;
  private int y;

  point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  int getX() {
    return x;
  }

  void setX(int x) {
    this.x = x;
  }

  int getY() {
    return y;
  }

  void setY(int y) {
    this.y = y;
  }

  double calculate_distance(point p) {
    double distance;
    distance = Math.sqrt((p.x - this.x) * (p.x - this.x)
        + (p.y - this.y) * (p.y - this.y));
    return distance;
  }

  double calculate_sum_of_distances(List<point> points) {
    double distance_sum;
    distance_sum = 0;
    for (int i = 0; i < points.size(); i++) {
      distance_sum += this.calculate_distance(points.get(i));
    }
    return distance_sum;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}

public class q6_ClosestMeetPoint {

  /*
    Coderust V2.0 (euclidean dist)
    https://www.educative.io/collection/page/5642554087309312/5679846214598656/120001
   */
  public static point shortest_distance_travelled_2(int m, List<point> points) {
    point min_pt = new point(0, 0);
    double x = 0;
    double y = 0;

    // calculate the centroid
    point centroid = new point(0, 0);
    for (int i = 0; i < points.size(); i++) {
      x += points.get(i).getX();
      y += points.get(i).getY();
    }
    centroid.setX((int) Math.round(x / points.size()));
    centroid.setY((int) Math.round(y / points.size()));

    // initialize the min_pt to centroid
    min_pt.setX(centroid.getX());
    min_pt.setY(centroid.getY());

    double min_distance = min_pt.calculate_sum_of_distances(points);

    // checking points surrounding the potential centroid
    for (int i = min_pt.getX() - 1; i < min_pt.getX() + 2; i++) {
      for (int j = min_pt.getY() - 1; j < min_pt.getY() + 2; j++) {
        if (i < 1 || j > m) {
          continue;
        }
        point pt = new point(i, j);
        double distance = pt.calculate_sum_of_distances(points);
        if (distance < min_distance) {
          min_distance = distance;
          min_pt.setX(pt.getX());
          min_pt.setY(pt.getY());
        }
      }
    }

    return min_pt;
  }

  /*
   Leetcode problem 296 (manhattan dist)
   https://discuss.leetcode.com/topic/27710/14ms-java-solution
  */
  public static point minTotalDistance(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    List<Integer> I = new ArrayList<>(m);
    List<Integer> J = new ArrayList<>(n);

    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        if(grid[i][j] == 1){
          I.add(i);
          J.add(j);
        }
      }
    }
//    return getMin(I) + getMin(J);
    return new point(getMin(I), getMin(J));
  }

  private static int getMin(List<Integer> list) {
    int ret = 0;

    Collections.sort(list);

    int i = 0;
    int j = list.size() - 1;
    while(i < j){
      ret += list.get(j--) - list.get(i++);
    }

//    return ret;
    return list.get(i);
  }

  public static point bestMeetingPoint(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    List<Integer> row = new ArrayList<>();
    List<Integer> col = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          row.add(i);
          col.add(j);
        }
      }
    }
    Collections.sort(row);
    Collections.sort(col);
    return new point(getMedian(row), getMedian(col));
  }

  public static Integer getMedian(List<Integer> sorted) {
    int m = sorted.size() / 2;
    return sorted.get(m);
  }


  public static void main(String[] args) {
    System.out.println("Coderust: " + shortest_distance_travelled_2(5, Arrays.asList(
        new point(0,0),
        new point(0,4),
        new point(2,2))));

    System.out.println("Leetcode: " + minTotalDistance(new int[][]{
        {1, 0, 0, 0, 1},
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    }));

    System.out.println("Carlos algo: " + bestMeetingPoint(new int[][]{
        {1, 0, 0, 0, 1},
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    }));
  }
}
