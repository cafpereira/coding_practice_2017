package com.example.coderust.part03_math;

import java.util.*;

public class q06_Permute_String {

  public static List<String> permutations(String str) {
    List<String> res = new ArrayList<>();
    char[] permutation = str.toCharArray();
    permutationHelper(permutation, 0, res);
    return res;
  }

  public static void permutationHelper(char[] permutation, int cur, List<String> res) {
    // Base-case
    if (cur == permutation.length) {
      res.add(new String(permutation));
      return;
    }

    for (int i = cur; i < permutation.length; i++) {
      swap(permutation, cur, i);
      permutationHelper(permutation, cur + 1, res);
      // Backtrack
      swap(permutation, i, cur);
    }
  }

  private static void swap(char[] array, int src, int dest) {
    char tmp = array[src];
    array[src] = array[dest];
    array[dest] = tmp;
  }

  public static void main(String[] args) {
    System.out.println("permutations('bad') = " + permutations("bad"));
  }
}
