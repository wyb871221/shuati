/*
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 

Example 1:

Input: matrix = [[1,2,3,4],[5,1,2,3],[9,5,1,2]]
Output: True
Explanation:
1234
5123
9512

In the above grid, the diagonals are "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]", and in each diagonal all elements are the same, so the answer is True.
Example 2:

Input: matrix = [[1,2],[2,2]]
Output: False
Explanation:
The diagonal "[1, 2]" has different elements.
Note:

matrix will be a 2D array of integers.
matrix will have a number of rows and columns in range [1, 20].
matrix[i][j] will be integers in range [0, 99].
*/

// my own solution
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
    	boolean[][] visited = new boolean[matrix.length][matrix[0].length]; // added pruning
        return isToeplitzMatrixHelper(matrix, visited, matrix.length - 1, 0);
    }

    public boolean isToeplitzMatrixHelper(int[][] matrix, boolean[][] visited, int row, int col) {
    	visited[row][col] = true;
    	int next_row = row - 1;
    	int next_col = col + 1;
    	if (next_row < 0 || next_col >= matrix[0].length) {
    		return true;
    	}

    	if (matrix[next_row][col] != matrix[row][next_col]) {
    		return false;
    	}

    	if (visited[next_row][col]) {
    		return isToeplitzMatrixHelper(matrix, visited, row, next_col);
    	}

    	return isToeplitzMatrixHelper(matrix, visited, row, next_col) && isToeplitzMatrixHelper(matrix, visited, next_row, col);
    }
}

// time complexity = O(n) n is the number of nodes in the matrix, because you need to traverse every node once