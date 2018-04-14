/*
531. Lonely Pixel I

Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
*/

//O(nm) Time, O(n+m) Space Solution:
class Solution {
    public int findLonelyPixel(char[][] picture) {
        int n = picture.length, m = picture[0].length;
        
        int[] rowCount = new int[n], colCount = new int[m];
        for (int i=0;i<n;i++) 
            for (int j=0;j<m;j++) 
                if (picture[i][j] == 'B') { rowCount[i]++; colCount[j]++; }

        int count = 0;
        for (int i=0;i<n;i++) 
            for (int j=0;j<m;j++) 
                if (picture[i][j] == 'B' && rowCount[i] == 1 && colCount[j] == 1) count++;
                    
        return count;
    }
}

//O(nm) Time, O(1) Space Solution:
/*
    The hack here is to mutate the first row and first column of the given matrix to store the counts of items in the row/column.

    W + 1 = X --> one item in the row/column
    B + 1 = C --> one item in the row/column, and the first row is the black pixel
    W + 2 = Y --> two items in the row/column
    W - 1 = V --> this prevents wrap-around past W if there are more than 255 black pixels in a row/column
*/

class Solution {
    public int findLonelyPixel(char[][] picture) {
        int n = picture.length, m = picture[0].length;

        int firstRowCount = 0;
        for (int i=0;i<n;i++) 
            for (int j=0;j<m;j++) 
                if (picture[i][j] == 'B') {   
                    if (picture[0][j] < 'Y' && picture[0][j] != 'D') picture[0][j]++;
                    if (i == 0) firstRowCount++; // picture[0][0] is for counting number of black in the first column, that's why we need a firstRowCount
                    else if (picture[i][0] < 'Y' && picture[i][0] != 'D') picture[i][0]++;
                }

        int count = 0;
        for (int i=0;i<n;i++) 
            for (int j=0;j<m;j++) 
                if (picture[i][j] < 'W' && (picture[0][j] == 'C' || picture[0][j] == 'X')) { 
                    if (i == 0) count += firstRowCount == 1 ? 1 : 0;
                    else if (picture[i][0] == 'C' || picture[i][0] == 'X') count++;
                }

        return count;
    }
}