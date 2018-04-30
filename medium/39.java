/*
39. Combination Sum

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

// first solution without sorting
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        DFS(ret, curr, candidates, target, 0);
        return ret;
    }
    
    private void DFS(List<List<Integer>> ret, List<Integer> curr, int[] candidates, int target, int index) {
        // 第一个终结条件，如果当前path算出的结果超出target，返回
        if (target < 0)
            return;
        
        // 第二个终结条件，如果当前path正好算出target,将当前path加入结果中
        if (target == 0) {
            ret.add(new ArrayList<Integer>(curr));
            return;
        }
        
        for (int i = index; i < candidates.length; ++i) {
            curr.add(candidates[i]);
            DFS(ret, curr, candidates, target - candidates[i], i);
            curr.remove(curr.size() - 1);
        }
        
        return;
    }
}

// with sorting, we are able to do some prunning
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        Arrays.sort(candidates);
        DFS(ret, curr, candidates, target, 0);
        return ret;
    }
    
    private void DFS(List<List<Integer>> ret, List<Integer> curr, int[] candidates, int target, int index) {
        // 第一个终结条件，如果当前path算出的结果超出target，返回
        if (target < 0)
            return;
        
        // 第二个终结条件，如果当前path正好算出target,将当前path加入结果中
        if (target == 0) {
            ret.add(new ArrayList<Integer>(curr));
            return;
        }
        
        for (int i = index; i < candidates.length; ++i) {
            if (candidates[i] > target) return; // 如果当前candidates已经超过target,没有必要继续做下去，因为我们已经将数组排序
            curr.add(candidates[i]);
            DFS(ret, curr, candidates, target - candidates[i], i);
            curr.remove(curr.size() - 1);
        }
        
        return;
    }
}