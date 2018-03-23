/*
152. Maximum Product Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

*/

// dynamic programming solution, keep records of max and min state of previous subarray
class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0];
        
        for (int i = 1, max = res, min = res; i < nums.length; ++i) {
            // if number is negative, swap the max and min, because when you multiply by negative, big number becomes small, small number become big
            if (nums[i] < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            
            res = Math.max(res, max);
        }
        
        return res;
    }
}