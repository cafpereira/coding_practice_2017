package com.example.hackerrank.part01_datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-ransom-note
 * guide: https://www.youtube.com/watch?v=1uIwiIjw1fw
 */
class RansomNote {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int m = in.nextInt();
    int n = in.nextInt();
    String magazine[] = new String[m];
    for (int magazine_i = 0; magazine_i < m; magazine_i++) {
      magazine[magazine_i] = in.next();
    }
    String ransom[] = new String[n];
    for (int ransom_i = 0; ransom_i < n; ransom_i++) {
      ransom[ransom_i] = in.next();
    }

    String res = canCreateNote(magazine, ransom) ? "Yes" : "No";
    System.out.println(res);
  }

  public static boolean canCreateNote(String[] magazine, String[] ransom) {
    if (ransom.length < 1) {
      return true;
    }

    if (magazine.length < ransom.length) {
      return false;
    }

    Map<String, Integer> wordCount = new HashMap<>();
    for (String w : ransom) {
      String word = w.toLowerCase();
      Integer count = wordCount.get(word);
      if (count == null) {
        count = 0;
      }
      wordCount.put(word, ++count);
    }

    for (String m : magazine) {
      String mag = m.toLowerCase();
      Integer count = wordCount.get(mag);
      if (count != null) {
        count--;
        if (count == 0) {
          wordCount.remove(mag);
          if (wordCount.isEmpty()) {
            break;
          }
        } else {
          wordCount.put(mag, count);
        }
      }
    }
    return wordCount.isEmpty();
  }
}
