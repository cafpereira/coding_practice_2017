package com.example.leetcode;

import static com.example.leetcode.utils.TreeHelper.deserialize;

import com.example.leetcode.utils.*;
import java.util.*;

public class p112_PathSum {
  public boolean hasPathSum(TreeNode root, int sum) {
    return dfs(root, sum, 0);
  }

  public boolean dfs(TreeNode node, int sum, int acc) {
    if (node == null) {
      return false;
    }

    acc += node.val;

    if (node.left == null && node.right == null) {
      return acc == sum;
    }

    return dfs(node.left, sum, acc) || dfs(node.right, sum, acc);
  }

  public static void main(String[] args) {
    p112_PathSum solution = new p112_PathSum();
    TreeNode root = deserialize("[1,2,null,3,null,4,null,5]");
    System.out.println("hasPathSum: " + solution.hasPathSum(root, 6)); // expected false
  }
}
