package com.example.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class p273_IntegerToEnglish {

  private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  private final String[] LESS_THAN_100 = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
  private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
  private final String HUNDRED = "Hundred";
  private final String ZERO = "Zero";

  public String numberToWords(int num) {
    if (num == 0) return ZERO;

    Deque<String> words = new LinkedList<>();
    int suffix = 0;

    while (num > 0) {
      if (num % 1000 != 0) {
        // Parse integer starting from low to high order
        words.push(wordsHelper(num % 1000) + THOUSANDS[suffix]);
      }
      num = num / 1000;
      suffix++;
    }
    // Build string result from high to low
    StringBuilder sb = new StringBuilder();
    while(!words.isEmpty()) {
      sb.append(words.pop()).append(" ");
    }
    // clear blank spaces at the end
    return sb.toString().trim();
  }

  private String wordsHelper(int num) {
    if (num == 0) {
      return "";
    } else if (num < 20) {
      return LESS_THAN_20[num] + " ";
    } else if (num < 100) {
      return LESS_THAN_100[num / 10] + " " + wordsHelper(num % 10);
    } else { // 100 >= num > 1000
      return LESS_THAN_20[num / 100] + " " + HUNDRED + " " + wordsHelper(num % 100);
    }
  }
}
