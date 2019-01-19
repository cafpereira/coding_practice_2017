package com.example.leetcode;

import java.util.*;
import com.example.leetcode.utils.*;

public class p314_TreeVerticalOrderTraversal {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    // The main idea is to traverse the tree by levels and
    // assign column values to each node.
    // if col(root) = k, then col(root.left) = k-1 and col(root.right) = k+1

    // Why not a regular DFS traversal? As we traverse top to bottom, some
    // nodes that should appear last the list will be included first if the
    // recursion branch from one side crosses multiple columns.

    Deque<TreeNode> nodeQueue = new LinkedList<>();
    Deque<Integer> colQueue = new LinkedList<>(); // Since we cannot change the TreeNode class
    // lets keep a second queue for the col numbers

    // Each time we visit a node we add its value to the correspondent column list.
    Map<Integer, List<Integer>> columns = new HashMap<>();

    // min and max are used to iterate over columns in ascending order without
    // having to sort keys from the columns map.
    // we can replace this with a TreeMap and not use min/max var, but this
    // solution is much faster (5ms vs 9ms)
    int min = 0, max = 0;

    nodeQueue.add(root);
    colQueue.add(0);

    while(!nodeQueue.isEmpty()) {
      TreeNode node = nodeQueue.remove();
      int col = colQueue.remove();

      List<Integer> vertColumn = columns.getOrDefault(col, new ArrayList<>());
      vertColumn.add(node.val);
      columns.put(col, vertColumn);

      // Update min/max;
      min = Math.min(min, col);
      max = Math.max(max, col);

      if (node.left != null) {
        nodeQueue.add(node.left);
        colQueue.add(col - 1);
      }
      if (node.right != null) {
        nodeQueue.add(node.right);
        colQueue.add(col + 1);
      }
    }

    // Copy each column to response list from min to max.
    List<List<Integer>> res = new ArrayList<>();
    for (int i = min; i < max + 1; i++) {
      res.add(columns.get(i));
    }
    return res;
  }

  public static void main(String[] args) {
    p314_TreeVerticalOrderTraversal solution = new p314_TreeVerticalOrderTraversal();
    TreeNode root = buildTree();

    System.out.println("verticalOrder: " + solution.verticalOrder(root));
  }

  static TreeNode buildTree() {
    TreeNode a = new TreeNode(3);

    TreeNode b = new TreeNode(9);
    TreeNode c = new TreeNode(8);

    TreeNode d = new TreeNode(4);
    TreeNode e = new TreeNode(0);
    TreeNode f = new TreeNode(1);
    TreeNode g = new TreeNode(7);

    TreeNode h = new TreeNode(2);
    TreeNode i = new TreeNode(5);

    a.left = b;
    a.right = c;

    b.left = d;
    b.right = e;
    c.left = f;
    c.right = g;

    e.right = h;
    f.left = i;

    return a;
  }
}
