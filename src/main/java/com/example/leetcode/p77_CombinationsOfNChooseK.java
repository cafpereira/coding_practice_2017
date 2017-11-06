package com.example.leetcode;

import java.util.ArrayList;
import java.util.List;

public class p77_CombinationsOfNChooseK {

  public static List<List<Integer>> combinationsV1(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    combinationHelper(n, k, 1, new ArrayList<>(), res);
    return res;
  }

  public static void combinationHelper(int n, int k, int cur, List<Integer> combination,
                                       List<List<Integer>> res) {
    // Base-case
    if (combination.size() == k) {
      res.add(new ArrayList<>(combination));
      return;
    }

    int remaining = k - combination.size();
    for (int i = cur; i <= n && remaining <= n - i + 1; i++) {
      combination.add(i);
      combinationHelper(n, k, i + 1, combination, res);
      combination.remove(combination.size() - 1); // Backtrack
    }
  }

  public static List<List<Integer>> combinationsV2(int n, int k) {
    List<List<Integer>> combs = new ArrayList<List<Integer>>();
    combine(combs, new ArrayList<Integer>(), 1, n, k);
    return combs;
  }

  public static void combine(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
    if (k == 0) {
      combs.add(new ArrayList<Integer>(comb));
      return;
    }

    int remaining = k - comb.size();
    for (int i = start; i <= n && remaining <= n - i + 1; i++) {
      comb.add(i);
      combine(combs, comb, i + 1, n, k - 1);
      comb.remove(comb.size() - 1);
    }
  }

  public static void main(String[] args) {
    System.out.println("combinationsV1(4, 2) = " + combinationsV1(4, 2));
    System.out.println("combinationsV2(4, 2) = " + combinationsV2(4, 2));
  }
}
