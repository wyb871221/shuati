/*
526. Beautiful Arrangement

Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
Note:
N is a positive integer and will not exceed 15.
*/

// my solution, starting from the first node in the arrangement, try every number from 1 to N
class Solution {
    public int countArrangement(int N) {
        int ret = 0;
        int[] arngmnt = new int[N];
        boolean[] visited = new boolean[N + 1];
        int index = 0;
        
        for (int i = 1; i <= N; ++i) {
            arngmnt[0] = i;
            visited[i] = true;
            ret += findArrangement(arngmnt, visited, index, N);
            visited[i] = false;
        }
        
        return ret;
    }
    
    private int findArrangement(int[] arngmnt, boolean[] visited, int index, int N) {
        if ((index + 1) % arngmnt[index] == 0 || arngmnt[index] % (index + 1) == 0) {
            if (index == N - 1) {
                return 1;
            } else {
                int ret = 0;
                for (int i = 1; i < visited.length; ++i) {
                    if (visited[i] == false) {
                        arngmnt[index + 1] = i;
                        visited[i] = true;
                        ret += findArrangement(arngmnt, visited, index + 1, N);
                        visited[i] = false;
                    }
                }
                return ret;
            }
        } else {
            return 0;
        }
    }
}


// Clean solution, starting from N, try to put N in every possible position
public class Solution {
    public int countArrangement(int N) {
        // arr[0] is reserved for sum
        int[] array = new int[N + 1];
        settle(N, array);
        return array[0];
    }
    
    private void settle(int n, int[] array) {
        if (n == 1) {
            // 1 could be settled anywhere, sum++
            array[0] ++;
            return;
        }
        for (int i = 1; i < array.length; i ++) {
           // check i is not occupied and fit n
            if (array[i] == 0 && (n % i == 0 || i % n == 0)) {
                // n is settled to i
                array[i] = n;
               // get n - 1 settled
                settle(n - 1, array);
               // backtrack
                array[i] = 0;
            }
        }
    }
}