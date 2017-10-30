package com.example.codersnacks;

import java.util.*;

class Mastermind {

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
      }
      else {
        ansCount.put(answer[i], ansCount.containsKey(answer[i]) ? ansCount.get(answer[i]) + 1 : 1);
        gssCount.put(guess[i], gssCount.containsKey(guess[i]) ? gssCount.get(guess[i]) + 1 : 1);
      }
    }

    // Only iterate over guess colors since there is no need
    // to count near misses for colors that were not used in
    // the player's guess
    for (Character color : gssCount.keySet()) {
      if (ansCount.containsKey(color)) {
        // Near miss count is equal the lesser between guess
        // count and solution count (not including hits).
        near += Math.min(ansCount.get(color), gssCount.get(color));
      }
    }
    return new int[]{hits, near};
  }

  public static void main(String[] args) {
    expect("OOBB", "OBYY", new int[]{1, 1});
    expect("YYYY", "YRRR", new int[]{1, 0});
    expect("YYYR", "OOOY", new int[]{0, 1});
    expect("RYBO", "RYBO", new int[]{4, 0});
    expect("RYBO", "OBYR", new int[]{0, 4});
    expect("OYOY", "YOOY", new int[]{2, 2});
  }

  public static void expect(String answer, String guess, int[] expected){
    System.out.println("checkGuess("+ answer+", "+ guess +") = " +
        Arrays.toString(checkGuess(answer, guess)) + " # Expected: " + Arrays.toString(expected));
  }
}
