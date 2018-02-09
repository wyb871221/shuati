/*
Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]

Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].
*/
// O(nlogn) solution
class Solution {
    public int arrayPairSum(int[] nums) {
        if (nums.length == 0) return 0;
        //if (nums.length / 2 != 0) return 0;
        Arrays.sort(nums);
        int result = 0;
        int i = 0;
        while (i < nums.length) {
            result = result + nums[i];
            i += 2;
        }
        return result;
    }
}

// O(n) solution
class Solution {
    public int arrayPairSum(int[] nums) {
        int[] exists = new int[20001];
        for (int i = 0; i < nums.length; ++i) {
            exists[nums[i] + 10000]++;
        }
        
        int i = 0;
        boolean odd = true;
        int result = 0;
        while (i < exists.length) {
            while (exists[i] > 0) {
                if (odd) {
                    result = i - 10000 + result;
                }
                odd = !odd;
                exists[i]--;
            }
            ++i;
        }
        return result;
    }
}
