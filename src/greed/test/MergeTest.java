package greed.test;

import greed.algorithm.Merge;

import java.util.Arrays;

public class MergeTest {
    public static void main(String[] args) {
        int[][] intervals ={
                {1,4},{0,0}
        };

        Merge merge = new Merge();
        int[][] merge1 = merge.merge(intervals);

        for (int[] a : merge1){
            Arrays.stream(a).forEach(System.out::println);
        }
    }
}
