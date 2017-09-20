package com.example.codersnacks;

import java.util.*;

class AlienAlphabet {

  public static Set<List<Character>> getRelationships(String[] words) {
    Set<List<Character>> rel = new HashSet<>();
    for (int i = 1; i < words.length; i++) {
      int j = 0;
      String w1 = words[i-1];
      String w2 = words[i];
      while (j < w1.length() && j < w2.length()
              && w1.charAt(j) == w2.charAt(j)) {
        j++;
      }
      if (j < w1.length() && j < w2.length()) {
        rel.add(Arrays.asList(w1.charAt(j), w2.charAt(j)));
      }
    }
    return rel;
  }

  public static void main(String[] args) {
    System.out.println("# Expected: (c,a) (b,d) (d,a) (d,c)");
    System.out.println(getRelationships(new String[]{"bcca", "babc", "dadc", "aad", "aac"}));
    System.out.println("");
    System.out.println("# Expected: (a,b)");
    System.out.println(getRelationships(new String[]{"a", "b", "ba", "bb"}));
  }
}
