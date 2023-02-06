package stack.test;


import greed.algorithm.LargestSumAfterKNegations;

public class LargestSumAfterKNegationsTest {
    public static void main(String[] args) {
        int[] nums = new int[]{3,-1,0,2};
        int k = 3;
        LargestSumAfterKNegations largestSumAfterKNegations = new LargestSumAfterKNegations();
        int sum = largestSumAfterKNegations.largestSumAfterKNegations1(nums, k);
        System.out.println(sum);
    }
}
