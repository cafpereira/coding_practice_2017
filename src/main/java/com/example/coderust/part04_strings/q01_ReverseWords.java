package com.example.coderust.part04_strings;

public class q01_ReverseWords {
  public static String reverseSentence(String input) {
    char[] sentence = input.toCharArray();
    int n = sentence.length;

    reverse(0, n - 1, sentence);

    int start = 0;
    int end = 0;
    while (true) {
      while (start < n && sentence[start] == ' ') {
        start++;
      }
      if (start == n) {
        break;
      }
      end = start;
      while (end < n && sentence[end] != ' ') {
        end++;
      }
      reverse(start, end - 1, sentence);
      start = end;
    }
    return new String(sentence);
  }

  private static void reverse(int start, int end, char[] sentence) {
    while (start < end) {
      char tmp = sentence[start];
      sentence[start] = sentence[end];
      sentence[end] = tmp;
      start++;
      end--;
    }
  }

  public static void main(String[] args) {
    expect("Hello World", "World Hello");
    expect("  my    poney", "poney    my  ");
  }

  private static void expect(String input, String expected) {
    System.out.println("reverseSentence('"+input+"') = '" + reverseSentence(input) + "' # Expected: '" + expected + "'");
  }
}
