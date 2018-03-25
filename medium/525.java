/*
525. Contiguous Array

Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
*/

class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        // key is the preSum, if we have 0, we minus 1, if we have 1, we add 1, so if presum[0, i] - presum[0, j] = 0, it means [i + 1, j] has the same number of 0 and 1
        int res = 0;
        int preSum = 0;
        map.put(0, -1);
        
        for (int i = 0; i < nums.length; ++i) {
            preSum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(preSum)) {
                int range = i - map.get(preSum);
                res = Math.max(res, range);
            } else {
                map.put(preSum, i);
            }
        }
        
        return res;
    }
}