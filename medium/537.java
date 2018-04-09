/*
537. Complex Number Multiplication

Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.
*/

class Solution {
    public String complexNumberMultiply(String first, String second) {
        first = first.substring(0, first.length() - 1);
        second = second.substring(0, second.length() - 1);
        String[] firstSub = first.split("\\+");
        String[] secondSub = second.split("\\+");
        
        String aStr = firstSub[0];
        String bStr = firstSub[1];
        String cStr = secondSub[0];
        String dStr = secondSub[1];
        
        int a = Integer.parseInt(aStr);
        int b = Integer.parseInt(bStr);
        int c = Integer.parseInt(cStr);
        int d = Integer.parseInt(dStr);
        
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(a * c - b * d));
        sb.append("+");
        sb.append(Integer.toString(a * d + b * c));
        sb.append("i");
        
        return (a * c - b * d) + "+" + (a * d + b * c) + "i";
    }
}