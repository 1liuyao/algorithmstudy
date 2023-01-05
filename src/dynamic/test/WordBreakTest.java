package dynamic.test;

import dynamic.algorithm.fullbag.WordBreak;

import java.util.ArrayList;
import java.util.List;

public class WordBreakTest {
    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        String s = "catsandog";
        List<String> list = new ArrayList<>();
        list.add("cats");
        list.add("dog");
        list.add("sand");
        list.add("and");
        list.add("cat");
        wordBreak.wordBreak(s,list);
    }
}
