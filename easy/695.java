/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.
*/

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    max = Math.max(getArea(grid, i, j), max);
                }
            }
        }
        return max;
    }
    
    private int getArea(int[][] grid, int row, int col) {
        grid[row][col] = 0;
        int up = row > 0 ? grid[row - 1][col] : 0;
        int down = row < grid.length - 1 ? grid[row + 1][col] : 0;
        int left = col > 0 ? grid[row][col - 1] : 0;
        int right = col < grid[0].length - 1 ? grid[row][col + 1] : 0;
        int area = 1;
        
        if (up + down + left + right == 0) {
            return 1;
        } else {
            if (row > 0 && grid[row - 1][col] == 1) {
                area += getArea(grid, row - 1, col);
            }
            
            if (row < grid.length - 1 && grid[row + 1][col] == 1) {
                area += getArea(grid, row + 1, col);
            }
            
            if (col > 0 && grid[row][col - 1] == 1) {
                area += getArea(grid, row, col - 1);
            }
            
            if (col < grid[0].length - 1 && grid[row][col + 1] == 1) {
                area += getArea(grid, row, col + 1);
            }
        }
        
        return area;
    }
}