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

// binary search solution O(nlogn)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        
        if (nums.length == 0) return 0;
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        
        for (int i = 1; i < sums.length; ++i) {
            // in order to make sums[i] - sums[j] >= s, use binary search find the last sums[j] that fullfils this requirement.
            if (sums[i] >= s) {
                int toFind = sums[i] - s;
                int index = binarySearch(sums, toFind, 0, i - 1);
                min = Math.min(min, i - index);
            }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    private int binarySearch(int[] sums, int toFind, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sums[mid] == toFind) {
                return mid;
            }
            else if (sums[mid] > toFind) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}