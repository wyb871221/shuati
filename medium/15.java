/*
15. 3Sum
DescriptionHintsSubmissionsDiscussSolution
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        HashSet<List<Integer>> listSet = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length - 2; ++i) {
            for (int j = i + 1; j < nums.length - 1; ++j) {
                int target = 0 - nums[i] - nums[j];
                int index = binarySearch(nums, j + 1, nums.length - 1, target);
                if (index >= 0) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[index]);
                    if (listSet.add(list))
                        ret.add(list);
                }
            }
        }
        
        return ret;
    }
    
    private int binarySearch(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
                
            if (nums[mid] > target) {
                end  = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        
        return -1;
    }
}