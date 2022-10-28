package backtrack.test;

import backtrack.algorithm.FindSubsequences;

import java.util.List;

public class FindSubsequencesTest {
    public static void main(String[] args) {
        FindSubsequences findSubsequences = new FindSubsequences();
        int[] nums = {4,6,7,7};
        List<List<Integer>> subsequences = findSubsequences.findSubsequences(nums);

        for (List<Integer> a:
                subsequences) {
            System.out.println(a);
        }
    }
}
