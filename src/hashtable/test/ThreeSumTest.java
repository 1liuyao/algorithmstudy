package hashtable.test;

import hashtable.algorithm.ThreeSum;

import java.util.List;

public class ThreeSumTest {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(nums);
        for (List<Integer> a : lists) {
            for (Integer b : a) {
                System.out.print(b + " ");
            }
            System.out.println("-----------");
        }
    }
}
