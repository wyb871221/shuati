/*
200. Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
*/

//DFS solution, 将所有的岛都收缩成一个点
public class Solution {

	private char[][] grid;
	int[][] directions = new int[][]{
		{0, -1}, // left
		{0, 1}, // right
		{1, 0}, // down
		{-1, 0} // up
	};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        this.grid = grid;
        int m = grid[0].length;
        int n = grid.length;
        
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == '1') {
                    combineIsland(grid, i, j);
                    res++;
                }
            }
        }
        
        return res;
    }
    
    private void combineIsland(char[][] grid, int row, int col) {
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid[0].length - 1 || grid[row][col] != '1')
            return;
        
        grid[row][col] = '0';
        
        for (int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            combineIsland(grid, nextRow, nextCol);
        }
        
        return;
    }
}

// union find solution, time: O(2n), space: O(m * n)
public class Solution {

	private char[][] grid;
	boolean[][] visited;
	int[] keys;
	int[][] directions = new int[][]{
		{0, -1}, // left
		{0, 1}, // right
		{1, 0}, // down
		{-1, 0} // up
	};

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        this.grid = grid;
        this.visited = new boolean[grid.length][grid[0].length];
        this.keys = new int[grid.length * grid[0].length];
        int n = grid[0].length;
        int m = grid.length;

        int res = 0;
        // 将二维数组变成一维，是陆地的元素用本来的值表示他的parent,如果是水的话就用-1代替，表示没有parent
        for (int i = 0; i < grid.length; ++i) {
        	for (int j = 0; j < grid[0].length; ++j) {
        		if (grid[i][j] == '1') {
        			keys[i * grid[0].length + j] = i * grid[0].length + j;
        			res++;
        		} else {
        			keys[i * grid[0].length + j] = -1;
        		}
        	}
        }
        
        for (int i = 0; i < grid.length; ++i) {
        	for (int j = 0; j < grid[0].length; ++j) {
        		if (grid[i][j] != '1')
        			continue;
        		int curKey = i * n + j;
        		int parent1 = find(keys, curKey);
        		if (keys[curKey] != -1 && !visited[i][j]) {
        		    visited[i][j] = true;
        			for (int[] dir : directions) {
        				int nextRow = i + dir[0];
        				int nextCol = j + dir[1];
        				int nextKey = nextRow * n + nextCol;
        				if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol] || keys[nextKey] == -1) continue;
    					int parent2 = find(keys, nextKey);
    					// 这一步其实是union的操作，将parent不同而相关联的点union起来
    					if (parent1 != parent2) {
    						keys[parent2] = parent1;
    						res--;
    					}
        			}
        		}
        	}
        }

        return res;
    }

    private int find(int[] keys, int key) {
    	int tmp = key;
    	while (keys[tmp] != tmp) {
    		tmp = keys[tmp];
    	}
    	
    	//优化处理，将所有同一个ultimate parent底下的点都赋予ultimate parent的value,这样的话在往后只需一步就能找到parent
    	while (keys[key] != tmp) {
    	    key = keys[key];
    	    keys[key] = tmp;
    	}

    	return tmp;
    }
}