package com.example.dynamicprog_book;

/**
 * Given a list of items with values and weights, as well as a max
 * weight, find the maximum value you can generate from items,
 * where the sum of the weights is less than or equal to the max.
 *
 * items = {(w:2, v:6), (w:2, v:10), (w:3, v:12)}
 * max weight = 5
 * knapsack(items, max_weight) = 22
 */
public class Pg30_Knapsack_CarlosAlgo {

  private static int[] values = new int[]{6, 10, 12};
  private static int[] weights = new int[]{2, 2, 3};

  public static void main(String[] args) {
    System.out.println("knapsack(items, max_weight) = " + knapsack(0, 5));
  }

  private static int knapsack(int item, int max_weight) {
    if (item == values.length || max_weight == 0) {
      return 0;
    }

    int v = values[item];
    int w = weights[item];

    int withoutItem = knapsack(item + 1, max_weight);

    int withItem = 0;
    if (max_weight >= w) {
      withItem = knapsack(item + 1, max_weight - w) + v;
    }
    return Math.max(withoutItem, withItem);
  }
}

