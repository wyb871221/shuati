/*
718. Maximum Length of Repeated Subarray

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100

*/

// dp space O(mn)
class Solution {
    public int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int max = 0;
        
        for (int i = 0; i <= A.length; ++i) {
            for (int j = 0; j <= B.length; ++j) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // dp[i][j] is the longest common subarry ending with nums[i - 1] & nums[j - 1], only this way when we get a new element, we can safely add 1 to dp[i - 1][j - 1]
                } else {
                    if (A[i - 1] == B[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(dp[i][j], max);
                    }
                }
                System.out.print("dp[" + i + "][" + j + "] = " + dp[i][j] + " ");
            }
            System.out.print("\n");
        }
        
        return max;
    }
}

// dp space O(1), optimized version of previous solution, since we only utilize dp[i - 1][j - 1], 
// we can only store dp[i - 1][j - 1], just need to traverse diagonally 
class Solution {
    public int findLength(int[] A, int[] B) {
        
        int max = 0;
        
        for (int i = 0; i < A.length; ++i) {
        // try all types of diagonal, 
        // A[0],B[0] .. A[n]B[n] type1
        // A[0],B[1] .. A[n - 1]B[n] type2
        // A[0],B[2] .. A[n - 2]B[n] type3
        //     .             .
        //     .             .
        //     .             .
            int prevMax = 0; // reset preMax whenever we traverse one type of diagonal
            for (int j = 0, k = i; j < B.length && k < A.length; ++j, ++k) {// traverse diagonally, always keep a constant value between j and k, if i equals to 2, k is always 2 nodes ahead
                if (A[k] != B[j]) {
                    prevMax = 0; // reset preMax if theres a no-match, because it breaks the subarray
                } else {
                    prevMax++;
                    max = Math.max(prevMax, max);
                }
            }
        }
        
        for (int j = 1; j < B.length; ++j) {
        // try all types of diagonal, 
        // A[0],B[0] .. A[n]B[n] type1
        // A[1],B[0] .. A[n]B[n - 1] type2
        // A[2],B[0] .. A[n]B[n - 2] type3
        //     .             .
        //     .             .
        //     .             .
            int prevMax = 0;
            for (int i = 0, k = j; i < A.length && k < B.length; ++i, ++k) {
                if (A[i] != B[k]) {
                    prevMax = 0;
                } else {
                    prevMax++;
                    max = Math.max(prevMax, max);
                }
            }
        }
        
        return max;
    }
}