/*
305. Number of Islands II
Problem Description:

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]
*/

//Solution: use union find to keep track of each node's parent, when we see land in neighbor, we union with neighbor and decrease the total number of islands

public class Solution {

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		int[] rootArray = new int[m * n];
		
		Arrays.fill(rootArray, -1);
		ArrayList<Integer> result = new ArrayList<Integer>();
		int[][] directions = {
			{-1,0},
			{0,1},
			{1,0},
			{0,-1}
		};
		int count = 0;
		for(int k = 0; k < positions.length; k++){
			count++;
			int[] p = positions[k];
			int index = p[0] * n + p[1];
			rootArray[index] = index;//set root to be itself for each node
			for(int r=0; r<4; r++) {
				int i = p[0] + directions[r][0];
				int j = p[1] + directions[r][1];
				if(i >= 0 && j >= 0 && i < m && j < n && rootArray[i * n + j] != -1) {
					//get neighbor's root
					int thisRoot = getRoot(rootArray, i*n+j);
					if(thisRoot != index) {
						rootArray[thisRoot] = index;//set previous root's root
						count--;
					}
				}
			}
			result.add(count);
		}
		return result;
	}

	public int getRoot(int[] arr, int i){
		int tmp = i;
		while(tmp != arr[tmp]){
			tmp = arr[tmp];
		}

		while (arr[i] != tmp) {
			i = arr[i];
			arr[i] = tmp;
		}

		return tmp;
	}
}