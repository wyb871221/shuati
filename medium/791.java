/*
791. Custom Sort String

S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.
*/

// solution O(n), count appearence of characters in T, and then use the count to print in the order of S
class Solution {
    public String customSortString(String S, String T) {
        int[] map = new int[26];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < T.length(); ++i) {
            map[T.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < S.length(); ++i) {
            int count = map[S.charAt(i) - 'a'];
            for (int j = count; j > 0; --j) {
                sb.append(S.charAt(i));
            }
            map[S.charAt(i) - 'a'] = 0; // set count for character that exists in S to 0, so that we can print the rest
        }
        
        for (int i = 0; i < map.length; ++i) {
            if (map[i] != 0) {
                int count = map[i];
                for (int j = count; j > 0; --j) {
                    sb.append((char)(i + 97));
                }
            }
        }
        
        return sb.toString();
    }
}