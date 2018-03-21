/*
57. Insert Interval

Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new LinkedList<Interval>();
        if (intervals.size() < 1) {
            ret.add(newInterval);
            return ret;
        }
        
        int index = 0;
        while (index < intervals.size() && intervals.get(index).end < newInterval.start) {
            ret.add(intervals.get(index));
            index++;
        }

        while (index < intervals.size() && intervals.get(index).start <= newInterval.end) {
            int start = Math.min(intervals.get(index).start, newInterval.start);
            int end = Math.max(intervals.get(index).end, newInterval.end);
            newInterval = new Interval(start, end);
            index++;
        }

        ret.add(newInterval);

        while (index < intervals.size()) {
            ret.add(intervals.get(index));
            index++;
        }
        
        return ret;
    }
}