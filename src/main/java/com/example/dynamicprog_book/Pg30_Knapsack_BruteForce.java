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
public class Pg30_Knapsack_BruteForce {

  // Naive brute force solution. Recursively
  // include or exclude each item to try every
  // possible combination
  public int knapsack(Item[] items, int W) {
    return knapsack(items, W, 0);
  }

  // Overloaded recursive function
  private int knapsack(Item[] items, int W, int i) {
    // If we've gone through all the items,
    // return
    if (i == items.length) {
      return 0;
    }
    // If the item is too big to fill the
    // remaining space, skip it
    if (W - items[i].weight < 0) {
      return knapsack(items, W, i + 1);
    }
    // Find the maximum of including and not
    // including the current item
    return Math.max(
        knapsack(items, W - items[i].weight, i + 1) + items[i].value,
        knapsack(items, W, i + 1)
    );
  }

  public class Item {
    int weight;
    int value;
  }
}

