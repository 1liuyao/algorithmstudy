package hashtable.test;

import hashtable.algorithm.FourSum;

import java.util.List;

public class FourSumTest {
    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        int[] nums = new int[] {-3,-2,-1,0,0,1,2,3};
        int target = 0;
        List<List<Integer>> lists = fourSum.fourSum(nums, target);
        for (List<Integer> a : lists) {
            for (Integer b : a) {
                System.out.print(b + " ");
            }
            System.out.println("-----------");
        }
    }
}
