/*
697. Degree of an Array

Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
*/

class Solution {
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer[]> map = new HashMap<Integer, Integer[]>();
        int ret = nums.length;
        int max = 1;
        for (int i = 0; i < nums.length; ++i) {
            int curr = nums[i];
            if (map.containsKey(curr)) {
                Integer[] arr = map.get(curr);
                int length = i - arr[0] + 1;
                int degree = ++arr[1];
                if (degree > max) {
                    ret = length;
                    max = degree;
                } else if (degree == max) {
                    ret = Math.min(ret, length);
                }
            } else {
                Integer[] states = new Integer[2];
                states[0] = i;
                states[1] = 1;
                map.put(curr, states);
            }
        }
        
        return max == 1 ? 1 : ret;
    }
}