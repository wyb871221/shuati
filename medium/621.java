/*
621. Task Scheduler

Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
*/

//sorting solution
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int time = 0;
        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i <= 25 && map[25 - i] > 0) {
                    map[25 - i]--;
                }
                i++;
                time++;
            }
            Arrays.sort(map);
        }
        return time;
    }
}

// priority queue solution
class Solution {
    public int leastInterval(char[] tasks, int n) {
       int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        
        for (int f: map) {
            if (f > 0)
                queue.add(f);
        }
        
        int time = 0;
        while (queue.size() > 0) {
            List<Integer> list = new ArrayList<Integer>();
            int i = 0;
            while (i <= n) {
                if(!queue.isEmpty()) {
                    if (queue.peek() > 1) {
                        list.add(queue.poll() - 1);
                    } else {
                        queue.poll();
                    }
                }
                time++;
                if (queue.isEmpty() && list.size() == 0)
                    break;
                i++;
            }
            for (int l : list)
                queue.add(l);
        }
        return time;
    }
}