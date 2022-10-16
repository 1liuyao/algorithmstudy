package string.test;

import string.algorithm.ReverseStr2k;

public class ReverseStr2kTest {
    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
        ReverseStr2k reverseStr2k = new ReverseStr2k();
        String s1 = reverseStr2k.reverseStr(s, 2);
        System.out.println(s1);
    }
}
