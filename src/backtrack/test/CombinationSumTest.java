package backtrack.test;

import backtrack.algorithm.CombinationSum;

import java.util.List;

public class CombinationSumTest {
    public static void main(String[] args) {
        int[] candidates = {2,3,5};
        int target = 8;
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum1(candidates, target);
        System.out.println(lists);
    }
}
