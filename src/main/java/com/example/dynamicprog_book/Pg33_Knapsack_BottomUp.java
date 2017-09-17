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
public class Pg33_Knapsack_BottomUp {

  // Iterative bottom up solution.
  public int knapsack(Item[] items, int W) {
    // Initialize cache
    int[][] cache = new int[items.length + 1][W + 1];
    // For each item and weight, compute the max
    // value of the items up to that item that
    // doesn't go over W weight
    for (int i = 1; i <= items.length; i++) {
      for (int j = 0; j <= W; j++) {
        if (items[i - 1].weight > j) {
          cache[i][j] = cache[i - 1][j];
        } else {
          cache[i][j] = Math.max(cache[i - 1][j],
              cache[i - 1][j - items[i - 1].weight] +
                  items[i - 1].value);
        }
      }
    }
    return cache[items.length][W];
  }

  class Item {
    int weight;
    int value;
  }
}
