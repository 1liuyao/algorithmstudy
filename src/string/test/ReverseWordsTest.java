package string.test;

import string.algorithm.ReverseWords;

public class ReverseWordsTest {
    public static void main(String[] args) {
        //String s = "  hello  world  ";
        String s = "  hello world  ";
        ReverseWords reverseWords = new ReverseWords();
        String s1 = reverseWords.reverseWords1(s);
        System.out.println(s1);
    }
}
