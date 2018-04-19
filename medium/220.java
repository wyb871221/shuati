/*
220. Contains Duplicate III

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
*/

// tree set solution, this solution uses treeset to keep k element sorted, and check to see if there's any number that's within range of nums[i] - t <= nums[i] <= nums[i] + t
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return false;
        }
        
        final TreeSet<Long> set = new TreeSet<Long>();
        
        for (int i = 0; i < nums.length; ++i) {
            final Long ceiling = set.ceiling((long) nums[i] - t);
            final Long floor = set.floor((long) nums[i] + t);
            
            if (
                (ceiling != null && ceiling <= nums[i]) 
                || (floor != null && floor >= nums[i])
            ) {
                return true;
            }
            
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        
        return false;
    }
}

// bucket sort solution, 其实这道题目的中心思想就是如何将K个数排序然后找出在nums[i] - t & nums[i] + t之间的数
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        
        HashMap<Long, Long> map = new HashMap<Long, Long>();
        
        for (int i = 0; i < nums.length; ++i) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            
            if (
                map.containsKey(bucket) 
                ||
                (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                ||
                (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)
            )
                return true;
            
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            
            map.put(bucket, remappedNum);
        }
        
        return false;
    }
}