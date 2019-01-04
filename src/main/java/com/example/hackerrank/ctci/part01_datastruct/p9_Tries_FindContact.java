package com.example.hackerrank.ctci.part01_datastruct;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * problem: https://www.hackerrank.com/challenges/ctci-contacts
 * guide: https://www.youtube.com/watch?v=vlYZb68kAY0
 */
class FindContact {

  public static Node root = new Node();

  public static void add(Node node, String name) {
    if (name.equals("")) {
      return;
    }

    Node next = node.getOrCreate(name.charAt(0));
    next.value += 1;
    add(next, name.substring(1));
  }

  public static int find(Node node, String name) {
    if (name.equals("")) {
      return node.value;
    }

    Node next = node.adj.get(name.charAt(0));
    return next == null ? 0 : find(next, name.substring(1));
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for(int a0 = 0; a0 < n; a0++){
      String op = in.next();
      String contact = in.next();
      if ("add".equals(op)) {
        add(root, contact);
      } else if ("find".equals(op)) {
        System.out.println(find(root, contact));
      }
    }
  }

  static class Node {
    int value = 0;
    Map<Character, Node> adj = new HashMap<>();

    public Node getOrCreate(Character c) {
      if (!adj.containsKey(c)) {
        adj.put(c, new Node());
      }
      return adj.get(c);
    }
  }
}

