/*
461. Hamming Distance

The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.

*/

class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y; // 异或运算，只有不同的位置才会变成1
        int res = 0;
        while (xor != 0) {// 跟 0001做and运算，意思是只有最右边第一位是1才加到结果里，每一次循环都将xor往右边推，直到推完为止，这样就能算出xor里面一共有多少个1
            res += xor & 1;
            xor >>= 1;
        }
        return res;
    }
}