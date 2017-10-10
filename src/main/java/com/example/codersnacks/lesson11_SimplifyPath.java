package com.example.codersnacks;

import java.util.*;
import java.util.stream.Collectors;

class UnixPath {

  public static String simplify(String path) {
    Deque<String> stack = new LinkedList<>();

    if (path.charAt(0) == '/') {
      stack.push("/");
    }

    for (String p : path.split("/")) {
      if (p.isEmpty() || p.equals(".")) {
        continue;
      }
      if (p.equals("..")) {
        if (!stack.peek().equals("/")) {
          stack.pop();
        }
      } else {
        stack.push(p);
      }
    } // end for-loop

    // Build simplified path and append '/' between directories
    // PS: Java 8 we could do that with stack.stream().collect(Collectors.joining("/"));
    // but unfortunately there is no simple way to obtain a reverse stream iterator.
    Iterator<String> it = stack.descendingIterator();
    StringBuilder builder = new StringBuilder();

    while (it.hasNext()) {
      String p = it.next();
      builder.append(p);
      if (it.hasNext() && !p.equals("/")) {
        // Add path separator
        builder.append("/");
      }
    }
    return builder.toString();
  }


  public static void main(String[] args) {
    expect("/a/./b/../../c/", "/c");
    expect("/../../../usr/bin/", "/usr/bin");
    expect("./sc//./tc/awk/./", "sc/tc/awk");
  }

  public static void expect(String path, String expected){
    System.out.println("simplify('"+ path+ "') = '" + simplify(path) + "' # Expected: '" + expected + "'");
  }
}
