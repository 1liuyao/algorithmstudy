package dynamic.test;

import dynamic.algorithm.bagproblem.OneZero;

public class OneZeroTest {
    public static void main(String[] args) {
        String[] strs = {"10","0001","111001","1","0"};
        OneZero oneZero = new OneZero();
        int m = 5;
        int n = 3;
        int maxForm = oneZero.findMaxForm(strs, m, n);
        System.out.println(maxForm);
    }
}
