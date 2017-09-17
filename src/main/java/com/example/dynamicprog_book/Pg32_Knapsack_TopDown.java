package com.example.dynamicprog_book;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of items with values and weights, as well as a max
 * weight, find the maximum value you can generate from items,
 * where the sum of the weights is less than or equal to the max.
 *
 * items = {(w:2, v:6), (w:2, v:10), (w:3, v:12)}
 * max weight = 5
 * knapsack(items, max_weight) = 22
 */
public class Pg32_Knapsack_TopDown {

  // Top down dynamic programming solution.
  // Cache values in a HashMap - the cache may
  // be sparse
  public int knapsack(Item[] items, int W) {
    // Map: i -> W -> value
    Map<Integer, Map<Integer, Integer>> cache = new HashMap<>();
    return knapsack(items, W, 0, cache);
  }

  // Overloaded recursive function
  private int knapsack(Item[] items, int W, int
      i, Map<Integer, Map<Integer, Integer>> cache) {
    if (i == items.length) {
      return 0;
    }
    // Check if the value is in the cache
    if (!cache.containsKey(i)) { cache.put(i, new HashMap<>()); }

    Integer cached = cache.get(i).get(W);
    if (cached != null) {
      return cached;
    }

    // Compute the item and add it to the cache
    int toReturn;
    if (W - items[i].weight < 0) {
      toReturn = knapsack(items, W, i + 1, cache);
    } else {
      toReturn = Math.max(
              knapsack(items, W - items[i].weight, i + 1, cache) + items[i].value,
              knapsack(items, W, i + 1, cache)
          );
    }
    cache.get(i).put(W, toReturn);
    return toReturn;
  }

  public class Item {
    int weight;
    int value;
  }
}
