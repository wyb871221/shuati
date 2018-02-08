/*
You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:

Input: J = "aA", S = "aAAbbbb"
Output: 3
Example 2:

Input: J = "z", S = "ZZ"
Output: 0
Note:

S and J will consist of letters and have length at most 50.
The characters in J are distinct.
*/

class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null) return 0;
        int length = S.length();
        int result = 0;
        for (int i = 0; i < length; ++i) {
            char t = S.charAt(i);
            if (J.indexOf(t) >= 0) {
                result++;
            }
        }
        return result;
    }
}

class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J.length() == 0 || S.length() == 0) return 0;
        
        int[] characters = new int[58];
        for (int i = 0; i < J.length(); ++i) {
            characters[J.charAt(i) - 'A'] = 1;
        }
        
        int result = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (characters[S.charAt(i) - 'A'] == 1) {
                result++;
            }
        }
        return result;
    }
}