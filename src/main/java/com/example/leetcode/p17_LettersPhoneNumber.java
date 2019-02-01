package com.example.leetcode;

import java.util.*;

public class p17_LettersPhoneNumber {
  public final Map<Character, List<Character>> keys = new HashMap<Character, List<Character>>(){{
    put('2', Arrays.asList('a','b','c'));
    put('3', Arrays.asList('d','e','f'));
    put('4', Arrays.asList('g','h','i'));
    put('5', Arrays.asList('j','k','l'));
    put('6', Arrays.asList('m','n','o'));
    put('7', Arrays.asList('p','q','r','s'));
    put('8', Arrays.asList('t','u','v'));
    put('9', Arrays.asList('w','x','y','z'));
  }};

  public List<String> letterCombinations(String digits) {
    StringBuilder sb = new StringBuilder();
    List<String> res = new ArrayList<>();
    if (digits == null || digits.isEmpty()) {
      return res;
    }
    backtrack(digits, 0, sb, res);
    return res;
  }

  void backtrack(String digits, int i, StringBuilder sb, List<String> res) {
    int n = digits.length();
    // Base case
    if (i == n) {
      res.add(sb.toString());
      return;
    }
    for (char c : keys.get(digits.charAt(i))) {
      sb.append(c);
      backtrack(digits, i + 1, sb, res);
      sb.setLength(sb.length() - 1);
    }
  }
}
