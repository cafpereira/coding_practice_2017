package com.example.hackerrank.practice;

import java.io.*;
import java.util.*;

/**
 * problem: https://www.hackerrank.com/challenges/contacts/problem
 */
public class TrieContacts {

  private static class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    int wordCount = 0;

    void add(String contact) {
      if (contact.equals("")) {
        return;
      }
      TrieNode child = getOrCreate(contact.charAt(0));
      child.incrementCount();
      child.add(contact.substring(1));
    }

    void incrementCount() {
      this.wordCount++;
    }

    int find(String prefix) {
      if (prefix.equals("")) {
        // All letters from prefix search matched,
        // return word count of the last node.
        return this.wordCount;
      }
      TrieNode child = children.get(prefix.charAt(0));
      if (child == null) {
        // Cannot traverse further, which means the prefix
        // does not exist.
        return 0;
      }
      return child.find(prefix.substring(1));
    }

    TrieNode getOrCreate(char c) {
      if (!children.containsKey(c)) {
        children.put(c, new TrieNode());
      }
      return children.get(c);
    }
  }

  static TrieNode trie = new TrieNode();

  /*
   * Complete the contacts function below.
   */
  static int[] contacts(String[][] queries) {
    List<Integer> res = new ArrayList<>();
    for (String[] line : queries) {
      String action = line[0];
      String contact = line[1];
      if (action.equals("find")) {
        res.add(trie.find(contact));
      } else { // add
        trie.add(contact);
      }
    }
    return res.stream().mapToInt(i -> i).toArray();
  }

  public static void main(String[] args) throws IOException {

//    String[][] queries = {
//        {"add", "hack"},
//        {"add", "hackerrank"},
//        {"find", "hac"},
//        {"find", "hak"},
//    };

    String[][] queries = {
        {"add", "s"},
        {"add", "ss"},
        {"add", "sss"},
        {"add", "ssss"},
        {"add", "sssss"},
        {"find", "s"},
        {"find", "ss"},
        {"find", "sss"},
        {"find", "ssss"},
        {"find", "sssss"},
        {"find", "ssssss"},
    };

    System.out.println(Arrays.toString(contacts(queries)));
  }
}