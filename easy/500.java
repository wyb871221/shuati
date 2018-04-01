/*
500. Keyboard Row

Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.


American keyboard


Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]

*/

class Solution {
    public String[] findWords(String[] words) {
        String dict = "qwertyuiopQWERTYUIOPasdfghjklASDFGHJKLzxcvbnmZXCVBNM";   
        ArrayList<String> ret = new ArrayList<String>();

        for (String word : words) {
            int bucket = 0;
            int count = 0;
            
            for (int i = 0; i < word.length(); ++i) {
                int newBucket = 0;
                int index = dict.indexOf(word.charAt(i));
                if (index < 20) {
                    newBucket = 1;
                } else if (index >= 20 && index < 38) {
                    newBucket = 2;
                } else {
                    newBucket = 3;
                }
                
                if (i == 0) {
                    bucket = newBucket;
                }
                
                if (bucket == newBucket) {
                    count++;
                }
            }
            if (count == word.length())
                ret.add(word);
        }
        return ret.toArray(new String[0]);
    }
}