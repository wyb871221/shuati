/*
56. Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

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
    public List<Interval> merge(List<Interval> intervals) {
    	if (intervals.size() < 1) return intervals;

    	intervals.sort((Interval o1, Interval o2) -> o1.start - o2.start);
    	List<Interval> ret = ArrayList<Interval>();
    	int start = intervals.get(0).start;
    	int end = intervals.get(0).end;
    	for (int i = 1; i < intervals.size(); ++i) {
    		Interval curr_int = intervals.get(i);
    		if (curr_int.start > end) { // this interval cannot merge
    			Interval prev_int = new Interval(start, end);
    			ret.add(prev_int);
    			start = curr_int.start;
    			end = curr_int.end;
    		} else { // merge
    			end = Math.max(end, curr_int.end);
    		}
    	}
    	ret.add(new Interval(start, end));

    	return ret;
    }
}