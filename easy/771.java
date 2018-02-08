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