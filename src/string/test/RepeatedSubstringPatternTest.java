package string.test;

import string.algorithm.RepeatedSubstringPattern;

public class RepeatedSubstringPatternTest {
    public static void main(String[] args) {
//        String s = "abcabcabcabc";
        RepeatedSubstringPattern repeatedSubstringPattern = new RepeatedSubstringPattern();
//        boolean b = repeatedSubstringPattern.repeatedSubstringPattern(s);

        String s1 = "abac";
        boolean b1 = repeatedSubstringPattern.repeatedSubstringPattern1(s1);
        System.out.println(b1);
    }
}
