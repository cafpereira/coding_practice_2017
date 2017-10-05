package com.example.codersnacks;

import java.util.*;

class Mastermind {

  public static final char NONE = '\0';

  public static int[] checkGuess(String a, String g) {
    char[] answer = a.toCharArray();
    char[] guess = g.toCharArray();
    int hits = 0;
    int near = 0;

    Map<Character, Integer> ansCount = new HashMap<>();
    Map<Character, Integer> gssCount = new HashMap<>();

    for (int i = 0; i < guess.length; i++) {
      if (answer[i] == guess[i]){
        hits++;
        answer[i] = NONE;
        guess[i] = NONE;
      }
      else {
        ansCount.put(answer[i], ansCount.containsKey(answer[i]) ? ansCount.get(answer[i]) + 1 : 1);
        gssCount.put(guess[i], gssCount.containsKey(guess[i]) ? gssCount.get(guess[i]) + 1 : 1);
      }
    }

    for (Character color : gssCount.keySet()) {
      if (ansCount.containsKey(color)) {
        near += Math.min(ansCount.get(color), gssCount.get(color));
      }
    }

    return new int[]{hits, near};
  }

  public static void main(String[] args) {
    expect("OOBB", "OBYY", new int[]{1, 1});
    expect("YYYY", "YRRR", new int[]{1, 0});
    expect("YYYR", "OOOY", new int[]{0, 1});
  }

  public static void expect(String answer, String guess, int[] expected){
    System.out.println("checkGuess("+ answer+", "+ guess +") = " +
        Arrays.toString(checkGuess(answer, guess)) + " # Expected: " + Arrays.toString(expected));
  }
}
