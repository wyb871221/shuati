/*
723. Candy Crush

This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the current board.\
*/

// my solution
class Solution {
    public int[][] candyCrush(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean todo = false;
        
        // checking row by row for 3 consecutive numbers
        for (int row = 0; row < n; ++row) {
            int first = 0;
            int second = 1;
            while (second < m) {
                if (Math.abs(board[row][first]) != Math.abs(board[row][second])) {
                    int val = Math.abs(board[row][first]);
                    if (second - first >= 3) {
                        for (; first < second; ++first) {
                            board[row][first] = -val;
                        }
                        todo = true;
                    }
                    first = second;
                    second = first + 1;
                } else {
                    second++;
                }
            }
            
            if (Math.abs(board[row][first]) == Math.abs(board[row][second - 1]) && second - first >= 3) {
                int val = Math.abs(board[row][first]);
                for (; first < second; ++first) {
                    board[row][first] = -val;
                }
                todo = true;
            }
        }
        
        // checking column by column for 3 consecutive numbers
        for (int col = 0; col < m; ++col) {
            int first = 0;
            int second = 1;
            while (second < n) {
                if (Math.abs(board[first][col]) != Math.abs(board[second][col])) {
                    int val = Math.abs(board[first][col]);
                    if (second - first >= 3) {
                        for (; first < second; ++first) {
                            board[first][col] = -val;
                        }
                        todo = true;
                    }
                    first = second;
                    second = first + 1;
                } else {
                    second++;
                }
            }
            
            if (Math.abs(board[first][col]) == Math.abs(board[second - 1][col]) && second - first >= 3) {
                int val = Math.abs(board[first][col]);
                for (; first < second; ++first) {
                    board[first][col] = -val;
                }
                todo = true;
            }
        }
        
        for (int col = 0; col < m; ++col) {
            Stack<Integer> stack = new Stack<Integer>();
            for (int row = 0; row < n; ++row) {
                int val = board[row][col];
                stack.push(val);
            }
            
            int index = n - 1;
            while (!stack.empty()) {
                int curr = stack.pop();
                if (curr > 0) {
                    board[index][col] = curr;
                    index--;
                }
            }
            while (index >= 0) {
                board[index][col] = 0;
                index--;
            }
        }
        
        return board;
    }
}

// better solution 
class Solution {
    public int[][] candyCrush(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        boolean todo = false;
        
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col + 2 < m; ++col) {
                int val = Math.abs(board[row][col]);
                if (val != 0 && val == Math.abs(board[row][col + 1]) && val == Math.abs(board[row][col + 2])) {
                    todo = true;
                    board[row][col] = board[row][col + 1] = board[row][col + 2] = -val;
                }
            }
        }
        
        for (int col = 0; col < m; ++col) {
            for (int row = 0; row + 2 < n; ++row) {
                int val = Math.abs(board[row][col]);
                if (val != 0 && val == Math.abs(board[row + 1][col]) && val == Math.abs(board[row + 2][col])) {
                    todo = true;
                    board[row][col] = board[row + 1][col] = board[row + 2][col] = -val;
                }
            }
        }
        
        for (int col = 0; col < m; ++col) {
            Stack<Integer> stack = new Stack<Integer>();
            for (int row = 0; row < n; ++row) {
                int val = board[row][col];
                stack.push(val);
            }
            
            int index = n - 1;
            while (!stack.empty()) {
                int curr = stack.pop();
                if (curr > 0) {
                    board[index][col] = curr;
                    index--;
                }
            }
            while (index >= 0) {
                board[index][col] = 0;
                index--;
            }
        }
        
        return todo ? candyCrush(board) : board;
    }
}