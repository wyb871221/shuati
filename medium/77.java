/*
77. Combinations

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> curr = new ArrayList<Integer>();
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        DFS(1, n, k, curr, ret);
        return ret;
    }
    
    private void DFS(int index, int n, int k, List<Integer> curr, List<List<Integer>> ret) {
        if (curr.size() == k) {
            ret.add(new ArrayList<Integer>(curr));
            return;
        }
        
        for (int i = index; i <= n; ++i) {
            curr.add(i);
            DFS(i + 1, n, k, curr, ret);
            curr.remove(curr.size() - 1);
        }
        return;
    }
}