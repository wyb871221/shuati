/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
*/

/*  
    解法是将数组的值都换到对应的位置上，
    比如: nums[0] = 5, 应该将其换到nums[4], 重头到尾扫一遍数组过后，所有的负数都会被移到最后，前面的数列会变成排好序的数列，在这其中找出第一个位置跟数值不对称的即可
*/
public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
        	int val = nums[i];
        	if (val > 0 && val <= n && val != i + 1 && nums[val - 1] != val) {
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