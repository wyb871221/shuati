/*
266. Palindrome Permutation

Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

*/

class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[128];
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (map[c] == 0) { // 运用了二进制计数法，遇见两次清零
                map[c] = 1;
                count++;
            } else {
                map[c] = 0;
                count--;
            }
        }
        
        return count <= 1;
    }
}