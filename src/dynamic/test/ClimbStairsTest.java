package dynamic.test;

import dynamic.algorithm.baseproblem.ClimbStairs;

import java.util.Arrays;

public class ClimbStairsTest {
    public static void main(String[] args) {
        ClimbStairs climbStairs = new ClimbStairs();
        int n = 3;
        int result = climbStairs.climbStairs(n);
        System.out.println(result);
    }
}
