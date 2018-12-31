package com.example.codersnacks;

import java.util.*;

class Mastermind {

  public static int[] checkGuess(String a, String g) {
    char[] secret = a.toCharArray();
    char[] guess = g.toCharArray();
    int hits = 0;
    int near = 0;

    Map<Character, Integer> secretCount = new HashMap<>();
    Map<Character, Integer> guessCount = new HashMap<>();

    for (int i = 0; i < guess.length; i++) {
      if (secret[i] == guess[i]){
        // Count hits
        hits++;
      }
      else {
        // Keep frequency map of non-hit colors for guess and secret words
        secretCount.put(secret[i], secretCount.getOrDefault(secret[i], 0) + 1);
        guessCount.put(guess[i], guessCount.getOrDefault(guess[i], 0) + 1);
      }
    }

    // Lets use the freq maps to calculate the near miss count.

    // Only iterate over guess colors since there are no near misses for colors
    // that were not used in the player's guess
    for (Character color : guessCount.keySet()) {
      if (secretCount.containsKey(color)) {
        // To find near misses we take the less between guess count and secret count.
        // This works because:
        // A) guessCount > secretCount: extra guesses are not counted as near hits.
        // B) secretCount > guessCount: missing guesses are not counted as near hits.
        near += Math.min(secretCount.get(color), guessCount.get(color));
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
