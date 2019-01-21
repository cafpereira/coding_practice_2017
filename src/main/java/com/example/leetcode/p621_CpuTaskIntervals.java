package com.example.leetcode;

import java.util.*;

/**
 * The idea is to simulate the optimal allocation of tasks and then count how
 * many intervals would be required to run it:
 *
 * The tasks with the currently maximum number of pending instances will contribute
 * to a large number of idle cycles in the future, if not executed with appropriate
 * interleavings with the other tasks. Thus, we need to re-execute such a task as
 * soon as its cooling time is finished.
 *
 * Time: O(N)
 * Space: O(N)
 *
 * Is also possible to find the solution of the problem by calculating mathematically
 * how many idle slots exists in between tasks.
 */
public class p621_CpuTaskIntervals {
  static class Task {
    char type;
    int instances;
    Task(char type, int instances) {
      this.type = type;
      this.instances = instances;
    }
  }

  public int leastInterval(char[] tasks, int n) {
    Map<Character, Integer> taskCount = buildMap(tasks);

    // We will use priority queue to find quickly which task has most instances to run.
    PriorityQueue<Task> maxHeap = new PriorityQueue<>((t1, t2) ->  {
      if (t1.instances != t1.instances) {
        return Integer.compare(t2.instances, t1.instances);
      }
      return Character.compare(t1.type, t2.type);
    });
    for (Map.Entry<Character, Integer> e : taskCount.entrySet()) {
      maxHeap.add(new Task(e.getKey(), e.getValue()));
    }

    int intervals = 0;
    while(!maxHeap.isEmpty()) {
      List<Task> selectedTasks = new ArrayList<>();
      int cooldown = n + 1;

      while (cooldown > 0 && !maxHeap.isEmpty()) {
        // Take next task (most instances) and reduce cooldown
        selectedTasks.add(maxHeap.poll());
        cooldown--;
      }

      // Once cooldown is over or there is no more avaliable tasks to run during
      // the cooldown of the first task, we decrease the number of instances of
      // each selected task and re-add to the heap if there are remaining instances.
      for (Task task : selectedTasks) {
        task.instances -= 1;
        if (task.instances > 0) {
          maxHeap.add(task);
        }
      }

      intervals += selectedTasks.size();

      // If heap not empty well, then we need to start another cycle, add the remaining
      // cooldown time (if there is any time left in the cooldown counter).
      if (!maxHeap.isEmpty()) {
        intervals += cooldown;
      }
    }
    return intervals;
  }

  private Map<Character, Integer> buildMap(char[] tasks) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : tasks) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }
}
