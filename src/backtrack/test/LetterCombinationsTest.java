package backtrack.test;

import backtrack.algorithm.LetterCombinations;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsTest {
    public static void main(String[] args) {
        LetterCombinations letterCombinations = new LetterCombinations();
        String digits = "23";
        List<String> result = letterCombinations.letterCombinations(digits);
        System.out.println(result);
    }
}
