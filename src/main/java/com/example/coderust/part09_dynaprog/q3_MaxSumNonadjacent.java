package com.example.coderust.part09_dynaprog;

public class q3_MaxSumNonadjacent {

  public static int maxSumNonAdj (int[] array) {
    int inclusive = 0;
    int exclusive = 0;

    for (int num : array) {
      int withoutNum = inclusive;
      inclusive = Math.max(inclusive, exclusive + num);
      exclusive = withoutNum;
    }
    return Math.max(inclusive, exclusive);
  }

  public static void main(String[] args) {
    System.out.println("maxSumNonAdj(array) = " + maxSumNonAdj(new int[]{4,1,1,4,2,1}) + " # Expected: " + 9);
    System.out.println("maxSumNonAdj(array) = " + maxSumNonAdj(new int[]{1,6,10,14,-5,-1,2,-1,3}) + " # Expected: " + 25);
  }
}
