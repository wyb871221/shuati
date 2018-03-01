/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
        	int val = nums[i];
        	if (val > 0 && val <= n && val != i + 1 && nums[val - 1] != val) { // not only checking if current position is in place, but checking if index position is already in place to eliminate duplicate
        		swap(nums, i, val - 1);
        	} else i++;
        }
        i = 0;
        while (i < n && i == nums[i] - 1) i++;
        return i + 1;
    }
    
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}