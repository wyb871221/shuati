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
        
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        int insStart = newInterval.start;
        int insEnd = newInterval.end;
        
        int index = 0;
        while (index < intervals.size() && end < insStart) {
            ret.add(new Interval(start, end));
            start = intervals.get(index).start;
            end = intervals.get(index).end;
            index++;
        }
        
        if (insStart >= start) {
            end = Math.max(insEnd, end);
            index++;
        } else {
            start = insStart;
            end = insEnd;
        }
        
        for (int i = index; i < intervals.size(); ++i) {
            Interval curr = intervals.get(i);
            if (curr.start > end) {
                ret.add(new Interval(start, end));
                start = curr.start;
                end = curr.end;
            } else {
                end = Math.max(curr.end, end);
            }
        }
        ret.add(new Interval(start, end));
        
        return ret;
    }
}