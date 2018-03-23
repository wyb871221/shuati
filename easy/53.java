/*
53. Maximum Subarray

Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
*/

// dp solution
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // status means that the maximum sum of subarray that ends with nums[i]
        
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < n; ++i) {
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
            res = dp[i] > res ? dp[i] : res;
        }
        
        return res;
    }
}

// divide and conquer

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        return helper(nums, 0, n - 1);
    }
    
    private int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int middle = (left + right) / 2;
        int letfSum = helper(nums, left, middle);
        int rightSum = helper(nums, middle + 1, right);
        int leftmax = nums[middle];
        int rightmax = nums[middle + 1];
        
        int temp = 0;
        for(int i = middle; i >= left; i--) {
            temp += nums[i];
            if(temp > leftmax) 
                leftmax = temp;
        }

        temp = 0;
        for(int i = middle + 1; i <= right; i++) {
            temp += nums[i];
            if(temp > rightmax) 
                rightmax = temp;
        }
        
        return Math.max(Math.max(letfSum, rightSum), leftmax + rightmax);
    }
}