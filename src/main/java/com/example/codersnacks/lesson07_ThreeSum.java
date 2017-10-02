package com.example.codersnacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ThreeSum {
  public static List<Integer> findThreeSum(List<Integer> numbers, int target) {
    Collections.sort(numbers);
    int n = numbers.size();

    for (int i = 0; i < n - 2; i++) {
      int partial_target = target - numbers.get(i);
      int j = i + 1;
      int k = n - 1;
      while (j < k) {
        int partial_sum = numbers.get(j) + numbers.get(k);
        if (partial_sum == partial_target) {
          return Arrays.asList(numbers.get(i), numbers.get(j), numbers.get(k));
        } else if (partial_sum < partial_target) {
          j++;
        } else  { // partial_sum > partial_target
          k--;
        }
      }
    }
    return null;
  }

  public static void main(String[] args){
    expect(Arrays.asList(3, 7, 10, 11, 19), 18, null);
    expect(Arrays.asList(4, 5, 6), 12, null);
    expect(Arrays.asList(4, 10, 4, 15, 4), 12, Arrays.asList(4, 4, 4));
  }

  public static void expect(List<Integer> numbers, int target, List<Integer> expected){
    System.out.println("findThreeSum("+ numbers+", "+ target +") = " + findThreeSum(numbers, target) + " # Expected: " + expected);
  }
}