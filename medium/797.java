/*
797. All Paths From Source to Target

Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.
*/

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        LinkedList<Integer> currList = new LinkedList<Integer>();
        findPath(graph, ret, currList, 0);
        return ret;
    }
    
    private void findPath(int[][] graph, List<List<Integer>> ret, LinkedList<Integer> currList, int index) {
        currList.offer(index);
        if (graph[index].length == 0) {
            ret.add(new LinkedList<Integer>(currList));
            currList.removeLast();
            return;
        }
        
        int[] edges = graph[index];
        for (int i = 0; i < edges.length; ++i) {
            findPath(graph, ret, currList, edges[i]);
        }
        currList.removeLast();
        return;
    }
}