/*
59. Spiral Matrix II

Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/ 

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int num = 0;
        for (int level = 0; level <= n / 2; ++level) {
        	for (int i = 0 + level; i < n - 1 - level; ++i) {
        		num++;
        		ret[level][i] = num;
        	}
            
            for (int i = 0 + level; i < n - 1 - level; ++i) {
                num++;
                ret[i][n - 1 - level] = num;
            }

        	for (int i = n - 1 - level; i > 0 + level; --i) {
        		num++;
        		ret[n - 1 - level][i] = num;
        	}
            
            for (int i = n - 1 - level; i > 0 + level; --i) {
                num++;
        		ret[i][level] = num;
            }
        }
        if (n % 2 == 1)
            ret[n / 2][n / 2] = ++num;
        return ret;
    }
}