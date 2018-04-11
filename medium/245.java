/*
245. Shortest Word Distance III

This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
*/

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int first = -1;
        int second = -1;
        boolean isSame = word1.equals(word2);
        int flag = 0;
        int ret = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; ++i) {
            String curr = words[i];
            if (word1.equals(curr)) {
                if (isSame) {
                    if (flag == 0) {
                        first = i;
                        flag = 1;
                    } else {
                        second = i;
                        flag = 0;
                    }
                } else {
                    first = i;
                }
            }
            
            if (word2.equals(curr) && !isSame) {
                second = i;
            }
            
            if (second != -1 && first != -1) {
                ret = Math.min(ret, Math.abs(first - second));
            }
        }
        
        return ret;
    }
}