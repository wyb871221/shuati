/*
370. Range Addition

Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]
Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]
*/

class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int start = update[0];
            int end = update[1];
            int inc = update[2];
            
            res[start] = res[start] + inc; //每次只在start与end+1的地方标记，start标记inc，end + 1标记-inc
            /*
            	n = 5, [1 3 2]
            	0 2 0 0 -2 0 -> 这一步其实等价于从s开始，从s开始res[i] = res[i - 1]，所以数组变成0 2 0+2 0+2 -2+2 0 -> 0 2 2 2 0 0也就是目标操作
            	  s     e+1

            */
            if (end < length - 1) {
                res[end + 1] = res[end + 1] - inc;
            }
        }
        
        for (int i = 1; i < length; ++i) {
            res[i] += res[i - 1];
        }
        
        return res;
    }
}