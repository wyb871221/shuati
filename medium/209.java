/*
209. Minimum Size Subarray Sum

DescriptionHintsSubmissionsDiscussSolution
Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
*/

//sliding window solution, O(n)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0, slow = 0; i < nums.length; ++i) {
            sum = nums[i] + sum;
            while (sum >= s && slow < nums.length) {
                minLength = Math.min(minLength, i - slow + 1);
                sum = sum - nums[slow++];
            }
        }
        
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}