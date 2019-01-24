package com.example.leetcode;

import static com.example.leetcode.utils.TreeHelper.deserialize;

import com.example.leetcode.utils.TreeNode;

public class p124_BinaryTreeMaximumPathSum {
  int maxSum;

  public int maxPathSum(TreeNode root) {
    maxSum = Integer.MIN_VALUE;
    maxPathHelper(root);
    return maxSum;
  }

  public int maxPathHelper(TreeNode node) {
    // Base case
    if (node == null) {
      return 0;
    }

    // Take sums from left and right or zero if they are negative
    // Why? Assume our node val is K, if both left and right have
    // negative sums, then the best option is use only K to get
    // to the max sum
    int leftSum = Math.max(maxPathHelper(node.left), 0);
    int rightSum = Math.max(maxPathHelper(node.right), 0);

    // This is the max sum that includes value of node
    // on its path
    int centerSum = leftSum + rightSum + node.val;

    // Update max sum if a better sum is found.
    if (centerSum > maxSum) {
      maxSum = centerSum;
    }

    // Return the best branch (left or right) to the top level caller,
    // center is not an option because the sum path is linear (no forks)
    return Math.max(leftSum, rightSum) + node.val;
  }

  public static void main(String[] args) {
    p124_BinaryTreeMaximumPathSum solution = new p124_BinaryTreeMaximumPathSum();
    TreeNode root = deserialize("[5,4,8,11,null,13,4,7,2,null,null,null,1]");
    System.out.println("maxPathSum: " + solution.maxPathSum(root)); // expected 48
  }
}
