package com.example.geek4geeks;

/**
 * https://www.geeksforgeeks.org/minimum-number-of-swaps-required-for-arranging-pairs-adjacent-to-each-other/
 *
 * Problem:
 * There are n-pairs and therefore 2n people. everyone has one unique number ranging from 1 to 2n.
 * All these 2n persons are arranged in random fashion in an Array of size 2n.
 * We are also given who is partner of whom. Find the minimum number of swaps required to arrange these pairs such that all pairs become adjacent to each other.
 */
public class MinSwapsMoviePartners {
  public static int minSwaps(int[] A, Pair[] pairs) {
    int n = A.length;
    int[] pairMap = new int[n];
    for (Pair pair : pairs) {
      pairMap[pair.p1] = pair.p2;
      pairMap[pair.p2] = pair.p1;
    }
    int[] seatMap = new int[n];
    for (int i = 0; i < n; i++) {
      seatMap[A[i]] = i;
    }
//    return minSwapsRec(A, 0, seatMap, pairMap);
    return minSwapsGreedy(A, seatMap, pairMap);
  }

  /**
   * Greedily picks the next valid pair on each iteration
   */
  public static int minSwapsGreedy(int[] A, int[] seatMap, int[] pairMap) {
    int n = A.length;
    int i = 0;
    int swaps = 0;
    while (i < n) {
      int p1 = A[i];
      int p2 = A[i + 1];
      // Check is P1 and P2 are a valid pair
      if (pairMap[p1] != p2) {
        // swap P2 with P1’s partner
        int partner = pairMap[p1];
        swap(A, p2, partner, seatMap);
        swaps++;
      }
      // pair is valid, move ahead two seats
      i += 2;
    }
    return swaps;
  }

  /**
   * Search every possible valid pair of swaps
   * Time Complexity O(2^N)
   */
  public static int minSwapsRec(int[] A, int i, int[] seatMap, int[] pairMap) {
    int n = A.length;
    // Base-case
    if (i == n) {
      // all pairs ‘paired’, no more swaps
      return 0;
    }
    int p1 = A[i];
    int p2 = A[i + 1];
    // If current pair is already valid, just move ahead two seats
    if (pairMap[p1] == p2) {
      return minSwapsRec(A, i + 2, seatMap, pairMap);
    }
    // P1 and P2 are not a pair, swap P2 with P1’s partner
    int partner = pairMap[p1];
    swap(A, p2, partner, seatMap);
    int count1 = minSwapsRec(A, i + 2, seatMap, pairMap);
    // Backtrack
    swap(A, partner, p2, seatMap);

    // Swap P1 with P2’s partner
    partner = pairMap[p2];
    swap(A, p1, partner, seatMap);
    int count2 = minSwapsRec(A, i + 2, seatMap, pairMap);
    // Backtrack
    swap(A, partner, p1, seatMap);
    return 1 + Math.min(count1, count2);
  }

  public static void swap(int[] A, int p1, int p2, int[] seatMap) {
    int seat1 = seatMap[p1];
    int seat2 = seatMap[p2];
    A[seat1] = p2;
    A[seat2] = p1;

    seatMap[p1] = seat2;
    seatMap[p2] = seat1;
  }

  public static void main(String[] args) {
    int[] A = new int[]{2, 4, 5, 3, 0, 1};
    Pair[] pairs = new Pair[]{new Pair(0, 2), new Pair(1, 5), new Pair(3, 4)};
    System.out.println("minSwaps(A, pairs) = " + minSwaps(A, pairs) + " # Expected: 2");

    A = new int[]{0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
    pairs = new Pair[]{
        new Pair(1, 0), new Pair(3, 2),
        new Pair(5, 4), new Pair(7, 6),
        new Pair(9, 8)};
    System.out.println("minSwaps(A, pairs) = " + minSwaps(A, pairs) + " # Expected: 4");
  }

  public static class Pair {
    int p1;
    int p2;

    public Pair(int p1, int p2) {
      this.p1 = p1;
      this.p2 = p2;
    }
  }

}
