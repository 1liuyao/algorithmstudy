package string.test;

import string.algorithm.KMP;

public class KMPTest {
    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";

//        String haystack = "leetcode";
//        String needle = "leeto";

//        String haystack = "aaa";
//        String needle = "aaaa";

        KMP kmp = new KMP();
        int result = kmp.strStr1(haystack, needle);
        System.out.println("模式串出现的位置是：" + result);

//        int[] nextArray = kmp.createNextArray(needle);
//        for (int i = 0; i < nextArray.length; i++) {
//            System.out.print(nextArray[i] + " ");
//        }
    }
}
