/*
40. Combination Sum II

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        Arrays.sort(candidates);
        DFS(ret, curr, candidates, 0, target);
        return ret;
    }
    
    private void DFS(List<List<Integer>> ret, List<Integer> curr, int[] candidates, int index, int target) {
        if (target < 0) return;
        
        if (target == 0) {
            ret.add(new ArrayList<Integer>(curr));
            return;
        }
        
        for (int i = index; i < candidates.length; ++i) {
            int value = candidates[i];
            if (i > 0 && i != index && value == candidates[i - 1]) continue; // i != index是为了避免1 1 6这种情况被忽略
            if (value > target) return;
            curr.add(value);
            DFS(ret, curr, candidates, i + 1, target - value);
            curr.remove(curr.size() - 1);
        }
        
        return;
    }
}