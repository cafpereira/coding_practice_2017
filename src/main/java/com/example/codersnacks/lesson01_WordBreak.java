package com.example.codersnacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Assumptions:
 *  sentence is all lower case a-z;
 *  input fits in memory;
 *  for multiple answers, return any;
 *  for no answer, return empty
 */
class WordBreak {

  static Set<String> dic = new HashSet<>();

  public static List<String> sentenceSplit(String sentence) {
    int n = sentence.length();
    List<String>[] table = new List[n];
    for (int i = 0; i < n; i++) {
      table[i] = new ArrayList<>();
    }
    for (int i = n - 1; i >= 0; i--) {
      String sufix = sentence.substring(i, n);
      if (dic.contains(sufix)) {
        table[i].add(sufix);
      } else {
        // Dont need to go until N, since we already checked the entire prefix
        for (int j = i + 1 ; j < n; j++) {
          String prefix = sentence.substring(i, j);
          if (!table[j].isEmpty() && dic.contains(prefix)) {
            table[i].add(prefix);
            table[i].addAll(table[j]);
            // need only one valid solution
            break;
          }
        }
      }
    }
    return table[0];
  }

  public static void main(String[] args) {
    dic.addAll(Arrays.asList("es", "needles", "and", "sand", "in", "pin", "on"));

    System.out.println("sentenceSplit(\"onpinsandneedles\") = " + sentenceSplit("onpinsandneedles"));
  }
}
