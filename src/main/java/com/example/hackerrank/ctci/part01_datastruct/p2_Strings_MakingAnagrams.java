package com.example.hackerrank.ctci.part01_datastruct;

import java.util.*;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-making-anagrams
 * guide: https://www.youtube.com/watch?v=3MwRGPPB4tw
 */
class MakingAnagrams {
  public static int numberNeeded(String first, String second) {
    Map<Character, Integer> letters = new HashMap<>();
    for (char c : first.toCharArray()) {
      Integer count = letters.get(c);
      if (count == null) {
        letters.put(c, 1);
      } else {
        letters.put(c, ++count);
      }
    }

    int res = 0;

    for (char c : second.toCharArray()) {
      Integer count = letters.get(c);
      if (count == null) {
        res++;
      } else {
        count--;
        if (count == 0) {
          letters.remove(c);
        } else {
          letters.put(c, count);
        }
      }
    }

    for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
      res += entry.getValue();
    }

    return res;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String a = in.next();
    String b = in.next();
    System.out.println(numberNeeded(a, b));
  }
}
