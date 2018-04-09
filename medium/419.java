/*
419. Battleships in a Board

Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
*/

// first solution, need to modify array value
class Solution {
    public int countBattleships(char[][] board) {
        int ret = 0;
        
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 'X') {
                    DFS(board, i, j);
                    ret++;
                }  
            }
        }
        
        return ret;
    }
    
    private void DFS(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'X')
            return;
        board[row][col] = '.';
        int[][] directions = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
        };
        
        for (int[] direction : directions) {
            DFS(board, row + direction[0], col + direction[1]);
        }
        return;
    }
}

// second solution, no extra space required, no modification required and one pass

class Solution {
    public int countBattleships(char[][] board) {
        int ret = 0;
        
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                // basically we only count the first cell of the battle ship, and we use the top-left corner cell as the target, only count if the current cell is X and its up and right cell is either empty or '.'
                if (
                    board[i][j] == 'X' 
                    && 
                    (i - 1 < 0 || board[i - 1][j] == '.') 
                    && 
                    (j + 1 >= board[0].length || board[i][j + 1] == '.')
                )
                    ret++;
            }
        }
        
        return ret;
    }
}