/*
283. Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

class Solution {
    public void moveZeroes(int[] nums) {
        int first = 0;
        int second = 1;
        
        while (second < nums.length) {
            if (nums[first] != 0) {
                first++;
                second++;
            } else {
                if (nums[second] == 0) {
                    second++;
                } else {
                    swap(nums, first, second);
                }
            }
        }
    }
    
    private void swap(int[] nums, int first, int second) {
        int tmp = nums[first];
        nums[first] = nums[second];
        nums[second] = tmp;
    }
}